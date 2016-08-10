package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "t_tallas")
@NamedQuery(name = "TTalla.findAll", query = "SELECT t FROM TTalla t")
public class TTalla implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TTallaPK id;

	private Integer cantidad;

	// bi-directional many-to-one association to Talla
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "tal_codigo", insertable = false, updatable = false)
	private Talla talla;

	// bi-directional many-to-one association to Troquele
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "trq_codigo", insertable = false, updatable = false)
	private Troquele troquele;

	public TTalla() {
	}

	public TTallaPK getId() {
		return this.id;
	}

	public void setId(TTallaPK id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Talla getTalla() {
		return this.talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public Troquele getTroquele() {
		return this.troquele;
	}

	public void setTroquele(Troquele troquele) {
		this.troquele = troquele;
	}

}