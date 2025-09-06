package com.pruebabisa.blog.service.spec;

import java.util.List;

import com.pruebabisa.blog.dto.request.AutorRequest;
import com.pruebabisa.blog.dto.response.AutorResponse;

public interface IAutorService {

    List<AutorResponse> obtenerTodosLosAutores();

    AutorResponse obtenerAutorPorId(Long id);

    AutorResponse crearAutor(AutorRequest autorRequest);
    
    AutorResponse actualizarAutor(Long id, AutorRequest autorRequest);
    
    void eliminarAutor(Long id);
}
