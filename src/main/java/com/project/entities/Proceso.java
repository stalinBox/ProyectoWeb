package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the procesos database table.
 * 
 */
@Entity
@Table(name = "procesos")
@NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p")
public class Proceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_codigo")
	private Integer proCodigo;

	@Column(name = "pro_cap")
	private Integer proCap;

	@Column(name = "pro_duracion")
	private BigDecimal proDuracion;

	// bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name = "mod_codigo")
	private Modelo modelo;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_padre")
	private Proceso proceso;

	// bi-directional many-to-one association to Proceso
	@OneToMany(mappedBy = "proceso")
	private List<Proceso> procesos;

	// bi-directional many-to-one association to TipoProceso
	@ManyToOne
	@JoinColumn(name = "tpr_codigo")
	private TipoProceso tipoProceso;

	public Proceso() {
	}

	public Integer getProCodigo() {
		return this.proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}

	public Integer getProCap() {
		return this.proCap;
	}

	public void setProCap(Integer proCap) {
		this.proCap = proCap;
	}

	public BigDecimal getProDuracion() {
		return this.proDuracion;
	}

	public void setProDuracion(BigDecimal proDuracion) {
		this.proDuracion = proDuracion;
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

	public List<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public Proceso addProceso(Proceso proceso) {
		getProcesos().add(proceso);
		proceso.setProceso(this);

		return proceso;
	}

	public Proceso removeProceso(Proceso proceso) {
		getProcesos().remove(proceso);
		proceso.setProceso(null);

		return proceso;
	}

	public TipoProceso getTipoProceso() {
		return this.tipoProceso;
	}

	public void setTipoProceso(TipoProceso tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

}