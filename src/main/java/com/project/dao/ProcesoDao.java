package com.project.dao;

import java.util.List;

import com.project.entities.ModTrqTal;
import com.project.entities.Proceso;

public interface ProcesoDao {
	public List<Proceso> findAll();

	public List<ModTrqTal> selectItemsModelos();

	public boolean create(Proceso proceso);

	public boolean update(Proceso proceso);

	public boolean delete(Integer id);
}
