package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Rol;
import com.project.utils.HibernateUtil;

public class RolDaoImpl implements RolDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Rol> selectItems() {
		List<Rol> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Rol";
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERROR IMPLEMENTS SELECTITEMS ROL: " + e.toString());
		}
		return listado;
	}
}
