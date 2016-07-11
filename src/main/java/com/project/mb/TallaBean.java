package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.TallasDao;
import com.project.dao.TallasDaoImpl;
import com.project.entities.Talla;

@ManagedBean
@RequestScoped
public class TallaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Talla> tallas;
	private Talla selectedTalla;
	//private List<SelectItem> selectItemsTallas;
	//private List<SelectItem> selectItemsExcludeByTallas;
	//private Troquele troquel;
	
	@PostConstruct
	public void init() {
		selectedTalla = new Talla();
	}

	public TallaBean() {
		this.tallas = new ArrayList<Talla>();
	}

	/*public List<SelectItem> getSelectItemsExcludeByTallas() {
		this.selectItemsExcludeByTallas = new ArrayList<SelectItem>();
		TallasDao tallaExDao = new TallasDaoImpl();
		List<Talla> talla = tallaExDao.excludeByTalla(this.troquel);
		for (Talla tal : talla) {
			SelectItem selectItem = new SelectItem(tal.getTalCodigo(),
					tal.getTalNumero().toString());
			this.selectItemsExcludeByTallas.add(selectItem);
		}
		return selectItemsExcludeByTallas;
	}
public void setSelectItemsExcludeByTallas(
			List<SelectItem> selectItemsExcludeByTallas) {
		this.selectItemsExcludeByTallas = selectItemsExcludeByTallas;
	}*/	
	/*public List<SelectItem> getSelectItemsTallas() {
		this.selectItemsTallas = new ArrayList<SelectItem>();
		TallasDao tallaDao = new TallasDaoImpl();
		List<Talla> talla = tallaDao.selectItems();
		for (Talla tal : talla) {
			SelectItem selectItem = new SelectItem(tal.getTalCodigo(),
					tal.getTalNumero().toString());
			this.selectItemsTallas.add(selectItem);
		}
		return selectItemsTallas;
	}

	
	
	
	public void setSelectItemsTallas(List<SelectItem> selectItemsTallas) {
		this.selectItemsTallas = selectItemsTallas;
	}*/

	public List<Talla> getTallas() {
		TallasDao tallasDao = new TallasDaoImpl();
		this.tallas = tallasDao.findAll();
		return tallas;
	}

	public Talla getSelectedTalla() {
		return selectedTalla;
	}

	public void setSelectedTalla(Talla selectedTalla) {
		this.selectedTalla = selectedTalla;
	}
	
	//DMLS
	public void btnCreateTalla(ActionEvent actionEvent) {
		String msg = "";
		TallasDao tallasDao = new TallasDaoImpl();

		if (tallasDao.create(this.selectedTalla)) {
			msg = "Se ha añadido una nueva talla";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir una talla";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateTalla(ActionEvent actionEvent) {
		String msg;
		TallasDao tallasDao = new TallasDaoImpl();
		if (tallasDao.update(this.selectedTalla)) {
			msg = "Se ha modificado la talla";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar la talla";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteModelo(ActionEvent actionEvent) {
		String msg;
		TallasDao tallasDao = new TallasDaoImpl();
		if (tallasDao.delete(this.selectedTalla.getTalCodigo())) {
			msg = "Se eliminó la talla";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar la talla";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

}
