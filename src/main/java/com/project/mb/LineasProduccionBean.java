package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.LineasProdDao;
import com.project.dao.LineasProdDaoImpl;
import com.project.entities.Lineasprod;
import com.project.entities.TipoProceso;

@ManagedBean
@RequestScoped
public class LineasProduccionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Lineasprod> lineasProd;
	private Lineasprod selectedLineasProd;

	private boolean lAutomatico;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		selectedLineasProd = new Lineasprod();
	}

	public LineasProduccionBean() {
		this.lineasProd = new ArrayList<Lineasprod>();
	}

	// METODOS
	public void btnCreateLineaProd(ActionEvent actionEvent) {
		String msg = "";
		LineasProdDao lineasDao = new LineasProdDaoImpl();

		TipoProceso pp = new TipoProceso();
		pp.setTprCodigo(2);
		this.selectedLineasProd.setTipoProceso(pp);
		this.selectedLineasProd.setLineaaut(null);

		if (lineasDao.create(this.selectedLineasProd)) {
			msg = "Se ha añadido una nueva linea de producción";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al añadir una nueva linea de producción";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateLineaProd(ActionEvent actionEvent) {
		String msg = "";
		LineasProdDao lineasDao = new LineasProdDaoImpl();

		this.selectedLineasProd.setLineaaut(null);
		if (lineasDao.update(this.selectedLineasProd)) {
			msg = "Se ha modificado una linea de producción";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar la linea de producción";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteLineaProd(ActionEvent actionEvent) {
		String msg;
		LineasProdDao lineasDao = new LineasProdDaoImpl();
		if (lineasDao.delete(this.selectedLineasProd.getLineaproCodigo())) {
			msg = "Se eliminó una linea de producción";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar la linea de producción";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS
	public List<Lineasprod> getLineasProd() {
		LineasProdDao lineasDao = new LineasProdDaoImpl();
		this.lineasProd = lineasDao.findAll();
		return lineasProd;
	}

	public void setLineasProd(List<Lineasprod> lineasProd) {
		this.lineasProd = lineasProd;
	}

	public Lineasprod getSelectedLineasProd() {
		return selectedLineasProd;
	}

	public void setSelectedLineasProd(Lineasprod selectedLineasProd) {
		this.selectedLineasProd = selectedLineasProd;
	}

	public boolean islAutomatico() {
		return lAutomatico;
	}

	public void setlAutomatico(boolean lAutomatico) {
		this.lAutomatico = lAutomatico;
	}

}
