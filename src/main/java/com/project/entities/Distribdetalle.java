package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the distribdetalle database table.
 * 
 */
@Entity
@NamedQuery(name="Distribdetalle.findAll", query="SELECT d FROM Distribdetalle d")
public class Distribdetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="distrib_codigo")
	private Integer distribCodigo;

	//bi-directional many-to-one association to Detalleorden
	@ManyToOne
	@JoinColumn(name="detaorden_codigo")
	private Detalleorden detalleorden;

	//bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name="lineapro_codigo")
	private Lineasprod lineasprod;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo")
	private Proceso proceso;

	public Distribdetalle() {
	}

	public Integer getDistribCodigo() {
		return this.distribCodigo;
	}

	public void setDistribCodigo(Integer distribCodigo) {
		this.distribCodigo = distribCodigo;
	}

	public Detalleorden getDetalleorden() {
		return this.detalleorden;
	}

	public void setDetalleorden(Detalleorden detalleorden) {
		this.detalleorden = detalleorden;
	}

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

}