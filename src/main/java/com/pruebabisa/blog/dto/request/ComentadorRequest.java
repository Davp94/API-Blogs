package com.pruebabisa.blog.dto.request;

import com.pruebabisa.blog.entity.Comentador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComentadorRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String correoElectronico;

    @NotBlank(message = "El país de residencia es obligatorio")
    @Size(max = 100)
    private String paisResidencia;

    public static Comentador toEntity(ComentadorRequest comentadorRequest) {
        return Comentador.builder()
                .correoElectronico(comentadorRequest.getCorreoElectronico())
                .nombre(comentadorRequest.getNombre())
                .paisResidencia(comentadorRequest.getPaisResidencia())
                .build();
    }

}
