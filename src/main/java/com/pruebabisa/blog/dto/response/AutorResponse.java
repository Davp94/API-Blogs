package com.pruebabisa.blog.dto.response;

import java.time.LocalDate;

import com.pruebabisa.blog.entity.Autor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutorResponse {
     private Long id;
    
    private String nombres;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private LocalDate fechaNacimiento;
    
    private String paisResidencia;
    
    private String correoElectronico;

    public static AutorResponse fromEntity(Autor autor){
        return AutorResponse.builder()
            .id(autor.getId())
            .nombres(autor.getNombres())
            .apellidoPaterno(autor.getApellidoPaterno())
            .apellidoMaterno(autor.getApellidoMaterno())
            .fechaNacimiento(autor.getFechaNacimiento())
            .paisResidencia(autor.getPaisResidencia())
            .correoElectronico(autor.getCorreoElectronico())
            .build();
    }
}
