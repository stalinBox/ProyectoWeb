package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	private List<SelectItem> selectedItemsTalla;
	private List<SelectItem> selectedItemsModelo;
	private Detalleorden selectdDetaOrden;

	private Map<Integer, String> availableItems;
	private String selectedItem;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		availableItems = new LinkedHashMap<Integer, String>();
		// selectdDetaOrden = new Detalleorden();
		// selectdDetaOrden.setModelo(new Modelo());
		// selectdDetaOrden.setTalla(new Talla());
	}

	public DetaOrdenBean() {
	}

	// SETTERS AND GETTERS

	public Map<Integer, String> getAvailableItems() {
		this.availableItems = new LinkedHashMap<Integer, String>();
		ModelosDao modeloDao = new ModelosDaoImpl();
		List<Modelo> modelo = modeloDao.findAll();
		availableItems.clear();

		for (Modelo mod : modelo) {
			// SelectItem selectItem = new SelectItem(mod.getModCodigo(),
			// mod.getModNombre());
			this.availableItems.put(mod.getModCodigo(), mod.getModNombre());
		}
		return availableItems;
	}

	public void setAvailableItems(Map<Integer, String> availableItems) {
		this.availableItems = availableItems;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<SelectItem> getSelectedItemsTalla() {
		this.selectedItemsTalla = new ArrayList<SelectItem>();
		TallasDao tallasDao = new TallasDaoImpl();
		List<Talla> tallas = tallasDao.findAll();
		selectedItemsTalla.clear();

		for (Talla tll : tallas) {
			SelectItem selectItem = new SelectItem(tll.getTalCodigo(), tll
					.getTalNumero().toString());
			this.selectedItemsTalla.add(selectItem);
		}
		return selectedItemsTalla;
	}

	public void setSelectedItemsTalla(List<SelectItem> selectedTalla) {
		this.selectedItemsTalla = selectedTalla;
	}

	public List<SelectItem> getSelectedItemsModelo() {
		this.selectedItemsModelo = new ArrayList<SelectItem>();
		ModelosDao modeloDao = new ModelosDaoImpl();
		List<Modelo> modelo = modeloDao.findAll();
		selectedItemsModelo.clear();

		for (Modelo mod : modelo) {
			SelectItem selectItem = new SelectItem(mod.getModCodigo(),
					mod.getModNombre());
			this.selectedItemsModelo.add(selectItem);
		}
		return selectedItemsModelo;
	}

	public void setSelectedItemsModelo(List<SelectItem> selectedModelo) {
		this.selectedItemsModelo = selectedModelo;
	}

	public Detalleorden getSelectdDetaOrden() {
		return selectdDetaOrden;
	}

	public void setSelectdDetaOrden(Detalleorden selectdDetaOrden) {
		this.selectdDetaOrden = selectdDetaOrden;
	}

	// DML METODOS
	public void submit() {
		System.out.println("Selectd ITEMS: " + selectedItem);

		for (SelectItem a : this.selectedItemsModelo) {
			System.out.println("ITEMSSSValue: " + a.getValue().toString());
			System.out.println("ITEMSSSLabel: " + a.getLabel());
		}

	}
}
