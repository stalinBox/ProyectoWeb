package com.project.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
public class InicioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public void redirect() {
		String ruta = "";
		ruta = "http://www.stackoverflow.com";
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			externalContext.redirect(ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
