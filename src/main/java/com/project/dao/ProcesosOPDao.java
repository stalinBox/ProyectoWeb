package com.project.dao;

import java.util.List;

import com.project.entities.Procesosop;

public interface ProcesosOPDao {
	public List<Procesosop> findAll();

	public List<Procesosop> findByOrden(Integer codOrden);

	public List<Procesosop> findByCodPop(Integer codPop);

	public Procesosop getLastResp();

	public Procesosop getLastRecord(Integer id);

	public boolean create(Procesosop processOP);

	public boolean update(Procesosop processOP);

	public boolean delete(Integer id);
}
