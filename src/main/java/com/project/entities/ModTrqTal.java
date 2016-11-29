package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mod_trq_tal database table.
 * 
 */
@Entity
@Table(name="mod_trq_tal")
@NamedQuery(name="ModTrqTal.findAll", query="SELECT m FROM ModTrqTal m")
public class ModTrqTal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mtt_codigo")
	private Integer mttCodigo;

	private String disponibilidad;

	//bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name="mod_codigo", insertable = false, updatable = false)
	private Modelo modelo;

	//bi-directional many-to-one association to Talla
	@ManyToOne
	@JoinColumn(name="tal_codigo", insertable = false, updatable = false)
	private Talla talla;

	//bi-directional many-to-one association to Troquele
	@ManyToOne
	@JoinColumn(name="trq_codigo", insertable = false, updatable = false)
	private Troquele troquele;

	public ModTrqTal() {
	}

	public Integer getMttCodigo() {
		return this.mttCodigo;
	}

	public void setMttCodigo(Integer mttCodigo) {
		this.mttCodigo = mttCodigo;
	}

	public String getDisponibilidad() {
		return this.disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
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