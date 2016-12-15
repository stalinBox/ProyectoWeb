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

import org.primefaces.event.FlowEvent;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipprocesoDaoImpl;
import com.project.dao.TipprocesosDao;
import com.project.entities.Modelo;
import com.project.entities.Proceso;
import com.project.entities.TipoProceso;
import com.project.utils.ProcesoMapeo;
import com.project.utils.RefreshPage;

@ManagedBean(name = "procesoBean")
@ViewScoped
public class ProcesoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Proceso> procesos;
	private Proceso selectedProceso;
	private List<SelectItem> selectedTprProceso;
	private List<SelectItem> selectedModelo;
	private List<SelectItem> selectedListProceso;
	private boolean skip;
	RefreshPage refresh = new RefreshPage();

	private ProcesoMapeo procesoMapeo = new ProcesoMapeo();

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		this.selectedProceso = new Proceso();
	}

	// METODOS
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = true;
			return "Guardado...!!";
		} else {
			return event.getNewStep();
		}
	}

	public void btnCreateProceso(ActionEvent actionEvent) {
		String msg = "";
		ProcesoDao procesosDao = new ProcesoDaoImpl();

		Modelo modelo = new Modelo();
		modelo.setModCodigo(this.procesoMapeo.getModeloPro());

		TipoProceso tprProceso = new TipoProceso();
		tprProceso.setTprCodigo(this.procesoMapeo.getTprProcesoPro());

		Proceso proceso = new Proceso();
		proceso.setProCodigo(this.procesoMapeo.getPadrePro());

		if (this.procesoMapeo.getPadrePro() == 0) {
			this.selectedProceso.setProceso(null);
		} else {
			this.selectedProceso.setProceso(proceso);
		}

		// MAPEO DE OBJETO SELECTEDPROCESO
		this.selectedProceso.setModelo(modelo);
		this.selectedProceso.setTipoProceso(tprProceso);
		this.selectedProceso.setProCap(this.procesoMapeo.getCapacidadPro());
		this.selectedProceso.setProDuracion(this.procesoMapeo.getDuracionPro());
		this.selectedProceso.setProAuto(this.procesoMapeo.isAutoPro());
		this.selectedProceso.setProCifreal(this.procesoMapeo.getCifRefPro());
		this.selectedProceso.setProCifpresu(this.procesoMapeo.getCifPro());
		this.selectedProceso.setProTmano(this.procesoMapeo.gettManoPro());
		this.selectedProceso.setProTs(this.procesoMapeo.getTsPro());
		this.selectedProceso.setProCostmanobra(this.procesoMapeo
				.getCostObraPro());
		this.selectedProceso.setProCostmanreal(this.procesoMapeo
				.getCostRealPro());
		this.selectedProceso.setProNumTrab(this.procesoMapeo.getNumTrabPro());
		this.selectedProceso.setProActivo(this.procesoMapeo.isActivoPro());
		this.selectedProceso.setProDescrip(this.procesoMapeo.getDescPro());

		if (procesosDao.create(this.selectedProceso)) {
			msg = "Se ha añadido un nuevo proceso";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			refresh.refresh();
		} else {
			msg = "Error al momento de añadir un modelo";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateProceso(ActionEvent actionEvent) {
	}

	public void btnDeleteProceso(ActionEvent actionEvent) {
	}

	// SETTERS AND GETTERS

	public List<Proceso> getProcesos() {
		ProcesoDao procesosDao = new ProcesoDaoImpl();
		this.procesos = procesosDao.findAll();
		return procesos;
	}

	public List<SelectItem> getSelectedListProceso() {
		this.selectedListProceso = new ArrayList<SelectItem>();
		ProcesoDao procesoDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesoDao.findAll();
		this.selectedListProceso.clear();
		for (Proceso proce : proceso) {
			SelectItem selectItem = new SelectItem(proce.getProCodigo(), proce
					.getTipoProceso().getTprNombre());
			this.selectedListProceso.add(selectItem);
		}
		return selectedListProceso;
	}

	public void setSelectedListProceso(List<SelectItem> selectedListProceso) {
		this.selectedListProceso = selectedListProceso;
	}

	public List<SelectItem> getSelectedModelo() {
		this.selectedModelo = new ArrayList<SelectItem>();
		ModelosDao modelosDao = new ModelosDaoImpl();
		List<Modelo> modelo = modelosDao.findAll();
		this.selectedModelo.clear();
		for (Modelo mod : modelo) {
			SelectItem selectItem = new SelectItem(mod.getModCodigo(),
					mod.getModNombre());
			this.selectedModelo.add(selectItem);
		}
		return selectedModelo;
	}

	public void setSelectedModelo(List<SelectItem> selectedModelo) {
		this.selectedModelo = selectedModelo;
	}

	public List<SelectItem> getSelectedTprProceso() {
		this.selectedTprProceso = new ArrayList<SelectItem>();
		TipprocesosDao tprDao = new TipprocesoDaoImpl();
		List<TipoProceso> tipoProceso = tprDao.findAll();
		this.selectedTprProceso.clear();
		for (TipoProceso tpr : tipoProceso) {
			SelectItem selectItem = new SelectItem(tpr.getTprCodigo(),
					tpr.getTprNombre());
			this.selectedTprProceso.add(selectItem);
		}
		return selectedTprProceso;
	}

	public void setSelectedTprProceso(List<SelectItem> selectedTprProceso) {
		this.selectedTprProceso = selectedTprProceso;
	}

	public ProcesoMapeo getProcesoMapeo() {
		return procesoMapeo;
	}

	public void setProcesoMapeo(ProcesoMapeo procesoMapeo) {
		this.procesoMapeo = procesoMapeo;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
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

}
