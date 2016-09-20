package com.project.mb;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ParametrizacionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private Date currentDate;
	private String diasLaborables = "";

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		currentDate = new Date();
	}

	public ParametrizacionBean() {
	}

	public void updateColumns() {
		DataGridGenerate2 ab = new DataGridGenerate2();
		ab.updateColumns(this.diasLaborables);
	}

	public void updateColumnsDistrib() {
		DataGridGenerate ac = new DataGridGenerate();
		ac.updateColumnsDistrib(this.diasLaborables);

	}

	// SETTERS AND GETTERS
	public Date getCurrentDate() {
		return currentDate;
	}

	public String getDiasLaborables() {
		return diasLaborables;
	}

	public void setDiasLaborables(String diasLaborables) {
		this.diasLaborables = diasLaborables;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}
