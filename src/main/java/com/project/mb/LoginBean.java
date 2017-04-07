package com.project.mb;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
//import javax.inject.Named;

import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.project.dao.UsuarioDao;
import com.project.dao.UsuarioDaoImpl;
import com.project.entities.Usuario;
import com.project.utils.MyUtil;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioDao usuarioDao;

	public LoginBean() {
		this.usuarioDao = new UsuarioDaoImpl();
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message;
		boolean loggedIn;

		String ruta = "";
		this.usuario = this.usuarioDao.login(this.usuario);
		if (this.usuario != null) {
			loggedIn = true;
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("usuario", this.usuario.getUserName());
			ruta = MyUtil.basepathlogin() + "inicio.jsf";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Bienvenido", this.usuario.getUserName());
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Error de autentificación", "Usuario y/o Clave incorrectos");

			if (this.usuario == null) {
				this.usuario = new Usuario();
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("loggedIn", loggedIn);
		context.addCallbackParam("ruta", ruta);
	}

	public void logout() {
		String ruta = MyUtil.basepathlogin() + "login.xhtml";
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpSession sesion = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		sesion.invalidate();

		context.addCallbackParam("loggetOut", true);
		context.addCallbackParam("ruta", ruta);
	}

}
