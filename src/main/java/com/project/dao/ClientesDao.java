package com.project.dao;

import java.util.List;

import com.project.entities.Cliente;

public interface ClientesDao {
	public List<Cliente> findAll();

	public boolean create(Cliente cliente);

	public boolean update(Cliente cliente);

	public boolean delete(Integer id);
}
