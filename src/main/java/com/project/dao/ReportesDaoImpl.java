package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Parametro;
import com.project.utils.HibernateUtil;

public class ReportesDaoImpl implements ReportesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByCapxOrdenRPT(Integer codOrden) {
		List<Object[]> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Ordenprod op inner join op.detalleordens dto , Empresa emp, Logosfap logos where op.ordenprodCodigo = "
				+ codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR OBTENERTODOS: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findParaByCod(Integer codOrden) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Parametro pr where pr.ordenprod.ordenprodCodigo = "
				+ codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDPARABYCOD: " + e.toString());
		}
		return listado;
	}
}
