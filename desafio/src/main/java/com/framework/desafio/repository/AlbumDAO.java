package com.framework.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.framework.desafio.entity.Album;

public interface AlbumDAO extends JpaRepository<Album, String> {
	Optional<Album> findByNome(String nome);
}
