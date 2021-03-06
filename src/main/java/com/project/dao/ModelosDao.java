package com.project.dao;

import java.util.List;

import com.project.entities.Modelo;

public interface ModelosDao {

	public List<Modelo> findAll();

	public List<Modelo> findByModelByFindTipLinea(Integer codMod);

	public List<Modelo> findByDistrib(Integer codOrden);

	public boolean create(Modelo modelo);

	public boolean update(Modelo modelo);

	public boolean delete(Integer id);

}
