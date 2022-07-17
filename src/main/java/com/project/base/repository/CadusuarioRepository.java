package com.project.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.base.model.Cadusuario;

@Repository
public interface CadusuarioRepository extends JpaRepository<Cadusuario, Long> {
	
}