package com.pruebabisa.blog.service.spec;

import com.pruebabisa.blog.entity.Blog;
import com.pruebabisa.blog.entity.BlogHistorico;

public interface IBlogHistoricoService {

    BlogHistorico guardarHistorico(Blog blog, String changeReason);
}
