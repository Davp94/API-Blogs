package com.pruebabisa.blog.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "comentario", indexes = {
    @Index(name = "idx_comentario_blog", columnList = "blog_id"),
    @Index(name = "idx_comentario_comentador", columnList = "comentador_id"),
    @Index(name = "idx_comentario_created", columnList = "created_at"),
    @Index(name = "idx_comentario_puntuacion", columnList = "puntuacion")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotBlank(message = "El contenido del comentario es obligatorio")
    @Size(max = 2000, message = "El comentario no puede exceder 2000 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;
    
    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 0, message = "La puntuación mínima es 0")
    @Max(value = 10, message = "La puntuación máxima es 10")
    @Column(nullable = false)
    private Double puntuacion;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blog_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comentario_blog"))
    @JsonBackReference("blog-comentarios")
    @ToString.Exclude
    private Blog blog;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comentador_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comentario_comentador"))
    @JsonBackReference("comentador-comentarios")
    @ToString.Exclude
    private Comentador comentador;
}
