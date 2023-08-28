package com.framework.desafio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post", schema="blog")
public class Post {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	

	@Column(name = "autor")
	private String autor;

	@Column(name = "data_post")
	private String dataPost;

	@Column(name = "texto")
	private String texto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDataPost() {
		return dataPost;
	}

	public void setDataPost(String dataPost) {
		this.dataPost = dataPost;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	

}
