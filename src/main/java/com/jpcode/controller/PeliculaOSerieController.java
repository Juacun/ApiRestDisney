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

import com.jpcode.model.Genero;
import com.jpcode.model.PeliculaOSerie;
import com.jpcode.model.Personaje;
import com.jpcode.service.IGeneroService;
import com.jpcode.service.IPeliculaOSerieService;
import com.jpcode.service.IPersonajeService;

@RestController
public class PeliculaOSerieController {

	@Autowired
	IGeneroService generoService;
	
	@Autowired
	IPersonajeService personajeService;

	@Autowired
	IPeliculaOSerieService peliculaOSerieService;

	@GetMapping("/movies")
	public ResponseEntity<Collection<PeliculaOSerie>> getAllPeliculaOSerie() {

		try {

			List<PeliculaOSerie> peliculasOSeries = peliculaOSerieService.findAllPeliculasOSeries();
			return new ResponseEntity<>(peliculasOSeries, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/movies/{id}")
	public ResponseEntity<PeliculaOSerie> getPeliculaOSerieById(@PathVariable int id) {

		try {
			PeliculaOSerie peliculaOSerie = peliculaOSerieService.findPeliculaOSerieById(id);
			return new ResponseEntity<PeliculaOSerie>(peliculaOSerie, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/movies", params = "name")
	public ResponseEntity<PeliculaOSerie> getPeliculaOSerieByNombre(@RequestParam String name) {
		
		try {
			PeliculaOSerie peliculaOSerie = peliculaOSerieService.findPeliculaOSerieByTitulo(name);
			if(peliculaOSerie == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<>(peliculaOSerie, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<PeliculaOSerie>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/movies", params = "genre")
	public ResponseEntity<Collection<PeliculaOSerie>> getPeliculaOSerieByIdGenero(@RequestParam int genre) {

		try {
			Genero genero = generoService.findGeneroById(genre);
			if(genero == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			List<PeliculaOSerie> peliculasOSeries = peliculaOSerieService.findPeliculasOSeriesByIdGenero(genre);
			if (peliculasOSeries == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(peliculasOSeries, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/movies", params = "order")
	public ResponseEntity<Collection<PeliculaOSerie>> getPeliculaOSerieByFechaDeCreacionOrder(@RequestParam String order) {

		
		try {
			if(order == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
					
			List<PeliculaOSerie> peliculasOSeries = peliculaOSerieService.findPeliculasOSeriesByFechaDeCreacionSort(order);
			return new ResponseEntity<>(peliculasOSeries, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/movies")
	public ResponseEntity<PeliculaOSerie> savePeliculaOSerie(@RequestBody PeliculaOSerie peliculaOSerie) {

		try {

			peliculaOSerieService.crearPeliculaOSerie(peliculaOSerie);
			return new ResponseEntity<PeliculaOSerie>(peliculaOSerie, HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<PeliculaOSerie>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/movies/{id}")
	public ResponseEntity<PeliculaOSerie> updatePeliculaOSerie(@RequestBody PeliculaOSerie peliculaOSerie,
			@PathVariable int id) {

		try {

			PeliculaOSerie peliculaOSerieModificada = peliculaOSerie;
			peliculaOSerieModificada.setId(id);
			peliculaOSerieService.editarPeliculaOSerie(peliculaOSerieModificada);
			return new ResponseEntity<PeliculaOSerie>(peliculaOSerieModificada, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<PeliculaOSerie>(HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Void> deletePeliculaOSerie(@PathVariable int id) {

		try {

			peliculaOSerieService.eliminarPeliculaOSerie(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/movies/{idMovie}/generos/{idGenero}")
	public ResponseEntity<?> agregarGeneroAPeliculaOSerie(@PathVariable int idMovie, @PathVariable int idGenero) {
	
		try {
			
			PeliculaOSerie peliculaOSerie = peliculaOSerieService.findPeliculaOSerieById(idMovie);
			if(peliculaOSerie == null)
				return new ResponseEntity<>(peliculaOSerie, HttpStatus.NOT_FOUND);
			
			Genero genero = generoService.findGeneroById(idGenero);
			if(genero == null)
				return new ResponseEntity<>(genero, HttpStatus.NOT_FOUND);
			
			for (int i = 0; i < peliculaOSerie.getGenerosAsociados().size(); i++) {
				
				if(peliculaOSerie.getGenerosAsociados().get(i).getId() == idGenero) {
					
					return new ResponseEntity<>(HttpStatus.CONFLICT);
				}
			}
			
			List<Genero> generosAsociados = peliculaOSerie.getGenerosAsociados();
			generosAsociados.add(genero);

			peliculaOSerie.setGenerosAsociados(generosAsociados);

			updatePeliculaOSerie(peliculaOSerie, idMovie);

			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/movies/{idMovie}/characters/{idCharacter}")
	public ResponseEntity<?> agregarPersonajeAPeliculaOSerie(@PathVariable int idMovie, @PathVariable int idCharacter) {
		
		try {

			PeliculaOSerie peliculaOSerie = peliculaOSerieService.findPeliculaOSerieById(idMovie);
			if (peliculaOSerie == null)
				return new ResponseEntity<>(peliculaOSerie, HttpStatus.NOT_FOUND);
			
			Personaje personaje = personajeService.findPersonajeByID(idCharacter);
			if (personaje == null)
				return new ResponseEntity<>(personaje, HttpStatus.NOT_FOUND);
			
			for (int i = 0; i < peliculaOSerie.getPersonajesAsociados().size(); i++) {
				
				if(peliculaOSerie.getPersonajesAsociados().get(i).getId() == idCharacter) {
					
					return new ResponseEntity<>(HttpStatus.CONFLICT);
				}
			}
			
			peliculaOSerie.addPersonajeToMovie(personaje);
			updatePeliculaOSerie(peliculaOSerie, idMovie);
			
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/movies/{idMovie}/characters/{idCharacter}")
	public ResponseEntity<?> removerPersonajeDePeliculaOSerie(@PathVariable int idMovie, @PathVariable int idCharacter) {
		
		try {

			PeliculaOSerie peliculaOSerie = peliculaOSerieService.findPeliculaOSerieById(idMovie);
			if (peliculaOSerie == null)
				return new ResponseEntity<>(peliculaOSerie, HttpStatus.NOT_FOUND);

			for (int i = 0; i < peliculaOSerie.getPersonajesAsociados().size(); i++) {

				if (peliculaOSerie.getPersonajesAsociados().get(i).getId() == idCharacter) {

					peliculaOSerie.getPersonajesAsociados().remove(i);
					peliculaOSerieService.editarPeliculaOSerie(peliculaOSerie);
					
					return new ResponseEntity<>(peliculaOSerie, HttpStatus.OK);
				}
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
}
