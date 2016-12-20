package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Lineasprod;
import com.project.entities.TipoProceso;
import com.project.utils.HibernateUtil;

public class LineasProdDaoImpl implements LineasProdDao {

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
			// lienaPdb.setNummaq(lineaP.getNummaq());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasprod> selectedByMontaje(Lineasprod lineasLP) {
		List<Lineasprod> entities = null;

		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasprod WHERE tipoProceso.tprCodigo="
		/* + lineasLP.getTipoProceso().getTprCodigo() */+ " AND lineaaut= '"
				+ lineasLP.getLineaaut() + "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = sesion.createQuery(sql).list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasprod> selectedByAparado(Lineasprod lineasLP) {
		List<Lineasprod> entities = null;
		boolean f = false;
		TipoProceso tipPro = new TipoProceso();
		tipPro.setTprCodigo(2);
		lineasLP.setLineaaut(f);
		// lineasLP.setTipoProceso(tipPro);
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasprod WHERE tipoProceso.tprCodigo="
		/* + lineasLP.getTipoProceso().getTprCodigo() */+ " AND lineaaut= '"
				+ lineasLP.getLineaaut() + "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = sesion.createQuery(sql).list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasprod> selectedByTroquelado(Lineasprod lineasLP) {
		List<Lineasprod> entities = null;
		boolean f = false;
		TipoProceso tipPro = new TipoProceso();
		tipPro.setTprCodigo(3);
		lineasLP.setLineaaut(f);
		// lineasLP.setTipoProceso(tipPro);
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasprod WHERE tipoProceso.tprCodigo="
		/* + lineasLP.getTipoProceso().getTprCodigo() */+ " AND lineaaut= '"
				+ lineasLP.getLineaaut() + "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = sesion.createQuery(sql).list();
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

}
