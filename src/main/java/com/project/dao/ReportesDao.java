package com.project.dao;

import java.util.List;

import com.project.entities.Parametro;

public interface ReportesDao {
	public List<Object[]> findByCapxOrdenRPT(Integer codOrden);

	public List<Parametro> findParaByCod(Integer codOrden);
}
