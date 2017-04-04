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

import com.project.dao.LineasProdDao;
import com.project.dao.LineasProdDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.SettingTimesDao;
import com.project.dao.SettingTimesDaoImpl;
import com.project.entities.Confproceso;
import com.project.entities.Lineasprod;
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
	private List<SelectItem> selectedItemsLineas;
	private List<SelectItem> selectedItemsSub;
	private Integer codLinea;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedConfProceso = new Confproceso();
		this.selectedConfProceso.setModelo(new Modelo());
		this.selectedConfProceso.setProceso1(new Proceso());
		this.selectedConfProceso.setProceso2(new Proceso());
		this.selectedConfProceso.setLineasprod(new Lineasprod());
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
		String msg;
		SettingTimesDao sttDao = new SettingTimesDaoImpl();
		Lineasprod lp = new Lineasprod();
		lp.setLineaproCodigo(this.codLinea);

		this.selectedConfProceso.setLineasprod(lp);
		if (sttDao.update(this.selectedConfProceso)) {
			msg = "SE HA MODIFICADO";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "ERROR AL MODIFICAR";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

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

	public List<SelectItem> getSelectedItemsLineas() {
		if (this.selectedConfProceso.getProceso1().getProCodigo() != null
				&& !this.selectedConfProceso.getProceso1().getProCodigo()
						.equals("")
				&& this.selectedConfProceso.getProceso1().getProCodigo() != 0) {

			this.selectedItemsLineas = new ArrayList<SelectItem>();
			LineasProdDao lineasProDao = new LineasProdDaoImpl();
			List<Lineasprod> lineasP = lineasProDao
					.findByProceso(this.selectedConfProceso.getProceso1()
							.getProCodigo());
			for (Lineasprod li : lineasP) {
				SelectItem selectItem = new SelectItem(li.getLineaproCodigo(),
						li.getNomlinea());
				this.selectedItemsLineas.add(selectItem);
			}
			return selectedItemsLineas;
		} else {
			this.selectedItemsLineas = new ArrayList<SelectItem>();
			return selectedItemsLineas;
		}
	}

	public List<SelectItem> getSelectedItemsSub() {
		this.selectedItemsSub = new ArrayList<SelectItem>();
		ProcesoDao procesosDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesosDao.findSubProcesos();
		this.selectedItemsSub.clear();
		for (Proceso pro : proceso) {
			SelectItem selectItem = new SelectItem(pro.getProCodigo(), pro
					.getTipoProceso().getTprNombre());
			this.selectedItemsSub.add(selectItem);
		}

		return selectedItemsSub;
	}

	public void setSelectedItemsSub(List<SelectItem> selectedItemsSub) {
		this.selectedItemsSub = selectedItemsSub;
	}

	public Integer getCodLinea() {
		return codLinea;
	}

	public void setCodLinea(Integer codLinea) {
		this.codLinea = codLinea;
	}

	public void setSelectedItemsLineas(List<SelectItem> selectedItemsLineas) {
		this.selectedItemsLineas = selectedItemsLineas;
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
				&& this.selectedConfProceso.getProceso1().getProCodigo() != 0
				&& this.selectedConfProceso.getModelo().getModCodigo() != null
				&& this.selectedConfProceso.getModelo().getModCodigo() != 0) {

			this.selectedItemsSubProcesos = new ArrayList<SelectItem>();
			ProcesoDao procesosDao = new ProcesoDaoImpl();
			List<Proceso> proceso = procesosDao.findByProcesoBySetting(
					this.selectedConfProceso.getProceso1().getProCodigo(),
					this.selectedConfProceso.getModelo().getModCodigo());
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
