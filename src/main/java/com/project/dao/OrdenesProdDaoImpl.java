package com.project.dao;

import org.hibernate.Session;

import com.project.entities.Ordenprod;
import com.project.utils.HibernateUtil;

public class OrdenesProdDaoImpl implements OrdenesProdDao {

	@Override
	public boolean create(Ordenprod ordenProd) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(ordenProd);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE ORDEN PRODUCCION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Ordenprod ordenProd) {
		return false;
	}

	@Override
	public boolean delete(Ordenprod ordenProd) {
		return false;
	}

}
