package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the procesosop database table.
 * 
 */
@Entity
@NamedQuery(name = "Procesosop.findAll", query = "SELECT p FROM Procesosop p")
public class Procesosop implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProcesosopPK id;

	@Temporal(TemporalType.DATE)
	@Column(name = "f_actual")
	private Date fActual;

	@Temporal(TemporalType.DATE)
	@Column(name = "f_estim")
	private Date fEstim;

	// bi-directional many-to-one association to Lugare
	@ManyToOne
	@JoinColumn(name = "lugar_codigo_dest")
	private Lugare lugare1;

	// bi-directional many-to-one association to Lugare
	@ManyToOne
	@JoinColumn(name = "lugar_codigo_orig")
	private Lugare lugare2;

	// bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name = "ordenprod_codigo", insertable = false, updatable = false)
	private Ordenprod ordenprod;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_codigo", insertable = false, updatable = false)
	private Proceso proceso;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "user_id_resp", insertable = false, updatable = false)
	private Usuario usuario;

	public Procesosop() {
	}

	public ProcesosopPK getId() {
		return this.id;
	}

	public void setId(ProcesosopPK id) {
		this.id = id;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}