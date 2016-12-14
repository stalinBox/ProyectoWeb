package com.project.utils;

import java.io.Serializable;

public class ProcesoMapeo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer modeloPro;
	private Integer tprProcesoPro;
	private Integer padrePro;
	private Integer capacidadPro;
	private double duracionPro;
	private boolean autoPro;
	private double cifRefPro;
	private double cifPro;
	private double tManoPro;
	private double tsPro;
	private double costObraPro;
	private double costRealPro;
	private Integer numTrabPro;
	private boolean activoPro;
	private String descPro;

	public Integer getModeloPro() {
		return modeloPro;
	}

	public void setModeloPro(Integer modeloPro) {
		this.modeloPro = modeloPro;
	}

	public Integer getTprProcesoPro() {
		return tprProcesoPro;
	}

	public void setTprProcesoPro(Integer tprProcesoPro) {
		this.tprProcesoPro = tprProcesoPro;
	}

	public Integer getPadrePro() {
		return padrePro;
	}

	public void setPadrePro(Integer padrePro) {
		this.padrePro = padrePro;
	}

	public Integer getCapacidadPro() {
		return capacidadPro;
	}

	public void setCapacidadPro(Integer capacidadPro) {
		this.capacidadPro = capacidadPro;
	}

	public double getDuracionPro() {
		this.duracionPro = 0;
		return duracionPro;
	}

	public void setDuracionPro(double duracionPro) {
		this.duracionPro = duracionPro;
	}

	public double getCifRefPro() {
		return cifRefPro;
	}

	public void setCifRefPro(double cifRefPro) {
		this.cifRefPro = cifRefPro;
	}

	public double getCifPro() {
		return cifPro;
	}

	public void setCifPro(double cifPro) {
		this.cifPro = cifPro;
	}

	public double gettManoPro() {
		return tManoPro;
	}

	public void settManoPro(double tManoPro) {
		this.tManoPro = tManoPro;
	}

	public double getTsPro() {
		this.tsPro = 0;
		return tsPro;
	}

	public void setTsPro(double tsPro) {
		this.tsPro = tsPro;
	}

	public double getCostObraPro() {
		return costObraPro;
	}

	public void setCostObraPro(double costObraPro) {
		this.costObraPro = costObraPro;
	}

	public double getCostRealPro() {
		return costRealPro;
	}

	public void setCostRealPro(double costRealPro) {
		this.costRealPro = costRealPro;
	}

	public Integer getNumTrabPro() {
		return numTrabPro;
	}

	public void setNumTrabPro(Integer numTrabPro) {
		this.numTrabPro = numTrabPro;
	}

	public boolean isAutoPro() {
		this.autoPro = false;
		return autoPro;
	}

	public void setAutoPro(boolean autoPro) {
		this.autoPro = autoPro;
	}

	public boolean isActivoPro() {
		this.activoPro = false;
		return activoPro;
	}

	public void setActivoPro(boolean activoPro) {
		this.activoPro = activoPro;
	}

	public String getDescPro() {
		return descPro;
	}

	public void setDescPro(String descPro) {
		this.descPro = descPro;
	}

}