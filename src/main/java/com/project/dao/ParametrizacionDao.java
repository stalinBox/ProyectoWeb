package com.project.dao;

import java.util.List;

import com.project.entities.Parametro;

public interface ParametrizacionDao {
	public List<Parametro> findAll();

	public List<Parametro> findByStandarByProceso(Integer codOrden,
			Integer codProceso);

	public List<Parametro> findByOrdenProd(Integer codOrden);

	public List<Integer> findByProcesosInLT(Integer codOrden);

	public List<Parametro> getProcesosbyOrden(Integer codOrden);

	public List<Parametro> getCpByProcesoOrden(Integer codOrden,
			Integer codProceso);

	public boolean create(Parametro parametrizacion);

	public boolean update(Parametro parametrizacion);

	public boolean delete(Integer id);
}
