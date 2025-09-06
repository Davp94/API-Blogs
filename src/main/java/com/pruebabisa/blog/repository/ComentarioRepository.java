package com.pruebabisa.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebabisa.blog.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
    List<Comentario> findByBlogId(Long blogId);
    
    List<Comentario> findByBlogIdOrderByCreatedAtDesc(Long blogId);
    
}
