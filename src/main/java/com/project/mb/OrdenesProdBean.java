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

import com.project.dao.LugaresDao;
import com.project.dao.LugaresDaoImpl;
import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.dao.UsuarioDao;
import com.project.dao.UsuarioDaoImpl;
import com.project.entities.Lugare;
import com.project.entities.Ordenprod;
import com.project.entities.Usuario;

@ManagedBean
@ViewScoped
public class OrdenesProdBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date currentDate;
	private Date fEntrega;
	private Integer idUserCliente;
	private Integer idUserResp;
	private List<SelectItem> selectedUsuario;
	private List<SelectItem> selectedLugar;

	private Ordenprod selectedOp;
	private Integer nombProv;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		currentDate = new Date();

		selectedOp = new Ordenprod();
		selectedOp.setUsuario1(new Usuario());
		selectedOp.setUsuario2(new Usuario());
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

	public Integer getIdUserCliente() {
		return idUserCliente;
	}

	public void setIdUserCliente(Integer idUserCliente) {
		this.idUserCliente = idUserCliente;
	}

	public Integer getIdUserResp() {
		return idUserResp;
	}

	public void setIdUserResp(Integer idUserResp) {
		this.idUserResp = idUserResp;
	}

	public Integer getNombProv() {
		return nombProv;
	}

	public void setNombProv(Integer nombProv) {
		this.nombProv = nombProv;
	}

	public List<SelectItem> getSelectedLugar() {
		this.selectedLugar = new ArrayList<SelectItem>();
		LugaresDao lugarDao = new LugaresDaoImpl();
		List<Lugare> lugar = lugarDao.findAll();
		selectedLugar.clear();

		for (Lugare lug : lugar) {
			SelectItem selectItem = new SelectItem(lug.getLugarCodigo(),
					lug.getNomlugar());
			this.selectedLugar.add(selectItem);
		}

		return selectedLugar;
	}

	public void setSelectedLugar(List<SelectItem> selectedLugar) {
		this.selectedLugar = selectedLugar;
	}

	// DMLS
	public void saveOrdeProd(ActionEvent actionEvent) {
		System.out.println("Save into OrdenProd");
		String msg = "";
		OrdenesProdDao oProdDao = new OrdenesProdDaoImpl();
		// this.selectedOp.setNombordprod(nombProv);
		this.selectedOp.setFActual(currentDate);
		this.selectedOp.setFFinal(fEntrega);
		this.selectedOp.setLugare(new Lugare());
		if (oProdDao.create(this.selectedOp)) {
			msg = "Se ha añadido una nueva orden de producción";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir un troquel";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
