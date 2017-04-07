package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Modelo;
import com.project.utils.HibernateUtil;

public class ModelosDaoImpl implements ModelosDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Modelo> findAll() {
		List<Modelo> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Modelo order by modNombre";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLMODELOS: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Modelo modelo) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(modelo);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE MODELO CALZADO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Modelo modelo) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Modelo modelodb = (Modelo) sesion.load(Modelo.class,
					modelo.getModCodigo());
			// Parametros a cambiar
			modelodb.setModNombre(modelo.getModNombre());
			modelodb.setModPiezas(modelo.getModPiezas());
			// fin de parametros
			sesion.update(modelodb);
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
			Modelo modelo = (Modelo) sesion.load(Modelo.class, id);
			sesion.delete(modelo);
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
	public List<Modelo> findByDistrib(Integer codOrden) {
		// TODO Auto-generated method stub
		List<Modelo> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Modelo m where m.modCodigo in ( select dt.modelo.modCodigo from Detalleorden dt where dt.ordenprod.ordenprodCodigo = "
				+ codOrden + ")";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLMODELOS: " + e.toString());
		}
		return listado;
	}
}
