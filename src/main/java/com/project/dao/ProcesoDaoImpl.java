package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Proceso;
import com.project.utils.HibernateUtil;

public class ProcesoDaoImpl implements ProcesoDao {

	@Override
	public boolean create(Proceso proceso) {
		return false;
	}

	@Override
	public boolean update(Proceso proceso) {
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proceso> findAll() {
		List<Proceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Proceso";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL PROCESOS: " + e.toString());
		}
		return listado;
	}

}
