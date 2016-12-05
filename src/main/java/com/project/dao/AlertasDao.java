package com.project.dao;

import java.util.List;

import com.project.entities.Alerta;

public interface AlertasDao {
	public List<Alerta> findAll();

	public boolean create(Alerta alerta);

	public boolean update(Alerta alerta);

	public boolean delete(Integer id);
}
