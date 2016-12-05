package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.project.entities.Alerta;

@ManagedBean
@ViewScoped
public class AlertasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	// VARIABLES
	private List<Alerta> alertas;
	private Alerta selectedAlerta;

	// INICIALIZADORES

	// METODOS
	public void btnCreateAlerta(ActionEvent actionEvent) {

	}

	public void btnUpdateAlerta(ActionEvent actionEvent) {

	}

	public void btnDeleteAlerta(ActionEvent actionEvent) {

	}

	// SETTERS AND GETTERS
	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}

	public Alerta getSelectedAlerta() {
		return selectedAlerta;
	}

	public void setSelectedAlerta(Alerta selectedAlerta) {
		this.selectedAlerta = selectedAlerta;
	}
}
