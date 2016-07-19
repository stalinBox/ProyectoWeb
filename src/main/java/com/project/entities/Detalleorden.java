package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the detalleorden database table.
 * 
 */
@Entity
@NamedQuery(name="Detalleorden.findAll", query="SELECT d FROM Detalleorden d")
public class Detalleorden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="detaorden_codigo")
	private Integer detaordenCodigo;

	private Integer cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="f_actual")
	private Date fActual;

	@Temporal(TemporalType.DATE)
	@Column(name="f_estim")
	private Date fEstim;

	//bi-directional many-to-one association to Lugare
	@ManyToOne
	@JoinColumn(name="lugar_codigo_dest")
	private Lugare lugare1;

	//bi-directional many-to-one association to Lugare
	@ManyToOne
	@JoinColumn(name="lugar_codigo_orig")
	private Lugare lugare2;

	//bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name="mod_codigo")
	private Modelo modelo;

	//bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name="ordenprod_codigo")
	private Ordenprod ordenprod;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo")
	private Proceso proceso;

	//bi-directional many-to-one association to Talla
	@ManyToOne
	@JoinColumn(name="tal_codigo")
	private Talla talla;

	//bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name="turno_codigo")
	private Turno turno;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="user_id_resp")
	private Usuario usuario;

	public Detalleorden() {
	}

	public Integer getDetaordenCodigo() {
		return this.detaordenCodigo;
	}

	public void setDetaordenCodigo(Integer detaordenCodigo) {
		this.detaordenCodigo = detaordenCodigo;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFActual() {
		return this.fActual;
	}

	public void setFActual(Date fActual) {
		this.fActual = fActual;
	}

	public Date getFEstim() {
		return this.fEstim;
	}

	public void setFEstim(Date fEstim) {
		this.fEstim = fEstim;
	}

	public Lugare getLugare1() {
		return this.lugare1;
	}

	public void setLugare1(Lugare lugare1) {
		this.lugare1 = lugare1;
	}

	public Lugare getLugare2() {
		return this.lugare2;
	}

	public void setLugare2(Lugare lugare2) {
		this.lugare2 = lugare2;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Ordenprod getOrdenprod() {
		return this.ordenprod;
	}

	public void setOrdenprod(Ordenprod ordenprod) {
		this.ordenprod = ordenprod;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}