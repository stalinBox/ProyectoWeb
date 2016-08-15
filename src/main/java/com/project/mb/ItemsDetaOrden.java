package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

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

	private Map<Integer, String> vari2;

	// SETTERS AND GETTERS

	// INICIO PRUEBAS
	public Map<Integer, String> getVari2() {
		return vari2;
	}

	public void setVari2(Map<Integer, String> vari2) {
		this.vari2 = vari2;
	}

	// FIN PRUEBAS

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
		// Iterator it = vari2.keySet().iterator();
		// while (it.hasNext()) {
		// Integer key = (Integer) it.next();
		// System.out
		// .println("Clave: " + key + " -> Valor: " + vari2.get(key));
		// }

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
