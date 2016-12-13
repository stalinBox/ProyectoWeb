package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.entities.Proceso;
import com.project.utils.ProcesoMapeo;

@ManagedBean(name = "procesoBean")
@ViewScoped
public class ProcesoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Proceso> procesos;
	private Proceso selectedProceso;
	private boolean skip;

	private ProcesoMapeo procesoMapeo = new ProcesoMapeo();

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		this.selectedProceso = new Proceso();
	}

	// METODOS
	public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}

	public void save() {
		FacesMessage msg = new FacesMessage("Exitoso", "Guardado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		refresh();
	}

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
		if (procesosDao.create(this.selectedProceso)) {
			msg = "Se ha añadido un nuevo proceso";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			refresh();
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
