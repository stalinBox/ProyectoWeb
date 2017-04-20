package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the distribdetalle database table.
 * 
 */
@Entity
@NamedQuery(name="Distribdetalle.findAll", query="SELECT d FROM Distribdetalle d")
public class Distribdetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="distrib_codigo")
	private Integer distribCodigo;

	//bi-directional many-to-one association to Detalleorden
	@ManyToOne
	@JoinColumn(name="detaorden_codigo")
	private Detalleorden detalleorden;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo")
	private Proceso proceso;

	//bi-directional many-to-one association to TipLinea
	@ManyToOne
	@JoinColumn(name="codigo_tiplinea")
	private TipLinea tipLinea;

	//bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy="distribdetalle")
	private List<Parametro> parametros;

	public Distribdetalle() {
	}

	public Integer getDistribCodigo() {
		return this.distribCodigo;
	}

	public void setDistribCodigo(Integer distribCodigo) {
		this.distribCodigo = distribCodigo;
	}

	public Detalleorden getDetalleorden() {
		return this.detalleorden;
	}

	public void setDetalleorden(Detalleorden detalleorden) {
		this.detalleorden = detalleorden;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public TipLinea getTipLinea() {
		return this.tipLinea;
	}

	public void setTipLinea(TipLinea tipLinea) {
		this.tipLinea = tipLinea;
	}

	public List<Parametro> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getParametros().add(parametro);
		parametro.setDistribdetalle(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setDistribdetalle(null);

		return parametro;
	}

}