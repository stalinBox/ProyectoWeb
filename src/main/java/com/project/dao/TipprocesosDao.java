package com.project.dao;

import java.util.List;

import com.project.entities.TipoProceso;

public interface TipprocesosDao {
	public List<TipoProceso> findAll();

	public boolean create(TipoProceso tipProceso);

	public boolean update(TipoProceso tipProceso);

	public boolean delete(Integer id);
}
