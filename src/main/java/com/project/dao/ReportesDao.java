package com.project.dao;

import java.util.List;

import com.project.entities.Ordenprod;

public interface ReportesDao {
	public List<Object[]> findByCapxOrden(Integer proCod);

	public List<Object[]> findByCapxOrdenPrueba(Integer proCod);

	public List<Ordenprod> ObtenerTodos(Integer codOrden);
}
