package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the programturnos database table.
 * 
 */
@Entity
@Table(name="programturnos")
@NamedQuery(name="Programturno.findAll", query="SELECT p FROM Programturno p")
public class Programturno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="program_codigo")
	private Integer programCodigo;

	@Column(name="cant_estim")
	private Integer cantEstim;

	@Column(name="cant_real")
	private Integer cantReal;

	private Integer dia;

	@Column(name="estado_tur")
	private String estadoTur;

	@Temporal(TemporalType.DATE)
	@Column(name="f_final")
	private Date fFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="f_inicio")
	private Date fInicio;

	@Temporal(TemporalType.DATE)
	private Date hora;

	@Column(name="no_plan")
	private String noPlan;

	//bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name="mod_codigo")
	private Modelo modelo;

	//bi-directional many-to-one association to Procesosop
	@ManyToOne
	@JoinColumn(name="processop_cod")
	private Procesosop procesosop;

	//bi-directional many-to-one association to Programdia
	@ManyToOne
	@JoinColumn(name="progdias_codigo")
	private Programdia programdia;

	//bi-directional many-to-one association to Talla
	@ManyToOne
	@JoinColumn(name="tal_codigo")
	private Talla talla;

	//bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name="turno_codigo")
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

	public Integer getDia() {
		return this.dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public String getEstadoTur() {
		return this.estadoTur;
	}

	public void setEstadoTur(String estadoTur) {
		this.estadoTur = estadoTur;
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

	public String getNoPlan() {
		return this.noPlan;
	}

	public void setNoPlan(String noPlan) {
		this.noPlan = noPlan;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Procesosop getProcesosop() {
		return this.procesosop;
	}

	public void setProcesosop(Procesosop procesosop) {
		this.procesosop = procesosop;
	}

	public Programdia getProgramdia() {
		return this.programdia;
	}

	public void setProgramdia(Programdia programdia) {
		this.programdia = programdia;
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