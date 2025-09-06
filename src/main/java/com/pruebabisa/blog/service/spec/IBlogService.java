package com.pruebabisa.blog.service.spec;

import com.pruebabisa.blog.dto.request.BlogRequest;
import com.pruebabisa.blog.dto.response.BlogResponse;

public interface IBlogService {

    BlogResponse obtenerBlogPorId(Long id);

    BlogResponse crearBlog(BlogRequest blogRequest);

    BlogResponse actualizarBlog(Long id, BlogRequest blogActualizado);

    void eliminarBlog(Long id);

}
