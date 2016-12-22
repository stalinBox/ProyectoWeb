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
import com.project.dao.SettingTimesDao;
import com.project.dao.SettingTimesDaoImpl;
import com.project.entities.Confproceso;
import com.project.entities.Modelo;
import com.project.entities.Proceso;
import com.project.utils.RefreshPage;

/**
 * @author Stalin
 *
 */
@ManagedBean
@ViewScoped
public class SettingsConfProcesoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Confproceso> confProceso;
	private Confproceso selectedConfProceso;

	private List<SelectItem> selectedItemsProcesos;
	private List<SelectItem> selectedItemsSubProcesos;

	private Integer codModelo;
	private Integer codProceso;
	private Integer codSubProceso;
	private double ts;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedConfProceso = new Confproceso();
		this.selectedConfProceso.setModelo(new Modelo());
		this.selectedConfProceso.setProceso1(new Proceso());
		this.selectedConfProceso.setProceso2(new Proceso());
	}

	// METODOS
	public void btnCreateConfTimes(ActionEvent actionEvent) {
		String msg = "";
		SettingTimesDao sttDao = new SettingTimesDaoImpl();

		if (sttDao.create(this.selectedConfProceso)) {
			msg = "Se ha añadido un nuevo Tiempo";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			RefreshPage refresh = new RefreshPage();
			refresh.refresh();
		} else {
			msg = "Error al momento de añadir una configuracion de tiempo";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateConfTimes(ActionEvent actionEvent) {

	}

	public void btnDeleteConfTimes(ActionEvent actionEvent) {
		String msg;
		SettingTimesDao sttDao = new SettingTimesDaoImpl();
		if (sttDao.delete(this.selectedConfProceso.getConfproCodigo())) {
			msg = "Se eliminó la confi de tiempo exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			RefreshPage refresh = new RefreshPage();
			refresh.refresh();
		} else {
			msg = "Error al eliminar la conf de tiempo";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS

	public Integer getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(Integer codModelo) {
		this.codModelo = codModelo;
	}

	public Integer getCodProceso() {
		return codProceso;
	}

	public void setCodProceso(Integer codProceso) {
		this.codProceso = codProceso;
	}

	public Integer getCodSubProceso() {
		return codSubProceso;
	}

	public void setCodSubProceso(Integer codSubProceso) {
		this.codSubProceso = codSubProceso;
	}

	public double getTs() {
		return ts;
	}

	public void setTs(double ts) {
		this.ts = ts;
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

	public List<SelectItem> getSelectedItemsSubProcesos() {
		if (this.selectedConfProceso.getProceso1().getProCodigo() != null
				&& !this.selectedConfProceso.getProceso1().getProCodigo()
						.equals("")
				&& this.selectedConfProceso.getProceso1().getProCodigo() != 0) {

			this.selectedItemsSubProcesos = new ArrayList<SelectItem>();
			ProcesoDao procesosDao = new ProcesoDaoImpl();
			List<Proceso> proceso = procesosDao
					.findByProceso(this.selectedConfProceso.getProceso1()
							.getProCodigo());
			this.selectedItemsSubProcesos.clear();
			for (Proceso pro : proceso) {
				SelectItem selectItem = new SelectItem(pro.getProCodigo(), pro
						.getTipoProceso().getTprNombre());
				this.selectedItemsSubProcesos.add(selectItem);
			}
			return selectedItemsSubProcesos;
		} else {
			{
				this.selectedItemsSubProcesos = new ArrayList<SelectItem>();
				return selectedItemsSubProcesos;
			}
		}
	}

	public void setSelectedItemsSubProcesos(
			List<SelectItem> selectedItemsSubProcesos) {
		this.selectedItemsSubProcesos = selectedItemsSubProcesos;
	}

	public List<Confproceso> getConfProceso() {
		SettingTimesDao sttDao = new SettingTimesDaoImpl();
		this.confProceso = sttDao.findAll();
		return confProceso;
	}

	public void setConfProceso(List<Confproceso> confProceso) {
		this.confProceso = confProceso;
	}

	public Confproceso getSelectedConfProceso() {
		return selectedConfProceso;
	}

	public void setSelectedConfProceso(Confproceso selectedConfProceso) {
		this.selectedConfProceso = selectedConfProceso;
	}

}
