package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.dao.ParametrizacionDao;
import com.project.dao.ParametrizacionDaoImpl;
import com.project.entities.Lugare;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;
import com.project.entities.Proceso;
import com.project.entities.Procesosop;
import com.project.entities.Usuario;

@ManagedBean
@ViewScoped
public class ProcesosOPBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Procesosop> procesosOP;
	private Procesosop selectedProcesosOP;
	private Date fActual;
	private Integer nOrden;
	// VARIABLE BASE
	private List<SelectItem> selectedItemsOrdenes;
	private List<SelectItem> selectedItemsProceso;

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		this.selectedProcesosOP = new Procesosop();
		this.selectedProcesosOP.setProceso(new Proceso());
		this.selectedProcesosOP.setOrdenprod(new Ordenprod());
		this.selectedProcesosOP.setLugare1(new Lugare());
		this.selectedProcesosOP.setLugare2(new Lugare());
		this.selectedProcesosOP.setUsuario(new Usuario());

		this.fActual = new Date();
	}

	// METODOS
	public void onChangeProcesos(ActionEvent actionEvent) {
		System.out.println("variable: " + this.nOrden);

	}

	public void btnConsultar() {
		System.out.println("Variable: " + this.nOrden);
	}

	public void btnCreateProcesosOP() {

	}

	// SETTERS AND GETTERS

	public List<Procesosop> getProcesosOP() {
		return procesosOP;
	}

	public List<SelectItem> getSelectedItemsProceso() {
		if (this.nOrden != null && !this.nOrden.equals("") && this.nOrden != 0) {
			this.selectedItemsProceso = new ArrayList<SelectItem>();
			ParametrizacionDao paramDao = new ParametrizacionDaoImpl();
			List<Parametro> param = paramDao.findByOrdenProd(this.nOrden);
			for (Parametro p : param) {
				SelectItem selectItem = new SelectItem(p.getProceso()
						.getProCodigo(), p.getProceso().getTipoProceso()
						.getTprNombre());
				this.selectedItemsProceso.add(selectItem);
			}
			return selectedItemsProceso;
		} else {
			this.selectedItemsProceso = new ArrayList<SelectItem>();
			return selectedItemsProceso;
		}

	}

	public void setSelectedItemsProceso(List<SelectItem> selectedItemsProceso) {
		this.selectedItemsProceso = selectedItemsProceso;
	}

	public Integer getnOrden() {
		return nOrden;
	}

	public void setnOrden(Integer nOrden) {
		this.nOrden = nOrden;
	}

	public Date getfActual() {
		return fActual;
	}

	public void setfActual(Date fActual) {
		this.fActual = fActual;
	}

	public List<SelectItem> getSelectedItemsOrdenes() {
		this.selectedItemsOrdenes = new ArrayList<SelectItem>();
		OrdenesProdDao ordenesDao = new OrdenesProdDaoImpl();
		List<Ordenprod> ordenes = ordenesDao.getAllOrderN();
		for (Ordenprod ord : ordenes) {
			SelectItem selectItem = new SelectItem(ord.getOrdenprodCodigo(),
					ord.getCliente().getNombrecli());
			this.selectedItemsOrdenes.add(selectItem);
		}
		return selectedItemsOrdenes;
	}

	public void setSelectedItemsOrdenes(List<SelectItem> selectedItemsOrdenes) {
		this.selectedItemsOrdenes = selectedItemsOrdenes;
	}

	public void setProcesosOP(List<Procesosop> procesosOP) {
		this.procesosOP = procesosOP;
	}

	public Procesosop getSelectedProcesosOP() {
		return selectedProcesosOP;
	}

	public void setSelectedProcesosOP(Procesosop selectedProcesosOP) {
		this.selectedProcesosOP = selectedProcesosOP;
	}

}
