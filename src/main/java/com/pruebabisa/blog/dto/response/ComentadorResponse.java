package com.pruebabisa.blog.dto.response;

import com.pruebabisa.blog.entity.Comentador;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComentadorResponse {

    private Long id;

    private String nombre;

    private String correoElectronico;

    private String paisResidencia;

    public static ComentadorResponse fromEntity(Comentador comentador) {
        return ComentadorResponse.builder()
                .id(comentador.getId())
                .nombre(comentador.getNombre())
                .correoElectronico(comentador.getCorreoElectronico())
                .paisResidencia(comentador.getPaisResidencia())
                .build();
    }
}
