package com.project.utils;

import java.io.Serializable;

public class ItemsParams implements Serializable {

	private static final long serialVersionUID = 1L;
	private static String proceso;
	private static String TipoLinea;
	private static Integer cpPonderado;

	public ItemsParams(String proceso, String TipoLinea, Integer cpPonderado) {
		ItemsParams.proceso = proceso;
		ItemsParams.TipoLinea = TipoLinea;
		ItemsParams.cpPonderado = cpPonderado;
	}

	public ItemsParams() {
	}

	public static String getProceso() {
		return proceso;
	}

	public static void setProceso(String proceso) {
		ItemsParams.proceso = proceso;
	}

	public static String getTipoLinea() {
		return TipoLinea;
	}

	public static void setTipoLinea(String tipoLinea) {
		TipoLinea = tipoLinea;
	}

	public static Integer getCpPonderado() {
		return cpPonderado;
	}

	public static void setCpPonderado(Integer cpPonderado) {
		ItemsParams.cpPonderado = cpPonderado;
	}

}
