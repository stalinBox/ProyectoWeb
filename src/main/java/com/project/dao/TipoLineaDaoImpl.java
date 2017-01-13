package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.TipLinea;
import com.project.utils.HibernateUtil;

public class TipoLineaDaoImpl implements TipoLineaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipLinea> findAll() {
		List<TipLinea> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM TipLinea ORDER BY tipolinea";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL TIPO DE LINEA: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(TipLinea tipoLinea) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(tipoLinea);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE TIPO DE LINEA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(TipLinea tipoLinea) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			TipLinea tipoLineadb = (TipLinea) sesion.load(TipLinea.class,
					tipoLinea.getCodigoTiplinea());
			// Parametros a cambiar
			tipoLineadb.setTipolinea(tipoLinea.getTipolinea());
			tipoLineadb.setDesctiplinea(tipoLinea.getDesctiplinea());
			// fin de parametros
			sesion.update(tipoLineadb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE TIPO LINEA: "
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
			TipLinea tipoLineadb = (TipLinea) sesion.load(TipLinea.class, id);
			sesion.delete(tipoLineadb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE TIPO DE LINEA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}