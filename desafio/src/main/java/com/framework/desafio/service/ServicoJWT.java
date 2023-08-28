package com.framework.desafio.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.desafio.dtos.RespostaDeLogin;
import com.framework.desafio.entity.Usuario;
import com.framework.desafio.filtros.FiltroDeJWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class ServicoJWT {
	@Autowired
	private UsuarioService UsuarioServices;
	public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweij";

	public RespostaDeLogin autentica(Usuario usuario) {

		if (!UsuarioServices.validaUsuarioSenha(usuario)) {
			return new RespostaDeLogin("Usuario ou senha invalidos. Nao foi realizado o login.");
		}

		String token = geraToken(usuario.getEmail());
		return new RespostaDeLogin(token);
	}

	private String geraToken(String email) {
		return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email)
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 9 * 60 * 1000)).compact();
	}

	public String getSujeitoDoToken(String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new SecurityException("Token inexistente ou mal formatado!");
		}

		String token = authorizationHeader.substring(FiltroDeJWT.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new SecurityException("Token invalido ou expirado!");
		}
		return subject;
	}
}
