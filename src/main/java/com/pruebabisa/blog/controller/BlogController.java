package com.pruebabisa.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebabisa.blog.dto.request.BlogRequest;
import com.pruebabisa.blog.dto.response.BlogResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import com.pruebabisa.blog.service.spec.IBlogService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Blogs", description = "API para gestión de blogs")
public class BlogController {
    
    private final IBlogService blogService;
    
    @PostMapping
    @Operation(summary = "Crear un nuevo blog")
    public ResponseEntity<BlogResponse> crearBlog(
            @Valid @RequestBody BlogRequest blog) {
        return ResponseEntity.status(HttpStatus.CREATED).body( blogService.crearBlog(blog));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener blog por ID")
    public ResponseEntity<BlogResponse> obtenerBlogPorId(
            @Parameter(description = "ID del blog") @PathVariable Long id) {
        return ResponseEntity.ok(blogService.obtenerBlogPorId(id));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar blog")
    public ResponseEntity<BlogResponse> actualizarBlog(
            @Parameter(description = "ID del blog") @PathVariable Long id,
            @Valid @RequestBody BlogRequest blogRequest) {
        return ResponseEntity.ok(blogService.actualizarBlog(id, blogRequest));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar blog")
    public ResponseEntity<Void> eliminarBlog(
            @Parameter(description = "ID del blog") @PathVariable Long id) {
        blogService.eliminarBlog(id);
        return ResponseEntity.noContent().build();
    }
}
