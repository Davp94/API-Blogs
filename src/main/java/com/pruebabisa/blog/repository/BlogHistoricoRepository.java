package com.pruebabisa.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebabisa.blog.entity.BlogHistorico;

@Repository
public interface BlogHistoricoRepository extends JpaRepository<BlogHistorico, Long> {
    
}
