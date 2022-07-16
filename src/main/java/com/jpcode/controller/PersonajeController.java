package com.jpcode.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpcode.model.PeliculaOSerie;
import com.jpcode.model.Personaje;
import com.jpcode.service.IPeliculaOSerieService;
import com.jpcode.service.IPersonajeService;

@RestController
public class PersonajeController {

	@Autowired
	IPersonajeService personajeService;
	
	@Autowired
	IPeliculaOSerieService peliculaOSerieService;

	@GetMapping("/characters")
	public ResponseEntity<Collection<Personaje>> getAllPersonaje() {

		try {
			List<Personaje> personajes = personajeService.findAllPersonaje();
			return new ResponseEntity<>(personajes, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/characters/{id}")
	public ResponseEntity<Personaje> getPersonajeById(@PathVariable int id) {

		try {

			Personaje personaje = personajeService.findPersonajeByID(id);
			return new ResponseEntity<>(personaje, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/characters", params = "name")
	public ResponseEntity<Personaje> getPersonajeByNombre(@RequestParam String name) {

		try {
			Personaje personaje = personajeService.findPersonajeByNombre(name);
			if (personaje == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(personaje, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/characters", params = "edad")
	public ResponseEntity<Collection<Personaje>> getPersonajeByEdad(@RequestParam int edad) {

		try {
			List<Personaje> personajes = personajeService.findPersonajesByEdad(edad);
			if (personajes == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(personajes, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/characters", params = "idMovies")
	public ResponseEntity<Collection<Personaje>> getPersonajeByIdMovies(@RequestParam int idMovies) {

		try {
			PeliculaOSerie peliculaOSerie = peliculaOSerieService.findPeliculaOSerieById(idMovies);
			if(peliculaOSerie == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			List<Personaje> personajes = personajeService.findPersonajesByIdMovies(idMovies);
			if (personajes == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(personajes, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/characters")
	public ResponseEntity<Personaje> savePersonaje(@RequestBody Personaje personaje) {

		try {
			personajeService.crearPersonaje(personaje);
			return new ResponseEntity<>(personaje, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Personaje> updatePersonaje(@RequestBody Personaje personaje, @PathVariable int id) {

		try {

			Personaje personajeModificado = personaje;
			personajeModificado.setId(id);
			personajeService.editarPersonaje(personajeModificado);
			return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Personaje>(HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePersonaje(@PathVariable int id) {

		try {

			personajeService.eliminarPersonaje(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

}
