package com.pruebabisa.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebabisa.blog.dto.request.ComentadorRequest;
import com.pruebabisa.blog.dto.response.ComentadorResponse;
import com.pruebabisa.blog.service.spec.IComentadorService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/comentador")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Comentadores", description = "API para gestión de comentadores")
public class ComentadorController {

    private final IComentadorService comentadorService;

    @PostMapping
    public ResponseEntity<ComentadorResponse> createComentador(@RequestBody ComentadorRequest comentadorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body( comentadorService.crearComentador(comentadorRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentadorResponse> getComentadorById(@PathVariable Long id) {
        return ResponseEntity.ok().body(comentadorService.obtenerComentadorPorId(id));
    }
    
    

}
