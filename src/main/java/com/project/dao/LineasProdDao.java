package com.project.dao;

import java.util.List;

import com.project.entities.Lineasprod;

public interface LineasProdDao {

	public List<Lineasprod> findAll();

	public Lineasprod selectedByMontaje(Lineasprod lineasLP);

	public List<Lineasprod> findByMontaje();

	public Lineasprod selectedByAparado(Lineasprod lineasLP);

	public List<Lineasprod> findByAparado();

	public Lineasprod selectedByTroquelado(Lineasprod lineasLP);

	public List<Lineasprod> findByTroquelado();

	public boolean create(Lineasprod lineaP);

	public boolean update(Lineasprod lineaP);

	public boolean delete(Integer id);

}
