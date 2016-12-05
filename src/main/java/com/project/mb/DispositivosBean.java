package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.project.entities.Dispositivo;

;

@ManagedBean
@ViewScoped
public class DispositivosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// VARIABLES
	private List<Dispositivo> dispositivos;
	private Dispositivo selectedDispositivo;

	// INICIALIZADORES

	// METODOS
	public void btnCreateDispositivo(ActionEvent actionEvent) {

	}

	public void btnUpdateDispositivo(ActionEvent actionEvent) {

	}

	public void btnDeleteDispositivo(ActionEvent actionEvent) {

	}

	// SETTERS AND GETTERS
	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public Dispositivo getSelectedDispositivo() {
		return selectedDispositivo;
	}

	public void setSelectedDispositivo(Dispositivo selectedDispositivo) {
		this.selectedDispositivo = selectedDispositivo;
	}
}
