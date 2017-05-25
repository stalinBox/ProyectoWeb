package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import com.project.entities.Lineasturno;

public interface LineasTurnosDao {

	public List<Lineasturno> findAll();

	public List<Lineasturno> findByOrden(Integer codOrden);

	public List<Lineasturno> findByOrdenBYPROCESOSOP(Integer codOrden);

	public List<Lineasturno> findByParam(Integer codParam);

	public List<Lineasturno> findByDETALLE(Integer codOrden);

	public List<Lineasturno> findByOrden(Integer codOrden, Integer codProceso);

	public List<Integer> GetCodProcesoByOrden(Integer codOrden);

	public Object GetArrayByProcesoByOrden(Integer codOrden, Integer codProceso);

	public ArrayList<Integer> findByOrdenProd(Integer codOrden, Integer codPro);

	public List<Integer> getLineasByProceso(Integer codPro, Integer codOrden);

	public Object getCountTurnosByLineas(Integer lineaCod, Integer codOrden);

	public Object getCountTurnosByLineas(Integer lineaCod, Integer codOrden,
			Integer codPro);

	public boolean create(Lineasturno lienasTurnos);

	public boolean update(Lineasturno lienasTurnos);

	public boolean delete(Integer id);
}
