package com.project.dao;

import java.util.List;

import com.project.entities.Lineasprod;

public interface LineasProdDao {

	public List<Lineasprod> findAll();

	public List<Lineasprod> findByParam(Integer codPro, Integer codTpLinea,
			Integer coodOrden);

	public List<Lineasprod> findByProceso(Integer codPro);

	public boolean create(Lineasprod lineaP);

	public boolean update(Lineasprod lineaP);

	public boolean delete(Integer id);

}
