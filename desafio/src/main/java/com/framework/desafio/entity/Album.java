package com.framework.desafio.entity;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Album {
	@Id @GeneratedValue
	private long id;
	private String nome;
    private String usuario;
    // private File foto;
    
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    // public File getFoto() {
    //     return foto;
    // }
    // public void setFoto(File foto) {
    //     this.foto = foto;
    // }
    // public MultipartFile getFoto() {
    //     return foto;
    // }
    // public void setFoto(MultipartFile foto) {
    //     this.foto = foto;
    // }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    // public Usuario getUsuario() {
    //     return usuario;
    // }
    // public void setUsuario(Usuario usuario) {
    //     this.usuario = usuario;
    // }


	
}
