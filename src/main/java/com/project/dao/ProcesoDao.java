package com.project.dao;

import java.util.List;

import com.project.entities.Parametro;
import com.project.entities.Proceso;

public interface ProcesoDao {
	public List<Proceso> findAll();

	public List<Proceso> findPadre();

	public List<Proceso> findSubProcesos();

	public List<Proceso> findByProceso(Integer a);

	public List<Proceso> findByProcesoBySetting(Integer codPro, Integer codMod);

	public List<Proceso> findByProcesoPadreByOrden(Integer codOrden);

	public List<Proceso> findByOrdenProdNotInParam(Integer codOrden);

	public List<Parametro> findByOrdenParam(Integer codOrden);

	public List<Proceso> findProcesosDistribByOrden(Integer codOrden);

	public Integer findByNameProceso(String nomProceso);

	public boolean create(Proceso proceso);

	public boolean update(Proceso proceso);

	public boolean delete(Integer id);

}
