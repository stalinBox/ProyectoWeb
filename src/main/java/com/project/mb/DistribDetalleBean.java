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

import com.project.dao.DetaOrdenDao;
import com.project.dao.DetaOrdenDaoImpl;
import com.project.dao.DistribDetalleDao;
import com.project.dao.DistribDetalleDaoImpl;
import com.project.dao.LineasProdDao;
import com.project.dao.LineasProdDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.entities.Detalleorden;
import com.project.entities.Distribdetalle;
import com.project.entities.Lineasprod;
import com.project.entities.Proceso;
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
	private List<SelectItem> selectedItemsLinea;
	private List<SelectItem> selectedItemsModDeta;
	private Integer proCodigo;
	private Integer nDias;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		codDetaOrden = ItemCodOrden.getCodOrden();
		this.selectedDistribDeta = new Distribdetalle();
		this.selectedDistribDeta.setProceso(new Proceso());
		this.selectedDistribDeta.setLineasprod(new Lineasprod());
		this.selectedDistribDeta.setDetalleorden(new Detalleorden());
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
		String msg = "";
		DistribDetalleDao distribDao = new DistribDetalleDaoImpl();
		Proceso p = new Proceso();
		p.setProCodigo(proCodigo);
		this.selectedDistribDeta.setProceso(p);
		if (distribDao.create(this.selectedDistribDeta)) {
			msg = "Se ha añadido un Item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al añadir un Item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnProcesar(ActionEvent actionEvent) {
		System.out.println("Procesando..");
	}

	public void btnDeleteDistrib(ActionEvent actionEvent) {
		String msg;
		DistribDetalleDao distribDao = new DistribDetalleDaoImpl();
		if (distribDao.delete(this.selectedDistribDeta.getDistribCodigo())) {
			msg = "Se eliminó un Item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar una Item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS

	public List<Distribdetalle> getDistribDetalle() {
		DistribDetalleDao distribDetaDao = new DistribDetalleDaoImpl();
		this.distribDetalle = distribDetaDao.findByOrden(codDetaOrden);
		return distribDetalle;
	}

	public Integer getnDias() {
		return nDias;
	}

	public void setnDias(Integer nDias) {
		this.nDias = nDias;
	}

	public List<SelectItem> getSelectedItemsModDeta() {
		this.selectedItemsModDeta = new ArrayList<SelectItem>();
		DetaOrdenDao detaDao = new DetaOrdenDaoImpl();
		List<Detalleorden> deta = detaDao.getByOrden2(codDetaOrden, proCodigo);
		for (Detalleorden d : deta) {
			SelectItem selectItem = new SelectItem(d.getDetaordenCodigo(), d
					.getModelo().getModNombre());
			this.selectedItemsModDeta.add(selectItem);
		}
		return selectedItemsModDeta;
	}

	public void setSelectedItemsModDeta(List<SelectItem> selectedItemsModDeta) {
		this.selectedItemsModDeta = selectedItemsModDeta;
	}

	public Integer getProCodigo() {
		return proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
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

	public List<SelectItem> getSelectedItemsLinea() {
		if (this.proCodigo != null && !this.proCodigo.equals("")
				&& this.proCodigo != 0) {
			this.selectedItemsLinea = new ArrayList<SelectItem>();
			LineasProdDao lpDao = new LineasProdDaoImpl();
			List<Lineasprod> lp = lpDao.findByProceso(this.proCodigo);
			for (Lineasprod l : lp) {
				SelectItem selecItem = new SelectItem(l.getLineaproCodigo(),
						l.getNomlinea());
				this.selectedItemsLinea.add(selecItem);
			}
			return selectedItemsLinea;
		} else {
			this.selectedItemsLinea = new ArrayList<SelectItem>();
			return selectedItemsLinea;
		}
	}

	public void setSelectedItemsLinea(List<SelectItem> selectedItemsLinea) {
		this.selectedItemsLinea = selectedItemsLinea;
	}

}
