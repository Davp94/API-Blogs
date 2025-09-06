package com.pruebabisa.blog.dto.response;

import java.util.List;

import com.pruebabisa.blog.entity.Blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {
    private Long id;
    private String titulo;
    private String tema;
    private String contenido;
    private String periodicidad;
    private Boolean permiteComentarios;
    private String nombreCompletoAutor;
    private String correoAutor;
    private List<ComentarioResponse> comentarios;
    private PuntuacionResponse puntuacion;
    private String createdAt;
    private String updatedAt;

    public static BlogResponse fromEntity(Blog blog){
        return BlogResponse.builder()
        .id(blog.getId())
        .titulo(blog.getTitulo())
        .tema(blog.getTema())
        .contenido(blog.getContenido())
        .periodicidad(String.valueOf(blog.getPeriodicidad()))
        .nombreCompletoAutor(blog.getNombreCompletoAutor())
        .correoAutor(blog.getAutor().getCorreoElectronico())
        .permiteComentarios(blog.getPermiteComentarios())
        .createdAt(blog.getCreatedAt().toString())
        .updatedAt(blog.getUpdatedAt() != null ? blog.getUpdatedAt().toString() : null)
        .build();
    }
           
}
