package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.project.entities.TTalla;
import com.project.entities.TTallaPK;
import com.project.entities.Talla;
import com.project.entities.Troquele;
import com.project.utils.HibernateUtil;

public class TtallasDaoImpl implements TtallasDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TTalla> findAll() {
		List<TTalla> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM TTalla";
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL TTALLAS: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Troquele> selectItems() {
		List<Troquele> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Troquele";
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
	public List<Talla> excludeByTalla(Integer trq) {
		List<Talla> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Talla ta WHERE ta.talCodigo NOT IN (SELECT t.talCodigo FROM Talla t INNER JOIN t.TTallas AS tt WHERE tt.id.trqCodigo = '"
				+ trq + "')";
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRR exludeByTalla: " + e.toString());
		}
		return listado;
	}

	// DMLS
	@Override
	public boolean create(TTalla ttalla) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(ttalla);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE TTALLA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(TTallaPK tTallaPK, TTalla ttallla) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			tTallaPK.getTalCodigo();
			tTallaPK.getTrqCodigo();
			TTalla ttallasdb = (TTalla) sesion.load(TTalla.class, tTallaPK);
			// Parametros a cambiar
			ttallasdb.setCantidad(ttallla.getCantidad());
			// fin de parametros
			sesion.update(ttallasdb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE TTALLA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean delete(TTallaPK tTallaPK, TTalla ttallla) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Query query = sesion
					.createQuery("delete from TTalla  where tal_codigo = :id1 and trq_codigo = :id2");
			query.setParameter("id1", tTallaPK.getTalCodigo());
			query.setParameter("id2", tTallaPK.getTrqCodigo());
			@SuppressWarnings("unused")
			int result = query.executeUpdate();
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE TTALLA: "
					+ e.getMessage().toString());
		}
		return flag;
	}
}
