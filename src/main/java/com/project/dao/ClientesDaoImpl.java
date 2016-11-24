package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Cliente;
import com.project.utils.HibernateUtil;

public class ClientesDaoImpl implements ClientesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		List<Cliente> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Cliente ORDER BY nombrecli";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLTALLA: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Cliente cliente) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(cliente);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE CLIENTE: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Cliente cliente) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Cliente clientedb = (Cliente) sesion.load(Cliente.class,
					cliente.getCodCliente());
			// Parametros a cambiar
			clientedb.setNombrecli(cliente.getNombrecli());
			clientedb.setApellidocli(cliente.getApellidocli());
			clientedb.setTelefono(cliente.getTelefono());
			clientedb.setDireccion(cliente.getDireccion());
			clientedb.setDescripcioncli(cliente.getDescripcioncli());
			// fin de parametros
			sesion.update(clientedb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE CLIENTE: "
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
			Cliente cliente = (Cliente) sesion.load(Cliente.class, id);
			sesion.delete(cliente);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE CLIENTE: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
