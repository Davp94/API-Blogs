package com.pruebabisa.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pruebabisa.blog.dto.request.AutorRequest;
import com.pruebabisa.blog.dto.response.AutorResponse;
import com.pruebabisa.blog.entity.Autor;
import com.pruebabisa.blog.exception.custom.DuplicateResourceException;
import com.pruebabisa.blog.exception.custom.ResourceNotFoundException;
import com.pruebabisa.blog.repository.AutorRepository;
import com.pruebabisa.blog.service.spec.IAutorService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AutorService implements IAutorService{
    
    private final AutorRepository autorRepository;
    
    @Override
    public List<AutorResponse> obtenerTodosLosAutores() {
        return autorRepository.findAll().stream().map(AutorResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AutorResponse obtenerAutorPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con ID: " + id));
         return AutorResponse.fromEntity(autor);
    }

    @Override
    public AutorResponse crearAutor(AutorRequest autorRequest) {
        log.info("Creando nuevo autor: {}", autorRequest.getCorreoElectronico());

        //Validacion logica
        if (autorRepository.existsByCorreoElectronico(autorRequest.getCorreoElectronico())) {
            throw new DuplicateResourceException("Ya existe un autor con el correo: " + autorRequest.getCorreoElectronico());
        }
        return AutorResponse.fromEntity(autorRepository.save(AutorRequest.toEntity(autorRequest)));
    }

    @Override
    public AutorResponse actualizarAutor(Long id, AutorRequest autorRequest) {
        Autor autorRetrieved = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con ID: " + id));
        
        // Verificar duplicado de correo si se está cambiando
        if (!autorRetrieved.getCorreoElectronico().equals(autorRequest.getCorreoElectronico()) &&
            autorRepository.existsByCorreoElectronico(autorRequest.getCorreoElectronico())) {
            throw new DuplicateResourceException("Ya existe un autor con el correo: " + autorRetrieved.getCorreoElectronico());
        }
        //update autor
        autorRetrieved.setNombres(autorRequest.getNombres());
        autorRetrieved.setApellidoPaterno(autorRequest.getApellidoPaterno());
        autorRetrieved.setApellidoMaterno(autorRequest.getApellidoMaterno());
        autorRetrieved.setFechaNacimiento(autorRequest.getFechaNacimiento());
        autorRetrieved.setPaisResidencia(autorRequest.getPaisResidencia());
        autorRetrieved.setCorreoElectronico(autorRequest.getCorreoElectronico());
        
        return AutorResponse.fromEntity(autorRepository.save(autorRetrieved));
    }

    @Override
    public void eliminarAutor(Long id) {
        try {
            AutorResponse autor = obtenerAutorPorId(id);
            autorRepository.deleteById(id);
            log.info("Autor eliminado: {}", autor.getCorreoElectronico());
        } catch (Exception e) {
            log.error("Error al eliminar el autor: {}", e);
            throw e;
        }
    }
}
