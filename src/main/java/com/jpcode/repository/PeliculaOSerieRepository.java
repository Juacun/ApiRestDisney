package com.jpcode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpcode.model.Genero;
import com.jpcode.model.PeliculaOSerie;

@Repository
public interface PeliculaOSerieRepository extends JpaRepository<PeliculaOSerie, Integer> {

	public PeliculaOSerie findById(int id);
	
	public PeliculaOSerie findByTitulo(String name);
	
	public List<PeliculaOSerie> findByGenerosAsociados(Genero genero);
	
	// Metodo para ordenar ASC y DESC
}
