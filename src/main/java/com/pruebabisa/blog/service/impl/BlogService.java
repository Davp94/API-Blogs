package com.pruebabisa.blog.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebabisa.blog.dto.request.BlogRequest;
import com.pruebabisa.blog.dto.response.BlogResponse;
import com.pruebabisa.blog.dto.response.PuntuacionResponse;
import com.pruebabisa.blog.entity.Autor;
import com.pruebabisa.blog.entity.Blog;
import com.pruebabisa.blog.exception.custom.ResourceNotFoundException;
import com.pruebabisa.blog.repository.AutorRepository;
import com.pruebabisa.blog.repository.BlogRepository;
import com.pruebabisa.blog.service.spec.IBlogHistoricoService;
import com.pruebabisa.blog.service.spec.IBlogService;
import com.pruebabisa.blog.service.spec.IComentarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BlogService implements IBlogService {

    private final BlogRepository blogRepository;
    private final AutorRepository autorRepository;
    private final IBlogHistoricoService blogHistoricoService;
    private final IComentarioService comentarioService;

    @Override
    public BlogResponse obtenerBlogPorId(Long id) {
        BlogResponse blogRetrieved = BlogResponse.fromEntity(blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog no encontrado con ID: " + id)));
        blogRetrieved.setPuntuacion(obtenerResumenPuntuaciones(blogRetrieved.getId()));
        blogRetrieved.setComentarios(comentarioService.getComentariosByBlog(id));
        return blogRetrieved;
    }

    @Override
    public BlogResponse crearBlog(BlogRequest blogRequest) {
        Autor autor = autorRepository.findById(blogRequest.getAutorId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Autor no encontrado con ID: " + blogRequest.getAutorId()));
        Blog blogToCreate = BlogRequest.toEntity(blogRequest);
        blogToCreate.setAutor(autor);
        blogToCreate.setCreatedAt(LocalDateTime.now());
        return BlogResponse.fromEntity(blogRepository.save(blogToCreate));
    }

    @Override
    public BlogResponse actualizarBlog(Long id, BlogRequest blogRequest) {
        Blog blogRetrieved = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog no encontrado con ID: " + id));

        // Guardar versión anterior en el histórico
        blogHistoricoService.guardarHistorico(blogRetrieved, "Actualización manual del blog");

        // Actualizar campos
        blogRetrieved.setTitulo(blogRequest.getTitulo());
        blogRetrieved.setTema(blogRequest.getTema());
        blogRetrieved.setContenido(blogRequest.getContenido());
        blogRetrieved.setPeriodicidad(blogRequest.getPeriodicidad());
        blogRetrieved.setPermiteComentarios(blogRequest.getPermiteComentarios());
        blogRetrieved.setUpdatedAt(LocalDateTime.now());
        log.info("Blog actualizado: {}", blogRequest.getTitulo());
        return BlogResponse.fromEntity(blogRepository.save(blogRetrieved));
    }

    @Override
    public void eliminarBlog(Long id) {
        try {
            BlogResponse blog = obtenerBlogPorId(id);
            blogRepository.deleteById(id);
            log.info("Blog eliminado: {}", blog.getTitulo());
        } catch (Exception e) {
            log.error("Error al eliminar el blog: {}", e);
            throw e;
        }
    }

    public PuntuacionResponse obtenerResumenPuntuaciones(Long blogId) {
        return comentarioService.puntuacionByBlogId(blogId);
    }

}
