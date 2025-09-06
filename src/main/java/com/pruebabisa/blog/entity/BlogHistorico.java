package com.pruebabisa.blog.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pruebabisa.blog.enums.PeriodicidadEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "blog_historico", indexes = {
    @Index(name = "idx_history_blog", columnList = "blog_id"),
    @Index(name = "idx_history_created", columnList = "created_at")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogHistorico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String titulo;
    
    @Column(nullable = false, length = 100)
    private String tema;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PeriodicidadEnum periodicidad;
    
    @Column(name = "permite_comentarios", nullable = false)
    private Boolean permiteComentarios;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blog_id", nullable = false, foreignKey = @ForeignKey(name = "fk_historico_blog"))
    @JsonBackReference("blog-historico")
    @ToString.Exclude
    private Blog blog;
}
