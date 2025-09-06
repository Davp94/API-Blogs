package com.pruebabisa.blog.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebabisa.blog.dto.request.ComentarioRequest;
import com.pruebabisa.blog.dto.response.ComentarioResponse;
import com.pruebabisa.blog.dto.response.PuntuacionResponse;
import com.pruebabisa.blog.entity.Blog;
import com.pruebabisa.blog.entity.Comentario;
import com.pruebabisa.blog.exception.custom.BusinessRuleException;
import com.pruebabisa.blog.exception.custom.ResourceNotFoundException;
import com.pruebabisa.blog.repository.BlogRepository;
import com.pruebabisa.blog.repository.ComentadorRepository;
import com.pruebabisa.blog.repository.ComentarioRepository;
import com.pruebabisa.blog.service.spec.IComentarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ComentarioService implements IComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final BlogRepository blogRepository;
    private final ComentadorRepository comentadorRepository;

    @Override
    public List<ComentarioResponse> getComentariosByBlog(Long blogId) {
        return comentarioRepository.findByBlogIdOrderByCreatedAtDesc(blogId).stream()
                .map(ComentarioResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ComentarioResponse crearComentario(ComentarioRequest comentarioRequest, Long blogId) {
        log.info("Creando comentario para blog ID: {}", blogId);

        // Verificar que el blog existe y permite comentarios
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog no encontrado con ID: " + blogId));

        if (!blog.getPermiteComentarios()) {
            throw new BusinessRuleException("Este blog no permite comentarios");
        }

        // Crear comentario
        Comentario comentarioToSave = ComentarioRequest.toEntity(comentarioRequest);
        comentarioToSave.setBlog(blog);
        comentarioToSave.setCreatedAt(LocalDateTime.now());
        comentarioToSave.setComentador(comentadorRepository.findById(comentarioRequest.getComentadorId()).get());

        return ComentarioResponse.fromEntity(comentarioRepository.save(comentarioToSave));
    }

    @Override
    public PuntuacionResponse puntuacionByBlogId(Long blogId) {
        List<Double> comentariosPuntuacionRetrieved = comentarioRepository.findByBlogId(blogId).stream()
                .map(Comentario::getPuntuacion)
                .filter(puntuacion -> puntuacion != null)
                .toList();
        return PuntuacionResponse.builder()
                .puntuacionMaxima(comentariosPuntuacionRetrieved.stream().max(Double::compareTo).orElse(0.0))
                .puntuacionMinima(comentariosPuntuacionRetrieved.stream().min(Double::compareTo).orElse(0.0))
                .puntuacionPromedio(comentariosPuntuacionRetrieved.stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0))
                .totalComentarios((long)comentariosPuntuacionRetrieved.size())
                .build();
    }
}