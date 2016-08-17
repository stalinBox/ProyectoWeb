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
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipprocesoDaoImpl;
import com.project.dao.TipprocesosDao;
import com.project.entities.ModTrqTal;
import com.project.entities.Modelo;
import com.project.entities.Proceso;
import com.project.entities.TipoProceso;

@ManagedBean(name = "procesoBean")
@ViewScoped
public class ProcesoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	// VARIABLES
	private List<ColProcesos> proList = new ArrayList<ColProcesos>();
	private List<SelectItem> selectedItemsModelo;
	private List<SelectItem> selectedItemsProcesos;
	private float duracion;
	private Integer capacidad;
	private float tBase;
	private float tMaq;
	private float tMano;
	private float tStandar;
	private float manObra;
	private float manReal;
	private String descripcion;
	private String automatico;
	private Proceso selectedProceso;

	// SETTERS AND GETTERS

	public Proceso getSelectedProceso() {
		return selectedProceso;
	}

	public void setSelectedProceso(Proceso selectedProceso) {
		this.selectedProceso = selectedProceso;
	}

	public List<ColProcesos> getProList() {
		if (proList.isEmpty()) {
			prepareSampleData();
		}
		return proList;
	}

	public void setProList(List<ColProcesos> proList) {
		this.proList = proList;
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

	public void setSelectedItemsModelo(List<SelectItem> selectedItemsModelo) {
		this.selectedItemsModelo = selectedItemsModelo;
	}

	public List<SelectItem> getSelectedItemsProcesos() {
		this.selectedItemsProcesos = new ArrayList<SelectItem>();
		TipprocesosDao tpDao = new TipprocesoDaoImpl();
		List<TipoProceso> tp = tpDao.findAll();
		selectedItemsProcesos.clear();
		for (TipoProceso tipoPro : tp) {
			SelectItem selectItem = new SelectItem(tipoPro.getTprCodigo(),
					tipoPro.getTprNombre());
			this.selectedItemsProcesos.add(selectItem);
		}
		return selectedItemsProcesos;
	}

	public void setSelectedItemsProcesos(List<SelectItem> selectedItemsProcesos) {
		this.selectedItemsProcesos = selectedItemsProcesos;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public float gettBase() {
		return tBase;
	}

	public void settBase(float tBase) {
		this.tBase = tBase;
	}

	public float gettMaq() {
		return tMaq;
	}

	public void settMaq(float tMaq) {
		this.tMaq = tMaq;
	}

	public float gettMano() {
		return tMano;
	}

	public void settMano(float tMano) {
		this.tMano = tMano;
	}

	public float gettStandar() {
		return tStandar;
	}

	public void settStandar(float tStandar) {
		this.tStandar = tStandar;
	}

	public float getManObra() {
		return manObra;
	}

	public void setManObra(float manObra) {
		this.manObra = manObra;
	}

	public float getManReal() {
		return manReal;
	}

	public void setManReal(float manReal) {
		this.manReal = manReal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAutomatico() {
		return automatico;
	}

	public void setAutomatico(String automatico) {
		this.automatico = automatico;
	}

	// FUNCIONES
	private void prepareSampleData() {
		// ColProcesos toyotaVios = new ColProcesos();
		// toyotaVios.setModelo(1);
		// toyotaVios.setDescripcion("DESCRIPCION");
		//
		// proList.add(toyotaVios);
	}

	public void addProList() {
		ColProcesos newPro = new ColProcesos();
		proList.add(newPro);
	}

	public void removeProList(ColProcesos proce) {
		proList.remove(proce);
	}
}
