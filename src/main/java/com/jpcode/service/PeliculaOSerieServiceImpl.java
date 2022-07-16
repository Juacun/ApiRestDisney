package com.jpcode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpcode.model.PeliculaOSerie;
import com.jpcode.repository.GeneroRepository;
import com.jpcode.repository.PeliculaOSerieRepository;

@Service
public class PeliculaOSerieServiceImpl implements IPeliculaOSerieService {

	@Autowired
	PeliculaOSerieRepository peliculaOSerieRepository;
	
	@Autowired
	GeneroRepository generoRepository;

	@Override
	public List<PeliculaOSerie> findAllPeliculasOSeries() {
		
		return peliculaOSerieRepository.findAll();
	}

	@Override
	public PeliculaOSerie findPeliculaOSerieById(int id) {

		return peliculaOSerieRepository.findById(id);
	}
	
	@Override
	public PeliculaOSerie findPeliculaOSerieByTitulo(String name) {
		
		return peliculaOSerieRepository.findByTitulo(name);
	}

	@Override
	public List<PeliculaOSerie> findPeliculasOSeriesByIdGenero(int idGenero) {
		
		return peliculaOSerieRepository.findByGenerosAsociados(generoRepository.findById(idGenero));
	}
	
	@Override
	public List<PeliculaOSerie> findPeliculasOSeriesByFechaDeCreacionSort(String sort) {
		
		List<PeliculaOSerie> peliculasOSeriesSorted = new ArrayList<PeliculaOSerie>();
		
		if(sort == null ) peliculasOSeriesSorted = null;
		
		if(sort == "ASC") peliculasOSeriesSorted = peliculaOSerieRepository.findAll(Sort.by("fechaDeCreacion").ascending());
		
		if(sort == "DESC") peliculasOSeriesSorted = peliculaOSerieRepository.findAll(Sort.by("fechaDeCreacion").descending());
		
		return peliculasOSeriesSorted;
	}
	
	@Override
	public PeliculaOSerie crearPeliculaOSerie(PeliculaOSerie peliculaOSerieNueva) {
		
		return peliculaOSerieRepository.save(peliculaOSerieNueva);
	}

	@Override
	public PeliculaOSerie editarPeliculaOSerie(PeliculaOSerie peliculaOSerieModificada) {
		
		return peliculaOSerieRepository.save(peliculaOSerieModificada);
	}

	@Override
	public void eliminarPeliculaOSerie(int id) {
		
		peliculaOSerieRepository.deleteById(id);
	}
	
}
