package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the programdias database table.
 * 
 */
@Entity
@Table(name = "programdias")
@NamedQuery(name = "Programdia.findAll", query = "SELECT p FROM Programdia p")
public class Programdia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "progdias_codigo")
	private Integer progdiasCodigo;

	private Integer cantidad;

	private String dia;

	// bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name = "lineapro_codigo", insertable = false, updatable = false, nullable = false)
	private Lineasprod lineasprod;

	// bi-directional many-to-one association to Parametro
	@ManyToOne
	@JoinColumn(name = "param_codigo")
	private Parametro parametro;

	public Programdia() {
	}

	public Integer getProgdiasCodigo() {
		return this.progdiasCodigo;
	}

	public void setProgdiasCodigo(Integer progdiasCodigo) {
		this.progdiasCodigo = progdiasCodigo;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
	}

	public Parametro getParametro() {
		return this.parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

}