package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.TipLinea;
import com.project.utils.HibernateUtil;

public class TipoLineaDaoImpl implements TipoLineaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipLinea> findAll() {
		List<TipLinea> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM TipLinea ORDER BY codigoTiplinea";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL TIPO DE LINEA: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(TipLinea tipoLinea) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(tipoLinea);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE TIPO DE LINEA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(TipLinea tipoLinea) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			TipLinea tipoLineadb = (TipLinea) sesion.load(TipLinea.class,
					tipoLinea.getCodigoTiplinea());
			// Parametros a cambiar
			tipoLineadb.setTipolinea(tipoLinea.getTipolinea());
			tipoLineadb.setDesctiplinea(tipoLinea.getDesctiplinea());
			// fin de parametros
			sesion.update(tipoLineadb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR UPDATE TIPO LINEA: "
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
			TipLinea tipoLineadb = (TipLinea) sesion.load(TipLinea.class, id);
			sesion.delete(tipoLineadb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE TIPO DE LINEA: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipLinea> findbyProceso(Integer codPro) {
		List<TipLinea> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from TipLinea tp where tp.codigoTiplinea in (select lp.tipLinea.codigoTiplinea from Lineasprod lp where lp.proceso.proCodigo = "
				+ codPro + ")";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL TIPO DE LINEA: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipLinea> findByProcesoByModelo(String codPro, String codMod) {
		List<TipLinea> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from TipLinea tp where tp.codigoTiplinea in ( select conf.tipLinea.codigoTiplinea from Confproceso conf where conf.modelo.modNombre = '"
				+ codMod
				+ "' and conf.proceso1.tipoProceso.tprNombre = '"
				+ codPro + "' )";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL TIPO DE LINEA: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipLinea> findTpLineaByDistrib(Integer codOrden) {
		List<TipLinea> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from TipLinea tpl where tpl.codigoTiplinea in "
				+ "( select dt.tipLinea.codigoTiplinea "
				+ "from Distribdetalle dt where dt.detalleorden.detaordenCodigo "
				+ "in ( select dto.detaordenCodigo from Detalleorden "
				+ "dto where dto.ordenprod.ordenprodCodigo = " + codOrden
				+ " ))";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL TIPO DE LINEA: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public Integer findByNombre(String nomTpLinea) {
		Integer listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "SELECT tp.codigoTiplinea FROM TipLinea tp WHERE tp.tipolinea = '"
				+ nomTpLinea + "'";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = (Integer) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL FINDBYNOMBRE: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public String findByModAndPro(Integer codMod, Integer codPro) {
		String listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select  tpl.tipolinea from TipLinea tpl where tpl.codigoTiplinea in "
				+ " ( select dtt.tipLinea.codigoTiplinea "
				+ " from Distribdetalle dtt where dtt.detalleorden.modelo.modCodigo = "
				+ codMod + " and dtt.proceso.proCodigo = " + codPro + ")";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = (String) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL TIPO DE LINEA: "
					+ e.toString());
		}
		return listado;
	}
}
