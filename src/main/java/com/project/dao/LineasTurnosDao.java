package com.project.dao;

import java.util.List;

import com.project.entities.Lineasturno;

public interface LineasTurnosDao {
	public List<Lineasturno> findAll();

	public boolean create(Lineasturno lienasTurnos);

	public boolean update(Lineasturno lienasTurnos);

	public boolean delete(Integer id);
}
