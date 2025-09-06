package com.pruebabisa.blog.dto.request;

import com.pruebabisa.blog.entity.Comentario;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComentarioRequest {
    
    @NotBlank(message = "El contenido del comentario es obligatorio")
    @Size(max = 2000, message = "El comentario no puede exceder 2000 caracteres")
    private String contenido;
    
    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 0, message = "La puntuación mínima es 0")
    @Max(value = 10, message = "La puntuación máxima es 10")
    private Double puntuacion;
    
    @NotNull(message = "El id del comentador es obligatorio")
    private Long comentadorId;

    public static Comentario toEntity(ComentarioRequest comentarioRequest){
        return Comentario.builder()
        .contenido(comentarioRequest.getContenido())
        .puntuacion(comentarioRequest.getPuntuacion())
        .build();
    }
}
