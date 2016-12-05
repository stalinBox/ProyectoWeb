package com.project.dao;

import java.util.List;

import com.project.entities.Dispositivo;

public interface DispositivosDao {
	public List<Dispositivo> findAll();

	public boolean create(Dispositivo dispositivo);

	public boolean update(Dispositivo dispositivo);

	public boolean delete(Integer id);
}
