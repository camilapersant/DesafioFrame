package com.framework.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.framework.desafio.entity.Post;

@Repository
public interface PostDAO extends JpaRepository<Post, Long> {

}
