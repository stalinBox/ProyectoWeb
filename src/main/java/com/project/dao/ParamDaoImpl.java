package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Parametro;
import com.project.utils.HibernateUtil;

public class ParamDaoImpl implements ParamDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findAll() {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Parametro";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Parametro Parametro) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(Parametro);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE PARAMETRO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Parametro parametro) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Parametro param = (Parametro) sesion.load(Parametro.class, id);
			sesion.delete(param);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE PARAMETROS: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public Parametro findbyCodParam(Integer codParam) {
		Parametro listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Parametro param WHERE param.paramCodigo = "
				+ codParam;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = (Parametro) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> getProcesosbyOrden(Integer codOrden) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Parametro pa  where pa.ordenprod.ordenprodCodigo = "
				+ codOrden + " and pa.paramCodigo in "
				+ " ( select lt.parametro.paramCodigo from Lineasturno lt)";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FIND STANDARES: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> getCpByProcesoOrden(Integer codOrden,
			Integer codProceso) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Parametro pa where pa.ordenprod.ordenprodCodigo =  "
				+ codOrden + " and pa.proceso.proCodigo = " + codProceso;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRRRR GETCPBYPROCESOORDEN: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findByOrdenProd(Integer codOrden) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Parametro ptro WHERE ptro.ordenprod = " + codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETIZACION: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findbyCodParam2(Integer codOrden, Integer codParam) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Parametro ptro WHERE ptro.ordenprod = " + codOrden
				+ " and ptro.paramCodigo = " + codParam;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findByCodLinea(Integer codLinea) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Parametro param where param.paramCodigo in ( select lt.parametro.paramCodigo from Lineasturno lt where lt.lineasprod.lineaproCodigo = "
				+ codLinea + ")";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findByParamInLT(Integer codOrden) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Parametro param where param.paramCodigo in ( select lt.parametro.paramCodigo from Lineasturno lt) and param.ordenprod.ordenprodCodigo = "
				+ codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@Override
	public Integer findByParam(Integer codParam) {
		Integer listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select param.proceso.proCodigo from Parametro param where param.paramCodigo = "
				+ codParam;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = (Integer) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findByParamInLT(Integer codOrden, Integer codPro) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Parametro param where param.paramCodigo in ( select lt.parametro.paramCodigo from Lineasturno lt) and param.ordenprod.ordenprodCodigo = "
				+ codOrden + " and param.proceso.proCodigo = " + codPro;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> findByCodProByCodTpl(Integer codPro, Integer codTpl) {
		List<Parametro> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Parametro param where param.proceso.proCodigo = "
				+ codPro + "and param.tipLinea.codigoTiplinea = " + codTpl;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALLPARAMETRO: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findParamAndLineaProd(Integer codParam) {
		List<?> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Parametro param inner join param.lineasturnos lt where param.paramCodigo =  "
				+ codParam;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERROR FINDPARAMANDLINEAPROD: " + e.toString());
		}
		return listado;
	}

}
