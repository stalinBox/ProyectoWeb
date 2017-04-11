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

	//bi-directional many-to-one association to Capacidade
	@OneToMany(mappedBy="tipLinea1")
	private List<Capacidade> capacidades1;

	//bi-directional many-to-one association to Capacidade
	@OneToMany(mappedBy="tipLinea2")
	private List<Capacidade> capacidades2;

	//bi-directional many-to-one association to Capacidade
	@OneToMany(mappedBy="tipLinea3")
	private List<Capacidade> capacidades3;

	//bi-directional many-to-one association to Confproceso
	@OneToMany(mappedBy="tipLinea")
	private List<Confproceso> confprocesos;

	//bi-directional many-to-one association to Distribdetalle
	@OneToMany(mappedBy="tipLinea1")
	private List<Distribdetalle> distribdetalles1;

	//bi-directional many-to-one association to Distribdetalle
	@OneToMany(mappedBy="tipLinea2")
	private List<Distribdetalle> distribdetalles2;

	//bi-directional many-to-one association to Distribdetalle
	@OneToMany(mappedBy="tipLinea3")
	private List<Distribdetalle> distribdetalles3;

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

	public List<Capacidade> getCapacidades1() {
		return this.capacidades1;
	}

	public void setCapacidades1(List<Capacidade> capacidades1) {
		this.capacidades1 = capacidades1;
	}

	public Capacidade addCapacidades1(Capacidade capacidades1) {
		getCapacidades1().add(capacidades1);
		capacidades1.setTipLinea1(this);

		return capacidades1;
	}

	public Capacidade removeCapacidades1(Capacidade capacidades1) {
		getCapacidades1().remove(capacidades1);
		capacidades1.setTipLinea1(null);

		return capacidades1;
	}

	public List<Capacidade> getCapacidades2() {
		return this.capacidades2;
	}

	public void setCapacidades2(List<Capacidade> capacidades2) {
		this.capacidades2 = capacidades2;
	}

	public Capacidade addCapacidades2(Capacidade capacidades2) {
		getCapacidades2().add(capacidades2);
		capacidades2.setTipLinea2(this);

		return capacidades2;
	}

	public Capacidade removeCapacidades2(Capacidade capacidades2) {
		getCapacidades2().remove(capacidades2);
		capacidades2.setTipLinea2(null);

		return capacidades2;
	}

	public List<Capacidade> getCapacidades3() {
		return this.capacidades3;
	}

	public void setCapacidades3(List<Capacidade> capacidades3) {
		this.capacidades3 = capacidades3;
	}

	public Capacidade addCapacidades3(Capacidade capacidades3) {
		getCapacidades3().add(capacidades3);
		capacidades3.setTipLinea3(this);

		return capacidades3;
	}

	public Capacidade removeCapacidades3(Capacidade capacidades3) {
		getCapacidades3().remove(capacidades3);
		capacidades3.setTipLinea3(null);

		return capacidades3;
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

	public List<Distribdetalle> getDistribdetalles1() {
		return this.distribdetalles1;
	}

	public void setDistribdetalles1(List<Distribdetalle> distribdetalles1) {
		this.distribdetalles1 = distribdetalles1;
	}

	public Distribdetalle addDistribdetalles1(Distribdetalle distribdetalles1) {
		getDistribdetalles1().add(distribdetalles1);
		distribdetalles1.setTipLinea1(this);

		return distribdetalles1;
	}

	public Distribdetalle removeDistribdetalles1(Distribdetalle distribdetalles1) {
		getDistribdetalles1().remove(distribdetalles1);
		distribdetalles1.setTipLinea1(null);

		return distribdetalles1;
	}

	public List<Distribdetalle> getDistribdetalles2() {
		return this.distribdetalles2;
	}

	public void setDistribdetalles2(List<Distribdetalle> distribdetalles2) {
		this.distribdetalles2 = distribdetalles2;
	}

	public Distribdetalle addDistribdetalles2(Distribdetalle distribdetalles2) {
		getDistribdetalles2().add(distribdetalles2);
		distribdetalles2.setTipLinea2(this);

		return distribdetalles2;
	}

	public Distribdetalle removeDistribdetalles2(Distribdetalle distribdetalles2) {
		getDistribdetalles2().remove(distribdetalles2);
		distribdetalles2.setTipLinea2(null);

		return distribdetalles2;
	}

	public List<Distribdetalle> getDistribdetalles3() {
		return this.distribdetalles3;
	}

	public void setDistribdetalles3(List<Distribdetalle> distribdetalles3) {
		this.distribdetalles3 = distribdetalles3;
	}

	public Distribdetalle addDistribdetalles3(Distribdetalle distribdetalles3) {
		getDistribdetalles3().add(distribdetalles3);
		distribdetalles3.setTipLinea3(this);

		return distribdetalles3;
	}

	public Distribdetalle removeDistribdetalles3(Distribdetalle distribdetalles3) {
		getDistribdetalles3().remove(distribdetalles3);
		distribdetalles3.setTipLinea3(null);

		return distribdetalles3;
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