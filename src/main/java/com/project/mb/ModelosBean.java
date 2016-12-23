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
import javax.faces.model.SelectItem;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.entities.Modelo;

@ManagedBean
@RequestScoped
public class ModelosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	// VARIABLES
	private List<Modelo> modelos;
	private Modelo selectedModelo;
	private List<SelectItem> selectItemsModelo;

	@PostConstruct
	public void init() {
		selectedModelo = new Modelo();
	}

	public ModelosBean() {
		this.modelos = new ArrayList<Modelo>();
	}

	// SETTERS AND GETTERS
	public List<SelectItem> getSelectItemsModelo() {
		this.selectItemsModelo = new ArrayList<SelectItem>();
		ModelosDao modDao = new ModelosDaoImpl();
		List<Modelo> modelo = modDao.findAll();
		for (Modelo mod : modelo) {
			SelectItem selectItem = new SelectItem(mod.getModCodigo(),
					mod.getModNombre());
			this.selectItemsModelo.add(selectItem);
		}
		return selectItemsModelo;
	}

	public void setSelectItemsModelo(List<SelectItem> selectItemsModelo) {
		this.selectItemsModelo = selectItemsModelo;
	}

	public List<Modelo> getModelos() {
		ModelosDao modelosDao = new ModelosDaoImpl();
		this.modelos = modelosDao.findAll();
		return modelos;
	}

	public Modelo getSelectedModelos() {
		return selectedModelo;
	}

	public void setSelectedModelos(Modelo selectedModelos) {
		this.selectedModelo = selectedModelos;
	}

	// METODOS
	public void btnCreateModelo(ActionEvent actionEvent) {
		String msg = "";
		ModelosDao modeloDao = new ModelosDaoImpl();

		if (modeloDao.create(this.selectedModelo)) {
			msg = "Se ha añadido un nuevo modelo";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir un modelo";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateModelo(ActionEvent actionEvent) {
		String msg;
		ModelosDao modeloDao = new ModelosDaoImpl();
		if (modeloDao.update(this.selectedModelo)) {
			msg = "Se ha modificado el modelo";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el modelo";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteModelo(ActionEvent actionEvent) {
		String msg;
		ModelosDao modeloDao = new ModelosDaoImpl();
		if (modeloDao.delete(this.selectedModelo.getModCodigo())) {
			msg = "Se eliminó el modelo de calzado exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el modelo de calzado";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

}
