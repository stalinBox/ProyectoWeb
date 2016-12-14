package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the proc_alerta database table.
 * 
 */
@Entity
@Table(name = "proc_alerta")
@NamedQuery(name = "ProcAlerta.findAll", query = "SELECT p FROM ProcAlerta p")
public class ProcAlerta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proc_ale_codigo")
	private Integer procAleCodigo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String observacion;

	// bi-directional many-to-one association to Alerta
	@ManyToOne
	@JoinColumn(name = "alerta_codigo", insertable = false, updatable = false, nullable = false)
	private Alerta alerta;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_codigo", insertable = false, updatable = false, nullable = false)
	private Proceso proceso;

	public ProcAlerta() {
	}

	public Integer getProcAleCodigo() {
		return this.procAleCodigo;
	}

	public void setProcAleCodigo(Integer procAleCodigo) {
		this.procAleCodigo = procAleCodigo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Alerta getAlerta() {
		return this.alerta;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

}