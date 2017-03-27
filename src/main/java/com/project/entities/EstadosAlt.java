package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estados_alt database table.
 * 
 */
@Entity
@Table(name="estados_alt")
@NamedQuery(name="EstadosAlt.findAll", query="SELECT e FROM EstadosAlt e")
public class EstadosAlt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="estado_codigo")
	private Integer estadoCodigo;

	private String estado;

	//bi-directional many-to-one association to ProcAlerta
	@OneToMany(mappedBy="estadosAlt")
	private List<ProcAlerta> procAlertas;

	public EstadosAlt() {
	}

	public Integer getEstadoCodigo() {
		return this.estadoCodigo;
	}

	public void setEstadoCodigo(Integer estadoCodigo) {
		this.estadoCodigo = estadoCodigo;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<ProcAlerta> getProcAlertas() {
		return this.procAlertas;
	}

	public void setProcAlertas(List<ProcAlerta> procAlertas) {
		this.procAlertas = procAlertas;
	}

	public ProcAlerta addProcAlerta(ProcAlerta procAlerta) {
		getProcAlertas().add(procAlerta);
		procAlerta.setEstadosAlt(this);

		return procAlerta;
	}

	public ProcAlerta removeProcAlerta(ProcAlerta procAlerta) {
		getProcAlertas().remove(procAlerta);
		procAlerta.setEstadosAlt(null);

		return procAlerta;
	}

}