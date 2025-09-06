package com.pruebabisa.blog.service.spec;

import com.pruebabisa.blog.dto.request.ComentadorRequest;
import com.pruebabisa.blog.dto.response.ComentadorResponse;

public interface IComentadorService {

    ComentadorResponse obtenerComentadorPorId(Long id);

    ComentadorResponse crearComentador(ComentadorRequest comentadorRequest);
    
}
