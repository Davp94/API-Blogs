package com.pruebabisa.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebabisa.blog.entity.Comentador;

public interface ComentadorRepository extends JpaRepository<Comentador, Long> {

}