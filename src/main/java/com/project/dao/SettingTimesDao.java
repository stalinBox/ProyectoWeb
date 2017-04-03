package com.project.dao;

import java.util.List;

import com.project.entities.Confproceso;

public interface SettingTimesDao {
	public List<Confproceso> findAll();

	public boolean create(Confproceso confPro);

	public boolean update(Confproceso confPro);

	public boolean delete(Integer id);

	public double findByTs(String mNombre, String tNombre, Integer nDia);

}
