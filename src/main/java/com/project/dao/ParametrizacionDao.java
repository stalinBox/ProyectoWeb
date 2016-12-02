package com.project.dao;

import java.util.List;

import com.project.entities.Parametro;

;

public interface ParametrizacionDao {
	public List<Parametro> findAll();

	public boolean create(Parametro parametrizacion);

	public boolean update(Parametro parametrizacion);

	public boolean delete(Integer id);
}
