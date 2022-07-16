package com.jpcode.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "personajes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nombre")
public class Personaje implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;
	
	@Lob
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "nombre", unique = true)
	private String nombre;
	
	@Column(name = "edad")
	private int edad;
	
	@Column(name = "peso")
	private float peso;
	
	@Column(name = "historia")
	private String historia;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "personajes_and_peliculas_o_series",
			joinColumns = @JoinColumn(name = "id_personajes", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_peliculas_o_series", referencedColumnName = "id"))
	private List<PeliculaOSerie> peliculasOSeriesAsociadas;
	
	
	public Personaje() {
		
	}

	public Personaje(String imagen, String nombre, int edad, float peso, String historia, List<PeliculaOSerie> peliculasOSeriesAsociadas) {
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculasOSeriesAsociadas = peliculasOSeriesAsociadas;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public List<PeliculaOSerie> getPeliculasOSeriesAsociadas() {
		return peliculasOSeriesAsociadas;
	}

	public void setPeliculasOSeriesAsociadas(List<PeliculaOSerie> peliculasOSeriesAsociadas) {
		this.peliculasOSeriesAsociadas = peliculasOSeriesAsociadas;
	}

	@Override
	public String toString() {
		return "Personaje [id=" + id + ", imagen=" + imagen + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso
				+ ", historia=" + historia + "]";
	}
	
}
