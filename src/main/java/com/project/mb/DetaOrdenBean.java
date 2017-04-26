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
import javax.faces.model.SelectItem;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.TallasDao;
import com.project.dao.TallasDaoImpl;
import com.project.entities.Detalleorden;
import com.project.entities.Modelo;
import com.project.entities.Talla;
import com.project.utils.Items;
import com.project.utils.MyUtil;

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
	private static ArrayList<Integer> cp = new ArrayList<Integer>();
	Items order;
	private static final ArrayList<Items> orderList = new ArrayList<Items>();
	private static Integer total;
	private Integer nDias;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		// selectdDetaOrden = new Detalleorden();
		// selectdDetaOrden.setModelo(new Modelo());
		// selectdDetaOrden.setTalla(new Talla());
	}

	public DetaOrdenBean() {
	}

	// ****************** METODOS ************************* //

	// Ingresa los pedidos en la variable orderList
	public String addAction() {
		String itemModelo = null;
		Integer itemTalla = null;
		// Convertir las claves a los valores
		for (SelectItem a : this.selectedItemsModelo) {
			if (this.modelo.equals(a.getValue().toString())) {
				itemModelo = a.getLabel();
			}
		}
		for (SelectItem b : this.selectedItemsTalla) {
			if (this.talla.toString().equals(b.getValue().toString())) {
				itemTalla = Integer.parseInt(b.getLabel().toString());
			}
		}
		// Añadir al Array los labels convertidos
		if ((itemModelo != null) && (itemTalla != null)) {
			Items orderitem = new Items(itemModelo, itemTalla, this.cantidad);
			orderList.add(orderitem);
		}

		modelo = "";
		talla = 0;
		cantidad = 0;
		totalOrden();
		return null;
	}

	public String deleteAction(Items order) {
		FacesMessage msg = new FacesMessage("Item Eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		orderList.remove(order);
		totalOrden();
		return null;
	}

	@SuppressWarnings("static-access")
	public void displayOrden() throws InvalidFormatException, IOException {
		// MATANDO PROCESO EN EXCEL
		// KillProcessEXCEL a = new KillProcessEXCEL();
		// a.main(null);

		// Store la capacidad de produccion
		// cp = wr.GenerarEstandar(orderList, nDias);

		// Store la orden total del pedido
		total = totalOrden();

		System.out.println("TOTAL DE LA ORDEN BEANDETA: " + this.total);
		System.out.println("CAPACIDAD TOTAL BEANDETA : " + this.cp);

		String ruta = "";
		ruta = MyUtil.calzadoPath() + "parametrizacion/parametrizacion.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);

			// ParametrizacionBean nn = new ParametrizacionBean(cp, total);

			// nn.setValoresCP(cp);
			// nn.setTotPedido(total);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Total de la orden
	public Integer totalOrden() {
		total = 0;
		for (Items ol : orderList) {
			total += Integer.parseInt(ol.getCantidad().toString());
		}
		return total;
	}

	// PRUEBAS
	public void submit() throws IOException {
		// Metodo para hacer pruebas
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

	public Integer getnDias() {
		return nDias;
	}

	public void setnDias(Integer nDias) {
		this.nDias = nDias;
	}

	public static ArrayList<Integer> getCp() {
		return cp;
	}

	public static void setCp(ArrayList<Integer> cp) {
		DetaOrdenBean.cp = cp;
	}

	@SuppressWarnings("static-access")
	public void setTotal(Integer total) {
		this.total = total;
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

	public Items getOrder() {
		return order;
	}

	public void setOrder(Items order) {
		this.order = order;
	}

	public Integer getTotal() {
		return total;
	}

	public ArrayList<Items> getOrderlist() {
		return orderList;
	}

}
