package com.pruebabisa.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebabisa.blog.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    
}
