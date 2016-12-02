package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Lineasturno;
import com.project.utils.HibernateUtil;

public class LineasTurnosDaoImpl implements LineasTurnosDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasturno> findAll() {
		List<Lineasturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasturno";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL LINEASTURNOS: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Lineasturno lienasTurnos) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(lienasTurnos);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE LINEAS TURNOS: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Lineasturno lienasTurnos) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Lineasturno lineasTurnosdb = (Lineasturno) sesion.load(
					Lineasturno.class, lienasTurnos.getLtcodigo());
			// Parametros a cambiar
			// parametros a cambiar ****************
			// fin de parametros
			sesion.update(lineasTurnosdb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE MODELO: "
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
			Lineasturno lineasTurnos = (Lineasturno) sesion.load(
					Lineasturno.class, id);
			sesion.delete(lineasTurnos);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE MODELO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
