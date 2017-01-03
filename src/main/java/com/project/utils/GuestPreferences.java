package com.project.utils;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

	private static final long serialVersionUID = 1L;
	private String theme = "aristo";

	public String getTheme() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		if (params.containsKey("theme")) {
			theme = params.get("theme");
		}
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
