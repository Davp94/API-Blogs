package com.pruebabisa.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebabisa.blog.dto.request.ComentarioRequest;
import com.pruebabisa.blog.dto.response.ComentarioResponse;
import com.pruebabisa.blog.service.spec.IComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Comentarios", description = "API para gestión de comentarios")
public class ComentarioController {

    private final IComentarioService comentarioService;

    @PostMapping("/blog/{blogId}")
    @Operation(summary = "Crear un comentario en un blog")
    public ResponseEntity<ComentarioResponse> crearComentario(
            @Parameter(description = "ID del blog") @PathVariable Long blogId,
            @Valid @RequestBody ComentarioRequest commentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comentarioService.crearComentario(commentRequest, blogId));
    }

    @GetMapping("/blog/{blogId}")
    @Operation(summary = "Obtener comentarios de un blog")
    public ResponseEntity<List<ComentarioResponse>> obtenerComentariosPorBlog(
            @Parameter(description = "ID del blog") @PathVariable Long blogId) {
        return ResponseEntity.ok(comentarioService.getComentariosByBlog(blogId));
    }

}
