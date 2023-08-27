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
		//String encodedString = Base64.getEncoder().encodeToString(album.getNome().getBytes());
		//FileUtils.writeStringToFile(new File("/home/album/"), encodedString, StandardCharsets.UTF_8);
		File diretorio = new File("C:/home/album/");
        diretorio.mkdirs();
        File file = new File(diretorio.getAbsolutePath()+File.separator+"PROJETO.FDB");
		Usuario usuario = new Usuario();
		usuario.setNome(user);
		album.setUsuario(usuario.getNome());
		return album;
	}

    public String adicionaPhoto(String album, MultipartFile file, String user) {
		Album dadosAlbum = getAlbum(album);
		if (user.equals(dadosAlbum.getUsuario())) {
			
			//dadosAlbum.setFoto(file);
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

    // public Photo adicionaPhoto(MultipartFile file, String user) {
    //     if(album.getUsuario().equals(user)){
	// 		return null;
	// 	}
    // }

	// public MultiValueMap adicionaPhoto(MultipartFile file, String user) {
	// 	if(album.getUsuario().equals(user)){
	// 		return null;
	// 	}
	// 	return null;
	// }

	// 	Optional<PalavraChave> aPalavraChave = repositorioDePalavrasChave.findById(palavraChave);
	// 	System.out.println("=========> palavraChave recuperada = " + aPalavraChave.get());
	// 	if (aPalavraChave.isPresent()) {
	// 		return aPalavraChave.get().getPerguntas();
	// 	}
	// 	throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Palavra-chave nao encontrada: " + palavraChave);

	// }

	// public List<Resposta> getRespostas(long id) {
	// 	if (repository.findById(id).isEmpty()) {
	// 		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
	// 	}

	// 	return repositorioDeRespostas.findByPergunta(repositoryPost.findById(id).get());
	// }

}
