package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Empresa;
import com.project.utils.HibernateUtil;

public class EmpresaDaoImpl implements EmpresaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> findAll() {
		List<Empresa> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Empresa";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLEMPRESA: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Empresa empresa) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(empresa);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE EMPRESA DE CALZADO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Empresa empresa) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Empresa empresadb = (Empresa) sesion.load(Empresa.class,
					empresa.getEmpCodigo());
			// Parametros a cambiar
			empresadb.setEmpNom(empresa.getEmpNom());
			// fin de parametros
			sesion.update(empresadb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE EMPRESA: "
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
			Empresa empresa = (Empresa) sesion.load(Empresa.class, id);
			sesion.delete(empresa);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE EMPRESA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
