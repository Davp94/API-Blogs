package com.pruebabisa.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebabisa.blog.entity.Blog;
import com.pruebabisa.blog.entity.BlogHistorico;
import com.pruebabisa.blog.repository.BlogHistoricoRepository;
import com.pruebabisa.blog.service.spec.IBlogHistoricoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BlogHistoricoService implements IBlogHistoricoService {

    private final BlogHistoricoRepository blogHistoricoRepository;

    @Override
    public BlogHistorico guardarHistorico(Blog blog, String changeReason) {

        BlogHistorico history = BlogHistorico.builder()
        .blog(blog)
        .titulo(blog.getTitulo())
        .tema(blog.getTema())
        .contenido(blog.getContenido())
        .periodicidad(blog.getPeriodicidad())
        .permiteComentarios(blog.getPermiteComentarios())
        .build();

        BlogHistorico savedHistory = blogHistoricoRepository.save(history);
        log.info("Histórico guardado para blog ID: {}", blog.getId());

        return savedHistory;
    }
}