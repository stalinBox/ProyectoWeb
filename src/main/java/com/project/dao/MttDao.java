package com.project.dao;

import java.util.List;

import com.project.entities.ModTrqTal;
import com.project.entities.Modelo;
import com.project.entities.TTalla;
import com.project.entities.Troquele;

public interface MttDao {
	public List<ModTrqTal> findAll();

	public List<Troquele> selectItemsTroqueles();

	public List<Modelo> selectItemsModelos();

	public List<TTalla> selectItemsTallas(Integer codTrq);

	public boolean create(ModTrqTal mtt);
}
