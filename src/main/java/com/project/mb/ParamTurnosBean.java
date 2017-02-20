package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.project.entities.Programturno;

@ManagedBean
@ViewScoped
public class ParamTurnosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Programturno> programTurnos;
	private Programturno selectedProgramTurnos;

	// CONSTRUCTORES
	@PostConstruct
	public void init() {
	}

	// METODOS
	public void btnCreateProgramTurns() {
	}

	public void btnDeleteProgramTurns() {
	}

	public void btnUpdateProgramTurns() {
	}

	// SETTERS AND GETTERS

	public List<Programturno> getProgramTurnos() {
		return programTurnos;
	}

	public void setProgramTurnos(List<Programturno> programTurnos) {
		this.programTurnos = programTurnos;
	}

	public Programturno getSelectedProgramTurnos() {
		return selectedProgramTurnos;
	}

	public void setSelectedProgramTurnos(Programturno selectedProgramTurnos) {
		this.selectedProgramTurnos = selectedProgramTurnos;
	}

}
