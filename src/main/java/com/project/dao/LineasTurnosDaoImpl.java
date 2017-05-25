package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.project.entities.Lineasturno;
import com.project.utils.HibernateUtil;

public class LineasTurnosDaoImpl implements LineasTurnosDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasturno> findAll() {
		List<Lineasturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasturno";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL LINEASTURNOS: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Lineasturno lienasTurnos) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(lienasTurnos);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE LINEAS TURNOS: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Lineasturno lienasTurnos) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Lineasturno lineasTurnosdb = (Lineasturno) sesion.load(
					Lineasturno.class, lienasTurnos.getLtcodigo());
			// Parametros a cambiar
			// parametros a cambiar ****************
			// fin de parametros
			sesion.update(lineasTurnosdb);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRRRR UPDATE: " + e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Lineasturno lineasTurnos = (Lineasturno) sesion.load(
					Lineasturno.class, id);
			sesion.delete(lineasTurnos);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out
					.println("ERRORRRRR DELETE: " + e.getMessage().toString());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> findByOrdenProd(Integer codOrden, Integer codPro) {
		ArrayList<Integer> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select count(lt) from Lineasturno lt  "
				+ " inner join lt.parametro as pa  "
				+ " where pa.ordenprod.ordenprodCodigo = " + codOrden
				+ " and pa.proceso.proCodigo = " + codPro
				+ " group by lt.lineasprod.lineaproCodigo";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = (ArrayList<Integer>) sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDBYORDENPROD: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasturno> findByOrden(Integer codOrden) {
		List<Lineasturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasturno lt where lt.parametro.paramCodigo in "
				+ "(select p.paramCodigo from Parametro p where p.ordenprod = "
				+ codOrden + ")";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDBYORDEN: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> GetCodProcesoByOrden(Integer codOrden) {
		List<Integer> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select distinct(lt.lineasprod.lineaproCodigo) "
				+ "from Lineasturno lt "
				+ "where lt.parametro.paramCodigo in "
				+ "(select pa.paramCodigo from Parametro pa where pa.ordenprod.ordenprodCodigo = "
				+ codOrden + ")";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETCODPROCESOBYORDEN: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public Object GetArrayByProcesoByOrden(Integer codOrden, Integer codProceso) {
		Object listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String hql = "select count(lt.lineasprod.lineaproCodigo) "
				+ " from Lineasturno lt inner join lt.parametro as pa "
				+ " where pa.ordenprod.ordenprodCodigo = " + codOrden
				+ " and lt.lineasprod.lineaproCodigo = " + codProceso;
		System.out.println(hql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(hql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETARRAYBYPROCESOBYORDEN: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getLineasByProceso(Integer codPro, Integer codOrden) {
		List<Integer> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select distinct (lt.lineasprod.lineaproCodigo) from Lineasturno lt "
				+ "inner join lt.parametro pa where pa.proceso.proCodigo = "
				+ codPro + "and pa.ordenprod.ordenprodCodigo = " + codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETLINEASBYPROCESO: " + e.toString());
		}
		return listado;
	}

	@Override
	public Object getCountTurnosByLineas(Integer lineaCod, Integer codOrden) {
		Object listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select count(lt.lineasprod.lineaproCodigo)from Lineasturno lt "
				+ "inner join lt.parametro pa where lt.lineasprod.lineaproCodigo = "
				+ lineaCod + " and pa.ordenprod.ordenprodCodigo = " + codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETCOUNTTURNOSBYLINEAS: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasturno> findByOrden(Integer codOrden, Integer codProceso) {
		List<Lineasturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasturno lt where lt.parametro.paramCodigo = "
				+ " (select p.paramCodigo from Parametro p where p.ordenprod = "
				+ codOrden + " and p.paramCodigo = " + codProceso + " )";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDBYORDEN: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasturno> findByDETALLE(Integer codOrden) {
		List<Lineasturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Lineasturno lt WHERE lt.parametro.ordenprod.ordenprodCodigo = "
				+ codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL LINEASTURNOS: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasturno> findByParam(Integer codParam) {
		List<Lineasturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Lineasturno lt where lt.parametro.paramCodigo = "
				+ codParam;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL LINEASTURNOS: "
					+ e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lineasturno> findByOrdenBYPROCESOSOP(Integer codOrden) {
		List<Lineasturno> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Lineasturno lt where lt.parametro.ordenprod.ordenprodCodigo = "
				+ codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL LINEASTURNOS: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public Object getCountTurnosByLineas(Integer lineaCod, Integer codOrden,
			Integer codPro) {
		Object listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "select count(lt.lineasprod.lineaproCodigo)from Lineasturno lt "
				+ "inner join lt.parametro pa where lt.lineasprod.lineaproCodigo = "
				+ lineaCod
				+ " and pa.ordenprod.ordenprodCodigo = "
				+ codOrden
				+ " and pa.proceso.proCodigo = " + codPro;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR GETCOUNTTURNOSBYLINEAS: "
					+ e.toString());
		}
		return listado;
	}
}
