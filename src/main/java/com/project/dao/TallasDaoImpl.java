package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Talla;
import com.project.utils.HibernateUtil;

public class TallasDaoImpl implements TallasDao {

	@Override
	public Talla findByTalla(Talla talla) {
		Talla entities = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Talla WHERE talNumero='" + talla.getTalNumero()
				+ "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = (Talla) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRR FINDBYTALLA: " + e.toString());
		}
		return entities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Talla> findAll() {
		List<Talla> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Talla order by talNumero";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLTALLA: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Talla talla) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(talla);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE TALLA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Talla talla) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Talla talladb = (Talla) sesion.load(Talla.class,
					talla.getTalCodigo());
			// Parametros a cambiar
			talladb.setTalNumero(talla.getTalNumero());
			// fin de parametros
			sesion.update(talladb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE TALLA: "
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
			Talla talla = (Talla) sesion.load(Talla.class, id);
			sesion.delete(talla);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE TALLA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	/*
	@SuppressWarnings("unchecked")
	@Override
	public List<Talla> selectItems() {
		List<Talla> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Talla order by talNumero";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLSELECTITEMSTALLA: " + e.toString());
		}
		return listado;
	}*/

}
