package com.project.utils;

import java.io.Serializable;

public class ItemsParams implements Serializable {
	private static final long serialVersionUID = 1L;

	private String proceso;
	private String TipoLinea;
	private Integer cpPonderado;

	public ItemsParams(String proceso, String TipoLinea, Integer cpPonderado) {
		this.proceso = proceso;
		this.TipoLinea = TipoLinea;
		this.cpPonderado = cpPonderado;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getTipoLinea() {
		return TipoLinea;
	}

	public void setTipoLinea(String tipoLinea) {
		TipoLinea = tipoLinea;
	}

	public Integer getCpPonderado() {
		return cpPonderado;
	}

	public void setCpPonderado(Integer cpPonderado) {
		this.cpPonderado = cpPonderado;
	}

}
