package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.UsuarioDao;
import com.project.dao.UsuarioDaoImpl;
import com.project.entities.Rol;
import com.project.entities.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Usuario> usuarios;
	private Usuario selectedUsuario;
	private Integer rolId;

	@PostConstruct
	public void init() {
		selectedUsuario = new Usuario();
	}

	public UsuarioBean() {
		this.usuarios = new ArrayList<Usuario>();
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public List<Usuario> getUsuarios() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		this.usuarios = usuarioDao.findAll();
		return usuarios;
	}

	public Usuario getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(Usuario selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

	public void btnCreateUsuario(ActionEvent actionEvent) {
		String msg = "";
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		Rol rol = new Rol();
		this.selectedUsuario.setUserCreation("admin");

		System.out.println("El rol elegido Rol " + this.rolId);
		rol.setRolId(this.rolId);
		this.selectedUsuario.setRol1(rol);

		Date hoy = new Date();
		// String fecha = new SimpleDateFormat("yyyy-mm-dd").format(hoy);
		this.selectedUsuario.setUserDateCreation(hoy);

		if (usuarioDao.create(this.selectedUsuario)) {
			msg = "Se creo correctamente el registro";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al crear el registro";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateUsuario(ActionEvent actionEvent) {
		String msg;
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		if (usuarioDao.update(this.selectedUsuario)) {
			msg = "Se modificó el registro";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el registro";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteUsuario(ActionEvent actionEvent) {
		String msg;
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		if (usuarioDao.delete(this.selectedUsuario.getUserId())) {
			msg = "Se eliminó el registro satisfactoriamente.";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el registro";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
