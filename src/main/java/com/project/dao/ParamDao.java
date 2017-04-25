package com.project.dao;

import java.util.List;

import com.project.entities.Parametro;

public interface ParamDao {
	public List<Parametro> findAll();

	public boolean create(Parametro parametro);

	public boolean update(Parametro parametro);

	public boolean delete(Integer id);
}
