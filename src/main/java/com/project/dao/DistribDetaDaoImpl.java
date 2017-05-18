package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Distribdetalle;
import com.project.utils.HibernateUtil;

public class DistribDetaDaoImpl implements DistribDetaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Distribdetalle> findAll() {
		List<Distribdetalle> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Distribdetalle";
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
	public boolean create(Distribdetalle distribDeta) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(distribDeta);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE DISTRIBDETALLE: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Distribdetalle distribDeta) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Distribdetalle distribdetadb = (Distribdetalle) sesion.load(
					Distribdetalle.class, distribDeta.getDistribCodigo());
			// Parametros a cambiar
			// modelodb.setModNombre(modelo.getModNombre());
			// modelodb.setModPiezas(modelo.getModPiezas());
			// fin de parametros
			sesion.update(distribdetadb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE DISTRIBDETALLE: "
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
			Distribdetalle distribDeta = (Distribdetalle) sesion.load(
					Distribdetalle.class, id);
			sesion.delete(distribDeta);
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
	public List<Distribdetalle> findByOrder(Integer codOrden) {
		List<Distribdetalle> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Distribdetalle dt where dt.detalleorden.detaordenCodigo in "
				+ "(select deto.detaordenCodigo from Detalleorden deto where deto.ordenprod.ordenprodCodigo= "
				+ codOrden + ")";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Distribdetalle> findByOrderByProByTL(Integer codOrden,
			Integer codPro, Integer codTLinea) {
		List<Distribdetalle> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Distribdetalle dt where dt.proceso.proCodigo = "
				+ codPro
				+ " and dt.tipLinea.codigoTiplinea = "
				+ codTLinea
				+ " and dt.detalleorden.detaordenCodigo in "
				+ "( select deto.detaordenCodigo from Detalleorden deto where deto.ordenprod.ordenprodCodigo= "
				+ codOrden + ")";

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
	public Object getSumByProTip(Integer codOrden, Integer codPro,
			Integer codTLinea) {

		Object listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select SUM(dtt.detalleorden.cantidad) from Distribdetalle "
				+ " dtt where dtt.detalleorden.detaordenCodigo in "
				+ " ( select dto.detaordenCodigo from Detalleorden dto "
				+ " where dto.ordenprod.ordenprodCodigo = "
				+ codOrden
				+ ") and "
				+ " dtt.proceso.proCodigo = "
				+ codPro
				+ "and dtt.tipLinea.codigoTiplinea = " + codTLinea;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETSUMBYPROTIP: " + e.toString());
		}
		return listado;
	}

}
