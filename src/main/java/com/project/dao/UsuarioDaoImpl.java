package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Usuario;
import com.project.utils.HibernateUtil;

public class UsuarioDaoImpl implements UsuarioDao {

	@Override
	public Usuario findByUsuario(Usuario usuario) {
		Usuario entities = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Usuario WHERE user_name='" + usuario.getUserName()
				+ "'";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			entities = (Usuario) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRR FINDBYUSUARIO USER: " + e.toString());
			throw e;
		}
		return entities;
	}

	@Override
	public Usuario login(Usuario usuario) {
		Usuario entities = this.findByUsuario(usuario);
		if (entities != null) {
			if (!usuario.getUserPasswd().equals(entities.getUserPasswd())) {
				entities = null;
			}
		}
		return entities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll() {
		List<Usuario> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Usuario";
		System.out.println(sql);

		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL USER: " + e.toString());
			// throw e;
		}
		return listado;
	}

	// DMLS
	@Override
	public boolean create(Usuario usuario) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(usuario);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE USER: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Usuario usuario) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			sesion.beginTransaction();
			Usuario usuariodb = (Usuario) sesion.load(Usuario.class,
					usuario.getUserId());
			// Parametros a cambiar
			usuariodb.setUserEmail(usuario.getUserEmail());
			usuariodb.setUserName(usuario.getUserName());
			usuariodb.setRol(usuario.getRol());
			usuariodb.setUserState(usuario.getUserState());
			// fin de parametros
			sesion.update(usuariodb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE USER: "
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
			Usuario usuario = (Usuario) sesion.load(Usuario.class, id);
			sesion.delete(usuario);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE USER: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
