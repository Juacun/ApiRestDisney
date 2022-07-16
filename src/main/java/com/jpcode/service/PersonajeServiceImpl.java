package com.jpcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpcode.model.Personaje;
import com.jpcode.repository.PersonajeRepository;

@Service
public class PersonajeServiceImpl implements IPersonajeService {

	@Autowired
	PersonajeRepository personajeRepository;
	
	@Autowired
	IPeliculaOSerieService peliculaOSerieService;
	
	@Override
	public List<Personaje> findAllPersonaje() {
				
		return personajeRepository.findAll();
	}

	@Override
	public Personaje findPersonajeByID(int id) {

		return personajeRepository.findById(id);
	}

	@Override
	public Personaje findPersonajeByNombre(String nombre) {
		
		return personajeRepository.findByNombre(nombre);
	}
	
	@Override
	public List<Personaje> findPersonajesByEdad(int edad) {
		
		return personajeRepository.findByEdad(edad);
	}
	
	@Override
	public List<Personaje> findPersonajesByIdMovies(int idMovies) {
		
		return personajeRepository.findByPeliculasOSeriesAsociadas(peliculaOSerieService.findPeliculaOSerieById(idMovies));
	}
	
	@Override
	public Personaje crearPersonaje(Personaje personajeNuevo) {
			
		return personajeRepository.save(personajeNuevo);
	}

	@Override
	public Personaje editarPersonaje(Personaje personajeModificado) {
		
		return personajeRepository.save(personajeModificado);
	}

	@Override
	public void eliminarPersonaje(int id) {
		
		personajeRepository.deleteById(id);
	}

}
