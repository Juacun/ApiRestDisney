package com.jpcode.service;

import java.util.List;

import com.jpcode.model.Genero;

public interface IGeneroService {

	public List<Genero> findAllGenero();
	
	public Genero findGeneroById(int id);
	
	public Genero crearGenero(Genero generoNuevo);
}
