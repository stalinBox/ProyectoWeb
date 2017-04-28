package com.project.dao;

import java.util.List;

import com.project.entities.Parametro;

public interface ParamDao {
	public List<Parametro> findAll();

	public Parametro findbyCodParam(Integer codParam);

	public List<Parametro> getProcesosbyOrden(Integer codOrden);

	public boolean create(Parametro parametro);

	public boolean update(Parametro parametro);

	public boolean delete(Integer id);
}
