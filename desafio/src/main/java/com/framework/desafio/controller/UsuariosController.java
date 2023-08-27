package com.framework.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.framework.desafio.entity.Usuario;
import com.framework.desafio.service.UsuarioService;

@RestController
public class UsuariosController {

	@Autowired
	private UsuarioService usuariosService;

	public UsuariosController(UsuarioService usuariosService) {
		super();
		this.usuariosService = usuariosService;
	}

	@PostMapping("/user")
	public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(this.usuariosService.adicionaUsuario(usuario), HttpStatus.OK);
	}

	// @GetMapping("/auth/usuarios/{email}")
	// public ResponseEntity<Usuario> adicionaUsuario(@PathVariable String email) {
	// 	try {
	// 		return new ResponseEntity<Usuario>(usuariosService.getUsuario(email), HttpStatus.OK);
	// 	} catch (IllegalArgumentException iae) {
	// 		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	// 	}
	// }

	// @DeleteMapping("/auth/usuarios/{email}")
	// public ResponseEntity<Usuario> removeUsuario(@PathVariable String email,
	// 		@RequestHeader("Authorization") String header) {
	// 	try {
	// 		return new ResponseEntity<Usuario>(usuariosService.removeUsuario(email, header), HttpStatus.OK);
	// 	} catch (IllegalArgumentException iae) {
	// 		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	// 	} catch (ServletException e) {
	// 		return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
	// 	}
	// }
}
