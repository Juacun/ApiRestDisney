package com.jpcode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nombre", nullable = false, unique = true)
	private String nombre;
	
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	private String role = "ROLE_ADMIN";

	
	public Usuario() {
		
	}
	
	public Usuario(String nombre, String password, String email, String role) {
		this.nombre = nombre;
		this.password = password;
		this.email = email;
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
