package com.framework.desafio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.desafio.dtos.BlogDTO;
import com.framework.desafio.dtos.RespostaDTO;
import com.framework.desafio.entity.Album;
import com.framework.desafio.entity.Comment;
import com.framework.desafio.entity.Post;
import com.framework.desafio.service.BlogService;

import io.jsonwebtoken.Jwts;

@RestController
public class BlogController {
	@Autowired
	private BlogService blogService;

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
		String user = Jwts.parser()
								.parseClaimsJws(token)
								.getBody()
								.getSubject();
			try {
			return new ResponseEntity<>(blogService.adicionaAlbum(album, user),
					HttpStatus.CREATED);
		} catch (HttpClientErrorException hce) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/photo")
	 public String singleFileUpload(String album, @RequestParam("file") MultipartFile file,
								    HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String user = Jwts.parser()
								.parseClaimsJws(token)
								.getBody()
								.getSubject();
		try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("/home/album/" + file.getOriginalFilename());
            Files.write(path, bytes);

			return blogService.adicionaPhoto(album,file, user);

        } catch (IOException e) {
            e.printStackTrace();
        }
		return "redirect:/getAlbuns";
	}
	@GetMapping("/getAlbuns")
	public List<Album> uploadStatus() {
		return blogService.getTodosAlbum();
         
    }
}

