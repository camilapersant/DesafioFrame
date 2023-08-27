package com.framework.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.framework.desafio.entity.Comment;


@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {
}
