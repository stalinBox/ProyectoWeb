package com.project.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BeanProgressLoading implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private String url;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String action() throws Exception {
		Thread.sleep(6000);
		System.out.println("Esta es la varible: " + message);
		return "";
	}
}
