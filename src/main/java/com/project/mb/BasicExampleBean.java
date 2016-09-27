package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BasicExampleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ColumnModel> columns = new ArrayList<ColumnModel>(0);

	@PostConstruct
	public void init() {

		columns = new ReflectionColumnModelBuilder(User.class)
				.setExcludedProperties("id").build();

	}

	public List<User> getUsers() {

		return User.getAll();

	}

	public List<ColumnModel> getColumns() {

		return columns;

	}

}