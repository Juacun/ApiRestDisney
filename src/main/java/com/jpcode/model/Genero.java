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
@Table(name = "generos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Genero implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;
	
	@Column(name = "nombre", unique = true)
	private String nombre;
	
	@Column(name = "imagen")
	private String imagen;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "generos_and_peliculas_o_series",
			joinColumns = @JoinColumn(name = "generos_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "peliculas_o_series_id", referencedColumnName = "id"))
	private List<PeliculaOSerie> peliculasOSeriesAsociadas = new ArrayList<PeliculaOSerie>();
	
	
	public Genero() {
		
	}

	public Genero(String nombre, String imagen) {
		this.nombre = nombre;
		this.imagen = imagen;
		//this.peliculasOSeriesAsociadas = peliculasOSeriesAsociadas;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public List<PeliculaOSerie> getPeliculasOSeriesAsociadas() {
		return peliculasOSeriesAsociadas;
	}


	public void setPeliculasOSeriesAsociadas(List<PeliculaOSerie> peliculasOSeriesAsociadas) {
		this.peliculasOSeriesAsociadas = peliculasOSeriesAsociadas;
	}
	
}
