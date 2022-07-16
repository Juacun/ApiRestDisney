package com.jpcode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpcode.model.Usuario;
import com.jpcode.service.IUsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

	@Autowired
	IUsuarioService usuarioService;
	
	@GetMapping("/auth/login")
	public ResponseEntity<Usuario> getLogin(Usuario usuario) {
		
		return null;
	}
	
	@PostMapping("/auth/register")
	public ResponseEntity<Usuario> getRegister(@Valid @RequestBody Usuario usuario) {
		
		return null;
	}
}
