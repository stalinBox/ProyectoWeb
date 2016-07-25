package com.project.dao;

import java.util.List;

import com.project.entities.Turno;

public interface TurnosDao {
	public List<Turno> findAll();

	public boolean create(Turno turno);

	public boolean update(Turno turno);

	public boolean delete(Integer id);
}
