package com.project.dao;

import java.util.List;

import com.project.entities.Distribdetalle;

public interface DistribDetalleDao {

	public List<Distribdetalle> findAll();

	public List<Distribdetalle> findByOrden(Integer codOrden);

	public boolean create(Distribdetalle distribDetalle);

	public boolean update(Distribdetalle distribDetalle);

	public boolean delete(Integer id);
}
