package com.project.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.utils.MyUtil;

@ManagedBean(name = "bean")
@ViewScoped
public class BeanPrueba implements Serializable {
	// TODO Clase que genera las tablas
	// DistribucionPares/DistribucionHoras/DistribucionFechas

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {

	}

	public BeanPrueba() {

	}

	// navegar entre paginas
	public void navegaEnlace(ActionEvent actionEvent) {
		String ruta = "";
		ruta = MyUtil.messages() + "sucess.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// navegar entre paginas
	public void volverEnlace(ActionEvent actionEvent) throws Exception {
		String ruta = "";
		ruta = MyUtil.messages() + "failed.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
