package com.project.mb;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.project.utils.MyUtil;

@ManagedBean
@ApplicationScoped
public class AppBean {

	public AppBean() {
		// TODO Auto-generated constructor stub
	}

	public String getBaseUrl() {
		return MyUtil.baseurl();
	}

	public String getBasePath() {
		return MyUtil.basePath();
	}

	public String calzadoPath() {
		return MyUtil.calzadoPath();
	}

	public String themePath() {
		return MyUtil.themePath();
	}

	public String messagesPath() {
		return MyUtil.messages();
	}

	public String nextPath() {
		return MyUtil.next();
	}
}
