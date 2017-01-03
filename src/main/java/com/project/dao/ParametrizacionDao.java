package com.project.dao;

import java.util.List;

import com.project.entities.Parametro;

public interface ParametrizacionDao {
	public List<Parametro> findAll();

	public List<Parametro> findByOrdenProd(Integer codOrden);

	public boolean create(Parametro parametrizacion);

	public boolean update(Parametro parametrizacion);

	public boolean delete(Integer id);
}
