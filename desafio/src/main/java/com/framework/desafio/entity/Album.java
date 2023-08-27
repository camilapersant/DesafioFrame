package com.framework.desafio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "album", schema="blog")
public class Album {
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "nome")
    private String nome;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "foto")
    private byte[] foto;
        
    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
   
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
    	
}
