package com.project.dao;

import com.project.entities.Ordenprod;

public interface OrdenesProdDao {
	public boolean create(Ordenprod ordenProd);

	public boolean update(Ordenprod ordenProd);

	public boolean delete(Ordenprod ordenProd);

}
