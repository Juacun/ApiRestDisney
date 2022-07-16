package com.jpcode.service;

import java.util.List;

import com.jpcode.model.PeliculaOSerie;

public interface IPeliculaOSerieService {

	public List<PeliculaOSerie> findAllPeliculasOSeries();
	
	public PeliculaOSerie findPeliculaOSerieById(int id);
	
	public PeliculaOSerie findPeliculaOSerieByTitulo(String name);
	
	public List<PeliculaOSerie> findPeliculasOSeriesByIdGenero(int idGenero);
	
	public List<PeliculaOSerie> findPeliculasOSeriesByFechaDeCreacionSort(String sort);
	
	public PeliculaOSerie crearPeliculaOSerie(PeliculaOSerie peliculaOSerieNueva);
	
	public PeliculaOSerie editarPeliculaOSerie(PeliculaOSerie peliculaOSerieModificada);
	
	public void eliminarPeliculaOSerie(int id);
}
