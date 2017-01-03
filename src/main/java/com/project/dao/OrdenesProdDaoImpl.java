package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.project.entities.Ordenprod;
import com.project.utils.HibernateUtil;

public class OrdenesProdDaoImpl implements OrdenesProdDao {

	@Override
	public boolean create(Ordenprod ordenProd) {

		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();

			String hql = "INSERT INTO ORDENPROD(ORDENPROD_CODIGO,USER_ID_SOLI,USER_ID_RESP,LUGAR_CODIGO_DEST,F_ACTUAL,F_ESTIM,F_FINAL) "
					+ "VALUES(DEFAULT,:val1,:val2,:val3,:val4,:val5,:val6)";

			Query query = sesion.createSQLQuery(hql);

			query.setParameter("val1", ordenProd.getCliente().getCodCliente(),
					StandardBasicTypes.INTEGER);

			query.setParameter("val2", ordenProd.getUsuario().getUserId(),
					StandardBasicTypes.INTEGER);

			query.setParameter("val3", ordenProd.getLugare().getLugarCodigo(),
					StandardBasicTypes.INTEGER);

			query.setParameter("val4", ordenProd.getFActual(),
					StandardBasicTypes.DATE);
			query.setParameter("val5", ordenProd.getFEstim(),
					StandardBasicTypes.DATE);
			query.setParameter("val6", ordenProd.getFFinal(),
					StandardBasicTypes.DATE);

			query.executeUpdate();
			sesion.getTransaction().commit();

			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE ORDEN DE PRODUCCION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Ordenprod ordenProd) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Ordenprod ordenproddb = (Ordenprod) sesion.load(Ordenprod.class,
					ordenProd.getOrdenprodCodigo());

			// Parametros a cambiar
			ordenproddb.setCliente(ordenProd.getCliente());
			// poner los demas datos a actualizar ***********
			// fin de parametros

			sesion.update(ordenproddb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE ORDEN DE PRODUCCION: "
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
			Ordenprod ordenprod = (Ordenprod) sesion.load(Ordenprod.class, id);
			sesion.delete(ordenprod);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE ORDEN DE PRODUCCION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ordenprod> findAll() {
		List<Ordenprod> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Ordenprod order by ordenprodCodigo";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FIND ALL ORDEN DE PRODUCCION: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public Ordenprod LastRespOrden() {
		Ordenprod entities = null;
		Integer ent = 0;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select max(op.ordenprodCodigo) from Ordenprod op";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			ent = (Integer) sesion.createQuery(sql).uniqueResult();
			entities = this.findByLast(ent);
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRR LASTRESORDEN: " + e.toString());
			throw e;
		}
		return entities;
	}

	@Override
	public Ordenprod findByLast(Integer id) {
		Ordenprod entities = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Ordenprod op where op.ordenprodCodigo = " + id;
		System.out.println(sql);

		try {
			entities = (Ordenprod) sesion.createQuery(sql).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entities;
	}
}
