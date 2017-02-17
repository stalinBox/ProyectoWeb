package com.project.dao;

import java.util.List;

import com.project.entities.Procesosop;

public interface ProcesosOPDao {
	public List<Procesosop> findAll();

	public boolean create(Procesosop processOP);

	public boolean update(Procesosop processOP);

	public boolean delete(Integer id);
}
