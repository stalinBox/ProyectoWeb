package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.project.entities.Proceso;
import com.project.utils.HibernateUtil;

public class ProcesoDaoImpl implements ProcesoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Proceso> findAll() {
		List<Proceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Proceso order by proCodigo ";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL PROCESOS: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Proceso proceso) {
		boolean flag;

		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "INSERT INTO PROCESOS(PRO_CODIGO, TPR_CODIGO, PRO_PADRE,PRO_ACTIVO,PRO_DESCRIP) "
					+ "VALUES(DEFAULT,:val1,:val2,:val3,:val4)";
			Query query = sesion.createSQLQuery(hql);
			query.setParameter("val1", proceso.getTipoProceso().getTprCodigo());
			query.setParameter("val2", proceso.getProceso().getProCodigo(),
					StandardBasicTypes.INTEGER);
			query.setParameter("val3", proceso.getProActivo());
			query.setParameter("val4", proceso.getProDescrip());
			query.executeUpdate();
			sesion.getTransaction().commit();

			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE PROCESO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Proceso proceso) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Proceso procesodb = (Proceso) sesion.load(Proceso.class,
					proceso.getProCodigo());
			// Parametros a cambiar
			// procesodb.
			// modelodb.setModPiezas(modelo.getModPiezas());
			// fin de parametros
			sesion.update(procesodb);
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
			Proceso proceso = (Proceso) sesion.load(Proceso.class, id);
			sesion.delete(proceso);
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
	public List<Proceso> findPadre() {
		List<Proceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Proceso p WHERE p.proceso.proCodigo is null";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL PROCESOS: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proceso> findSubProcesos() {
		List<Proceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Proceso p WHERE p.proceso.proCodigo is not null";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL PROCESOS: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proceso> findByProceso(Integer a) {
		List<Proceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Proceso p where p.proceso = '" + a + "'";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL PROCESOS: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proceso> findByProcesoBySetting(Integer codPro, Integer codMod) {
		List<Proceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String hql = "from Proceso p where p.proceso = "
				+ codPro
				+ " and p.proCodigo not in (select conf.proceso2.proCodigo from Confproceso conf where conf.modelo.modCodigo = "
				+ codMod + ")";
		System.out.println(hql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(hql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL PROCESOS: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proceso> findByProcesoPadreByOrden(Integer codOrden) {
		List<Proceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String hql = "FROM Proceso pro INNER JOIN pro.parametros as pa "
				+ "INNER JOIN pa.lineasturnos as lt "
				+ "WHERE pro.proceso.proCodigo IS NULL "
				+ "AND pa.ordenprod.ordenprodCodigo = " + codOrden;
		System.out.println(hql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(hql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL PROCESOS: " + e.toString());
		}
		return listado;
	}
}
