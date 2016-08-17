package com.project.dao;

import java.util.List;

import com.project.entities.Proceso;

public interface ProcesoDao {
	public boolean create(Proceso proceso);

	public boolean update(Proceso proceso);

	public boolean delete(Integer id);

}
