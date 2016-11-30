package com.project.dao;

import java.util.List;

import com.project.entities.Lineasprod;

public interface LineasProdDao {

	public List<Lineasprod> findAll();

	public List<Lineasprod> selectedByMontaje(Lineasprod lineasLP);

	public List<Lineasprod> selectedByAparado(Lineasprod lineasLP);

	public List<Lineasprod> selectedByTroquelado(Lineasprod lineasLP);

	public boolean create(Lineasprod lineaP);

	public boolean update(Lineasprod lineaP);

	public boolean delete(Integer id);

}
