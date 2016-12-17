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

import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipprocesoDaoImpl;
import com.project.dao.TipprocesosDao;
import com.project.entities.Proceso;
import com.project.entities.TipoProceso;
import com.project.utils.RefreshPage;

@ManagedBean(name = "procesoBean")
@ViewScoped
public class ProcesoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Proceso> procesos;
	private Proceso selectedProceso;

	private List<SelectItem> selectedTprProceso;
	private List<SelectItem> selectedListProceso;

	private Integer proTrp;
	private Integer proPadre;
	private boolean proActivo;
	private String proDesc;

	RefreshPage refresh = new RefreshPage();

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
	}

	// METODOS
	public void btnCreateProceso() {
		System.out.println("Proceso: " + this.proTrp);
		System.out.println("Padre: " + this.proPadre);
		System.out.println("Activo: " + this.proActivo);
		System.out.println("Descripcion: " + this.proDesc);

		String msg = "";
		ProcesoDao procesoDao = new ProcesoDaoImpl();

		// TipoProceso tprProceso = new TipoProceso();
		// tprProceso.setTprCodigo(this.proTrp);
		//
		// Proceso pro = new Proceso();
		// pro.setProCodigo(this.proPadre);

		// this.selectedProceso.setTipoProceso(tprProceso);
		// this.selectedProceso.setProceso(pro);
		this.selectedProceso.setProActivo(this.proActivo);
		// this.selectedProceso.setProDescrip(this.proDesc);

		// if (procesoDao.create(this.selectedProceso)) {
		// msg = "Se ha añadido un nuevo proceso";
		// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		// msg, null);
		// FacesContext.getCurrentInstance().addMessage(null, message);
		// refresh.refresh();
		// } else {
		// msg = "Error al momento de añadir un proceso";
		// FacesMessage message = new FacesMessage(
		// FacesMessage.SEVERITY_ERROR, msg, null);
		// FacesContext.getCurrentInstance().addMessage(null, message);
		// }
	}

	public void btnUpdateProceso(ActionEvent actionEvent) {
		System.out.println("Update..");
	}

	public void btnDeleteProceso(ActionEvent actionEvent) {
		System.out.println("Delete..");
	}

	// SETTERS AND GETTERS

	public List<Proceso> getProcesos() {
		return procesos;
	}

	public Integer getProTrp() {
		return proTrp;
	}

	public void setProTrp(Integer proTrp) {
		this.proTrp = proTrp;
	}

	public Integer getProPadre() {
		return proPadre;
	}

	public void setProPadre(Integer proPadre) {
		this.proPadre = proPadre;
	}

	public boolean isProActivo() {
		return proActivo;
	}

	public void setProActivo(boolean proActivo) {
		this.proActivo = proActivo;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public Proceso getSelectedProceso() {
		return selectedProceso;
	}

	public void setSelectedProceso(Proceso selectedProceso) {
		this.selectedProceso = selectedProceso;
	}

	public List<SelectItem> getSelectedTprProceso() {
		this.selectedTprProceso = new ArrayList<SelectItem>();
		TipprocesosDao tprDao = new TipprocesoDaoImpl();
		List<TipoProceso> tipoProceso = tprDao.findAll();
		this.selectedTprProceso.clear();
		for (TipoProceso tip : tipoProceso) {
			SelectItem selectItem = new SelectItem(tip.getTprCodigo(),
					tip.getTprNombre());
			this.selectedTprProceso.add(selectItem);
		}
		return selectedTprProceso;
	}

	public void setSelectedTprProceso(List<SelectItem> selectedTprProceso) {
		this.selectedTprProceso = selectedTprProceso;
	}

	public List<SelectItem> getSelectedListProceso() {
		this.selectedListProceso = new ArrayList<SelectItem>();
		ProcesoDao procesoDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesoDao.findAll();
		this.selectedListProceso.clear();
		for (Proceso pro : proceso) {
			SelectItem selectItem = new SelectItem(pro.getProCodigo(), pro
					.getTipoProceso().getTprNombre());
			this.selectedTprProceso.add(selectItem);
		}
		return selectedListProceso;
	}

	public void setSelectedListProceso(List<SelectItem> selectedListProceso) {
		this.selectedListProceso = selectedListProceso;
	}

}
