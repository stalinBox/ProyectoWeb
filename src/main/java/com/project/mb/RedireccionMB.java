package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class RedireccionMB implements Serializable {
	private static final long serialVersionUID = -9167134823170396525L;

	// FUNCIONES
	private List<String> authors = new ArrayList<String>();
	private List<String> values;

	@PostConstruct
	public void init() {
		values = new ArrayList();
		values.add("");
	}

	public void submit() {
		// save values in database
	}

	public void extend() {
		values.add("");
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public void addAuthor() {
		authors.add("");
	}

	public void removeAuthor(ActionEvent e) {
		String index = e.getComponent().getAttributes().get("index").toString();
		int i = Integer.parseInt(index);
		authors.remove(i);
	}

	public void redirect() {
		RequestContext context = RequestContext.getCurrentInstance();
		String ruta = "";
		ruta = MyUtil.basepathlogin() + "inicio.xhtml";
		context.addCallbackParam("ruta", ruta);
	}

}
