package com.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuarios.model.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer>{
	
}
