package com.project.dao;

import java.util.List;

import com.project.entities.Empresa;

public interface EmpresaDao {
	public List<Empresa> findAll();

	public boolean create(Empresa empresa);

	public boolean update(Empresa empresa);

	public boolean delete(Integer id);
}
