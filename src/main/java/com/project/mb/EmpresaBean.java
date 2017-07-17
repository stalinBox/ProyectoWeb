package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;
import javax.faces.model.SelectItem;

import com.project.dao.EmpresaDao;
import com.project.dao.EmpresaDaoImpl;
import com.project.entities.Empresa;

@ManagedBean
@ViewScoped
public class EmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Empresa> empresa;
	private Empresa selectedEmpresa;
	private List<SelectItem> selectItemsEmpresa;

	@PostConstruct
	public void init() {
		this.selectedEmpresa = new Empresa();
	}

	// METODOS
	public void btnCreateEmpresa(ActionListener actionListener) {
		String msg = "";
		EmpresaDao empresaDao = new EmpresaDaoImpl();

		if (empresaDao.create(this.selectedEmpresa)) {
			msg = "Se ha añadido un Item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir un item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateEmpresa(ActionListener actionListener) {
		String msg;
		EmpresaDao empresaDao = new EmpresaDaoImpl();
		if (empresaDao.update(this.selectedEmpresa)) {
			msg = "Se ha modificado un Item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar un Item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteEmpresa(ActionListener actionListener) {
		String msg;
		EmpresaDao empresaDao = new EmpresaDaoImpl();
		if (empresaDao.delete(this.selectedEmpresa.getEmpCodigo())) {
			msg = "Se eliminó un Item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar un Item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS
	public List<Empresa> getEmpresa() {
		EmpresaDao empresaDao = new EmpresaDaoImpl();
		this.empresa = empresaDao.findAll();
		return empresa;
	}

	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}

	public Empresa getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public void setSelectedEmpresa(Empresa selectedEmpresa) {
		this.selectedEmpresa = selectedEmpresa;
	}

	public List<SelectItem> getSelectItemsEmpresa() {
		return selectItemsEmpresa;
	}

	public void setSelectItemsEmpresa(List<SelectItem> selectItemsEmpresa) {
		this.selectItemsEmpresa = selectItemsEmpresa;
	}

}
