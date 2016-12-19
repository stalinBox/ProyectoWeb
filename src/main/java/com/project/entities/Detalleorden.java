package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the detalleorden database table.
 * 
 */
@Entity
@NamedQuery(name = "Detalleorden.findAll", query = "SELECT d FROM Detalleorden d")
public class Detalleorden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detaorden_codigo")
	private Integer detaordenCodigo;

	private Integer cantidad;

	// bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name = "mod_codigo", insertable = false, updatable = false)
	private Modelo modelo;

	// bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name = "ordenprod_codigo", insertable = false, updatable = false)
	private Ordenprod ordenprod;

	// bi-directional many-to-one association to Talla
	@ManyToOne
	@JoinColumn(name = "tal_codigo", insertable = false, updatable = false)
	private Talla talla;

	public Detalleorden() {
	}

	public Integer getDetaordenCodigo() {
		return this.detaordenCodigo;
	}

	public void setDetaordenCodigo(Integer detaordenCodigo) {
		this.detaordenCodigo = detaordenCodigo;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Ordenprod getOrdenprod() {
		return this.ordenprod;
	}

	public void setOrdenprod(Ordenprod ordenprod) {
		this.ordenprod = ordenprod;
	}

	public Talla getTalla() {
		return this.talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

}