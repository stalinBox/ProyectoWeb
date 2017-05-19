package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.project.entities.Confproceso;
import com.project.utils.HibernateUtil;

public class SettingTimesDaoImpl implements SettingTimesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Confproceso> findAll() {
		List<Confproceso> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "FROM Confproceso order by confproCodigo";
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR FINDALL CONFTIMES PROCESO: "
					+ e.toString());
		}
		return listado;
	}

	@Override
	public boolean create(Confproceso confPro) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			String sql = "INSERT INTO CONFPROCESO(CONFPRO_CODIGO,MOD_CODIGO,PRO_CODIGO,SUB_PRO,TIEMPO_TS) "
					+ "VALUES(DEFAULT,:val1,:val2,:val3,:val4)";

			Query query = sesion.createSQLQuery(sql);
			query.setParameter("val1", confPro.getModelo().getModCodigo());
			query.setParameter("val2", confPro.getProceso1().getProCodigo(),
					StandardBasicTypes.INTEGER);
			query.setParameter("val3", confPro.getProceso2().getProCodigo());
			query.setParameter("val4", confPro.getTiempoTs());
			query.executeUpdate();

			// sesion.save(confPro);

			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR CREATE CONF PROCESO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public boolean update(Confproceso confPro) {
		boolean flag;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			sesion.beginTransaction();
			Confproceso sttdb = (Confproceso) sesion.load(Confproceso.class,
					confPro.getConfproCodigo());
			// Parametros a cambiar
			sttdb.setModelo(confPro.getModelo());
			sttdb.setTipLinea(confPro.getTipLinea());
			sttdb.setProceso1(confPro.getProceso1());
			sttdb.setProceso2(confPro.getProceso2());
			sttdb.setTiempoTs(confPro.getTiempoTs());
			// fin de parametros
			sesion.update(sttdb);
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
			Confproceso confProceso = (Confproceso) sesion.load(
					Confproceso.class, id);
			sesion.delete(confProceso);
			sesion.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR DELETE CONF PROCESO: "
					+ e.getMessage().toString());
		}
		return flag;
	}

	@Override
	public double findByTs(String mNombre, String tNombre, Integer nDia) {
		double a = 0;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String hql = "SELECT (ROUND((1/MAX(c.tiempoTs))*8*60))*"
				+ " FROM Confproceso c"
				+ " WHERE c.modelo.modCodigo = (SELECT m.modCodigo"
				+ " FROM Modelo m WHERE  m.modNombre = '" + mNombre + "')"
				+ " AND c.proceso1.proCodigo =(SELECT p.proCodigo"
				+ " FROM Proceso p"
				+ " WHERE p.tipoProceso.tprCodigo =(SELECT tp.tprCodigo"
				+ " FROM TipoProceso tp" + " WHERE tp.tprNombre = '" + tNombre
				+ "'))";
		try {
			sesion.beginTransaction();
			a = (Double) (sesion.createQuery(hql).uniqueResult());
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("Error en la consulta de tiempos: "
					+ e.toString());
			throw e;
		}
		return a;
	}

	@Override
	public Double findByTs(String codMod, Integer codPro, Integer codTLinea,
			Double nDia) {
		Double a = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String hql = "SELECT (ROUND((1/MAX(c.tiempoTs))*8*60))* " + nDia
				+ " FROM Confproceso c where c.modelo.modNombre = '" + codMod
				+ "' and c.proceso1.proCodigo = " + codPro
				+ " and c.tipLinea.codigoTiplinea = " + codTLinea;
		try {
			sesion.beginTransaction();
			a = (Double) (sesion.createQuery(hql).uniqueResult());
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("Error en la consulta de tiempos: "
					+ e.toString());
			throw e;
		}
		return a;
	}
}
