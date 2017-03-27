package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the capacidades database table.
 * 
 */
@Entity
@Table(name="capacidades")
@NamedQuery(name="Capacidade.findAll", query="SELECT c FROM Capacidade c")
public class Capacidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cap_codigo")
	private Integer capCodigo;

	private Integer standar;

	//bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name="lineapro_codigo")
	private Lineasprod lineasprod;

	//bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name="ordenprod_codigo")
	private Ordenprod ordenprod;

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

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
	}

	public Ordenprod getOrdenprod() {
		return this.ordenprod;
	}

	public void setOrdenprod(Ordenprod ordenprod) {
		this.ordenprod = ordenprod;
	}

}