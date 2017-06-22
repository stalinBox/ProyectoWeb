package com.project.dao;

import java.util.List;

import com.project.entities.Usuario;

public interface UsuarioDao {
	public Usuario findByUsuario(Usuario usuario);

	public Usuario login(Usuario usuario);

	public List<Usuario> findAll();

	public List<Usuario> findByProcesAndLP(Integer codPro, Integer codLP);

	public boolean create(Usuario usuario);

	public boolean update(Usuario usuario);

	public boolean delete(Integer id);
}