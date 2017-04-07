package com.project.dao;

import java.util.List;

import com.project.entities.TipLinea;

public interface TipoLineaDao {
	public List<TipLinea> findAll();

	public List<TipLinea> findbyProceso(Integer codPro);

	public List<TipLinea> findByProcesoByModelo(Integer codPro, Integer codMod);

	public boolean create(TipLinea tipoLinea);

	public boolean update(TipLinea tipoLinea);

	public boolean delete(Integer id);
}
