package com.project.utils;

import java.io.Serializable;

/**
 * @author Stalin
 *
 */
public class ContentParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Integer codOrden;
	private static Integer StandarPonderado;
	private static String proceso;
	private static String TipoLinea;

	public ContentParam(Integer codOrden, String proceso, String TipoLinea,
			Integer StandarPonderado) {

		ContentParam.codOrden = codOrden;
		ContentParam.proceso = proceso;
		ContentParam.TipoLinea = TipoLinea;
		ContentParam.StandarPonderado = StandarPonderado;
	}

	public ContentParam() {

	}

	public static Integer getCodOrden() {
		return codOrden;
	}

	public static void setCodOrden(Integer codOrden) {
		ContentParam.codOrden = codOrden;
	}

	public static Integer getStandarPonderado() {
		return StandarPonderado;
	}

	public static void setStandarPonderado(Integer standarPonderado) {
		StandarPonderado = standarPonderado;
	}

	public static String getProceso() {
		return proceso;
	}

	public static void setProceso(String proceso) {
		ContentParam.proceso = proceso;
	}

	public static String getTipoLinea() {
		return TipoLinea;
	}

	public static void setTipoLinea(String tipoLinea) {
		TipoLinea = tipoLinea;
	}

}
