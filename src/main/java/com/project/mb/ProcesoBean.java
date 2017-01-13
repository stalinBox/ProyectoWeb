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

@ManagedBean
@ViewScoped
public class ProcesoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Proceso> procesos;
	private Proceso selectedProceso;

	private List<SelectItem> selectedTprProceso;
	private List<SelectItem> selectedItemsProceso;
	private boolean proActivo;
	private String proDesc;
	private Integer proPadre;
	private Integer proTrp;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedProceso = new Proceso();
		this.selectedProceso.setProceso(new Proceso());
	}

	// METODOS
	public void btnCreateProceso(ActionEvent actionEvent) {
		TipoProceso tprProceso = new TipoProceso();
		tprProceso.setTprCodigo(this.proTrp);

		if (this.proPadre == 0) {
			this.proPadre = null;
		}
		Proceso proce = new Proceso();
		proce.setProCodigo(this.proPadre);

		this.selectedProceso.setTipoProceso(tprProceso);
		this.selectedProceso.setProceso(proce);
		this.selectedProceso.setProActivo(this.proActivo);
		this.selectedProceso.setProDescrip(this.proDesc);

		String msg = "";
		ProcesoDao procDao = new ProcesoDaoImpl();
		if (procDao.create(this.selectedProceso)) {
			msg = "Se ha añadido un nuevo proceso";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			RefreshPage ref = new RefreshPage();
			ref.refresh();
		} else {
			msg = "Error al añadir un proceso";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteProceso(ActionEvent actionEvent) {
		String msg;
		ProcesoDao procesoDao = new ProcesoDaoImpl();
		if (procesoDao.delete(this.selectedProceso.getProCodigo())) {
			msg = "Se eliminó el proceso de calzado exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			RefreshPage ref = new RefreshPage();
			ref.refresh();
		} else {
			msg = "Error al eliminar el proceso de calzado";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS
	public List<Proceso> getProcesos() {
		ProcesoDao procesDao = new ProcesoDaoImpl();
		this.procesos = procesDao.findAll();
		return procesos;
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
		List<TipoProceso> tipoPro = tprDao.findExcludebyProceso();
		this.selectedTprProceso.clear();
		for (TipoProceso tpr : tipoPro) {
			SelectItem selectItem = new SelectItem(tpr.getTprCodigo(),
					tpr.getTprNombre());
			this.selectedTprProceso.add(selectItem);
		}
		return selectedTprProceso;
	}

	public void setSelectedTprProceso(List<SelectItem> selectedTprProceso) {
		this.selectedTprProceso = selectedTprProceso;
	}

	public List<SelectItem> getSelectedItemsProceso() {
		this.selectedItemsProceso = new ArrayList<SelectItem>();
		ProcesoDao procesoDao = new ProcesoDaoImpl();
		List<Proceso> process = procesoDao.findPadre();
		this.selectedItemsProceso.clear();
		for (Proceso pro : process) {
			SelectItem selectItem = new SelectItem(pro.getProCodigo(), pro
					.getTipoProceso().getTprNombre());
			this.selectedItemsProceso.add(selectItem);
		}
		return selectedItemsProceso;
	}

	public void setSelectedItemsProceso(List<SelectItem> selectedItemsProceso) {
		this.selectedItemsProceso = selectedItemsProceso;
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

	public Integer getProPadre() {
		return proPadre;
	}

	public void setProPadre(Integer proPadre) {
		this.proPadre = proPadre;
	}

	public Integer getProTrp() {
		return proTrp;
	}

	public void setProTrp(Integer proTrp) {
		this.proTrp = proTrp;
	}

}
