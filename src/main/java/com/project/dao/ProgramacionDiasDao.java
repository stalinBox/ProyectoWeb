package com.project.dao;

import java.util.List;

import com.project.entities.Programdia;

public interface ProgramacionDiasDao {

	public List<Programdia> findAll();

	public List<Programdia> getOrderDates(Integer codOrden, Integer codProceso);

	public boolean create(Programdia programDia);

	public boolean update(Programdia programDia);

	public boolean delete(Integer id);

	public List<Object[]> findByProceso(Integer proCod);
}
