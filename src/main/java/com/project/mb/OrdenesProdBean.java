package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.project.dao.UsuarioDao;
import com.project.dao.UsuarioDaoImpl;
import com.project.entities.Ordenprod;
import com.project.entities.Usuario;

@ManagedBean
@ViewScoped
public class OrdenesProdBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date currentDate;
	private Date fEntrega;
	private List<SelectItem> selectedUsuario;
	private Ordenprod selectedOp;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		currentDate = new Date();
	}

	public OrdenesProdBean() {
	}

	// SETTERS AND GETTERS
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public Date getfEntrega() {
		return fEntrega;
	}

	public void setfEntrega(Date fEntrega) {
		this.fEntrega = fEntrega;
	}

	public List<SelectItem> getSelectedUsuario() {
		this.selectedUsuario = new ArrayList<SelectItem>();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		List<Usuario> usuario = usuarioDao.findAll();
		selectedUsuario.clear();
		for (Usuario user : usuario) {
			SelectItem selectItem = new SelectItem(user.getUserId(),
					user.getUserName());
			this.selectedUsuario.add(selectItem);
		}
		return selectedUsuario;
	}

	public void setSelectedUsuario(List<SelectItem> selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

	public Ordenprod getSelectedOp() {
		return selectedOp;
	}

	public void setSelectedOp(Ordenprod selectedOp) {
		this.selectedOp = selectedOp;
	}

}
