package com.project.mb;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.project.dao.ClientesDao;
import com.project.dao.ClientesDaoImpl;
import com.project.dao.DetaOrdenDao;
import com.project.dao.DetaOrdenDaoImpl;
import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.dao.UsuarioDao;
import com.project.dao.UsuarioDaoImpl;
import com.project.entities.Cliente;
import com.project.entities.Detalleorden;
import com.project.entities.Lugare;
import com.project.entities.Modelo;
import com.project.entities.Ordenprod;
import com.project.entities.Talla;
import com.project.entities.Usuario;
import com.project.utils.ContentParam;
import com.project.utils.Items;
import com.project.utils.MyUtil;
import com.project.utils.RefreshPage;
import com.project.utils.WriteAndReadExcel;

@ManagedBean
@ViewScoped
public class OrdenProduccionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Ordenprod> ordenProduccion;
	private Ordenprod selectedOrden;

	private List<Detalleorden> detaOrden;
	private Detalleorden selectedDetaOrden;

	private List<SelectItem> selectedItemsClientes;
	private List<SelectItem> selectedItemsUsuarios;

	private static String Cliente = null;
	private static String Responsable = null;
	private static Integer Codigo = null;

	private Date currentDate;

	private static final ArrayList<Items> orderList = new ArrayList<Items>();
	private Integer total;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		// Entidad Ordenprod
		this.selectedOrden = new Ordenprod();
		this.selectedOrden.setCliente(new Cliente());
		this.selectedOrden.setUsuario(new Usuario());

		this.currentDate = new Date();
		this.selectedOrden.setFActual(this.currentDate);

		// Entidad Detalleorden
		this.selectedDetaOrden = new Detalleorden();
		this.selectedDetaOrden.setModelo(new Modelo());
		this.selectedDetaOrden.setTalla(new Talla());
	}

	// METODOS

	// DML ORDEN PRODUCCION
	public void btnCreateOrden(ActionEvent actionEvent) {
		this.selectedOrden.setFActual(new Date());
		Lugare lugar = new Lugare();
		lugar.setLugarCodigo(null);

		this.selectedOrden.setLugare(lugar);
		this.selectedOrden.setFEstim(null);
		this.selectedOrden.setFFinal(null);

		String msg = "";

		OrdenesProdDao ordenProDao = new OrdenesProdDaoImpl();
		if (ordenProDao.create(this.selectedOrden)) {
			msg = "Se ha añadido una orden";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);

			lastOrden();
		} else {
			msg = "Error al añadir una orden";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateOrden(ActionEvent actionEvent) {
	}

	public void btnDeleteOrden(ActionEvent actionEvent) {

		String msg = "";
		OrdenesProdDao ordenDao = new OrdenesProdDaoImpl();
		DetaOrdenDao dd = new DetaOrdenDaoImpl();

		if (dd.deleleByOrden(Codigo)) {
			if (ordenDao.delete(Codigo)) {
				msg = "Se ha eliminado la Orden de Produccion";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
				Codigo = null;
				Cliente = null;
				Responsable = null;
			}
			RefreshPage rf = new RefreshPage();
			rf.refresh();
		} else {
			msg = "Error No se ha eliminado la orden de produccion";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void lastOrden() {
		String msg = "";
		OrdenesProdDao ordenProDao = new OrdenesProdDaoImpl();
		Ordenprod op = ordenProDao.LastRespOrden();

		if (op != null) {
			Responsable = op.getUsuario().getUserName();
			Cliente = op.getCliente().getNombrecli();
			Codigo = op.getOrdenprodCodigo();
		} else {
			msg = "ERROR";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// DML DETALLE ORDEN
	public void btnCreateDetaOrden(ActionEvent actionEvent) {
		String msg = "";
		if (Codigo != null) {
			DetaOrdenDao detaorden = new DetaOrdenDaoImpl();
			Ordenprod ordenprod = new Ordenprod();
			ordenprod.setOrdenprodCodigo(Codigo);
			this.selectedDetaOrden.setOrdenprod(ordenprod);
			if (detaorden.create(this.selectedDetaOrden)) {
				msg = "Se ha añadido un item";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al añadir un item";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			msg = "Crear una orden primero";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void btnUpdateDetaOrden(ActionEvent actionEvent) {

	}

	public void btnDeleteDetaOrden(ActionEvent actionEvent) {
		String msg = "";
		DetaOrdenDao detaorden = new DetaOrdenDaoImpl();
		if (detaorden.delete(this.selectedDetaOrden.getDetaordenCodigo())) {
			msg = "Se ha eliminado un item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminado un item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void NextOrden() throws InvalidFormatException, IOException {
		String ruta = "";
		ruta = MyUtil.calzadoPath() + "ordenesProd/ordenLineas.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
			ContentParam.setCodOrden(Codigo);
			System.out.println("Codigo orden" + Codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SETTERS AND GETTERS
	public String getTotalsOrden() {
		int total = 0;
		DetaOrdenDao detaOrdenDao = new DetaOrdenDaoImpl();
		this.detaOrden = detaOrdenDao.findByOrden(Codigo);
		for (Detalleorden dt : detaOrden) {
			total += dt.getCantidad();
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public List<Ordenprod> getOrdenProduccion() {
		return ordenProduccion;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public static ArrayList<Items> getOrderlist() {
		return orderList;
	}

	public Integer getCodigo() {
		return Codigo;
	}

	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}

	public List<Detalleorden> getDetaOrden() {
		DetaOrdenDao detaOrdenDao = new DetaOrdenDaoImpl();
		this.detaOrden = detaOrdenDao.findByOrden(Codigo);
		return detaOrden;
	}

	public void setDetaOrden(List<Detalleorden> detaOrden) {
		this.detaOrden = detaOrden;
	}

	public Detalleorden getSelectedDetaOrden() {
		return selectedDetaOrden;
	}

	public void setSelectedDetaOrden(Detalleorden selectedDetaOrden) {
		this.selectedDetaOrden = selectedDetaOrden;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public void setOrdenProduccion(List<Ordenprod> ordenProduccion) {
		this.ordenProduccion = ordenProduccion;
	}

	public Ordenprod getSelectedOrden() {
		return selectedOrden;
	}

	public void setSelectedOrden(Ordenprod selectedOrden) {
		this.selectedOrden = selectedOrden;
	}

	public List<SelectItem> getSelectedItemsClientes() {
		this.selectedItemsClientes = new ArrayList<SelectItem>();
		ClientesDao clientesDao = new ClientesDaoImpl();
		List<Cliente> cliente = clientesDao.findAll();
		this.selectedItemsClientes.clear();
		for (Cliente cli : cliente) {
			SelectItem selectItem = new SelectItem(cli.getCodCliente(),
					cli.getNombrecli());
			this.selectedItemsClientes.add(selectItem);
		}
		return selectedItemsClientes;
	}

	public void setSelectedItemsClientes(List<SelectItem> selectedItemsClientes) {
		this.selectedItemsClientes = selectedItemsClientes;
	}

	public List<SelectItem> getSelectedItemsUsuarios() {
		this.selectedItemsUsuarios = new ArrayList<SelectItem>();
		UsuarioDao usuariosDao = new UsuarioDaoImpl();
		List<Usuario> usuario = usuariosDao.findAll();
		this.selectedItemsUsuarios.clear();
		for (Usuario user : usuario) {
			SelectItem selectItem = new SelectItem(user.getUserId(),
					user.getUserName());
			this.selectedItemsUsuarios.add(selectItem);
		}
		return selectedItemsUsuarios;
	}

	public void setSelectedItemsUsuarios(List<SelectItem> selectedItemsUsuarios) {
		this.selectedItemsUsuarios = selectedItemsUsuarios;
	}

	public String getCliente() {
		return Cliente;
	}

	public static void setCliente(String cliente) {
		Cliente = cliente;
	}

	public String getResponsable() {
		return Responsable;
	}

	public void setResponsable(String responsable) {
		Responsable = responsable;
	}
}
