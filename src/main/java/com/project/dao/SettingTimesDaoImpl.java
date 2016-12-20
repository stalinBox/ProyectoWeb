package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Confproceso;
import com.project.utils.HibernateUtil;

public class SettingTimesDaoImpl implements SettingTimesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Confproceso> findAll() {
		List<Confproceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Confproceso order by confproCodigo";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL CONFTIMES PROCESO: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Confproceso confPro) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(confPro);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE CONF PROCESO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Confproceso confPro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Confproceso confProceso = (Confproceso) sesion.load(
					Confproceso.class, id);
			sesion.delete(confProceso);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE CONF PROCESO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
