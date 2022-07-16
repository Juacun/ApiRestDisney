package com.jpcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpcode.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

	public Genero findById(int id);
	
	public Genero findByNombre(String nombre);
}
