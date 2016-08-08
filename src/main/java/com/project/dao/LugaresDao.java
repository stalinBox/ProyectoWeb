package com.project.dao;

import java.util.List;

import com.project.entities.Lugare;

public interface LugaresDao {
	public List<Lugare> findAll();

	public boolean create(Lugare lugar);

	public boolean update(Lugare lugar);

	public boolean delete(Integer id);
}
