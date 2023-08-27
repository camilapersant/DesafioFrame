package com.framework.desafio.controller;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framework.desafio.dtos.RespostaDeLogin;
import com.framework.desafio.entity.Usuario;
import com.framework.desafio.service.ServicoJWT;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private ServicoJWT servicoJwt;

	@PostMapping("/login")
	public ResponseEntity<RespostaDeLogin> autentica(@RequestBody Usuario usuario) throws ServletException {
		return new ResponseEntity<RespostaDeLogin>(servicoJwt.autentica(usuario), HttpStatus.OK);
	}

}
