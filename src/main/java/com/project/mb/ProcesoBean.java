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

@ManagedBean(name = "procesoBean")
@ViewScoped
public class ProcesoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Proceso> procesos;
	private Proceso selectedProceso;
	private boolean skip;
	private Process process = new Process();

	// CONSTRUCTOR
	@PostConstruct
	public void init() {

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
			skip = false;
			return "Guardado...!!";
		} else {
			return event.getNewStep();
		}
	}

	public void btnCreateProceso(ActionEvent actionEvent) {
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

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
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

	// HELP CLASS
	public class Process implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer TipProceso;

		private Integer ProcesoPadre;

		private Integer Modelo;

		private Integer CapModelo;

		private float CifRef;

		private float CifPresupuestado;

		private float ManoReal;

		private float ManObra;

		private Integer NumTrabajadores;

		private String ProDescp;

		public Integer getTipProceso() {
			return TipProceso;
		}

		public void setTipProceso(Integer tipProceso) {
			TipProceso = tipProceso;
		}

		public Integer getProcesoPadre() {
			return ProcesoPadre;
		}

		public void setProcesoPadre(Integer procesoPadre) {
			ProcesoPadre = procesoPadre;
		}

		public Integer getModelo() {
			return Modelo;
		}

		public void setModelo(Integer modelo) {
			Modelo = modelo;
		}

		public Integer getCapModelo() {
			return CapModelo;
		}

		public void setCapModelo(Integer capModelo) {
			CapModelo = capModelo;
		}

		public float getCifRef() {
			return CifRef;
		}

		public void setCifRef(float cifRef) {
			CifRef = cifRef;
		}

		public float getCifPresupuestado() {
			return CifPresupuestado;
		}

		public void setCifPresupuestado(float cifPresupuestado) {
			CifPresupuestado = cifPresupuestado;
		}

		public float getManoReal() {
			return ManoReal;
		}

		public void setManoReal(float manoReal) {
			ManoReal = manoReal;
		}

		public float getManObra() {
			return ManObra;
		}

		public void setManObra(float manObra) {
			ManObra = manObra;
		}

		public Integer getNumTrabajadores() {
			return NumTrabajadores;
		}

		public void setNumTrabajadores(Integer numTrabajadores) {
			NumTrabajadores = numTrabajadores;
		}

		public String getProDescp() {
			return ProDescp;
		}

		public void setProDescp(String proDescp) {
			ProDescp = proDescp;
		}

	}
}
