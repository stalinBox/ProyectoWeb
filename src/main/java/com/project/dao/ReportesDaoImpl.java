package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.entities.Ordenprod;
import com.project.utils.HibernateUtil;

public class ReportesDaoImpl implements ReportesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByCapxOrden(Integer proCod) {
		List<Object[]> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "SELECT op.ordenprod_codigo, cl.nombrecli, us.user_name, m.mod_nombre, "
				+ " t.tal_numero, emp.emp_nombre,emp.emp_direcc,emp.emp_telf, "
				+ " op.f_actual, dto.cantidad, encode(emp.emp_logo,'base64') as emp_logo, "
				+ " encode(lgs.logos,'base64') as logos"
				+ " FROM ordenprod op inner join "
				+ " detalleorden dto on op.ordenprod_codigo = dto.ordenprod_codigo inner join"
				+ " usuario us on us.user_id = op.user_id_soli inner join"
				+ " clientes cl on cl.cod_cliente = op.user_id_soli inner join"
				+ " modelos m on m.mod_codigo = dto.mod_codigo inner join"
				+ " tallas t on t.tal_codigo = dto.tal_codigo, empresa emp, logosfaps lgs"
				+ " WHERE op.ordenprod_codigo = " + proCod;

		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = (List<Object[]>) sesion.createSQLQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR REPORTES CONSULTA: " + e.toString());
		}
		return listado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByCapxOrdenPrueba(Integer proCod) {
		List<Object[]> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Ordenprod op inner join op.detalleordens dto where op.ordenprodCodigo ="
				+ proCod;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Ordenprod> ObtenerTodos(Integer codOrden) {
		List<Ordenprod> listado = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		String sql = "from Ordenprod op inner join op.detalleordens dto where op.ordenprodCodigo =  "
				+ codOrden;
		System.out.println(sql);
		try {
			sesion.beginTransaction();
			listado = sesion.createQuery(sql).list();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			System.out.println("ERRORRRRR OBTENERTODOS: " + e.toString());
		}
		return listado;
	}
}
