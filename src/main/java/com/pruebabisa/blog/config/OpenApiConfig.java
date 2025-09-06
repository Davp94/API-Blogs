package com.pruebabisa.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    
    @Value("${blog.openapi.dev-url:http://localhost:8080}")
    private String devUrl;
    
    @Value("${blog.openapi.prod-url:https://blog-api.render.com}")
    private String prodUrl;
    
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");
        
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");
        
        Contact contact = new Contact();
        contact.setEmail("david.villca.pacheco@gmail.com");
        contact.setName("David Villca");
        contact.setUrl("https://github.com/Davp94/API-Blogs");
        
        Info info = new Info()
                .title("Blog API - REST")
                .version("1.0")
                .contact(contact)
                .description("API REST para la gestión de blogs, autores y comentarios")
                .termsOfService("https://blog-api.com/terms");
        
        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
