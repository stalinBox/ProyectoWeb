package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the tipo_procesos database table.
 * 
 */
@Entity
@Table(name = "tipo_procesos")
@NamedQuery(name = "TipoProceso.findAll", query = "SELECT t FROM TipoProceso t")
public class TipoProceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tpr_codigo")
	private Integer tprCodigo;

	@Column(name = "tpr_nombre")
	private String tprNombre;

	// bi-directional many-to-one association to Proceso
	@OneToMany(mappedBy = "tipoProceso")
	private List<Proceso> procesos;

	public TipoProceso() {
	}

	public Integer getTprCodigo() {
		return this.tprCodigo;
	}

	public void setTprCodigo(Integer tprCodigo) {
		this.tprCodigo = tprCodigo;
	}

	public String getTprNombre() {
		return this.tprNombre;
	}

	public void setTprNombre(String tprNombre) {
		this.tprNombre = tprNombre;
	}

	public List<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public Proceso addProceso(Proceso proceso) {
		getProcesos().add(proceso);
		proceso.setTipoProceso(this);

		return proceso;
	}

	public Proceso removeProceso(Proceso proceso) {
		getProcesos().remove(proceso);
		proceso.setTipoProceso(null);

		return proceso;
	}

}