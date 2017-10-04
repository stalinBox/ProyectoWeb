package com.project.reportsBean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.dao.ReportesDao;
import com.project.dao.ReportesDaoImpl;
import com.project.entities.Modelo;
import com.project.entities.Ordenprod;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Stalin
 *
 */
@ManagedBean
@ViewScoped
public class CapxOrdenBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<SelectItem> selectedItemsOrdenes;
	private Integer nOrden;
	private List<Object[]> reporte = new ArrayList<Object[]>();
	private List<Modelo> RPTmodelos = new ArrayList<Modelo>();
	private List<Ordenprod> listaReporte = new ArrayList<Ordenprod>();

	// CONSTRUCTOR
	@PostConstruct
	public void init() {

	}

	public void onChange() {
		System.out.println("VARIALBE nOrden: " + this.nOrden);
	}

	public void exportarPDFCapxOrden(ActionEvent actionEvent)
			throws JRException, IOException {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codOrden", this.nOrden);

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/PlantillasRPT/rptCapxOrden.jasper"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), parametros, new JRBeanCollectionDataSource(
						this.getReporte()));

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition",
				"attachment; filename=CapacidadPorOrden.pdf");
		ServletOutputStream stream = response.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();

	}

	public void exportarPDFCapxOrden2(ActionEvent actionEvent)
			throws JRException, IOException {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codOrder", this.nOrden);

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/PlantillasRPT/rptCapxOrdenPrueba.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), parametros, new JRBeanCollectionDataSource(
						this.getReporte()));

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition",
				"attachment; filename=CapacidadPorOrden.pdf");
		ServletOutputStream stream = response.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();

	}

	public void exportarPDFCapxOrden3(ActionEvent actionEvent)
			throws JRException, IOException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codAlgo", this.nOrden);
		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/PlantillasRPT/rptCapxOrdenPrueba2.jasper"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), parametros, new JRBeanCollectionDataSource(
						this.getListaReporte()));

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition",
				"attachment; filename=CapacidadPorOrden.pdf");
		ServletOutputStream stream = response.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void exportarPDFModelos(ActionEvent actionEvent) throws JRException,
			IOException {
		System.out.println("MODELOS 1");
		// Map<String, Object> parametros = new HashMap<String, Object>();
		// parametros.put("txtUsuario", "Stalin Ramírez");

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/PlantillasRPT/rptModelos.jasper"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), null,
				new JRBeanCollectionDataSource(this.getRPTmodelos()));

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

	public List<Modelo> getRPTmodelos() {
		ModelosDao modelosDao = new ModelosDaoImpl();
		this.RPTmodelos = modelosDao.findAll();
		return RPTmodelos;
	}

	public List<Object[]> getReporte() {
		ReportesDao reportesDao = new ReportesDaoImpl();
		reporte = reportesDao.findByCapxOrdenPrueba(nOrden);
		return reporte;
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

	public List<Ordenprod> getListaReporte() {
		ReportesDao dao = new ReportesDaoImpl();
		listaReporte = dao.ObtenerTodos(nOrden);
		return listaReporte;
	}

	public void setListaReporte(List<Ordenprod> listaReporte) {
		this.listaReporte = listaReporte;
	}

}
