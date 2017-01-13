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
import javax.faces.model.SelectItem;

import com.project.dao.LineasProdDao;
import com.project.dao.LineasProdDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.entities.Lineasprod;
import com.project.entities.Proceso;
import com.project.entities.TipLinea;

@ManagedBean
@RequestScoped
public class LineasProduccionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Lineasprod> lineasProd;
	private Lineasprod selectedLineasProd;
	private List<SelectItem> selectedItemsProcesos;
	private List<SelectItem> selectedItemsTipoLineas;
	private boolean lAutomatico;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedLineasProd = new Lineasprod();
		this.selectedLineasProd.setProceso(new Proceso());
		this.selectedLineasProd.setTipLinea(new TipLinea());
	}

	// METODOS
	public void btnCreateLineaProd(ActionEvent actionEvent) {
		String msg = "";
		LineasProdDao lineasDao = new LineasProdDaoImpl();

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

		// this.selectedLineasProd.setLineaaut(null);
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

	public List<SelectItem> getSelectedItemsTipoLineas() {
		this.selectedItemsTipoLineas = new ArrayList<SelectItem>();
		TipoLineaDao tipoLineaDao = new TipoLineaDaoImpl();
		List<TipLinea> tipoLinea = tipoLineaDao.findAll();
		for (TipLinea tip : tipoLinea) {
			SelectItem selectItem = new SelectItem(tip.getCodigoTiplinea(),
					tip.getTipolinea());
			this.selectedItemsTipoLineas.add(selectItem);
		}
		return selectedItemsTipoLineas;
	}

	public void setSelectedItemsTipoLineas(
			List<SelectItem> selectedItemsTipoLineas) {
		this.selectedItemsTipoLineas = selectedItemsTipoLineas;
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
