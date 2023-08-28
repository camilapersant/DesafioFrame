package com.framework.desafio.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.framework.desafio.entity.Album;
import com.framework.desafio.entity.Comment;
import com.framework.desafio.entity.Post;
import com.framework.desafio.entity.Usuario;
import com.framework.desafio.repository.PostDAO;
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
	
	public Post adicionaPost(Post post) {
		return repositoryPost.save(post);
	}

	public Comment adicionaComentario(Comment comentario) {
		return repositoryComment.save(comentario);
	}

    public Album adicionaAlbum(Album album, String user) throws IOException {
		File diretorio = new File("C:/BlogFrame./AlbunsBlog./" + album.getNome());
        diretorio.mkdirs();
		Usuario usuario = new Usuario();
		usuario.setEmail(user);
		album.setUsuario(usuario.getEmail());
		return repositoryAlbum.save(album);
	}

	public String adicionaPhoto(String album, MultipartFile file, String user) throws IOException {
		Optional<Album> optAlbum = albumDAO.findByNome(album);
		if(optAlbum == null) {
			return "Álbum não encontrado!"; 
		}
		else {
			Album dadosAlbum = optAlbum.get();
			if (user.equals(dadosAlbum.getUsuario())) {
				dadosAlbum.setFoto(file.getBytes());
				repositoryAlbum.save(dadosAlbum);		
				String orgName = file.getOriginalFilename();
				String filePath = "C:/BlogFrame/AlbunsBlog/" + dadosAlbum.getNome()+"/"+orgName;
				File dest = new File(filePath);
				try { 
					file.transferTo(dest); }
				catch (IllegalStateException e) { 
					e.printStackTrace();
				}
				return "Foto salva com sucesso!";
			}
		}
		return "Usuário não tem permissão para esse álbum!";
	}

}
