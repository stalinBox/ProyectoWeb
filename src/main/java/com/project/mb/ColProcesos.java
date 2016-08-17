package com.project.mb;

public class ColProcesos {
	// VARIABLES
	private Integer modelo;
	private Integer tipProceso;
	private Integer proPadre;
	private Integer capacidad;
	private float duracion;
	private String descripcion;
	private String proAutomatico;
	private float tBase;
	private float tMaq;
	private float tMano;
	private float tStandar;
	private float CostoManoObra;
	private float CostoManoReal;

	// SETTERS AND GETTERS
	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public Integer getTipProceso() {
		return tipProceso;
	}

	public void setTipProceso(Integer tipProceso) {
		this.tipProceso = tipProceso;
	}

	public Integer getProPadre() {
		return proPadre;
	}

	public void setProPadre(Integer proPadre) {
		this.proPadre = proPadre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getProAutomatico() {
		return proAutomatico;
	}

	public void setProAutomatico(String proAutomatico) {
		this.proAutomatico = proAutomatico;
	}

	public float gettBase() {
		return tBase;
	}

	public void settBase(float tBase) {
		this.tBase = tBase;
	}

	public float gettMaq() {
		return tMaq;
	}

	public void settMaq(float tMaq) {
		this.tMaq = tMaq;
	}

	public float gettMano() {
		return tMano;
	}

	public void settMano(float tMano) {
		this.tMano = tMano;
	}

	public float gettStandar() {
		return tStandar;
	}

	public void settStandar(float tStandar) {
		this.tStandar = tStandar;
	}

	public float getCostoManoObra() {
		return CostoManoObra;
	}

	public void setCostoManoObra(float costoManoObra) {
		CostoManoObra = costoManoObra;
	}

	public float getCostoManoReal() {
		return CostoManoReal;
	}

	public void setCostoManoReal(float costoManoReal) {
		CostoManoReal = costoManoReal;
	}

}
