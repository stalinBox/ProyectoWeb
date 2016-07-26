package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Turno;
import com.project.utils.HibernateUtil;

public class TurnosDaoImpl implements TurnosDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Turno> findAll() {
		List<Turno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Turno";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLTURNOS: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Turno turno) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(turno);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE TURNO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Turno turno) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Turno turnodb = (Turno) sesion.load(Turno.class,
					turno.getTurnoCodigo());

			// Parametros a cambiar
			turnodb.setNombturno(turno.getNombturno());
			turnodb.setHInicio(turno.getHInicio());
			turnodb.setHFin(turno.getHFin());
			// fin de parametros

			sesion.update(turnodb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE TURNO: "
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
			Turno turno = (Turno) sesion.load(Turno.class, id);
			sesion.delete(turno);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE TURNO: "
					+ e.getMessage().toString());
		}
		return flag;
	}
}
