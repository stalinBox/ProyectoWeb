package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Parametro;
import com.project.utils.HibernateUtil;

public class ParametrizacionDaoImpl implements ParametrizacionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findAll() {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Parametro";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLMODELOS: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Parametro parametrizacion) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(parametrizacion);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE PARAMETRIZACION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Parametro parametrizacion) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Parametro parametrizaciondb = (Parametro) sesion.load(
					Parametro.class, parametrizacion.getParamCodigo());
			// Parametros a cambiar
			// PARAMETROS A CAMBIAR *********************
			// fin de parametros
			sesion.update(parametrizaciondb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE PARAMETRIZACION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Parametro parametrizacion = (Parametro) sesion.load(
					Parametro.class, id);
			sesion.delete(parametrizacion);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE PARAMETRIZACION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
