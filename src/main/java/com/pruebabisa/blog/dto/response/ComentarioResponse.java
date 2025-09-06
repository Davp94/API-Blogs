package com.pruebabisa.blog.dto.response;

import com.pruebabisa.blog.entity.Comentario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComentarioResponse {
    private Long id;
    private String contenido;
    private Double puntuacion;
    private String nombreComentarista;
    private String correoComentarista;
    private String paisComentarista;
    private String createdAt;

    public static ComentarioResponse fromEntity(Comentario comentario) {
        return ComentarioResponse.builder()
        .id(comentario.getId())
        .contenido(comentario.getContenido())
        .puntuacion(comentario.getPuntuacion())
        .nombreComentarista(comentario.getComentador().getNombre())
        .correoComentarista(comentario.getComentador().getCorreoElectronico())
        .paisComentarista(comentario.getComentador().getPaisResidencia())
        .createdAt(comentario.getCreatedAt().toString())
        .build();
    }
}
