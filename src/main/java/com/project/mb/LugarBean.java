package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.LugaresDao;
import com.project.dao.LugaresDaoImpl;
import com.project.entities.Lugare;

@ManagedBean
@ViewScoped
public class LugarBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Lugare> lugar;
	private Lugare selectedLugar;

	@PostConstruct
	public void init() {
		selectedLugar = new Lugare();
	}

	public LugarBean() {
		this.lugar = new ArrayList<Lugare>();
	}

	// SETTERS AND GETTERS
	public List<Lugare> getLugar() {
		LugaresDao lugaresDao = new LugaresDaoImpl();
		this.lugar = lugaresDao.findAll();
		return lugar;
	}

	public void setLugar(List<Lugare> lugar) {
		this.lugar = lugar;
	}

	public Lugare getSelectedLugar() {
		return selectedLugar;
	}

	public void setSelectedLugar(Lugare selectedLugar) {
		this.selectedLugar = selectedLugar;
	}

	// DMLS
	public void btnCreateLugar(ActionEvent actionEvent) {
		String msg = "";
		LugaresDao lugarDao = new LugaresDaoImpl();

		if (lugarDao.create(this.selectedLugar)) {
			msg = "Se ha añadido un nuevo lugar";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir un troquel";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateLugar(ActionEvent actionEvent) {
		String msg;
		LugaresDao lugarDao = new LugaresDaoImpl();
		if (lugarDao.update(this.selectedLugar)) {
			msg = "Se ha modificado el nombre del Lugar";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el nombre del lugar";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteLugar(ActionEvent actionEvent) {
		String msg;
		LugaresDao lugarDao = new LugaresDaoImpl();
		if (lugarDao.delete(this.selectedLugar.getLugarCodigo())) {
			msg = "Se eliminó el lugar exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el lugar";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
