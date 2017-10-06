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

import org.apache.commons.codec.binary.Base64;

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
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author Stalin Ramirez
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

	// CONSTRUCTOR
	@PostConstruct
	public void init() {

	}

	public void onChange() {
		System.out.println("VARIALBE nOrden: " + this.nOrden);
		getListaReporte();
		getListaStandares();
	}

	public void exportarPDFCapxOrden(ActionEvent actionEvent)
			throws JRException, IOException {

		Map<String, Object> parametros = new HashMap<String, Object>();

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/PlantillasRPT/rptCapxOrder.jasper"));

		File SubJasper = new File(
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getRealPath(
								"/PlantillasRPT/rptCapxOrder_subreportCaps.jasper"));

		JasperReport subreport = (JasperReport) JRLoader
				.loadObjectFromFile(SubJasper.getPath());

		parametros.put("subReportCaps", new JRBeanCollectionDataSource(
				orderListStand));

		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), parametros, new JRBeanCollectionDataSource(
						this.orderListCxO));

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
			System.out.println("encodedBytes1: " + new String(bytesEncodedEmp));
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
