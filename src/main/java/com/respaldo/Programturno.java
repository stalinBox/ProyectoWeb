package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the programturnos database table.
 * 
 */
@Entity
@Table(name = "programturnos")
@NamedQuery(name = "Programturno.findAll", query = "SELECT p FROM Programturno p")
public class Programturno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "program_codigo")
	private Integer programCodigo;

	@Column(name = "cant_estim")
	private Integer cantEstim;

	@Column(name = "cant_real")
	private Integer cantReal;

	private String dia;

	@Temporal(TemporalType.DATE)
	@Column(name = "f_final")
	private Date fFinal;

	@Temporal(TemporalType.DATE)
	@Column(name = "f_inicio")
	private Date fInicio;

	@Temporal(TemporalType.DATE)
	private Date hora;

	// bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name = "lineapro_codigo", nullable = false, insertable = false, updatable = false)
	private Lineasprod lineasprod;

	// bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name = "mod_codigo", nullable = false, insertable = false, updatable = false)
	private Modelo modelo;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_codigo", nullable = false, insertable = false, updatable = false)
	private Proceso proceso;

	// bi-directional many-to-one association to Talla
	@ManyToOne
	@JoinColumn(name = "tal_codigo", nullable = false, insertable = false, updatable = false)
	private Talla talla;

	// bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name = "turno_codigo", nullable = false, insertable = false, updatable = false)
	private Turno turno;

	public Programturno() {
	}

	public Integer getProgramCodigo() {
		return this.programCodigo;
	}

	public void setProgramCodigo(Integer programCodigo) {
		this.programCodigo = programCodigo;
	}

	public Integer getCantEstim() {
		return this.cantEstim;
	}

	public void setCantEstim(Integer cantEstim) {
		this.cantEstim = cantEstim;
	}

	public Integer getCantReal() {
		return this.cantReal;
	}

	public void setCantReal(Integer cantReal) {
		this.cantReal = cantReal;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Date getFFinal() {
		return this.fFinal;
	}

	public void setFFinal(Date fFinal) {
		this.fFinal = fFinal;
	}

	public Date getFInicio() {
		return this.fInicio;
	}

	public void setFInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Talla getTalla() {
		return this.talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}