package com.project.reportsBean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.project.dao.EmpresaDao;
import com.project.dao.EmpresaDaoImpl;
import com.project.dao.LogoFapsDao;
import com.project.dao.LogoFapsDaoImpl;
import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.dao.ReportesDao;
import com.project.dao.ReportesDaoImpl;
import com.project.entities.Detalleorden;
import com.project.entities.Empresa;
import com.project.entities.Logosfap;
import com.project.entities.Modelo;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author STALIN RAMIREZ
 *
 */
@ManagedBean
@ViewScoped
public class CapxOrdenBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<SelectItem> selectedItemsOrdenes;
	private Integer nOrden;
	private List<Modelo> RPTmodelos = new ArrayList<Modelo>();
	private List<Object[]> listaReporte = new ArrayList<Object[]>();
	private List<Parametro> listaStandares = new ArrayList<Parametro>();

	private ArrayList<CapxOrdenEntity> orderListCxO = new ArrayList<CapxOrdenEntity>();
	private ArrayList<CapacidadesEntity> orderListStand = new ArrayList<CapacidadesEntity>();

	private Date fInicial;
	private Date fFinal;

	private Empresa empresa;
	private Logosfap logoFaps;

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		getEmpresa();
		getLogoFaps();
	}

	public void onChange() {
		System.out.println("VARIALBE nOrden: " + this.nOrden);
		getListaReporte();
		getListaStandares();

	}

	public void exportarPDFParesxFecha(ActionEvent actionEvent)
			throws JRException, IOException {
	}

	public void exportarPDFParesxOrdenes(ActionEvent actionEvent)
			throws JRException, IOException {
	}

	public void exportarPDFCapxOrden(ActionEvent actionEvent)
			throws JRException, IOException {
		String msg = "";
		if (this.nOrden != null) {
			Map<String, Object> parametros = new HashMap<String, Object>();

			File jasper = new File(FacesContext.getCurrentInstance()
					.getExternalContext()
					.getRealPath("/PlantillasRPT/rptCapxOrder.jasper"));

			parametros.put("subReportCaps", this.orderListStand);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasper.getPath(), parametros,
					new JRBeanCollectionDataSource(this.orderListCxO));

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition",
					"attachment; filename=CapacidadPorOrden.pdf");
			ServletOutputStream stream = response.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

			stream.flush();
			stream.close();
			FacesContext.getCurrentInstance().responseComplete();

			msg = "Reporte generado exitosamente";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "No se ha seleccionado una orden";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void exportarPDFModelos(ActionEvent actionEvent) throws JRException,
			IOException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/PlantillasRPT/rptModelos.jasper"));
		// System.out.println("Empresa: " + this.empresa.getEmpNombre());
		// System.out.println("Logo: " + this.logoFaps.getLogos().toString());

		parametros.put("empNombre", this.empresa.getEmpNombre());
		parametros.put("empDir", this.empresa.getEmpDirecc());
		parametros.put("empTelf", this.empresa.getEmpTelf());
		parametros.put("empLogo", this.empresa.getEmpLogo());
		parametros.put("logoFaps", this.logoFaps.getLogos());

		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), parametros, new JRBeanCollectionDataSource(
						this.getRPTmodelos()));

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition",
				"attachment; filename=ListaModelos.pdf");
		ServletOutputStream stream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();

	}

	// SETTERS AND GETTERS

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

	public Logosfap getLogoFaps() {
		LogoFapsDao logofapsDao = new LogoFapsDaoImpl();
		this.logoFaps = logofapsDao.findUniqueLogo();
		System.out.println("Logo Nombre: " + logoFaps.getNomlogo());
		return logoFaps;
	}

	public void setLogoFaps(Logosfap logoFaps) {
		this.logoFaps = logoFaps;
	}

	public Empresa getEmpresa() {
		EmpresaDao empresaDao = new EmpresaDaoImpl();
		this.empresa = empresaDao.findUnique();
		System.out.println("Empresa Nombre: " + empresa.getEmpNombre());
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getfInicial() {
		return fInicial;
	}

	public void setfInicial(Date fInicial) {
		this.fInicial = fInicial;
	}

	public Date getfFinal() {
		return fFinal;
	}

	public void setfFinal(Date fFinal) {
		this.fFinal = fFinal;
	}

	public List<Modelo> getRPTmodelos() {
		ModelosDao modelosDao = new ModelosDaoImpl();
		this.RPTmodelos = modelosDao.findAll();
		return RPTmodelos;
	}

	public void setSelectedItemsOrdenes(List<SelectItem> selectedItemsOrdenes) {
		this.selectedItemsOrdenes = selectedItemsOrdenes;
	}

	public Integer getnOrden() {
		return nOrden;
	}

	public void setnOrden(Integer nOrden) {
		this.nOrden = nOrden;
	}

	public List<Object[]> getListaReporte() {
		ReportesDao dao = new ReportesDaoImpl();
		listaReporte = dao.findByCapxOrdenRPT(nOrden);

		for (Object[] result : listaReporte) {
			Ordenprod op = (Ordenprod) result[0];
			Detalleorden dto = (Detalleorden) result[1];
			Empresa emp = (Empresa) result[2];
			Logosfap lgs = (Logosfap) result[3];
			byte[] bytesEncodedEmp = Base64.encodeBase64(emp.getEmpLogo());
			byte[] bytesEncodedLgs = Base64.encodeBase64(lgs.getLogos());
			CapxOrdenEntity cxp = new CapxOrdenEntity(op.getOrdenprodCodigo(),
					op.getCliente().getNombrecli(), op.getUsuario()
							.getUserName(), dto.getModelo().getModNombre(), dto
							.getTalla().getTalNumero(), emp.getEmpNombre(),
					emp.getEmpDirecc(), emp.getEmpTelf(), op.getFActual(),
					dto.getCantidad(), new String(bytesEncodedEmp), new String(
							bytesEncodedLgs));
			this.orderListCxO.add(cxp);
		}
		return listaReporte;
	}

	public List<Parametro> getListaStandares() {
		ReportesDao dao = new ReportesDaoImpl();
		listaStandares = dao.findParaByCod(nOrden);

		for (Parametro i : listaStandares) {
			CapacidadesEntity cStandar = new CapacidadesEntity(i.getProceso()
					.getTipoProceso().getTprNombre(), i.getTipLinea()
					.getTipolinea(), i.getStandar());
			this.orderListStand.add(cStandar);
		}
		return listaStandares;
	}

}
