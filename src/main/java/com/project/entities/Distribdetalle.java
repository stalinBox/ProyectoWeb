package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the distribdetalle database table.
 * 
 */
@Entity
@NamedQuery(name = "Distribdetalle.findAll", query = "SELECT d FROM Distribdetalle d")
public class Distribdetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "distrib_codigo")
	private Integer distribCodigo;

	// bi-directional many-to-one association to Detalleorden
	@ManyToOne
	@JoinColumn(name = "detaorden_codigo")
	private Detalleorden detalleorden;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_codigo")
	private Proceso proceso;

	// bi-directional many-to-one association to TipLinea
	@ManyToOne
	@JoinColumn(name = "codigo_tiplinea", insertable = false, updatable = false)
	private TipLinea tipLinea1;

	// bi-directional many-to-one association to TipLinea
	@ManyToOne
	@JoinColumn(name = "codigo_tiplinea", insertable = false, updatable = false)
	private TipLinea tipLinea2;

	// bi-directional many-to-one association to TipLinea
	@ManyToOne
	@JoinColumn(name = "codigo_tiplinea", insertable = false, updatable = false)
	private TipLinea tipLinea3;

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

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public TipLinea getTipLinea1() {
		return this.tipLinea1;
	}

	public void setTipLinea1(TipLinea tipLinea1) {
		this.tipLinea1 = tipLinea1;
	}

	public TipLinea getTipLinea2() {
		return this.tipLinea2;
	}

	public void setTipLinea2(TipLinea tipLinea2) {
		this.tipLinea2 = tipLinea2;
	}

	public TipLinea getTipLinea3() {
		return this.tipLinea3;
	}

	public void setTipLinea3(TipLinea tipLinea3) {
		this.tipLinea3 = tipLinea3;
	}

}