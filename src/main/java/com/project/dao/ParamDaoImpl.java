package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Parametro;
import com.project.utils.HibernateUtil;

public class ParamDaoImpl implements ParamDao {

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
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Parametro Parametro) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(Parametro);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE PARAMETRO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Parametro parametro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Parametro findbyCodParam(Integer codParam) {
		Parametro listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Parametro param WHERE param.paramCodigo = "
				+ codParam;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = (Parametro) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

}
