package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.ModTrqTal;
import com.project.entities.Modelo;
import com.project.entities.TTalla;
import com.project.entities.Troquele;
import com.project.utils.HibernateUtil;

public class MttDaoImpl implements MttDao {

	@Override
	public List<ModTrqTal> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Troquele> selectItemsTroqueles() {
		List<Troquele> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Troquele";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRRRR SELECTITEMS TROQUEL: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modelo> selectItemsModelos() {
		List<Modelo> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Modelo";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRRRR SELECTITEMS MODELOS: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TTalla> selectItemsTallas(Integer codTrq) {
		List<TTalla> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from TTalla tt where tt.cantidad !=0 and tt.troquele.trqCodigo = "
				+ codTrq + "order by tt.talla.talNumero";
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
	public boolean create(ModTrqTal mtt) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(mtt);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE MTT: "
					+ e.getMessage().toString());
		}
		return flag;
	}

}
