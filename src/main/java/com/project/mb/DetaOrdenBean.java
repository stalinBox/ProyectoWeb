package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

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

	private String modelo;
	private Integer talla;
	private Integer cantidad;
	ItemsDetaOrdenBean order;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		// selectdDetaOrden = new Detalleorden();
		// selectdDetaOrden.setModelo(new Modelo());
		// selectdDetaOrden.setTalla(new Talla());
	}

	public DetaOrdenBean() {
	}

	// SETTERS AND GETTERS
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

	public ItemsDetaOrdenBean getOrder() {
		return order;
	}

	public void setOrder(ItemsDetaOrdenBean order) {
		this.order = order;
	}

	private ArrayList<ItemsDetaOrdenBean> orderList = new ArrayList<ItemsDetaOrdenBean>();

	public ArrayList<ItemsDetaOrdenBean> getOrderlist() {
		return orderList;
	}

	// DML METODOS
	public void submit() {
		for (SelectItem a : this.selectedItemsModelo) {
			System.out.println("ITEMSSSValue: " + a.getValue().toString());
			System.out.println("ITEMSSSLabel: " + a.getLabel());
		}
	}

	public String addAction() {
		ItemsDetaOrdenBean orderitem = new ItemsDetaOrdenBean(this.modelo,
				this.talla, this.cantidad);

		/* AQUI VA EL CODIGO PARA SACAR EN LA PANTALLA */
		orderList.add(orderitem);
		modelo = "";
		talla = 0;
		cantidad = 0;
		return null;
	}

	public String deleteAction(ItemsDetaOrdenBean order) {

		orderList.remove(order);
		return null;
	}

	public void onEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Editado",
				((ItemsDetaOrdenBean) event.getObject()).getModelo());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Item Eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		System.out.println("pokemon GO: "
				+ ((ItemsDetaOrdenBean) event.getObject()).getModelo()
						.toString());
		orderList.remove(((ItemsDetaOrdenBean) event.getObject()));
	}

	public void generateOrder() {
		System.out.println("Order Sucessfull");
	}
}
