package com.project.utils;

import java.io.Serializable;
import java.util.Date;

public class MapeoProgramacionDias implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codParam;
	private Double cantHoras;
	private Integer cantPares;
	private Date hInicio;
	private Date hFin;

	public MapeoProgramacionDias(Integer codParam, Double cantHoras,
			Integer cantPares, Date hInicio, Date hFin) {
		this.codParam = codParam;
		this.cantHoras = cantHoras;
		this.cantPares = cantPares;
		this.hInicio = hInicio;
		this.hFin = hFin;
	}

	public Integer getCodParam() {
		return codParam;
	}

	public void setCodParam(Integer codParam) {
		this.codParam = codParam;
	}

	public Double getCantHoras() {
		return cantHoras;
	}

	public void setCantHoras(Double cantHoras) {
		this.cantHoras = cantHoras;
	}

	public Integer getCantPares() {
		return cantPares;
	}

	public void setCantPares(Integer cantPares) {
		this.cantPares = cantPares;
	}

	public Date gethInicio() {
		return hInicio;
	}

	public void sethInicio(Date hInicio) {
		this.hInicio = hInicio;
	}

	public Date gethFin() {
		return hFin;
	}

	public void sethFin(Date hFin) {
		this.hFin = hFin;
	}
}
