package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.ParametrizacionDao;
import com.project.dao.ParametrizacionDaoImpl;
import com.project.entities.Parametro;

@ManagedBean
@ViewScoped
public class ParamBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Parametro> parametrizacion;
	private Parametro selectedParametrizacion;

	// INICIALIZADOR
	public void init() {

	}

	// METODOS
	public void btnCreateParametrizacion(ActionEvent actionEvent) {
		String msg = "";
		ParametrizacionDao paramDao = new ParametrizacionDaoImpl();

		if (paramDao.create(this.selectedParametrizacion)) {
			msg = "Se ha añadido un nuevo parametrizacion";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al añadir un cliente";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateParametrizacion(ActionEvent actionEvent) {
		String msg;
		ParametrizacionDao paramDao = new ParametrizacionDaoImpl();
		if (paramDao.update(this.selectedParametrizacion)) {
			msg = "Se ha modificado un parametro";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar un parametro";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteParametrizacion(ActionEvent actionEvent) {
		String msg;
		ParametrizacionDao paramDao = new ParametrizacionDaoImpl();
		if (paramDao.delete(this.selectedParametrizacion.getParamCodigo())) {
			msg = "Se eliminó una parametrizacion";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar una parametrizacion";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS
	public List<Parametro> getParametrizacion() {
		return parametrizacion;
	}

	public void setParametrizacion(List<Parametro> parametrizacion) {
		this.parametrizacion = parametrizacion;
	}

	public Parametro getSelectedParametrizacion() {
		return selectedParametrizacion;
	}

	public void setSelectedParametrizacion(Parametro selectedParametrizacion) {
		this.selectedParametrizacion = selectedParametrizacion;
	}

}
