package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class RedireccionMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Field> m_lFields;

	public RedireccionMB() {
		m_lFields = new ArrayList<Field>();

		m_lFields.add(new Field());
	}

	public void setFields(List<Field> p_lFields) {
		m_lFields = p_lFields;
	}

	public List<Field> getFields() {
		return m_lFields;
	}

	public void onButtonRemoveFieldClick(final Field p_oField) {
		m_lFields.remove(p_oField);
	}

	public void onButtonAddFieldClick(AjaxBehaviorEvent p_oEvent) {
		m_lFields.add(new Field());
	}

	// METODO PARA REDIRECCIONAR NO QUITAR
	public void redirect() {
		RequestContext context = RequestContext.getCurrentInstance();
		String ruta = "";
		ruta = MyUtil.basepathlogin() + "inicio.xhtml";
		context.addCallbackParam("ruta", ruta);
	}

}
