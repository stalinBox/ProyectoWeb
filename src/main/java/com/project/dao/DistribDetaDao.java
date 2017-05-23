package com.project.dao;

import java.util.List;

import com.project.entities.Distribdetalle;

public interface DistribDetaDao {
	public List<Distribdetalle> findAll();

	public List<Distribdetalle> findByOrder(Integer codOrden);

	public List<Distribdetalle> findByOrderByProByTL(Integer codOrden,
			Integer codPro, Integer codTLinea);

	public Object getSumByProTip(Integer codOrden, Integer codPro,
			Integer codTLinea, Integer codMod);

	public boolean create(Distribdetalle distribDeta);

	public boolean update(Distribdetalle distribDeta);

	public boolean delete(Integer id);

}
