package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import com.project.dao.EmpresaDao;
import com.project.dao.EmpresaDaoImpl;
import com.project.entities.Empresa;
import com.project.utils.UtilUploadImage;

@ManagedBean
@RequestScoped
public class EmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Empresa> empresa;
	private Empresa selectedEmpresa;
	private String imagenEmpresa;

	// CONSTR
	@PostConstruct
	public void init() {
		this.selectedEmpresa = new Empresa();
	}

	// METODOS
	/**
	 * METODO PARA SUBIR LA IMAGEN AL SERVIDOR
	 * 
	 * @param event
	 */
	public void UploadImage(FileUploadEvent event) {
		FacesMessage mensaje = new FacesMessage();
		try {
			selectedEmpresa.setEmpLogo(event.getFile().getContents());
			imagenEmpresa = UtilUploadImage
					.saveFileToTemp(selectedEmpresa.getEmpLogo(), event
							.getFile().getFileName());

			mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
			mensaje.setSummary("Imagen subida exitosamente");

		} catch (Exception e) {
			e.printStackTrace();
			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensaje.setSummary("Problemas al subir la imagen");
		}
		FacesContext.getCurrentInstance().addMessage("Mensage", mensaje);
	}

	public void btnCreateEmpresa(ActionEvent actionEvent) {
		String msg = "";
		EmpresaDao empresaDao = new EmpresaDaoImpl();

		if (empresaDao.create(this.selectedEmpresa)) {
			msg = "Se ha añadido una empresa";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir una empresa";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateEmpresa(ActionEvent actionEvent) {
		String msg;
		System.out.println("Ha entrado");
		EmpresaDao empresaDao = new EmpresaDaoImpl();
		if (empresaDao.update(this.selectedEmpresa)) {
			msg = "Se ha modificado";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteEmpresa(ActionEvent actionEvent) {
		String msg;
		EmpresaDao empresaDao = new EmpresaDaoImpl();
		if (empresaDao.delete(this.selectedEmpresa.getEmpCodigo())) {
			msg = "Se eliminó exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar";
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

	public String getImagenEmpresa() {
		return imagenEmpresa;
	}

	public void setImagenEmpresa(String imagenEmpresa) {
		this.imagenEmpresa = imagenEmpresa;
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

}
