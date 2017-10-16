package com.project.mb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.LogoFapsDao;
import com.project.dao.LogoFapsDaoImpl;
import com.project.entities.Logosfap;
import com.project.utils.Logos;

@ManagedBean
public class LogoBean {
	// VARIABLES
	private List<Logos> listLogos;
	private Logos logos;
	private String nombreLog;

	private Logosfap SelectedLogosFaps;
	private List<Logosfap> logosfap;

	@ManagedProperty("#{LogoService}")
	private LogoService service;

	@PostConstruct
	public void init() {
		listLogos = service.getLogos();

		SelectedLogosFaps = new Logosfap();
		this.logosfap = new ArrayList<Logosfap>();
	}

	// METODOS
	public void onChange() {
		System.out.println("nombre: " + nombreLog);
		System.out.println("Imagen: ");

		String fileName = "imagen 2";

		ClassLoader classLoader = this.getClass().getClassLoader();
		File configFile = new File(classLoader.getResource(fileName).getFile());

		System.out.println("path complete: " + configFile.getPath());
	}

	public void btnCreateEmpresa(ActionEvent actionEvent) {
		String msg = "";
		LogoFapsDao logoDao = new LogoFapsDaoImpl();
		if (logoDao.create(this.SelectedLogosFaps)) {
			msg = "Se ha añadido un logo";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir un logo";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteEmpresa(ActionEvent actionEvent) {
		String msg;
		LogoFapsDao logoDao = new LogoFapsDaoImpl();
		if (logoDao.delete(this.SelectedLogosFaps.getIdlogos())) {
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

	public List<Logos> getListLogos() {
		return listLogos;
	}

	public List<Logosfap> getLogosfap() {
		LogoFapsDao logoDao = new LogoFapsDaoImpl();
		this.logosfap = logoDao.findAll();
		return logosfap;
	}

	public void setLogosfap(List<Logosfap> logosfap) {
		this.logosfap = logosfap;
	}

	public Logosfap getSelectedLogosFaps() {
		return SelectedLogosFaps;
	}

	public void setSelectedLogosFaps(Logosfap selectedLogosFaps) {
		SelectedLogosFaps = selectedLogosFaps;
	}

	public String getNombreLog() {
		return nombreLog;
	}

	public void setNombreLog(String nombreLog) {
		this.nombreLog = nombreLog;
	}

	public void setListLogos(List<Logos> listLogos) {
		this.listLogos = listLogos;
	}

	public LogoService getService() {
		return service;
	}

	public void setService(LogoService service) {
		this.service = service;
	}

	public Logos getLogos() {
		return logos;
	}

	public void setLogos(Logos logos) {
		this.logos = logos;
	}

}
