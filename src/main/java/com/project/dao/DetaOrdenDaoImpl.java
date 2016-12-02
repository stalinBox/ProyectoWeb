package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Detalleorden;
import com.project.utils.HibernateUtil;

public class DetaOrdenDaoImpl implements DetaOrdenDao {

	@Override
	public boolean create(Detalleorden detaOrden) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(detaOrden);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE DETALLE ORDEN DE PRODUCCION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Detalleorden detaOrden) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Detalleorden detaOrderdb = (Detalleorden) sesion.load(
					Detalleorden.class, detaOrden.getDetaordenCodigo());

			// Parametros a cambiar
			detaOrderdb.setCantidad(detaOrden.getCantidad());
			// PONER LOS DEMAS CAMPOS A ACTUALIZAR ***********

			// fin de parametros
			sesion.update(detaOrderdb);
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
			Detalleorden detaorden = (Detalleorden) sesion.load(
					Detalleorden.class, id);
			sesion.delete(detaorden);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Detalleorden> findAll() {
		List<Detalleorden> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Detalleorden";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL DETAORDER: " + e.toString());
		}
		return listado;
	}

}
