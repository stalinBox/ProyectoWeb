package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.entities.TipLinea;

/**
 * @author Stalin
 *
 */
@ManagedBean
@ViewScoped
public class TipoLineaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// VARIABLES
	private List<TipLinea> tipoLinea;
	private TipLinea selectedTipoLinea;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedTipoLinea = new TipLinea();
	}

	// METODOS
	public void bntCreateTipLinea(ActionEvent actionEvent) {
		String msg = "";
		TipoLineaDao tipLineaDao = new TipoLineaDaoImpl();

		if (tipLineaDao.create(this.selectedTipoLinea)) {
			msg = "Se ha añadido un nuevo tipo de linea";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir un tipo de linea";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void bntUpdateTipLinea(ActionEvent actionEvent) {
		String msg;
		TipoLineaDao tipLineaDao = new TipoLineaDaoImpl();
		if (tipLineaDao.update(this.selectedTipoLinea)) {
			msg = "Se ha modificado un tipo de linea";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar un tipo de linea";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void bntDeleteTipLinea(ActionEvent actionEvent) {
		String msg;
		TipoLineaDao tipLineaDao = new TipoLineaDaoImpl();
		if (tipLineaDao.delete(this.selectedTipoLinea.getCodigoTiplinea())) {
			msg = "Se eliminó un tipo de linea";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar un tipo de linea";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS

	public List<TipLinea> getTipoLinea() {
		TipoLineaDao tipoLineaDao = new TipoLineaDaoImpl();
		this.tipoLinea = tipoLineaDao.findAll();
		return tipoLinea;
	}

	public void setTipoLinea(List<TipLinea> tipoLinea) {
		this.tipoLinea = tipoLinea;
	}

	public TipLinea getSelectedTipoLinea() {
		return selectedTipoLinea;
	}

	public void setSelectedTipoLinea(TipLinea selectedTipoLinea) {
		this.selectedTipoLinea = selectedTipoLinea;
	}

}
