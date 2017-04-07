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
import javax.faces.model.SelectItem;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.utils.Distribdetalle;
import com.project.entities.Modelo;
import com.project.entities.Proceso;
import com.project.entities.TipLinea;
import com.project.utils.ItemCodOrden;
import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class DistribDetalleBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Distribdetalle> distribDetalle;
	private Distribdetalle selectedDistribDeta;
	private Integer codDetaOrden;
	private List<SelectItem> selectedItemsProceso;
	private List<SelectItem> selectedItemsTipLinea;
	private List<SelectItem> selectedItemsModDeta;
	private Integer nDias;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		codDetaOrden = ItemCodOrden.getCodOrden();
	}

	// DML
	public void btnReprocesar(ActionEvent actionEvent) {
		String ruta = "";
		ruta = MyUtil.calzadoPath() + "ordenesProd/insertOrder.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnCrear(ActionEvent actionEvent) {
		System.out.println("Procesando..");
	}

	public void btnProcesar(ActionEvent actionEvent) {
		System.out.println("Procesando..");
	}

	public void btnDeleteDistrib(ActionEvent actionEvent) {
	}

	// SETTERS AND GETTERS

	public List<Distribdetalle> getDistribDetalle() {
		return distribDetalle;
	}

	public List<SelectItem> getSelectedItemsTipLinea() {
		return selectedItemsTipLinea;
	}

	public void setSelectedItemsTipLinea(List<SelectItem> selectedItemsTipLinea) {
		this.selectedItemsTipLinea = selectedItemsTipLinea;
	}

	public Integer getnDias() {
		return nDias;
	}

	public void setnDias(Integer nDias) {
		this.nDias = nDias;
	}

	public List<SelectItem> getSelectedItemsModDeta() {
		this.selectedItemsModDeta = new ArrayList<SelectItem>();
		ModelosDao modelosDao = new ModelosDaoImpl();
		List<Modelo> mod = modelosDao.findByDistrib(codDetaOrden);
		for (Modelo d : mod) {
			SelectItem selectItem = new SelectItem(d.getModCodigo(),
					d.getModNombre());
			this.selectedItemsModDeta.add(selectItem);
		}
		return selectedItemsModDeta;
	}

	public void setSelectedItemsModDeta(List<SelectItem> selectedItemsModDeta) {
		this.selectedItemsModDeta = selectedItemsModDeta;
	}

	public void setDistribDetalle(List<Distribdetalle> distribDetalle) {
		this.distribDetalle = distribDetalle;
	}

	public Distribdetalle getSelectedDistribDeta() {
		return selectedDistribDeta;
	}

	public void setSelectedDistribDeta(Distribdetalle selectedDistribDeta) {
		this.selectedDistribDeta = selectedDistribDeta;
	}

	public Integer getCodDetaOrden() {
		return codDetaOrden;
	}

	public void setCodDetaOrden(Integer codDetaOrden) {
		this.codDetaOrden = codDetaOrden;
	}

	public List<SelectItem> getSelectedItemsProceso() {
		this.selectedItemsProceso = new ArrayList<SelectItem>();
		ProcesoDao procesoDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesoDao.findPadre();
		for (Proceso p : proceso) {
			SelectItem selectItem = new SelectItem(p.getProCodigo(), p
					.getTipoProceso().getTprNombre());
			this.selectedItemsProceso.add(selectItem);
		}
		return selectedItemsProceso;
	}

	public void setSelectedItemsProceso(List<SelectItem> selectedItemsProceso) {
		this.selectedItemsProceso = selectedItemsProceso;
	}

}
