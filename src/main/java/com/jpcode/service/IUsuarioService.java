package com.jpcode.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jpcode.model.Usuario;

public interface IUsuarioService extends UserDetailsService{

	public Usuario findByEmail(String email);
	
	public Usuario findByNombre(String nombre);
	
	public Usuario userRegister(Usuario usuario);
}
