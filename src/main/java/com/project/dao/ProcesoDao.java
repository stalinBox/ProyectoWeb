package com.project.dao;

import java.util.List;

import com.project.entities.Proceso;

public interface ProcesoDao {
	public List<Proceso> findAll();

	public List<Proceso> findPadre();

	public List<Proceso> findSubProcesos();

	public List<Proceso> findByProceso(Integer a);

	public List<Proceso> findByProcesoBySetting(Integer codPro, Integer codMod);

	public boolean create(Proceso proceso);

	public boolean update(Proceso proceso);

	public boolean delete(Integer id);

}
