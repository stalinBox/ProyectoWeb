package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.project.entities.Maquina;

;

@ManagedBean
@ViewScoped
public class MaquinasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// VARIABLES
	private List<Maquina> maquinas;
	private Maquina selectedMaquinas;

	// INICIALIZADORES

	// METODOS
	public void btnCreateMaquina(ActionEvent actionEvent) {
	}

	public void btnUpdateMaquina(ActionEvent actionEvent) {
	}

	public void btnDeleteMaquina(ActionEvent actionEvent) {
	}

	// SETTERS AND GETTERS
	public List<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(List<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public Maquina getSelectedMaquinas() {
		return selectedMaquinas;
	}

	public void setSelectedMaquinas(Maquina selectedMaquinas) {
		this.selectedMaquinas = selectedMaquinas;
	}

}
