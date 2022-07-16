package com.jpcode.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "peliculas_o_series")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "titulo")
public class PeliculaOSerie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;
	
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "titulo", unique = true)
	private String titulo;
	
	@Column(name = "fecha_de_creacion")
	private String fechaDeCreacion;
	
	@Column(name = "calificacion")
	private int calificacion;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "personajes_and_peliculas_o_series",
			joinColumns = @JoinColumn(name = "id_personajes", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_peliculas_o_series", referencedColumnName = "id"))
	private List<Personaje> personajesAsociados = new ArrayList<Personaje>();
	
	@ManyToMany
	@JoinTable(
			name = "generos_and_peliculas_o_series",
			joinColumns = @JoinColumn(name = "peliculas_o_series_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "generos_id", referencedColumnName = "id"))
	private List<Genero> generosAsociados = new ArrayList<Genero>();
	
	
	public PeliculaOSerie() {
		
	}

	public PeliculaOSerie(String imagen, String titulo, String fechaDeCreacion, int calificacion) {
		this.imagen = imagen;
		this.titulo = titulo;
		this.fechaDeCreacion = fechaDeCreacion;
		this.calificacion = calificacion;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(String fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public List<Personaje> getPersonajesAsociados() {
		return personajesAsociados;
	}

	public void setPersonajesAsociados(List<Personaje> personajesAsociados) {
		this.personajesAsociados = personajesAsociados;
	}

	public void addPersonajeToMovie(Personaje personaje) {
		this.personajesAsociados.add(personaje);
	}
	
	public List<Genero> getGenerosAsociados() {
		return generosAsociados;
	}

	public void setGenerosAsociados(List<Genero> generosAsociados) {
		this.generosAsociados = generosAsociados;
	}
	
	public void addGeneroToMovie(Genero genero) {
		this.generosAsociados.add(genero);
	}
}
