package com.framework.desafio.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.framework.desafio.entity.Album;
import com.framework.desafio.entity.Comment;
import com.framework.desafio.entity.Post;
import com.framework.desafio.service.BlogService;
import com.framework.desafio.service.ServicoJWT;

@RestController
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private ServicoJWT servicoJwt;

	@PostMapping("/post")
	public ResponseEntity<Post> adicionaPost(@RequestBody String post) {
		return new ResponseEntity<Post>(blogService.adicionaPost(post),
				HttpStatus.CREATED);
	}

	@PostMapping("/comment")
	public ResponseEntity<Comment> adicionaComentario(@RequestBody String comentario) {
		try {
			return new ResponseEntity<Comment>(blogService.adicionaComentario(comentario),
					HttpStatus.CREATED);
		} catch (HttpClientErrorException hce) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/album")
	public ResponseEntity adicionaAlbum(@RequestBody Album album,
								    HttpServletRequest request) throws IOException {
		String token = request.getHeader("Authorization");
		String user = servicoJwt.getSujeitoDoToken(token);
			try {
			return new ResponseEntity<>(blogService.adicionaAlbum(album, user),
					HttpStatus.CREATED);
		} catch (HttpClientErrorException hce) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/photo")
	 public String singleFileUpload(@RequestBody Album album,
								    HttpServletRequest request) throws IOException {
		String token = request.getHeader("Authorization");
		String user = servicoJwt.getSujeitoDoToken(token);
		return blogService.adicionaPhoto(album, user);
	}
}

