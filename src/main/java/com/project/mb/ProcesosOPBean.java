package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import com.project.entities.Lugare;
import com.project.entities.Ordenprod;
import com.project.entities.Proceso;
import com.project.entities.Procesosop;
import com.project.entities.Usuario;

public class ProcesosOPBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Procesosop> procesosOP;
	private Procesosop selectedProcesosOP;

	// VARIABLE BASE
	private List<SelectItem> selectedItems;

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		this.selectedProcesosOP = new Procesosop();
		this.selectedProcesosOP.setProceso(new Proceso());
		this.selectedProcesosOP.setOrdenprod(new Ordenprod());
		this.selectedProcesosOP.setLugare1(new Lugare());
		this.selectedProcesosOP.setLugare2(new Lugare());
		this.selectedProcesosOP.setUsuario(new Usuario());
	}

	// METODOS
	public void btnCreateProcesosOP() {

	}

	// SETTERS AND GETTERS
	public List<Procesosop> getProcesosOP() {
		return procesosOP;
	}

	public void setProcesosOP(List<Procesosop> procesosOP) {
		this.procesosOP = procesosOP;
	}

	public Procesosop getSelectedProcesosOP() {
		return selectedProcesosOP;
	}

	public void setSelectedProcesosOP(Procesosop selectedProcesosOP) {
		this.selectedProcesosOP = selectedProcesosOP;
	}

}
