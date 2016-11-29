package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Lineasprod;
import com.project.utils.HibernateUtil;

public class LineasProdDaoImpl implements LineasProdDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasprod> findAll() {
		List<Lineasprod> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasprod order by nomlinea";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL LINEAS PRODUCCION: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Lineasprod lineaP) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(lineaP);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE LINEA DE PRODUCCION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Lineasprod lineaP) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Lineasprod lienaPdb = (Lineasprod) sesion.load(Lineasprod.class,
					lineaP.getLineaproCodigo());

			// Parametros a cambiar
			lienaPdb.setNomlinea(lineaP.getNomlinea());
			lienaPdb.setNummaq(lineaP.getNummaq());
			lienaPdb.setLineaaut(lineaP.getLineaaut());
			lienaPdb.setLineaDesc(lineaP.getLineaDesc());
			// fin de parametros

			sesion.update(lienaPdb);
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
			Lineasprod lineaP = (Lineasprod) sesion.load(Lineasprod.class, id);
			sesion.delete(lineaP);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE LINEA DE PRODUCCION: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public Lineasprod selectedByMontaje(Lineasprod lineasLP) {
		Lineasprod entities = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasprod WHERE tipoProceso.tprCodigo="
				+ lineasLP.getTipoProceso().getTprCodigo() + " AND lineaaut= '"
				+ lineasLP.getLineaaut() + "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = (Lineasprod) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRR FINDBYUSUARIO LINEAS DE PRODUCCION BY TIPO DE PROCESO: "
							+ e.toString());
			throw e;
		}
		return entities;
	}

	@Override
	public Lineasprod selectedByAparado(Lineasprod lineasLP) {
		Lineasprod entities = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasprod WHERE tipoProceso.tprCodigo="
				+ lineasLP.getTipoProceso().getTprCodigo() + " AND lineaaut= '"
				+ lineasLP.getLineaaut() + "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = (Lineasprod) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRR FINDBYUSUARIO LINEAS DE PRODUCCION BY TIPO DE PROCESO: "
							+ e.toString());
			throw e;
		}
		return entities;
	}

	@Override
	public Lineasprod selectedByTroquelado(Lineasprod lineasLP) {
		Lineasprod entities = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasprod WHERE tipoProceso.tprCodigo="
				+ lineasLP.getTipoProceso().getTprCodigo() + " AND lineaaut= '"
				+ lineasLP.getLineaaut() + "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = (Lineasprod) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRR FINDBYUSUARIO LINEAS DE PRODUCCION BY TIPO DE PROCESO: "
							+ e.toString());
			throw e;
		}
		return entities;
	}

	@Override
	public List<Lineasprod> findByMontaje() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lineasprod> findByAparado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lineasprod> findByTroquelado() {
		// TODO Auto-generated method stub
		return null;
	}

}
