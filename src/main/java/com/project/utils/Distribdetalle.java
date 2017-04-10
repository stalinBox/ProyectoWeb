package com.project.utils;

import java.io.Serializable;

public class Distribdetalle implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codProceso;
	private String codLinea;
	private String codModelo;

	public Distribdetalle(String codModelo, String codProceso, String codLinea) {
		this.codProceso = codProceso;
		this.codModelo = codModelo;
		this.codLinea = codLinea;
	}

	public Distribdetalle() {
	}

	public String getCodProceso() {
		return codProceso;
	}

	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}

	public String getCodLinea() {
		return codLinea;
	}

	public void setCodLinea(String codLinea) {
		this.codLinea = codLinea;
	}

	public String getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(String codModelo) {
		this.codModelo = codModelo;
	}

}
