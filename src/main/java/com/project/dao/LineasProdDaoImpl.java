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
		String sql = "FROM Lineasprod lp order by lp.proceso";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLLINEA: " + e.toString());
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
			System.out.println("ERRORRRRR CREATE LINEA CALZADO: "
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
			Lineasprod lineadb = (Lineasprod) sesion.load(Lineasprod.class,
					lineaP.getLineaproCodigo());
			// Parametros a cambiar
			// lineadb.setModNombre(modelo.getModNombre());
			// lineadb.setModPiezas(modelo.getModPiezas());
			// fin de parametros
			sesion.update(lineadb);
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
			Lineasprod linea = (Lineasprod) sesion.load(Lineasprod.class, id);
			sesion.delete(linea);
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

}
