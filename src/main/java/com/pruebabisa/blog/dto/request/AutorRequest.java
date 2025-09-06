package com.pruebabisa.blog.dto.request;

import java.time.LocalDate;

import com.pruebabisa.blog.entity.Autor;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutorRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombres;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 100)
    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 100)
    @Column(name = "apellido_materno", nullable = false, length = 100)
    private String apellidoMaterno;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El país de residencia es obligatorio")
    @Size(max = 100)
    @Column(name = "pais_residencia", nullable = false, length = 100)
    private String paisResidencia;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Size(max = 255)
    @Column(name = "correo_electronico", nullable = false, unique = true, length = 255)
    private String correoElectronico;

    public static Autor toEntity(AutorRequest autorRequest) {

        return Autor.builder()
                .nombres(autorRequest.getNombres())
                .apellidoMaterno(autorRequest.getApellidoMaterno())
                .apellidoPaterno(autorRequest.getApellidoPaterno())
                .fechaNacimiento(autorRequest.getFechaNacimiento())
                .paisResidencia(autorRequest.getPaisResidencia())
                .correoElectronico(autorRequest.getCorreoElectronico())
                .build();
    }

}
