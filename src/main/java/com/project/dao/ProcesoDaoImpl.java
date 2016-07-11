package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Proceso;
import com.project.utils.HibernateUtil;

public class ProcesoDaoImpl implements ProcesoDao {

	@Override
	public Proceso findByProceso(Proceso proceso) {
		Proceso entities = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Proceso WHERE pro_codigo='" + proceso.getProCodigo()
				+ "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = (Proceso) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRR FINDBYMODELOS CALZADO: " + e.toString());
		}
		return entities;
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
			System.out.println("ERRORRRRR FINDALLPROCESO: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Proceso proceso) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Proceso proceso) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
