package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Ordenprod;
import com.project.utils.HibernateUtil;

public class OrdenesProdDaoImpl implements OrdenesProdDao {

	@Override
	public boolean create(Ordenprod ordenProd) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(ordenProd);
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
}
