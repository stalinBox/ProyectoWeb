package com.project.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class AppBean {

	public AppBean() {

	}

	public String getAndonMonitor() {
		return MyUtil.andonMonitor();
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

	public String andonPath() {
		return MyUtil.andonPath();
	}

	public String costosPath() {
		return MyUtil.costosIndirestos();
	}

}
