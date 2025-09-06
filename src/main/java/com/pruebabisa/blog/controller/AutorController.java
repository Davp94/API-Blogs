package com.pruebabisa.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pruebabisa.blog.dto.request.AutorRequest;
import com.pruebabisa.blog.dto.response.AutorResponse;
import com.pruebabisa.blog.service.impl.AutorService;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Autores", description = "API para gestión de autores")
public class AutorController {

    private final AutorService autorService;

    @GetMapping
    @Operation(summary = "Obtener todos los autores")
    public ResponseEntity<List<AutorResponse>> obtenerTodosLosAutores() {
        return ResponseEntity.ok(autorService.obtenerTodosLosAutores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener autor por ID")
    public ResponseEntity<AutorResponse> obtenerAutorPorId(
            @Parameter(description = "ID del autor") @PathVariable Long id) {
        return ResponseEntity.ok(autorService.obtenerAutorPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo autor")
    public ResponseEntity<AutorResponse> crearAutor(@Valid @RequestBody AutorRequest autorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.crearAutor(autorRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar autor")
    public ResponseEntity<AutorResponse> actualizarAutor(
            @Parameter(description = "ID del autor") @PathVariable Long id,
            @Valid @RequestBody AutorRequest autorRequest) {
        return ResponseEntity.ok(autorService.actualizarAutor(id, autorRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar autor")
    public ResponseEntity<Void> eliminarAutor(
            @Parameter(description = "ID del autor") @PathVariable Long id) {
        autorService.eliminarAutor(id);
        return ResponseEntity.noContent().build();
    }
}
