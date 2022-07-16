package com.jpcode.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpcode.model.Genero;
import com.jpcode.service.IGeneroService;

@RestController
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	IGeneroService generoService;
	
	@GetMapping
	public ResponseEntity<Collection<Genero>> getAllPersonaje(){
		
		try {
			
			List<Genero> generos = generoService.findAllGenero();
			return new ResponseEntity<>(generos, HttpStatus.OK);
			
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Genero> saveGenero(@RequestBody Genero genero) {
		
		try {
			
			generoService.crearGenero(genero);
			return new ResponseEntity<Genero>(genero,HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
}
