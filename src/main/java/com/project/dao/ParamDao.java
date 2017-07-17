package com.project.dao;

import java.util.List;

import com.project.entities.Parametro;

public interface ParamDao {
	public List<Parametro> findAll();

	public List<?> findParamAndLineaProd(Integer codParam);

	public List<Parametro> findByCodProByCodTpl(Integer codPro, Integer codTpl);

	public Parametro findbyCodParam(Integer codParam);

	public List<Parametro> findByStand(Integer codParam);

	public List<Parametro> findByCodLinea(Integer codLinea);

	public List<Parametro> findByParamInLT(Integer codOrden);

	public List<Parametro> findByParamInLT(Integer codOrden, Integer codPro);

	public List<Parametro> findbyCodParam2(Integer codOrden, Integer codParam);

	public List<Parametro> getProcesosbyOrden(Integer codOrden);

	public List<Parametro> getCpByProcesoOrden(Integer codOrden,
			Integer codProceso);

	public List<Parametro> findByOrdenProd(Integer codOrden);

	public Integer findByParam(Integer codParam);

	public boolean create(Parametro parametro);

	public boolean update(Parametro parametro);

	public boolean delete(Integer id);
}
