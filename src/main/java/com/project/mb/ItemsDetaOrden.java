package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "itemDO")
@ViewScoped
public class ItemsDetaOrden implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private String modelo;
	private Integer talla;
	private Integer cantidad;
	ItemsDetaOrdenBean order;

	// SETTERS AND GETTERS

	public String getModelo() {
		return modelo;
	}

	public ItemsDetaOrdenBean getOrder() {
		return order;
	}

	public void setOrder(ItemsDetaOrdenBean order) {
		this.order = order;
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

	private static final ArrayList<ItemsDetaOrdenBean> orderList = new ArrayList<ItemsDetaOrdenBean>();

	public ArrayList<ItemsDetaOrdenBean> getOrderList() {
		return orderList;
	}

	public String addAction() {
		ItemsDetaOrdenBean orderitem = new ItemsDetaOrdenBean(this.modelo,
				this.talla, this.cantidad);
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
}
