package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Programdia;
import com.project.utils.HibernateUtil;

public class ProgramacionDiasDaoImpl implements ProgramacionDiasDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Programdia> findAll() {
		List<Programdia> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Programdia ORDER BY progdiasCodigo";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL PROGRAMACION DIASD: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Programdia programDia) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(programDia);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE PROGRAMACION DIAS: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Programdia programDia) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Programdia programacionDiasdb = (Programdia) sesion.load(
					Programdia.class, programDia.getProgdiasCodigo());

			// Parametros a cambiar
			// DATOS A CAMBIAR ***********
			// fin de parametros
			sesion.update(programacionDiasdb);
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
			Programdia progdias = (Programdia) sesion
					.load(Programdia.class, id);
			sesion.delete(progdias);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE PROGRAMACION DIAS: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByProceso(Integer proCod) {
		List<Object[]> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Ordenprod op INNER JOIN op.parametros as pa "
				+ "INNER JOIN pa.programdias as pr "
				+ "WHERE pa.proceso.proCodigo = " + proCod;

		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDBYPROCESO PROGRAMDIAS: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Programdia> getOrderDates(Integer codOrden, Integer codProceso) {
		List<Programdia> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Programdia pg "
				+ " WHERE pg.parametro.paramCodigo = "
				+ " ( SELECT pr.paramCodigo FROM Parametro pr "
				+ " WHERE pr.ordenprod.ordenprodCodigo = " + codOrden
				+ " AND pr.paramCodigo= " + codProceso + ") "
				+ " ORDER BY pg ASC";

		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETORDERDATES: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Programdia> findByCodProgram(Integer codProgram) {
		List<Programdia> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Programdia pg where pg.progdiasCodigo = "
				+ codProgram;

		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETORDERDATES: " + e.toString());
		}
		return listado;
	}
}
