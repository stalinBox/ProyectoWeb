package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Logosfap;
import com.project.utils.HibernateUtil;

public class LogoFapsDaoImpl implements LogoFapsDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Logosfap> findAll() {
		List<Logosfap> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Logosfap";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLLOGOS: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Logosfap logosFaps) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(logosFaps);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE LOGO FAPS: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Logosfap logosFaps) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Logosfap logosdb = (Logosfap) sesion.load(Logosfap.class,
					logosFaps.getIdlogos());
			// Parametros a cambiar
			// fin de parametros
			sesion.update(logosdb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE LOGOSFAPS: "
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
			Logosfap logo = (Logosfap) sesion.load(Logosfap.class, id);
			sesion.delete(logo);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE LOGO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
