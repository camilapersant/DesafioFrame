package com.framework.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.framework.desafio.entity.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, String> {
	Optional<Usuario> findByEmail(String email);
}
