package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.TallasDao;
import com.project.dao.TallasDaoImpl;
import com.project.entities.Detalleorden;
import com.project.entities.Modelo;
import com.project.entities.Talla;

@ManagedBean
@ViewScoped
public class DetaOrdenBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<SelectItem> selectedTalla;
	private List<SelectItem> selectedModelo;
	private Detalleorden selectdDetaOrden;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		selectdDetaOrden = new Detalleorden();
		selectdDetaOrden.setModelo(new Modelo());
		selectdDetaOrden.setTalla(new Talla());
	}

	public DetaOrdenBean() {
	}

	// SETTERS AND GETTERS
	public List<SelectItem> getSelectedTalla() {
		this.selectedTalla = new ArrayList<SelectItem>();
		TallasDao tallasDao = new TallasDaoImpl();
		List<Talla> tallas = tallasDao.findAll();
		selectedTalla.clear();

		for (Talla tll : tallas) {
			SelectItem selectItem = new SelectItem(tll.getTalCodigo(), tll
					.getTalNumero().toString());
			this.selectedTalla.add(selectItem);
		}
		return selectedTalla;
	}

	public void setSelectedTalla(List<SelectItem> selectedTalla) {
		this.selectedTalla = selectedTalla;
	}

	public List<SelectItem> getSelectedModelo() {
		this.selectedModelo = new ArrayList<SelectItem>();
		ModelosDao modeloDao = new ModelosDaoImpl();
		List<Modelo> modelo = modeloDao.findAll();
		selectedModelo.clear();

		for (Modelo mod : modelo) {
			SelectItem selectItem = new SelectItem(mod.getModCodigo(),
					mod.getModNombre());
			this.selectedModelo.add(selectItem);
		}
		return selectedModelo;
	}

	public void setSelectedModelo(List<SelectItem> selectedModelo) {
		this.selectedModelo = selectedModelo;
	}

	public Detalleorden getSelectdDetaOrden() {
		return selectdDetaOrden;
	}

	public void setSelectdDetaOrden(Detalleorden selectdDetaOrden) {
		this.selectdDetaOrden = selectdDetaOrden;
	}

	// DML METODOS
}
