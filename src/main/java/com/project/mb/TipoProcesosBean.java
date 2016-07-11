package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.TipprocesoDaoImpl;
import com.project.dao.TipprocesosDao;
import com.project.entities.TipoProceso;

@ManagedBean
@RequestScoped
public class TipoProcesosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<TipoProceso> tipProceso;
	private TipoProceso selectedTipProceso;

	@PostConstruct
	public void init() {
		selectedTipProceso = new TipoProceso();
	}

	public TipoProcesosBean() {
		this.tipProceso = new ArrayList<TipoProceso>();
	}
	
	public List<TipoProceso> getTipProceso() {
		TipprocesosDao tipProcesoDao = new TipprocesoDaoImpl();
		this.tipProceso = tipProcesoDao.findAll();
		return tipProceso;
	}

	public TipoProceso getSelectedTipProceso() {
		return selectedTipProceso;
	}

	public void setSelectedTipProceso(TipoProceso selectedTipProceso) {
		this.selectedTipProceso = selectedTipProceso;
	}
	
	//DMLS
	public void btnCreateTipoProceso(ActionEvent actionEvent) {
		String msg = "";
		TipprocesosDao tipProcesoDao = new TipprocesoDaoImpl();

		if (tipProcesoDao.create(this.selectedTipProceso)) {
			msg = "Se ha añadido un nuevo tipo de proceso";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir tipo de proceso";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateTipoProceso(ActionEvent actionEvent) {
		String msg;
		TipprocesosDao tipProcesoDao = new TipprocesoDaoImpl();
		if (tipProcesoDao.update(this.selectedTipProceso)) {
			msg = "Se ha modificado el nombre del tipo de proceso";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el nombre del tipo de proceso";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteTipoProceso(ActionEvent actionEvent) {
		String msg;
		TipprocesosDao tipProcesoDao = new TipprocesoDaoImpl();
		if (tipProcesoDao.delete(this.selectedTipProceso.getTprCodigo())) {
			msg = "Se eliminó el tipo de proceso de calzado exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el tipo de proceso de calzado";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
