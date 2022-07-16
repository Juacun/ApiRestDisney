package com.jpcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpcode.model.Genero;
import com.jpcode.repository.GeneroRepository;

@Service
public class GeneroServiceImpl implements IGeneroService{

	@Autowired
	GeneroRepository generoRepository;
	
	@Override
	public List<Genero> findAllGenero() {
		
		return generoRepository.findAll();
	}
	
	@Override
	public Genero findGeneroById(int id) {
		
		return generoRepository.findById(id);
	}
	
	@Override
	public Genero crearGenero(Genero generoNuevo) {
		
		return generoRepository.save(generoNuevo);
	}

}
