package com.project.mb;

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

import com.project.dao.DetaOrdenDao;
import com.project.dao.DetaOrdenDaoImpl;
import com.project.dao.LineasTurnosDao;
import com.project.dao.LineasTurnosDaoImpl;
import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.dao.ParamDao;
import com.project.dao.ParamDaoImpl;
import com.project.dao.ProcesosOPDao;
import com.project.dao.ProcesosOPDaoImpl;
import com.project.dao.ProgramTurnosDao;
import com.project.dao.ProgramTurnosDaoImpl;
import com.project.dao.ProgramacionDiasDao;
import com.project.dao.ProgramacionDiasDaoImpl;
import com.project.dao.UsuarioDao;
import com.project.dao.UsuarioDaoImpl;
import com.project.entities.Detalleorden;
import com.project.entities.Lineasprod;
import com.project.entities.Lineasturno;
import com.project.entities.Modelo;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;
import com.project.entities.Proceso;
import com.project.entities.Procesosop;
import com.project.entities.Programdia;
import com.project.entities.Programturno;
import com.project.entities.Talla;
import com.project.entities.Turno;
import com.project.entities.Usuario;

/**
 * @author Stalin
 *
 */
@ManagedBean
@ViewScoped
public class ProcesosOPBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Procesosop> procesosOP;
	private Procesosop selectedProcesosOP;

	private List<Programturno> programTurnos;
	private Programturno selectedProgramTurno;

	private Date fInicio;
	private Date fFin;
	private Integer nOrden;
	private Integer nProceso;
	private Integer codMod;
	private Integer dia;
	private Integer cantEstim;
	private Integer codPOP;
	private Integer codProgramD;

	// VARIABLE BASE
	private List<SelectItem> selectedItemsOrdenes;
	private List<SelectItem> selectedItemsProceso;
	private List<SelectItem> selectedItemsModelo;
	private List<SelectItem> selectedItemsTalla;
	private List<SelectItem> selectedItemsTurno;
	private List<SelectItem> selectedItemsFechas;
	private List<SelectItem> selectedItemsLT;
	private List<SelectItem> selectedItemsUser;

	private List<Detalleorden> detalleOrden;
	private List<Parametro> detallePrametro;
	private Integer total;

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		this.selectedProcesosOP = new Procesosop();
		this.selectedProcesosOP.setProceso(new Proceso());
		this.selectedProcesosOP.setOrdenprod(new Ordenprod());
		this.selectedProcesosOP.setUsuario(new Usuario());

		this.selectedProgramTurno = new Programturno();
		this.selectedProgramTurno.setProgramdia(new Programdia());
		this.selectedProgramTurno.setTalla(new Talla());
		this.selectedProgramTurno.setModelo(new Modelo());
		this.selectedProgramTurno.setTurno(new Turno());
		this.selectedProgramTurno.setLineasturno(new Lineasturno());

	}

	// METODOS
	public void btnDeleteProgramTurnos(ActionEvent actionEvent) {
		String msg;
		ProgramTurnosDao pgTurnosDao = new ProgramTurnosDaoImpl();
		if (pgTurnosDao.delete(this.selectedProgramTurno.getProgramCodigo())) {
			msg = "Se eliminó el item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnCreateProgramTurnos(ActionEvent actionEvent) {
		String msg;
		Integer dia = 1;
		Date dia2 = null;
		ProgramTurnosDao pgTurnosDao = new ProgramTurnosDaoImpl();

		Procesosop pop = new Procesosop();
		pop.setProcessopCod(this.codPOP);

		Modelo modelo = new Modelo();
		modelo.setModCodigo(this.codMod);

		Programdia progrD = new Programdia();
		progrD.setProgdiasCodigo(this.codProgramD);

		ProgramacionDiasDao programDias = new ProgramacionDiasDaoImpl();
		List<Programdia> pp = programDias.findByCodProgram(this.codProgramD);

		for (Programdia p : pp) {
			if (p.getFinicio() == dia2) {
				dia++;
				this.selectedProgramTurno.setFInicio(p.getFinicio());
				this.selectedProgramTurno.setDia(dia);
			} else {
				this.selectedProgramTurno.setFInicio(p.getFinicio());
				this.selectedProgramTurno.setDia(dia);
			}
		}

		this.selectedProgramTurno.setProgramdia(progrD);
		this.selectedProgramTurno.setProcesosop(pop);
		this.selectedProgramTurno.setModelo(modelo);
		this.selectedProgramTurno.setCantEstim(this.cantEstim);
		this.selectedProgramTurno.setCantReal(0);
		this.selectedProgramTurno.setEstadoTur("PENDIENTE");

		if (pgTurnosDao.create(this.selectedProgramTurno)) {
			msg = "Se creó un item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al crear un item para programTurnos";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onChangeProcesos(ActionEvent actionEvent) {
		System.out.println("variable codOrden: " + this.nOrden);
		System.err.println("Variable codProceso: " + this.nProceso);
		getFechas();
	}

	public void btnConsultar() {
		ProcesosOPDao popDao = new ProcesosOPDaoImpl();
		Procesosop pop = popDao.getLastResp();
		btnDeleteProcesoOP(pop.getProcessopCod());
	}

	public void btnCreateProcesosOP(ActionEvent actionEvent) {
		String msg;
		ProcesosOPDao procesoOPDao = new ProcesosOPDaoImpl();

		ParamDao paramDao = new ParamDaoImpl();
		Integer param = paramDao.findByParam(this.nProceso);

		Proceso pro = new Proceso();
		pro.setProCodigo(param);

		Ordenprod op = new Ordenprod();
		op.setOrdenprodCodigo(this.nOrden);

		this.selectedProcesosOP.setProceso(pro);
		this.selectedProcesosOP.setOrdenprod(op);
		this.selectedProcesosOP.setFActual(fInicio);
		this.selectedProcesosOP.setFEstim(fFin);

		if (procesoOPDao.create(this.selectedProcesosOP)) {
			msg = "Se creó un proceso operario";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			Procesosop pop = procesoOPDao.getLastResp();
			this.codPOP = pop.getProcessopCod();
		} else {
			msg = "Error al crear un procesoOP";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		System.out.println("******N Orden: " + this.nOrden);
	}

	public void btnDeleteProcesoOP(Integer codPop) {
		String msg;
		ProcesosOPDao procesoOPDao = new ProcesosOPDaoImpl();
		this.selectedProcesosOP.setProcessopCod(codPop);
		if (procesoOPDao.delete(this.selectedProcesosOP.getProcessopCod())) {
			msg = "Se eliminó el proceso operario";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el proceso operario";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void getFechas() {
		if (this.nProceso != 0) {
			ProgramacionDiasDao programDias = new ProgramacionDiasDaoImpl();
			List<Programdia> pp = programDias.getOrderDates(this.nOrden,
					this.nProceso);
			if (pp.isEmpty()) {
				FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"No se ha programado esta linea", " "));
			} else {
				this.fInicio = pp.get(0).getFinicio();
				this.fFin = pp.get(pp.size() - 1).getFfin();

				for (Programdia p : pp) {
					System.out.println("cantidad pares: " + p.getCantpares());
				}

			}
		} else {
			this.fInicio = null;
			this.fFin = null;
		}

	}

	// SETTERS AND GETTERS

	public Integer getnProceso() {
		return nProceso;
	}

	public List<SelectItem> getSelectedItemsUser() {
		// System.out.println("nProceso: " + this.nProceso);
		ParamDao paramDao = new ParamDaoImpl();
		List<?> param = paramDao.findParamAndLineaProd(this.nProceso);

		// System.out.println("Tamaño: " + param.size());

		for (int i = 0; i < param.size(); i++) {
			Object[] row = (Object[]) param.get(i);
			Parametro parametro = (Parametro) row[0];
			Lineasturno lp = (Lineasturno) row[1];

			this.selectedItemsUser = new ArrayList<SelectItem>();
			UsuarioDao usuarioDAO = new UsuarioDaoImpl();
			List<Usuario> usuario = usuarioDAO.findByProcesAndLP(parametro
					.getProceso().getProCodigo(), lp.getLineasprod()
					.getLineaproCodigo());
			for (Usuario us : usuario) {
				SelectItem selectItem = new SelectItem(us.getUserId(),
						us.getUserName());
				this.selectedItemsUser.add(selectItem);
			}
		}

		// this.selectedItemsUser = new ArrayList<SelectItem>();
		// UsuarioDao usuarioDAO = new UsuarioDaoImpl();
		// List<Usuario> usuario = usuarioDAO.findByProcesAndLP(1, 12);
		// for (Usuario us : usuario) {
		// SelectItem selectItem = new SelectItem(us.getUserId(),
		// us.getUserName());
		// this.selectedItemsUser.add(selectItem);
		// }
		return selectedItemsUser;
	}

	public void setSelectedItemsUser(List<SelectItem> selectedItemsUser) {
		this.selectedItemsUser = selectedItemsUser;
	}

	public Integer getCodProgramD() {
		return codProgramD;
	}

	public void setCodProgramD(Integer codProgramD) {
		this.codProgramD = codProgramD;
	}

	public List<SelectItem> getSelectedItemsFechas() {
		if (this.nOrden != null && !this.nOrden.equals("") && this.nOrden != 0) {
			this.selectedItemsFechas = new ArrayList<SelectItem>();
			ProgramacionDiasDao programDias = new ProgramacionDiasDaoImpl();
			List<Programdia> pp = programDias.getOrderDates(this.nOrden,
					this.nProceso);
			for (Programdia p : pp) {
				SelectItem selectItem = new SelectItem(p.getProgdiasCodigo(), p
						.getFinicio().toString());
				this.selectedItemsFechas.add(selectItem);
			}
			return selectedItemsFechas;
		} else {
			this.selectedItemsFechas = new ArrayList<SelectItem>();
			return selectedItemsFechas;
		}

	}

	public void setSelectedItemsFechas(List<SelectItem> selectedItemsFechas) {
		this.selectedItemsFechas = selectedItemsFechas;
	}

	public Integer getCodPOP() {
		return codPOP;
	}

	public void setCodPOP(Integer codPOP) {
		this.codPOP = codPOP;
	}

	public Integer getCantEstim() {
		// cantEstim = 165;
		return cantEstim;
	}

	public void setCantEstim(Integer cantEstim) {
		this.cantEstim = cantEstim;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public List<SelectItem> getSelectedItemsTurno() {
		this.selectedItemsTurno = new ArrayList<SelectItem>();
		LineasTurnosDao dtDao = new LineasTurnosDaoImpl();
		List<Lineasturno> deta = dtDao.findByOrden(this.nOrden, this.nProceso);
		for (Lineasturno dt : deta) {
			SelectItem selectItem = new SelectItem(dt.getTurno()
					.getTurnoCodigo(), dt.getTurno().getNombturno());
			this.selectedItemsTurno.add(selectItem);
		}
		return selectedItemsTurno;
	}

	public void setSelectedItemsTurno(List<SelectItem> selectedItemsTurno) {
		this.selectedItemsTurno = selectedItemsTurno;
	}

	public Integer getCodMod() {
		return codMod;
	}

	public void setCodMod(Integer codMod) {
		this.codMod = codMod;
	}

	public List<SelectItem> getSelectedItemsTalla() {
		this.selectedItemsTalla = new ArrayList<SelectItem>();
		DetaOrdenDao dtDao = new DetaOrdenDaoImpl();
		List<Detalleorden> deta = dtDao.findByOrdenByMod(this.nOrden,
				this.codMod);
		for (Detalleorden dt : deta) {
			SelectItem selectItem = new SelectItem(
					dt.getTalla().getTalCodigo(), dt.getTalla().getTalNumero()
							.toString());
			this.selectedItemsTalla.add(selectItem);
		}
		return selectedItemsTalla;
	}

	public void setSelectedItemsTalla(List<SelectItem> selectedItemsTalla) {
		this.selectedItemsTalla = selectedItemsTalla;
	}

	public List<SelectItem> getSelectedItemsModelo() {
		this.selectedItemsModelo = new ArrayList<SelectItem>();
		DetaOrdenDao dtDao = new DetaOrdenDaoImpl();
		List<Modelo> deta = dtDao.findByOrden2(this.nOrden);

		for (Modelo dt : deta) {
			SelectItem selectItem = new SelectItem(dt.getModCodigo(),
					dt.getModNombre());
			this.selectedItemsModelo.add(selectItem);
		}

		return selectedItemsModelo;
	}

	public void setSelectedItemsModelo(List<SelectItem> selectedItemsModelo) {
		this.selectedItemsModelo = selectedItemsModelo;
	}

	public void setnProceso(Integer nProceso) {
		this.nProceso = nProceso;
	}

	public List<Procesosop> getProcesosOP() {
		return procesosOP;
	}

	public List<Programturno> getProgramTurnos() {
		if (this.codPOP != null && !this.codPOP.equals("") && this.codPOP != 0) {
			ProgramTurnosDao pgTurnoDao = new ProgramTurnosDaoImpl();
			this.programTurnos = pgTurnoDao.findByPop(this.codPOP);
			return programTurnos;
		} else {
			this.programTurnos = new ArrayList<Programturno>();
			return programTurnos;
		}

	}

	public void setProgramTurnos(List<Programturno> programTurnos) {
		this.programTurnos = programTurnos;
	}

	public Programturno getSelectedProgramTurno() {
		return selectedProgramTurno;
	}

	public void setSelectedProgramTurno(Programturno selectedProgramTurno) {
		this.selectedProgramTurno = selectedProgramTurno;
	}

	public Date getfInicio() {
		return fInicio;
	}

	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	public Date getfFin() {
		return fFin;
	}

	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}

	public List<SelectItem> getSelectedItemsProceso() {
		if (this.nOrden != null && !this.nOrden.equals("") && this.nOrden != 0) {
			this.selectedItemsProceso = new ArrayList<SelectItem>();
			ParamDao paramDao = new ParamDaoImpl();
			List<Parametro> param = paramDao.findByOrdenProd(this.nOrden);
			for (Parametro p : param) {
				SelectItem selectItem = new SelectItem(p.getParamCodigo(), p
						.getProceso().getTipoProceso().getTprNombre()
						+ "/" + p.getTipLinea().getTipolinea());
				this.selectedItemsProceso.add(selectItem);
			}
			return selectedItemsProceso;
		} else {
			this.selectedItemsProceso = new ArrayList<SelectItem>();
			return selectedItemsProceso;
		}
	}

	public void setSelectedItemsProceso(List<SelectItem> selectedItemsProceso) {
		this.selectedItemsProceso = selectedItemsProceso;
	}

	public Integer getnOrden() {
		return nOrden;
	}

	public void setnOrden(Integer nOrden) {
		this.nOrden = nOrden;
	}

	public List<SelectItem> getSelectedItemsOrdenes() {
		this.selectedItemsOrdenes = new ArrayList<SelectItem>();
		OrdenesProdDao ordenesDao = new OrdenesProdDaoImpl();
		List<Ordenprod> ordenes = ordenesDao.getAllOrderN();
		for (Ordenprod ord : ordenes) {
			SelectItem selectItem = new SelectItem(ord.getOrdenprodCodigo(),
					ord.getOrdenprodCodigo() + " - "
							+ ord.getCliente().getNombrecli());
			this.selectedItemsOrdenes.add(selectItem);
		}
		return selectedItemsOrdenes;
	}

	public void setSelectedItemsOrdenes(List<SelectItem> selectedItemsOrdenes) {
		this.selectedItemsOrdenes = selectedItemsOrdenes;
	}

	public void setProcesosOP(List<Procesosop> procesosOP) {
		this.procesosOP = procesosOP;
	}

	public Procesosop getSelectedProcesosOP() {
		return selectedProcesosOP;
	}

	public void setSelectedProcesosOP(Procesosop selectedProcesosOP) {
		this.selectedProcesosOP = selectedProcesosOP;
	}

	public List<SelectItem> getSelectedItemsLT() {
		if (this.nOrden != null) {

			this.selectedItemsLT = new ArrayList<SelectItem>();
			LineasTurnosDao lineasturnosDao = new LineasTurnosDaoImpl();
			List<Lineasturno> lineast = lineasturnosDao
					.findByOrdenBYPROCESOSOP(this.nOrden, this.nProceso);
			for (Lineasturno lt : lineast) {
				SelectItem selectItem = new SelectItem(lt.getLtcodigo(), lt
						.getLineasprod().getNomlinea());
				this.selectedItemsLT.add(selectItem);
			}
			return selectedItemsLT;
		} else {
			this.selectedItemsLT = new ArrayList<SelectItem>();
			return selectedItemsLT;
		}
	}

	public void setSelectedItemsLT(List<SelectItem> selectedItemsLT) {
		this.selectedItemsLT = selectedItemsLT;
	}

	public List<Detalleorden> getDetalleOrden() {
		if (this.nOrden != null) {
			DetaOrdenDao detaDAO = new DetaOrdenDaoImpl();
			this.detalleOrden = detaDAO.findByOrden(this.nOrden);
			return detalleOrden;
		} else {
			this.detalleOrden = new ArrayList<Detalleorden>();
			return detalleOrden;
		}
	}

	public void setDetalleOrden(List<Detalleorden> detalleOrden) {
		this.detalleOrden = detalleOrden;
	}

	public String getTotalsOrden() {
		this.total = 0;
		DetaOrdenDao detaOrdenDao = new DetaOrdenDaoImpl();
		this.detalleOrden = detaOrdenDao.findByOrden(this.nOrden);
		for (Detalleorden dt : detalleOrden) {
			this.total += dt.getCantidad();
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Parametro> getDetallePrametro() {
		if (this.nOrden != null) {
			ParamDao paraDAO = new ParamDaoImpl();
			this.detallePrametro = paraDAO.findByOrdenProd(this.nOrden);
			return detallePrametro;
		} else {
			this.detalleOrden = new ArrayList<Detalleorden>();
			return detallePrametro;
		}
	}

	public void setDetallePrametro(List<Parametro> detallePrametro) {
		this.detallePrametro = detallePrametro;
	}

}
