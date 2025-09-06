package com.pruebabisa.blog.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pruebabisa.blog.enums.PeriodicidadEnum;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "blog", indexes = {
    @Index(name = "idx_blog_autor", columnList = "autor_id"),
    @Index(name = "idx_blog_tema", columnList = "tema"),
    @Index(name = "idx_blog_created", columnList = "created_at")
})
@Data
@Builder
public class Blog {
    
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
    private Boolean permiteComentarios = true;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_blog_autor"))
    @JsonBackReference("autor-blogs")
    @ToString.Exclude
    private Autor autor;
    
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("blog-comentarios")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comentario> comentarios = new ArrayList<>();
    
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("blog-historico")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<BlogHistorico> historico = new ArrayList<>();
    
    public String getNombreCompletoAutor() {
        if (autor != null) {
            return autor.getNombres() + " " + autor.getApellidoPaterno() + " " + autor.getApellidoMaterno();
        }
        return null;
    }
}