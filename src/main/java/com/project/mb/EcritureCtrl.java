package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.project.entities.Usuario;

@ManagedBean
@SessionScoped
public class EcritureCtrl implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private Date date;

	private String description;
	private List<Usuario> lignes = new ArrayList<Usuario>();
	private Usuario unUser;

	public String getCode() {
		return this.code;
	}

	public Date getDate() {
		return this.date;
	}

	public String getDescription() {
		return this.description;
	}

	public List<Usuario> getLignes() {
		return this.lignes;
	}

	public Usuario getUnUser() {
		return this.unUser;
	}

	@PostConstruct
	private void init() {
		this.lignes.add(new Usuario());
	}

	public void newLine(ActionEvent actionEvent) {
		this.lignes.add(new Usuario());
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLignes(List<Usuario> lignes) {
		this.lignes = lignes;
	}

	public void setUnUser(Usuario unUser) {
		this.unUser = unUser;
	}

}