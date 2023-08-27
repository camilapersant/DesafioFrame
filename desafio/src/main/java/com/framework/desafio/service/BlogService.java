package com.framework.desafio.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.framework.desafio.dtos.BlogDTO;
import com.framework.desafio.dtos.RespostaDTO;
import com.framework.desafio.entity.Album;
import com.framework.desafio.entity.Comment;
import com.framework.desafio.entity.Post;
import com.framework.desafio.entity.Usuario;
import com.framework.desafio.repository.PostDAO;
import com.framework.desafio.repository.UsuarioDAO;
import com.framework.desafio.repository.AlbumDAO;
import com.framework.desafio.repository.CommentDAO;

@Service
public class BlogService {
	@Autowired
	private AlbumDAO albumDAO;

	@Autowired
	private PostDAO repositoryPost;

	@Autowired
	private CommentDAO repositoryComment;

	@Autowired
	private AlbumDAO repositoryAlbum;
	
	public Post adicionaPost(String post) {
		Post novoPost = new Post();
		novoPost.setTexto(post);
		return repositoryPost.save(novoPost);
	}

	public Comment adicionaComentario(String comentario) {
		Comment novoComentario = new Comment();
		novoComentario.setTexto(comentario);
		return repositoryComment.save(novoComentario);
	}

    public Album adicionaAlbum(Album album, String user) throws IOException {
		File diretorio = new File("C:/home/album/");
        diretorio.mkdirs();
        //File file = new File(diretorio.getAbsolutePath());
		Usuario usuario = new Usuario();
		usuario.setEmail(user);
		album.setUsuario(usuario.getEmail());
		return repositoryAlbum.save(album);
	}

    public String adicionaPhoto(String album, MultipartFile file, String user) throws IOException {
		Album dadosAlbum = getAlbum(album);
		if (user.equals(dadosAlbum.getUsuario())) {
			dadosAlbum.setFoto(file.getBytes());
			//save bd
		}
		return user; 
    }

	public Album getAlbum(String album) {
		Optional<Album> optAlbum = albumDAO.findByNome(album);
		if (optAlbum.isEmpty())
			throw new IllegalArgumentException();
		return optAlbum.get();
	}

	public List<Album> getTodosAlbum() {
		return repositoryAlbum.findAll();
	}

}
