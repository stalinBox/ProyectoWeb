package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.TipoProceso;
import com.project.utils.HibernateUtil;

public class TipprocesoDaoImpl implements TipprocesosDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoProceso> findAll() {
		List<TipoProceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM TipoProceso order by tprNombre";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLTIPOPROCESO: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(TipoProceso tipProceso) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(tipProceso);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE TIPO PROCESO CALZADO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(TipoProceso tipProceso) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			TipoProceso tipoProcesodb = (TipoProceso) sesion.load(
					TipoProceso.class, tipProceso.getTprCodigo());
			// Parametros a cambiar
			tipoProcesodb.setTprNombre(tipProceso.getTprNombre());
			// fin de parametros
			sesion.update(tipoProcesodb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE TIPO PROCESO: "
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
			TipoProceso tipProceso = (TipoProceso) sesion.load(
					TipoProceso.class, id);
			sesion.delete(tipProceso);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE TIPO PROCESOS: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
