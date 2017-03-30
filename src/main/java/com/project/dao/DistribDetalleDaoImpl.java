package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Distribdetalle;
import com.project.utils.HibernateUtil;

public class DistribDetalleDaoImpl implements DistribDetalleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Distribdetalle> findAll() {
		List<Distribdetalle> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Distribdetalle ";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLDISTRIBDETALLE: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Distribdetalle distribDetalle) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(distribDetalle);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE MODELO CALZADO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Distribdetalle distribDetalle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Distribdetalle> findByOrden(Integer codOrden) {
		List<Distribdetalle> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Distribdetalle dt inner join dt.detalleorden do where do.ordenprod.ordenprodCodigo = "
				+ codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLDISTRIBDETALLE: "
					+ e.toString());
		}
		return listado;
	}

}