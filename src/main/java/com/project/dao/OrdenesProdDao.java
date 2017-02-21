package com.project.dao;

import java.util.List;

import com.project.entities.Ordenprod;

public interface OrdenesProdDao {
	public boolean create(Ordenprod ordenProd);

	public boolean update(Ordenprod ordenProd);

	public boolean delete(Integer id);

	public List<Ordenprod> findAll();

	public List<Ordenprod> getAllOrderN();

	public Ordenprod LastRespOrden();

	public Ordenprod findByLast(Integer id);

}
