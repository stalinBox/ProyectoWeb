package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
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

@ManagedBean
@ViewScoped
public class ProcesoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private Proceso proceso;
	private List<Proceso> selectedProceso;
	private List<SelectItem> selectItemsModelo;
	private List<SelectItem> selectItemsTipPro;
	private List<SelectItem> selectItemsPredecesor;
	private String[] ItemsPredecesor;
	private String codMod;
	private String tprCod;
	private String proCod;
	private String proCap;
	private String proDur;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		proceso = new Proceso();
	}

	public ProcesoBean() {
		this.selectedProceso = new ArrayList<Proceso>();
	}

	// SETTERS AND GETTERS
	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public List<Proceso> getSelectedProceso() {
		return selectedProceso;
	}

	public void setSelectedProceso(List<Proceso> selectedProceso) {
		this.selectedProceso = selectedProceso;
	}

	public String getCodMod() {
		return codMod;
	}

	public void setCodMod(String codMod) {
		this.codMod = codMod;
	}

	public String getTprCod() {
		return tprCod;
	}

	public void setTprCod(String tprCod) {
		this.tprCod = tprCod;
	}

	public String getProCod() {
		return proCod;
	}

	public void setProCod(String proCod) {
		this.proCod = proCod;
	}

	public String getProCap() {
		return proCap;
	}

	public void setProCap(String proCap) {
		this.proCap = proCap;
	}

	public String getProDur() {
		return proDur;
	}

	public void setProDur(String proDur) {
		this.proDur = proDur;
	}

	public List<SelectItem> getSelectItemsModelo() {
		this.selectItemsModelo = new ArrayList<SelectItem>();
		ProcesoDao modDao = new ProcesoDaoImpl();
		List<ModTrqTal> modelo = modDao.selectItemsModelos();
		for (ModTrqTal mod : modelo) {
			SelectItem selectItem = new SelectItem(mod.getMttCodigo(), mod
					.getModelo().getModNombre());
			this.selectItemsModelo.add(selectItem);
		}
		return selectItemsModelo;
	}

	public void setSelectItemsModelo(List<SelectItem> selectItemsModelo) {

		this.selectItemsModelo = selectItemsModelo;
	}

	public List<SelectItem> getSelectItemsTipPro() {
		this.selectItemsTipPro = new ArrayList<SelectItem>();
		TipprocesosDao tipProDao = new TipprocesoDaoImpl();
		List<TipoProceso> tipProceso = tipProDao.findAll();
		for (TipoProceso t : tipProceso) {
			SelectItem selectItem = new SelectItem(t.getTprCodigo(),
					t.getTprNombre());
			this.selectItemsTipPro.add(selectItem);
		}

		return selectItemsTipPro;
	}

	public void setSelectItemsTipPro(List<SelectItem> selectItemsTipPro) {
		this.selectItemsTipPro = selectItemsTipPro;
	}

	public List<SelectItem> getSelectItemsPredecesor() {
		this.selectItemsPredecesor = new ArrayList<SelectItem>();
		ProcesoDao proDao = new ProcesoDaoImpl();
		List<Proceso> predecesor = proDao.findAll();

		for (Proceso p : predecesor) {
			SelectItem selectItem = new SelectItem(p.getProCodigo(), p
					.getTipoProceso().toString());
			this.selectItemsPredecesor.add(selectItem);
		}
		return selectItemsPredecesor;
	}

	public void setSelectItemsPredecesor(List<SelectItem> selectItemsPredecesor) {
		this.selectItemsPredecesor = selectItemsPredecesor;
	}

	public String[] getItemsPredecesor() {
		return ItemsPredecesor;
	}

	public void setItemsPredecesor(String[] itemsPredecesor) {
		ItemsPredecesor = itemsPredecesor;
	}

	// METODOS
	public void btnCreateProceso() {

		System.out.println("Se ha guardado");
	}
}
