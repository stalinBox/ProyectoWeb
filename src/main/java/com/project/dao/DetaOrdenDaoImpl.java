package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.entities.Detalleorden;
import com.project.entities.Modelo;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Detalleorden> findByOrden(Integer idOrden) {
		List<Detalleorden> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Detalleorden d WHERE d.ordenprod.ordenprodCodigo = "
				+ idOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FIND BY ORDEN DETAORDER: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean deleleByOrden(Integer id) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = sesion.beginTransaction();
		try {
			String hql = "DELETE FROM Detalleorden do WHERE do.ordenprod.ordenprodCodigo = "
					+ id;
			Query query = sesion.createQuery(hql);
			query.executeUpdate();
			transaction.commit();
			flag = true;
		} catch (Throwable t) {
			flag = false;
			transaction.rollback();
			throw t;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getByOrden(Integer codOrden) {
		List<String> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select distinct(deta.modelo.modNombre) from Detalleorden deta where deta.ordenprod.ordenprodCodigo = "
				+ codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETBYORDEN: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getSumByModelo(Integer codOrden, String modelo) {
		List<Integer> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select SUM(deta.cantidad) from Detalleorden deta inner join deta.ordenprod op "
				+ "inner join deta.modelo m where op.ordenprodCodigo =  "
				+ codOrden + "and m.modNombre = '" + modelo + "'";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETSUMBYMODELO: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Detalleorden> findByOrdenByMod(Integer idOrden, Integer codMod) {
		List<Detalleorden> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Detalleorden dtO where dtO.ordenprod.ordenprodCodigo = "
				+ idOrden + " and dtO.modelo.modCodigo = " + codMod;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDBYORDENBYMOD: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modelo> findByOrden2(Integer idOrden) {
		List<Modelo> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "SELECT DISTINCT(d.modelo) FROM Detalleorden d WHERE d.ordenprod.ordenprodCodigo = "
				+ idOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FIND BY ORDEN DETAORDER: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Detalleorden> findByCCO(Integer idOrden, Integer codPro,
			Integer codTlinea) {
		List<Detalleorden> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Detalleorden dt where dt.modelo.modCodigo "
				+ "in( select cnf.modelo.modCodigo from "
				+ "Confproceso cnf where cnf.proceso1.proCodigo = " + codPro
				+ " and cnf.tipLinea.codigoTiplinea = " + codTlinea
				+ ") and dt.ordenprod.ordenprodCodigo = " + idOrden
				+ " and dt.detaordenCodigo not in "
				+ "( select dis.detalleorden.detaordenCodigo "
				+ "from Distribdetalle dis where dis.proceso.proCodigo = "
				+ codPro + " and dis.tipLinea.codigoTiplinea=" + codTlinea
				+ ")";
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

	@Override
	public Object sumByMod(Integer codMod, Integer codOrden) {
		Object listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select sum(dto.cantidad) from Detalleorden dto where  dto.ordenprod.ordenprodCodigo = "
				+ codOrden + " and dto.modelo.modCodigo = " + codMod;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL DETAORDER: " + e.toString());
		}
		return listado;
	}
}
