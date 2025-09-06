package com.pruebabisa.blog.service.spec;

import java.util.List;

import com.pruebabisa.blog.dto.request.ComentarioRequest;
import com.pruebabisa.blog.dto.response.ComentarioResponse;
import com.pruebabisa.blog.dto.response.PuntuacionResponse;

public interface IComentarioService {

    List<ComentarioResponse> getComentariosByBlog(Long blogId);
    
    ComentarioResponse crearComentario(ComentarioRequest comentarioRequest, Long blogId);

    PuntuacionResponse puntuacionByBlogId(Long blogId);
    
}
