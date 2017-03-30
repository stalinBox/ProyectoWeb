package com.project.dao;

import java.util.List;

import com.project.entities.Detalleorden;
import com.project.entities.Modelo;

public interface DetaOrdenDao {
	public boolean create(Detalleorden detaOrden);

	public boolean update(Detalleorden detaOrden);

	public boolean delete(Integer id);

	public boolean deleleByOrden(Integer id);

	public List<Detalleorden> findAll();

	public List<String> getByOrden(Integer codOrden);

	public List<Detalleorden> getByOrden2(Integer codOrden, Integer codPro);

	public List<Modelo> findByOrden2(Integer idOrden);

	public List<Integer> getSumByModelo(Integer codOrden, String modelo);

	public List<Detalleorden> findByOrden(Integer idOrden);

	public List<Detalleorden> findByOrdenByMod(Integer idOrden, Integer codMod);
}
