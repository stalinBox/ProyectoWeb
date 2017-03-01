package com.project.dao;

import java.util.List;

import com.project.entities.Programturno;

public interface ProgramTurnosDao {
	public List<Programturno> findAll();

	public List<Programturno> findByPop(Integer codPop);

	public boolean create(Programturno pgTurnos);

	public boolean update(Programturno pgTurnos);

	public boolean delete(Integer id);
}
