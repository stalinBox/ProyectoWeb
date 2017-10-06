package com.project.reportsBean;

import java.io.Serializable;

public class CapacidadesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codProceso;
	private String tpLinea;
	private Integer standar;

	public CapacidadesEntity(String codProceso, String tpLinea, Integer standar) {
		this.codProceso = codProceso;
		this.tpLinea = tpLinea;
		this.standar = standar;
	}

	public String getCodProceso() {
		return codProceso;
	}

	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}

	public String getTpLinea() {
		return tpLinea;
	}

	public void setTpLinea(String tpLinea) {
		this.tpLinea = tpLinea;
	}

	public Integer getStandar() {
		return standar;
	}

	public void setStandar(Integer standar) {
		this.standar = standar;
	}

}
