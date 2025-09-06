package com.pruebabisa.blog.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "comentador")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comentador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Size(max = 255)
    @Column(name = "correo_electronico", nullable = false, length = 255)
    private String correoElectronico;
    
    @NotBlank(message = "El país de residencia es obligatorio")
    @Size(max = 100)
    @Column(name = "pais_residencia", nullable = false, length = 100)
    private String paisResidencia;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "comentador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("comentador-comentarios")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comentario> comentarios;
}