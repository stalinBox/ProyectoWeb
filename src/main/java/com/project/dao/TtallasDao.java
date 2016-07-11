package com.project.dao;

import java.util.List;

import com.project.entities.TTalla;
import com.project.entities.TTallaPK;
import com.project.entities.Talla;
import com.project.entities.Troquele;

public interface TtallasDao {
	public List<TTalla> findAll();

	public List<Troquele> selectItems();

	public List<Talla> excludeByTalla(Integer trq);

	public boolean create(TTalla ttalla);

	public boolean update(TTallaPK tTallaPK, TTalla ttal);

	public boolean delete(TTallaPK tTallaPK, TTalla ttalla);
}
