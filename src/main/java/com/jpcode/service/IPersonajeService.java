package com.jpcode.service;

import java.util.List;

import com.jpcode.model.Personaje;

public interface IPersonajeService {

	public List<Personaje> findAllPersonaje();
	
	public Personaje findPersonajeByID(int id);
	
	public Personaje findPersonajeByNombre(String nombre);
	
	public List<Personaje> findPersonajesByEdad(int edad);
	
	public List<Personaje> findPersonajesByIdMovies(int idMovies);
	
	public Personaje crearPersonaje(Personaje personajeNuevo);
	
	public Personaje editarPersonaje(Personaje personajeModificado);
	
	public void eliminarPersonaje(int id);
	
}
