package com.project.mb;

import java.io.IOException;
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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.project.dao.DetaOrdenDao;
import com.project.dao.DetaOrdenDaoImpl;
import com.project.dao.DistribDetaDao;
import com.project.dao.DistribDetaDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.entities.Detalleorden;
import com.project.entities.Distribdetalle;
import com.project.entities.Proceso;
import com.project.entities.TipLinea;
import com.project.utils.ItemCodOrden;
import com.project.utils.ItemsParams;
import com.project.utils.MyUtil;
import com.project.utils.WriteAndReadExcel;

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
	private Double nDias;

	private ArrayList<ItemsDistrib> orderList = new ArrayList<ItemsDistrib>();
	private ArrayList<ItemsParams> orderListParams = new ArrayList<ItemsParams>();

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedDT = new Distribdetalle();
		this.selectedDT.setProceso(new Proceso());
		this.selectedDT.setTipLinea(new TipLinea());
		this.codDetaOrden = ItemCodOrden.getCodOrden();
	}

	// DML
	public void btnProcesar(ActionEvent actionEvent) {
		System.out.println("Procesando..");

		Integer cp = 0;

		ProcesoDao proDao = new ProcesoDaoImpl();
		List<Proceso> process = proDao.findProcesosDistribByOrden(codDetaOrden);

		TipoLineaDao tipLineaDao = new TipoLineaDaoImpl();
		List<TipLinea> tipo = tipLineaDao.findTpLineaByDistrib(codDetaOrden);

		DistribDetaDao distribDao = new DistribDetaDaoImpl();
		List<Distribdetalle> distro = null;

		ItemsDistrib orderList1 = new ItemsDistrib();
		ItemsParams orderListParams1 = new ItemsParams();
		for (Proceso p : process) {
			for (TipLinea tp : tipo) {
				distro = distribDao.findByOrderByProByTL(codDetaOrden,
						p.getProCodigo(), tp.getCodigoTiplinea());

				if (!(distro.isEmpty())) {
					for (Distribdetalle poc : distro) {
						orderList1 = new ItemsDistrib(poc.getDetalleorden()
								.getModelo().getModNombre(), poc
								.getDetalleorden().getTalla().getTalNumero(),
								poc.getDetalleorden().getCantidad());
						this.orderList.add(orderList1);
					}
					// System.out.println("PROCESO: "
					// + p.getTipoProceso().getTprNombre() + " Codigo: "
					// + p.getProCodigo());
					//
					// System.out.println("LINEA: " + tp.getTipolinea()
					// + " Codigo: " + tp.getCodigoTiplinea());

					// for (ItemsDistrib listado : orderList) {
					// System.out.println("Modelo: " + listado.getModelo()
					// + " Talla: " + listado.getTalla()
					// + " Cantidad: " + listado.getCantidad());
					// }
					WriteAndReadExcel wr = new WriteAndReadExcel();

					try {
						cp = wr.GenerarEstandar(orderList, this.nDias,
								p.getProCodigo(), tp.getCodigoTiplinea());
					} catch (InvalidFormatException | IOException e) {
						e.printStackTrace();
					}
					orderListParams1 = new ItemsParams(p.getTipoProceso()
							.getTprNombre(), tp.getTipolinea(), cp);
					this.orderListParams.add(orderListParams1);

				} else {
					continue;
				}
				this.orderList.clear();
			}

		}

		String ruta = "";
		ruta = MyUtil.calzadoPath() + "parametrizacion/param.jsf";

		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		String msg = "";
		DistribDetaDao distribDao = new DistribDetaDaoImpl();
		for (Detalleorden i : selectedDeta) {
			Detalleorden dt = new Detalleorden();
			dt.setDetaordenCodigo(i.getDetaordenCodigo());
			this.selectedDT.setDetalleorden(dt);

			if (distribDao.create(this.selectedDT)) {
				msg = "Se ha añadido un ITEM";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al momento de añadir un ITEM";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public void btnDeleteDistrib(ActionEvent actionEvent) {
		String msg;
		DistribDetaDao distribDao = new DistribDetaDaoImpl();
		if (distribDao.delete(this.selectedDT.getDistribCodigo())) {
			msg = "Se eliminó el item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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

	public ArrayList<ItemsParams> getOrderListParams() {
		return orderListParams;
	}

	public void setOrderListParams(ArrayList<ItemsParams> orderListParams) {
		this.orderListParams = orderListParams;
	}

	public ArrayList<ItemsDistrib> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<ItemsDistrib> orderList) {
		this.orderList = orderList;
	}

	public List<Detalleorden> getSelectedDeta() {
		return selectedDeta;
	}

	public void setSelectedDeta(List<Detalleorden> selectedDeta) {
		this.selectedDeta = selectedDeta;
	}

	public List<Detalleorden> getDetalleOrden() {
		DetaOrdenDao detaDao = new DetaOrdenDaoImpl();
		this.detalleOrden = detaDao.findByCCO(codDetaOrden, this.selectedDT
				.getProceso().getProCodigo(), this.selectedDT.getTipLinea()
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
		DistribDetaDao distribDao = new DistribDetaDaoImpl();
		this.distribDetalle = distribDao.findByOrder(this.codDetaOrden);
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

	public Double getnDias() {
		return nDias;
	}

	public void setnDias(Double nDias) {
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

	public class ItemsDistrib implements Serializable {
		private static final long serialVersionUID = 1L;

		private String modelo;
		private Integer talla;
		private Integer cantidad;

		public ItemsDistrib(String modelo, Integer talla, Integer cantidad) {
			this.modelo = modelo;
			this.talla = talla;
			this.cantidad = cantidad;
		}

		public ItemsDistrib() {
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public Integer getTalla() {
			return talla;
		}

		public void setTalla(Integer talla) {
			this.talla = talla;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
	}

}
