package com.project.mb;

import java.io.Serializable;
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

import com.project.dao.ClientesDao;
import com.project.dao.ClientesDaoImpl;
import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.dao.UsuarioDao;
import com.project.dao.UsuarioDaoImpl;
import com.project.entities.Cliente;
import com.project.entities.Lugare;
import com.project.entities.Ordenprod;
import com.project.entities.Usuario;

@ManagedBean
@ViewScoped
public class OrdenProduccionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Ordenprod> ordenProduccion;
	private Ordenprod selectedOrden;

	private List<SelectItem> selectedItemsClientes;
	private List<SelectItem> selectedItemsUsuarios;

	private String Cliente;
	private String Responsable;

	private Date currentDate;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedOrden = new Ordenprod();
		this.selectedOrden.setCliente(new Cliente());
		this.selectedOrden.setUsuario1(new Usuario());
		this.selectedOrden.setUsuario2(new Usuario());

		this.currentDate = new Date();
		this.selectedOrden.setFActual(this.currentDate);
	}

	// METODOS
	public void btnCreateOrden(ActionEvent actionEvent) {
		System.out.println("*Cliente:"
				+ this.selectedOrden.getCliente().getCodCliente());
		System.out.println("*Responsalbe1: "
				+ this.selectedOrden.getUsuario1().getUserId());

		System.out.println("*Responsalbe2: "
				+ this.selectedOrden.getUsuario2().getUserId());

		this.selectedOrden.setFActual(new Date());
		Lugare lugar = new Lugare();
		lugar.setLugarCodigo(null);
		this.selectedOrden.setLugare(lugar);
		this.selectedOrden.setFEstim(null);
		this.selectedOrden.setFFinal(null);

		String msg = "";

		// OrdenesProdDao ordenProDao = new OrdenesProdDaoImpl();
		// if (ordenProDao.create(this.selectedOrden)) {
		// msg = "Se ha añadido una orden";
		// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		// msg, null);
		// FacesContext.getCurrentInstance().addMessage(null, message);
		// } else {
		// msg = "Error al añadir una orden";
		// FacesMessage message = new FacesMessage(
		// FacesMessage.SEVERITY_ERROR, msg, null);
		// FacesContext.getCurrentInstance().addMessage(null, message);
		// }
	}

	public void btnUpdateOrden(ActionEvent actionEvent) {
	}

	public void btnDeleteOrden(ActionEvent actionEvent) {
	}

	// SETTERS AND GETTERS

	public List<Ordenprod> getOrdenProduccion() {
		return ordenProduccion;
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

	public void setCliente(String cliente) {
		Cliente = cliente;
	}

	public String getResponsable() {
		return Responsable;
	}

	public void setResponsable(String responsable) {
		Responsable = responsable;
	}
}
