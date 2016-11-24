package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.ClientesDao;
import com.project.dao.ClientesDaoImpl;
import com.project.entities.Cliente;

@ManagedBean
@RequestScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Cliente> clientes;
	private Cliente selectedCliente;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		selectedCliente = new Cliente();
	}

	public ClienteBean() {
		this.clientes = new ArrayList<Cliente>();
	}

	// METODOS
	public void btnCreateCliente(ActionEvent actionEvent) {
		String msg = "";
		ClientesDao clienteDao = new ClientesDaoImpl();

		if (clienteDao.create(this.selectedCliente)) {
			msg = "Se ha añadido un nuevo cliente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al añadir un cliente";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateCliente(ActionEvent actionEvent) {
		String msg;
		ClientesDao clienteDao = new ClientesDaoImpl();
		if (clienteDao.update(this.selectedCliente)) {
			msg = "Se ha modificado un cliente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar un cliente";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteCliente(ActionEvent actionEvent) {
		String msg;
		ClientesDao clienteDao = new ClientesDaoImpl();
		System.out.println("ESTEEEE: " + this.selectedCliente.getCodCliente());
		if (clienteDao.delete(this.selectedCliente.getCodCliente())) {
			msg = "Se eliminó un cliente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar un cliente";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS
	public List<Cliente> getClientes() {
		ClientesDao clienteDao = new ClientesDaoImpl();
		this.clientes = clienteDao.findAll();
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getSelectedCliente() {
		return selectedCliente;
	}

	public void setSelectedCliente(Cliente selectedCliente) {
		this.selectedCliente = selectedCliente;
	}
}
