package com.project.dao;

import java.util.List;

import com.project.entities.Troquele;

public interface TroqueleDao {
	public List<Troquele> findAll();
	public boolean create(Troquele troquel);
	public boolean update(Troquele troquel);
	public boolean delete(Integer id);
	
}
