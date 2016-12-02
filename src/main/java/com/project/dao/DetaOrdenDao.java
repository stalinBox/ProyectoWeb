package com.project.dao;

import java.util.List;

import com.project.entities.Detalleorden;

public interface DetaOrdenDao {
	public boolean create(Detalleorden detaOrden);

	public boolean update(Detalleorden detaOrden);

	public boolean delete(Integer id);

	public List<Detalleorden> findAll();
}
