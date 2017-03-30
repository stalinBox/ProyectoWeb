package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Procesosop;
import com.project.utils.HibernateUtil;

public class ProcesosOPDaoImpl implements ProcesosOPDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Procesosop> findAll() {
		List<Procesosop> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Procesosop ";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPROCESOSOP: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Procesosop processOP) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(processOP);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE PROCESOSOP: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Procesosop processOP) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Procesosop cliente = (Procesosop) sesion.load(Procesosop.class, id);
			sesion.delete(cliente);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE PROCESOSOP: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public Procesosop getLastResp() {
		Procesosop entities = null;
		Integer ent = 0;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select max(op.processopCod) from Procesosop op";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			ent = (Integer) sesion.createQuery(sql).uniqueResult();
			entities = this.getLastRecord(ent);
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRR LASTRESORDEN: " + e.toString());
			throw e;
		}
		return entities;
	}

	@Override
	public Procesosop getLastRecord(Integer id) {
		Procesosop entities = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Procesosop op where op.processopCod = " + id;
		System.out.println(sql);
		try {
			entities = (Procesosop) sesion.createQuery(sql).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entities;
	}

}
