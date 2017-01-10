package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import com.project.entities.Lineasturno;

public interface LineasTurnosDao {

	public List<Lineasturno> findAll();

	public List<Lineasturno> findByOrden(Integer codOrden);

	public List<Integer> GetCodProcesoByOrden(Integer codOrden);

	public ArrayList<Integer> GetArrayByProcesoByOrden(Integer codOrden,
			Integer codProceso);

	public ArrayList<Integer> findByOrdenProd(Integer codOrden, Integer codPro);

	public boolean create(Lineasturno lienasTurnos);

	public boolean update(Lineasturno lienasTurnos);

	public boolean delete(Integer id);
}
