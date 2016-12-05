package com.project.dao;

import java.util.List;

import com.project.entities.Maquina;

public interface MaquinasDao {
	public List<Maquina> findAll();

	public boolean create(Maquina maquina);

	public boolean update(Maquina maquina);

	public boolean delete(Integer id);
}
