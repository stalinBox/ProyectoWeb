package com.project.dao;

import java.util.List;

import com.project.entities.Logosfap;

public interface LogoFapsDao {
	public List<Logosfap> findAll();

	public Logosfap findUniqueLogo();

	public boolean create(Logosfap logosFaps);

	public boolean update(Logosfap logosFaps);

	public boolean delete(Integer id);
}
