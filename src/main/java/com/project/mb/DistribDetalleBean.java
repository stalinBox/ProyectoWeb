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

import com.project.dao.DetaOrdenDao;
import com.project.dao.DetaOrdenDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.entities.Detalleorden;
import com.project.entities.Distribdetalle;
import com.project.entities.Proceso;
import com.project.entities.TipLinea;
import com.project.utils.ItemCodOrden;
import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class DistribDetalleBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Distribdetalle> distribDetalle;

	private Distribdetalle selectedDT;

	private List<Detalleorden> detalleOrden;
	private List<Detalleorden> selectedDeta;
	private Detalleorden selectedDetalleorden;

	private Integer codDetaOrden;
	private List<SelectItem> selectedItemsProceso;
	private List<SelectItem> selectedItemsTipLinea;
	private Integer nDias;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedDT = new Distribdetalle();
		this.selectedDT.setProceso(new Proceso());
		this.selectedDT.setTipLinea1(new TipLinea());
		this.codDetaOrden = ItemCodOrden.getCodOrden();
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

	public void btnCrear() {
		for (Detalleorden i : selectedDeta) {
			Detalleorden dt = new Detalleorden();
			dt.setDetaordenCodigo(i.getDetaordenCodigo());
			this.selectedDT.setDetalleorden(dt);
			System.out.println("Detalle Orden: "
					+ this.selectedDT.getDetalleorden().getDetaordenCodigo());
			System.out.println("Proceso: "
					+ this.selectedDT.getProceso().getProCodigo());
			System.out.println("Tipo Linea: "
					+ this.selectedDT.getTipLinea1().getCodigoTiplinea());
			System.out.println("_________");
		}
		// Distribdetalle items = new Distribdetalle(this.codModelo,
		// this.codProceso, this.codTipLinea);
		// this.orderList.add(items);
		// System.out.println("ITEMS GUARDADOS: " + this.orderList);
		// Items orderitem = new Items(Integer.parseInt(k.toString()), dhora,
		// m.getTime(), m.getTime(), codParam);
		// this.orderList.add(orderitem);
	}

	public void remove(Distribdetalle distrib) {
		// try {
		// this.orderList.remove(distrib);
		// // Distribdetalle = orderList.searchAll();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public void btnProcesar(ActionEvent actionEvent) {
		System.out.println("Procesando..");

	}

	public void btnDeleteDistrib(ActionEvent actionEvent) {
	}

	// SETTERS AND GETTERS

	public List<SelectItem> getSelectedItemsTipLinea() {
		this.selectedItemsTipLinea = new ArrayList<SelectItem>();
		TipoLineaDao tpDao = new TipoLineaDaoImpl();
		List<TipLinea> tp = tpDao.findbyProceso(this.selectedDT.getProceso()
				.getProCodigo());
		for (TipLinea t : tp) {
			SelectItem selectItem = new SelectItem(t.getCodigoTiplinea(),
					t.getTipolinea());
			this.selectedItemsTipLinea.add(selectItem);
		}
		return selectedItemsTipLinea;

	}

	public List<Detalleorden> getSelectedDeta() {
		return selectedDeta;
	}

	public void setSelectedDeta(List<Detalleorden> selectedDeta) {
		this.selectedDeta = selectedDeta;
	}

	public List<Detalleorden> getDetalleOrden() {
		// if(){}else{}
		DetaOrdenDao detaDao = new DetaOrdenDaoImpl();
		this.detalleOrden = detaDao.findByCCO(codDetaOrden, this.selectedDT
				.getProceso().getProCodigo(), this.selectedDT.getTipLinea1()
				.getCodigoTiplinea());
		return detalleOrden;
	}

	public void setDetalleOrden(List<Detalleorden> detalleOrden) {
		this.detalleOrden = detalleOrden;
	}

	public Detalleorden getSelectedDetalleorden() {
		return selectedDetalleorden;
	}

	public void setSelectedDetalleorden(Detalleorden selectedDetalleorden) {
		this.selectedDetalleorden = selectedDetalleorden;
	}

	public List<Distribdetalle> getDistribDetalle() {
		return distribDetalle;
	}

	public void setDistribDetalle(List<Distribdetalle> distribDetalle) {
		this.distribDetalle = distribDetalle;
	}

	public Distribdetalle getSelectedDT() {
		return selectedDT;
	}

	public void setSelectedDT(Distribdetalle selectedDT) {
		this.selectedDT = selectedDT;
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
			SelectItem selectItem = new SelectItem(p.getProCodigo(), p
					.getTipoProceso().getTprNombre());
			this.selectedItemsProceso.add(selectItem);
		}
		return selectedItemsProceso;
	}

	public void setSelectedItemsProceso(List<SelectItem> selectedItemsProceso) {
		this.selectedItemsProceso = selectedItemsProceso;
	}

}
