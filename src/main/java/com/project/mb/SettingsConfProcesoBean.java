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
import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.entities.Confproceso;
import com.project.entities.Modelo;
import com.project.entities.Proceso;
import com.project.entities.TipLinea;
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
	private List<SelectItem> selectedItemsTipLineas;
	private List<SelectItem> selectedItemsSub;
	private Integer codLinea;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedConfProceso = new Confproceso();
		this.selectedConfProceso.setModelo(new Modelo());
		this.selectedConfProceso.setProceso1(new Proceso());
		this.selectedConfProceso.setProceso2(new Proceso());
		this.selectedConfProceso.setTipLinea(new TipLinea());
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
		TipLinea tlp = new TipLinea();
		tlp.setCodigoTiplinea(this.codLinea);

		this.selectedConfProceso.setTipLinea(tlp);
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

	public List<SelectItem> getSelectedItemsTipLineas() {
		if (this.selectedConfProceso.getProceso1().getProCodigo() != null
				&& !this.selectedConfProceso.getProceso1().getProCodigo()
						.equals("")
				&& this.selectedConfProceso.getProceso1().getProCodigo() != 0) {

			this.selectedItemsTipLineas = new ArrayList<SelectItem>();
			TipoLineaDao tipLineasDao = new TipoLineaDaoImpl();
			List<TipLinea> tipLinea = tipLineasDao
					.findbyProceso(this.selectedConfProceso.getProceso1()
							.getProCodigo());
			for (TipLinea li : tipLinea) {
				SelectItem selectItem = new SelectItem(li.getCodigoTiplinea(),
						li.getTipolinea());
				this.selectedItemsTipLineas.add(selectItem);
			}
			return selectedItemsTipLineas;
		} else {
			this.selectedItemsTipLineas = new ArrayList<SelectItem>();
			return selectedItemsTipLineas;
		}

	}

	public void setSelectedItemsTipLineas(
			List<SelectItem> selectedItemsTipLineas) {
		this.selectedItemsTipLineas = selectedItemsTipLineas;
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
