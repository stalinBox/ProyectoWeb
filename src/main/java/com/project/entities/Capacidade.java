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

	//bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name="ordenprod_codigo")
	private Ordenprod ordenprod;

	//bi-directional many-to-one association to TipLinea
	@ManyToOne
	@JoinColumn(name="codigo_tiplinea")
	private TipLinea tipLinea;

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

	public TipLinea getTipLinea() {
		return this.tipLinea;
	}

	public void setTipLinea(TipLinea tipLinea) {
		this.tipLinea = tipLinea;
	}

}