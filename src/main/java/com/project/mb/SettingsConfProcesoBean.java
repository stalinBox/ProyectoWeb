package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.entities.Confproceso;
import com.project.entities.Modelo;
import com.project.entities.Proceso;

@ManagedBean
@ViewScoped
public class SettingsConfProcesoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Confproceso> confProceso;
	private Confproceso selectedConfProceso;

	private List<SelectItem> selectedItemsModelos;
	private List<SelectItem> selectedItemsProcesos;
	private List<SelectItem> selectedItemsSubProcesos;

	// INICIALIZADORES
	@PostConstruct
	public void init() {

	}

	// METODOS
	public void btnCreateConfTimes(ActionEvent actionEvent) {
	}

	public void btnUpdateConfTimes(ActionEvent actionEvent) {
	}

	public void btnDeleteConfTimes(ActionEvent actionEvent) {
	}

	// SETTERS AND GETTERS
	public List<SelectItem> getSelectedItemsModelos() {
		this.selectedItemsModelos = new ArrayList<SelectItem>();
		ModelosDao modelosDao = new ModelosDaoImpl();
		List<Modelo> modelos = modelosDao.findAll();
		this.selectedItemsModelos.clear();
		for (Modelo mod : modelos) {
			SelectItem selectItem = new SelectItem(mod.getModCodigo(),
					mod.getModNombre());
			this.selectedItemsModelos.add(selectItem);
		}
		return selectedItemsModelos;
	}

	public void setSelectedItemsModelos(List<SelectItem> selectedItemsModelos) {
		this.selectedItemsModelos = selectedItemsModelos;
	}

	public List<SelectItem> getSelectedItemsProcesos() {
		this.selectedItemsProcesos = new ArrayList<SelectItem>();
		ProcesoDao procesosDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesosDao.findPadre();
		this.selectedItemsProcesos.clear();
		for (Proceso pro : proceso) {
			SelectItem selectItem = new SelectItem(pro.getProCodigo(), pro
					.getTipoProceso().getTprNombre());
			this.selectedItemsProcesos.add(selectItem);
		}
		return selectedItemsProcesos;
	}

	public void setSelectedItemsProcesos(List<SelectItem> selectedItemsProcesos) {
		this.selectedItemsProcesos = selectedItemsProcesos;
	}

	public List<SelectItem> getSelectedItemsSubProcesos() {
		this.selectedItemsSubProcesos = new ArrayList<SelectItem>();
		ProcesoDao procesosDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesosDao.findSubProcesos();
		this.selectedItemsSubProcesos.clear();
		for (Proceso pro : proceso) {
			SelectItem selectItem = new SelectItem(pro.getProCodigo(), pro
					.getTipoProceso().getTprNombre());
			this.selectedItemsSubProcesos.add(selectItem);
		}
		return selectedItemsSubProcesos;
	}

	public void setSelectedItemsSubProcesos(
			List<SelectItem> selectedItemsSubProcesos) {
		this.selectedItemsSubProcesos = selectedItemsSubProcesos;
	}

	public List<Confproceso> getConfProceso() {
		return confProceso;
	}

	public void setConfProceso(List<Confproceso> confProceso) {
		this.confProceso = confProceso;
	}

	public Confproceso getSelectedConfProceso() {
		return selectedConfProceso;
	}

	public void setSelectedConfProceso(Confproceso selectedConfProceso) {
		this.selectedConfProceso = selectedConfProceso;
	}
}
