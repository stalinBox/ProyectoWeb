package com.project.utils;

import java.io.Serializable;

public class Distribdetalle implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codProceso;
	private Integer codLinea;
	private Integer codModelo;

	public Distribdetalle(Integer codProceso, Integer codLinea,
			Integer codModelo) {
		this.codProceso = codProceso;
		this.codModelo = codModelo;
		this.codLinea = codLinea;
	}

	public Distribdetalle() {
	}

	public Integer getCodProceso() {
		return codProceso;
	}

	public void setCodProceso(Integer codProceso) {
		this.codProceso = codProceso;
	}

	public Integer getCodLinea() {
		return codLinea;
	}

	public void setCodLinea(Integer codLinea) {
		this.codLinea = codLinea;
	}

	public Integer getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(Integer codModelo) {
		this.codModelo = codModelo;
	}

}
