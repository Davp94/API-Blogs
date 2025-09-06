package com.pruebabisa.blog.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PuntuacionResponse {
    private Double puntuacionMinima;
    private Double puntuacionMaxima;
    private Double puntuacionPromedio;
    private Long totalComentarios;
}
