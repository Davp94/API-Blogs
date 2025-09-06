package com.pruebabisa.blog.dto.request;

import com.pruebabisa.blog.entity.Blog;
import com.pruebabisa.blog.enums.PeriodicidadEnum;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {
    
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255)
    private String titulo;
    
    @NotBlank(message = "El tema es obligatorio")
    @Size(max = 100)
    private String tema;
    
    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;
    
    @NotNull(message = "La periodicidad es obligatoria, los valores permitidos son 'DIARIA', 'MENSUAL' y 'SEMANAL'")
    private PeriodicidadEnum periodicidad;
    
    private Boolean permiteComentarios;
    
    @NotNull(message = "El ID del autor es obligatorio")
    private Long autorId;

    public static Blog toEntity(BlogRequest blogRequest){
        return Blog.builder()
        .titulo(blogRequest.getTitulo())
        .tema(blogRequest.getTema())
        .contenido(blogRequest.getContenido())
        .periodicidad(blogRequest.getPeriodicidad())
        .permiteComentarios(blogRequest.getPermiteComentarios())
        .build();
    }
}
