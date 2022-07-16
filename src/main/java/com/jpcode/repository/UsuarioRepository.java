package com.jpcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpcode.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByEmail(String email);
	
	public Usuario findByNombre(String nombre);
}
