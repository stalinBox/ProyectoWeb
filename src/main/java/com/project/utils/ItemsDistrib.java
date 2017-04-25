package com.project.utils;

import java.io.Serializable;

public class ItemsDistrib implements Serializable {
	private static final long serialVersionUID = 1L;

	private String modelo;
	private Integer talla;
	private Integer cantidad;

	public ItemsDistrib(String modelo, Integer talla, Integer cantidad) {
		this.modelo = modelo;
		this.talla = talla;
		this.cantidad = cantidad;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getTalla() {
		return talla;
	}

	public void setTalla(Integer talla) {
		this.talla = talla;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}