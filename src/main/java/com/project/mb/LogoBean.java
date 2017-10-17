package com.project.mb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

import com.project.dao.LogoFapsDao;
import com.project.dao.LogoFapsDaoImpl;
import com.project.entities.Logosfap;
import com.project.utils.Logos;
import com.project.utils.UtilUploadImage;

@ManagedBean
public class LogoBean {
	// VARIABLES
	private List<Logos> listLogos;
	private Logos logos;
	private String nombreLog;
	private String FinalLogo;
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
		System.out.println("shortPath: " + nombreLog);
		String a[] = nombreLog.split("[//]");
		Integer tam = a.length;
		this.FinalLogo = a[tam - 1];
		System.out.println("NombreFinal: " + FinalLogo);

		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

		String path = servletContext.getRealPath("") + File.separatorChar
				+ "images" + File.separatorChar + "contentFlowFaps"
				+ File.separatorChar + this.FinalLogo;

		Path path2 = Paths.get(path);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void btnCreateEmpresa(ActionEvent actionEvent) {
		String a[] = nombreLog.split("[//]");
		Integer tam = a.length;
		this.FinalLogo = a[tam - 1];
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

		String path = servletContext.getRealPath("") + File.separatorChar
				+ "images" + File.separatorChar + "contentFlowFaps"
				+ File.separatorChar + this.FinalLogo;

		Path path2 = Paths.get(path);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path2);
			this.SelectedLogosFaps.setLogos(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.SelectedLogosFaps.setNomlogo(FinalLogo);
		this.SelectedLogosFaps.setUrllogo(nombreLog);
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

	public String getFinalLogo() {
		return FinalLogo;
	}

	public void setFinalLogo(String finalLogo) {
		FinalLogo = finalLogo;
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
