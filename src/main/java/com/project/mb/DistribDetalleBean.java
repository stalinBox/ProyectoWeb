package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.mb.ProgramDiasBean.Items;
import com.project.utils.Distribdetalle;
import com.project.entities.Modelo;
import com.project.entities.Proceso;
import com.project.entities.TipLinea;
import com.project.utils.ItemCodOrden;
import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class DistribDetalleBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	// private List<Distribdetalle> distribDetalle;
	// private Distribdetalle selectedDistribDeta;

	private ArrayList<Distribdetalle> orderList = new ArrayList<Distribdetalle>();
	private String codProceso;
	private String codModelo;
	private String codTipLinea;
	private Integer codDetaOrden;
	private List<SelectItem> selectedItemsProceso;
	private List<SelectItem> selectedItemsTipLinea;
	private List<SelectItem> selectedItemsModDeta;
	private Integer nDias;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		codDetaOrden = ItemCodOrden.getCodOrden();
	}

	// DML
	public void btnReprocesar(ActionEvent actionEvent) {
		String ruta = "";
		ruta = MyUtil.calzadoPath() + "ordenesProd/insertOrder.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnCrear(ActionEvent actionEvent) {
		System.out.println("Procesando..");
		Distribdetalle items = new Distribdetalle(this.codModelo,
				this.codProceso, this.codTipLinea);
		this.orderList.add(items);
		System.out.println("ITEMS GUARDADOS: " + this.orderList);
		// Items orderitem = new Items(Integer.parseInt(k.toString()), dhora,
		// m.getTime(), m.getTime(), codParam);
		// this.orderList.add(orderitem);
	}

	public void remove(Distribdetalle distrib) {
		try {
			this.orderList.remove(distrib);
			// Distribdetalle = orderList.searchAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnProcesar(ActionEvent actionEvent) {
		System.out.println("Procesando..");
	}

	public void btnDeleteDistrib(ActionEvent actionEvent) {
	}

	// SETTERS AND GETTERS

	public ArrayList<Distribdetalle> getOrderList() {
		return orderList;
	}

	public String getCodProceso() {
		return codProceso;
	}

	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}

	public String getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(String codModelo) {
		this.codModelo = codModelo;
	}

	public String getCodTipLinea() {
		return codTipLinea;
	}

	public void setCodTipLinea(String codTipLinea) {
		this.codTipLinea = codTipLinea;
	}

	public void setOrderList(ArrayList<Distribdetalle> orderList) {
		this.orderList = orderList;
	}

	public List<SelectItem> getSelectedItemsTipLinea() {
		if (this.codModelo != null && this.codModelo != null) {
			this.selectedItemsTipLinea = new ArrayList<SelectItem>();
			TipoLineaDao tpDao = new TipoLineaDaoImpl();

			List<TipLinea> tp = tpDao.findByProcesoByModelo(this.codProceso,
					this.codModelo);
			for (TipLinea t : tp) {
				SelectItem selectItem = new SelectItem(t.getTipolinea(),
						t.getTipolinea());
				this.selectedItemsTipLinea.add(selectItem);
			}
			return selectedItemsTipLinea;
		} else {
			this.selectedItemsTipLinea = new ArrayList<SelectItem>();
			return selectedItemsTipLinea;
		}

	}

	public void setSelectedItemsTipLinea(List<SelectItem> selectedItemsTipLinea) {
		this.selectedItemsTipLinea = selectedItemsTipLinea;
	}

	public Integer getnDias() {
		return nDias;
	}

	public void setnDias(Integer nDias) {
		this.nDias = nDias;
	}

	public List<SelectItem> getSelectedItemsModDeta() {
		this.selectedItemsModDeta = new ArrayList<SelectItem>();
		ModelosDao modelosDao = new ModelosDaoImpl();
		List<Modelo> mod = modelosDao.findByDistrib(codDetaOrden);
		for (Modelo d : mod) {
			SelectItem selectItem = new SelectItem(d.getModNombre(),
					d.getModNombre());
			this.selectedItemsModDeta.add(selectItem);
		}
		return selectedItemsModDeta;
	}

	public void setSelectedItemsModDeta(List<SelectItem> selectedItemsModDeta) {
		this.selectedItemsModDeta = selectedItemsModDeta;
	}

	public Integer getCodDetaOrden() {
		return codDetaOrden;
	}

	public void setCodDetaOrden(Integer codDetaOrden) {
		this.codDetaOrden = codDetaOrden;
	}

	public List<SelectItem> getSelectedItemsProceso() {
		this.selectedItemsProceso = new ArrayList<SelectItem>();
		ProcesoDao procesoDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesoDao.findPadre();
		for (Proceso p : proceso) {
			SelectItem selectItem = new SelectItem(p.getTipoProceso()
					.getTprNombre(), p.getTipoProceso().getTprNombre());
			this.selectedItemsProceso.add(selectItem);
		}
		return selectedItemsProceso;
	}

	public void setSelectedItemsProceso(List<SelectItem> selectedItemsProceso) {
		this.selectedItemsProceso = selectedItemsProceso;
	}

}
