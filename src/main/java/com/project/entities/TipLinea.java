package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tip_linea database table.
 * 
 */
@Entity
@Table(name="tip_linea")
@NamedQuery(name="TipLinea.findAll", query="SELECT t FROM TipLinea t")
public class TipLinea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo_tiplinea")
	private Integer codigoTiplinea;

	private String desctiplinea;

	private String tipolinea;

	//bi-directional many-to-one association to Confproceso
	@OneToMany(mappedBy="tipLinea")
	private List<Confproceso> confprocesos;

	//bi-directional many-to-one association to Distribdetalle
	@OneToMany(mappedBy="tipLinea")
	private List<Distribdetalle> distribdetalles;

	//bi-directional many-to-one association to Lineasprod
	@OneToMany(mappedBy="tipLinea")
	private List<Lineasprod> lineasprods;

	public TipLinea() {
	}

	public Integer getCodigoTiplinea() {
		return this.codigoTiplinea;
	}

	public void setCodigoTiplinea(Integer codigoTiplinea) {
		this.codigoTiplinea = codigoTiplinea;
	}

	public String getDesctiplinea() {
		return this.desctiplinea;
	}

	public void setDesctiplinea(String desctiplinea) {
		this.desctiplinea = desctiplinea;
	}

	public String getTipolinea() {
		return this.tipolinea;
	}

	public void setTipolinea(String tipolinea) {
		this.tipolinea = tipolinea;
	}

	public List<Confproceso> getConfprocesos() {
		return this.confprocesos;
	}

	public void setConfprocesos(List<Confproceso> confprocesos) {
		this.confprocesos = confprocesos;
	}

	public Confproceso addConfproceso(Confproceso confproceso) {
		getConfprocesos().add(confproceso);
		confproceso.setTipLinea(this);

		return confproceso;
	}

	public Confproceso removeConfproceso(Confproceso confproceso) {
		getConfprocesos().remove(confproceso);
		confproceso.setTipLinea(null);

		return confproceso;
	}

	public List<Distribdetalle> getDistribdetalles() {
		return this.distribdetalles;
	}

	public void setDistribdetalles(List<Distribdetalle> distribdetalles) {
		this.distribdetalles = distribdetalles;
	}

	public Distribdetalle addDistribdetalle(Distribdetalle distribdetalle) {
		getDistribdetalles().add(distribdetalle);
		distribdetalle.setTipLinea(this);

		return distribdetalle;
	}

	public Distribdetalle removeDistribdetalle(Distribdetalle distribdetalle) {
		getDistribdetalles().remove(distribdetalle);
		distribdetalle.setTipLinea(null);

		return distribdetalle;
	}

	public List<Lineasprod> getLineasprods() {
		return this.lineasprods;
	}

	public void setLineasprods(List<Lineasprod> lineasprods) {
		this.lineasprods = lineasprods;
	}

	public Lineasprod addLineasprod(Lineasprod lineasprod) {
		getLineasprods().add(lineasprod);
		lineasprod.setTipLinea(this);

		return lineasprod;
	}

	public Lineasprod removeLineasprod(Lineasprod lineasprod) {
		getLineasprods().remove(lineasprod);
		lineasprod.setTipLinea(null);

		return lineasprod;
	}

}