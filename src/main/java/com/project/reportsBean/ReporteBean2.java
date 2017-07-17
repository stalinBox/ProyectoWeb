package com.project.reportsBean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.dao.ParamDao;
import com.project.dao.ParamDaoImpl;
import com.project.dao.ProgramacionDiasDao;
import com.project.dao.ProgramacionDiasDaoImpl;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;
import com.project.entities.Programdia;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
public class ReporteBean2 implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codOrden;
	private Integer codParam;

	private List<SelectItem> selectedItemsOrdenes;
	private List<SelectItem> selectedItemsProcesos;

	private List<Programdia> programdias = new ArrayList<Programdia>();

	// METODOS GENERAR REPORTE
	public void exportarPDF(ActionEvent actionEvent) throws JRException,
			IOException {
		// Map<String, Object> parametros = new HashMap<String, Object>();
		// parametros.put("txtUsuario", "Stalin Ramírez");

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/reporteProgramDias.jasper"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), null,
				new JRBeanCollectionDataSource(this.getProgramdias()));

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition",
				"attachment; filename=jsfReporte.pdf");
		ServletOutputStream stream = response.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void verPDF(ActionEvent actionEvent) throws Exception {
		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/reporteProgramDias.jasper"));

		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null,
				new JRBeanCollectionDataSource(this.getProgramdias()));
		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes, 0, bytes.length);
		outStream.flush();
		outStream.close();

		FacesContext.getCurrentInstance().responseComplete();
	}

	// SETTERS AND GETTERS

	public List<SelectItem> getSelectedItemsOrdenes() {
		this.selectedItemsOrdenes = new ArrayList<SelectItem>();

		OrdenesProdDao ordenesDAO = new OrdenesProdDaoImpl();
		List<Ordenprod> ordenes = ordenesDAO.findAll();

		for (Ordenprod o : ordenes) {
			SelectItem selectItem = new SelectItem(o.getOrdenprodCodigo(),
					o.getOrdenprodCodigo() + " - "
							+ o.getCliente().getNombrecli());
			this.selectedItemsOrdenes.add(selectItem);
		}
		return selectedItemsOrdenes;
	}

	public List<Programdia> getProgramdias() {
		ProgramacionDiasDao programacionDAO = new ProgramacionDiasDaoImpl();
		this.programdias = programacionDAO.findByOrdenByParam(codOrden,
				codParam);
		return programdias;
	}

	public void setProgramdias(List<Programdia> programdias) {
		this.programdias = programdias;
	}

	public void setSelectedItemsOrdenes(List<SelectItem> selectedItemsOrdenes) {
		this.selectedItemsOrdenes = selectedItemsOrdenes;
	}

	public List<SelectItem> getSelectedItemsProcesos() {
		if (this.codOrden != null && !this.codOrden.equals("")
				&& this.codOrden != 0) {
			this.selectedItemsProcesos = new ArrayList<SelectItem>();
			ParamDao paramDao = new ParamDaoImpl();
			List<Parametro> param = paramDao.findByOrdenProd(this.codOrden);
			for (Parametro p : param) {
				SelectItem selectItem = new SelectItem(p.getParamCodigo(), p
						.getProceso().getTipoProceso().getTprNombre()
						+ "/" + p.getTipLinea().getTipolinea());
				this.selectedItemsProcesos.add(selectItem);
			}
			return selectedItemsProcesos;
		} else {
			this.selectedItemsProcesos = new ArrayList<SelectItem>();
			return selectedItemsProcesos;
		}
	}

	public void setSelectedItemsProcesos(List<SelectItem> selectedItemsProcesos) {
		this.selectedItemsProcesos = selectedItemsProcesos;
	}

	public Integer getCodOrden() {
		return codOrden;
	}

	public void setCodOrden(Integer codOrden) {
		this.codOrden = codOrden;
	}

	public Integer getCodParam() {
		return codParam;
	}

	public void setCodParam(Integer codParam) {
		this.codParam = codParam;
	}

}
