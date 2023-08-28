package com.framework.desafio.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment", schema="blog")
public class Comment {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	

	@Column(name = "autor")
	private String autor;

	@Column(name = "data_comment")
	private LocalDateTime dataComment;

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

	public LocalDateTime getDataComment() {
		return dataComment;
	}

	public void setDataComment(LocalDateTime dataComment) {
		this.dataComment = dataComment;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
