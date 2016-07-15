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

import com.project.dao.TroquelDaoImpl;
import com.project.dao.TroqueleDao;
import com.project.entities.Troquele;

@ManagedBean
@RequestScoped
public class TroquelBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Troquele> torqueles;
	private Troquele selectedTroquel;

	// private List<SelectItem> selectItemsTroquel;

	@PostConstruct
	public void init() {
		selectedTroquel = new Troquele();
	}

	public TroquelBean() {
		this.torqueles = new ArrayList<Troquele>();
	}

	public List<Troquele> getTorqueles() {
		TroqueleDao troquelDao = new TroquelDaoImpl();
		this.torqueles = troquelDao.findAll();
		return torqueles;
	}

	public Troquele getSelectedTroquel() {
		return selectedTroquel;
	}

	public void setSelectedTroquel(Troquele selectedTroquel) {
		this.selectedTroquel = selectedTroquel;
	}

	// DMLS
	public void btnCreateTroquel(ActionEvent actionEvent) {
		String msg = "";
		TroqueleDao troquelDao = new TroquelDaoImpl();

		if (troquelDao.create(this.selectedTroquel)) {
			msg = "Se ha añadido un nuevo troquel";
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

	public void btnUpdateTroquel(ActionEvent actionEvent) {
		String msg;
		TroqueleDao troquelDao = new TroquelDaoImpl();
		if (troquelDao.update(this.selectedTroquel)) {
			msg = "Se ha modificado el nombre de troquel";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el nombre del troquel";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteModelo(ActionEvent actionEvent) {
		String msg;
		TroqueleDao troquelDao = new TroquelDaoImpl();
		if (troquelDao.delete(this.selectedTroquel.getTrqCodigo())) {
			msg = "Se eliminó el troquel de calzado exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el troquel de calzado";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
