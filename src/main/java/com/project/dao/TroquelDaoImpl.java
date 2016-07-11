package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Troquele;
import com.project.utils.HibernateUtil;

public class TroquelDaoImpl implements TroqueleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Troquele> findAll() {
		List<Troquele> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Troquele";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLTROQUELES: " + e.toString());
		}
		return listado;
	}

	// DMLS
	@Override
	public boolean create(Troquele troquel) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(troquel);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE TROQUEL CALZADO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Troquele troquel) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Troquele troquelodb = (Troquele) sesion.load(Troquele.class,
					troquel.getTrqCodigo());
			// Parametros a cambiar
			troquelodb.setTrqNombre(troquel.getTrqNombre());
			// fin de parametros
			sesion.update(troquelodb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE TROQUEL: "
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
			Troquele troquel = (Troquele) sesion.load(Troquele.class, id);
			sesion.delete(troquel);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE TROQUEL: "
					+ e.getMessage().toString());
		}
		return flag;
	}
}
