package com.project.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class RedireccionMB implements Serializable {
	private static final long serialVersionUID = 1L;

	// METODO PARA REDIRECCIONAR NO QUITAR
	public void redirect() {
		RequestContext context = RequestContext.getCurrentInstance();
		String ruta = "";
		ruta = MyUtil.basepathlogin() + "inicio.xhtml";
		context.addCallbackParam("ruta", ruta);
	}

}
