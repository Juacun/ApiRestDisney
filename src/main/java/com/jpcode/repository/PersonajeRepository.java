package com.jpcode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpcode.model.PeliculaOSerie;
import com.jpcode.model.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

	public Personaje findByNombre(String nombre);
	
	public Personaje findById(int id);
	
	public List<Personaje> findByEdad(int edad);
	
	public List<Personaje> findByPeliculasOSeriesAsociadas(PeliculaOSerie peliculaOSerie);
}
