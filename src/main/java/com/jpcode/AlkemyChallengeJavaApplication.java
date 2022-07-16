package com.jpcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpcode.model.Genero;
import com.jpcode.model.PeliculaOSerie;
import com.jpcode.model.Personaje;
import com.jpcode.service.IGeneroService;
import com.jpcode.service.IPeliculaOSerieService;
import com.jpcode.service.IPersonajeService;

@SpringBootApplication
public class AlkemyChallengeJavaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AlkemyChallengeJavaApplication.class, args);
	}

	@Autowired
	IGeneroService generoService;
	
	@Autowired
	IPersonajeService personajeService;
	
	@Autowired
	IPeliculaOSerieService peliculaOSerieService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Genero genero = new Genero("Animacion", "img");
		generoService.crearGenero(genero);
		
		Personaje personaje = new Personaje("img", "Woody", 40, 70, "lorem hipsum", null);
		personajeService.crearPersonaje(personaje);
		
		personaje = new Personaje("img", "Buzzligthyear", 45, 82, "lorem hipsum", null);
		personajeService.crearPersonaje(personaje);
		
		personaje = new Personaje("img", "Sr PotatoFace", 55, 56, "lorem hipsum", null);
		personajeService.crearPersonaje(personaje);
		
		personaje = new Personaje("img", "Rex", 30, 130, "lorem hipsum", null);
		personajeService.crearPersonaje(personaje);
		
		personaje = new Personaje("img", "Jessie", 38, 65, "lorem hipsum", null);
		personajeService.crearPersonaje(personaje);
		
		
		PeliculaOSerie peliculaOSerie = new PeliculaOSerie("img", "Toys Story I", "14 de marzo de 1996", 5);
		peliculaOSerieService.crearPeliculaOSerie(peliculaOSerie);
		
		
		
		peliculaOSerie = new PeliculaOSerie("img", "Toys Story II", "2 de diciembre de 1999", 3);
		peliculaOSerieService.crearPeliculaOSerie(peliculaOSerie);

		/*
		//peliculaOSerie = new PeliculaOSerie("img", "Toy Story III", "15 de junio de 2010", 4);
		//peliculaOSerieService.crearPeliculaOSerie(peliculaOSerie);
		*/
		
	}
	
}