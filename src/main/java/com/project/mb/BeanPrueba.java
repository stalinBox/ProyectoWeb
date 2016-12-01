package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "CheckboxTestBean")
@ViewScoped
public class BeanPrueba implements Serializable {

	private static final long serialVersionUID = 1L;
	public List<Boolean> list = new ArrayList<Boolean>();

	public BeanPrueba() {
		for (int i = 0; i < 5; i++) {
			list.add(Boolean.FALSE);
		}
	}

	public void actionListener(ActionEvent evt) {
		System.out.println("*** dumping whole form");
		System.out.println("*** list = " + list);
	}

	public List<Boolean> getList() {
		return list;
	}

	public void setList(List<Boolean> list) {
		this.list = list;
	}

	Items a = new Items(null, null, null);
}
