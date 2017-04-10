package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the capacidades database table.
 * 
 */
@Entity
@Table(name = "capacidades")
@NamedQuery(name = "Capacidade.findAll", query = "SELECT c FROM Capacidade c")
public class Capacidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cap_codigo")
	private Integer capCodigo;

	private Integer standar;

	// bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name = "ordenprod_codigo")
	private Ordenprod ordenprod;

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

	public Capacidade() {
	}

	public Integer getCapCodigo() {
		return this.capCodigo;
	}

	public void setCapCodigo(Integer capCodigo) {
		this.capCodigo = capCodigo;
	}

	public Integer getStandar() {
		return this.standar;
	}

	public void setStandar(Integer standar) {
		this.standar = standar;
	}

	public Ordenprod getOrdenprod() {
		return this.ordenprod;
	}

	public void setOrdenprod(Ordenprod ordenprod) {
		this.ordenprod = ordenprod;
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