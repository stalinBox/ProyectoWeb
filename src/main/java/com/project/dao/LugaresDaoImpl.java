package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Lugare;
import com.project.utils.HibernateUtil;

public class LugaresDaoImpl implements LugaresDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lugare> findAll() {
		List<Lugare> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lugare";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLLUGARES: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Lugare lugar) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(lugar);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE LUGARES: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Lugare lugar) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Lugare lugardb = (Lugare) sesion.load(Lugare.class,
					lugar.getLugarCodigo());
			// Parametros a cambiar
			lugardb.setNomlugar(lugar.getNomlugar());
			// fin de parametros
			sesion.update(lugardb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE LUGAR: "
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
			Lugare lugar = (Lugare) sesion.load(Lugare.class, id);
			sesion.delete(lugar);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE LUGAR: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
