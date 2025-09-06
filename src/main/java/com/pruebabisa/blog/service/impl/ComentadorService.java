package com.pruebabisa.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebabisa.blog.dto.request.ComentadorRequest;
import com.pruebabisa.blog.dto.response.ComentadorResponse;
import com.pruebabisa.blog.exception.custom.ResourceNotFoundException;
import com.pruebabisa.blog.repository.ComentadorRepository;
import com.pruebabisa.blog.service.spec.IComentadorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ComentadorService implements IComentadorService {

    private final ComentadorRepository comentadorRepository;

    @Override
    public ComentadorResponse obtenerComentadorPorId(Long id) {
        return ComentadorResponse.fromEntity(comentadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentador no encontrado con ID: " + id)));
    }

    @Override
    public ComentadorResponse crearComentador(ComentadorRequest comentadorRequest) {
        return ComentadorResponse.fromEntity(comentadorRepository.save(ComentadorRequest.toEntity(comentadorRequest)));
    }

}