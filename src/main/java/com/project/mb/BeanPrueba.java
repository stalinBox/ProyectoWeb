package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "requestBean")
@ViewScoped
public class BeanPrueba implements Serializable {
	private static final long serialVersionUID = 1L;

	@PostConstruct
	private void init() {

	}

	// METODO

}
