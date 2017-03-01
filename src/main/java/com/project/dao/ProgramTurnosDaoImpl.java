package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Programturno;
import com.project.utils.HibernateUtil;

public class ProgramTurnosDaoImpl implements ProgramTurnosDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Programturno> findAll() {
		List<Programturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Programturno ";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRRRR FINDALLPROGRAMTURNO: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Programturno pgTurnos) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(pgTurnos);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE PGTURNOS: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Programturno pgTurnos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Programturno cliente = (Programturno) sesion.load(
					Programturno.class, id);
			sesion.delete(cliente);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE PROGRAMTURNO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Programturno> findByPop(Integer codPop) {
		List<Programturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Programturno pgt where pgt.procesosop.processopCod in "
				+ "(select pop.processopCod from Procesosop pop where pop.processopCod = "
				+ codPop + ")";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRRRR FINDALLPROGRAMTURNO: " + e.toString());
		}
		return listado;
	}

}
