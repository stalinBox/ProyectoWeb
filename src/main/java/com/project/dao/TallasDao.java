package com.project.dao;

import java.util.List;

import com.project.entities.Talla;

public interface TallasDao {
	public Talla findByTalla(Talla talla);

	public List<Talla> findAll();

	public boolean create(Talla talla);

	public boolean update(Talla talla);

	public boolean delete(Integer id);

}
