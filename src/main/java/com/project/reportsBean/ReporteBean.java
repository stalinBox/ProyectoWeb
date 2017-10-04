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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.entities.Modelo;

@ManagedBean
@ViewScoped
public class ReporteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Modelo> modelo = new ArrayList<Modelo>();

	public void exportarPDF(ActionEvent actionEvent) throws JRException,
			IOException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("txtUsuario", "Stalin Ramírez");

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/rptJSF.jasper"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), parametros, new JRBeanCollectionDataSource(
						this.getModelo()));

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
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("txtUsuario", "Ing. Stalin Ramírez");

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/rptJSF.jasper"));

		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(),
				parametros, new JRBeanCollectionDataSource(this.getModelo()));
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
	public List<Modelo> getModelo() {
		ModelosDao modelosDao = new ModelosDaoImpl();
		this.modelo = modelosDao.findAll();
		return modelo;
	}

	public void setModelo(List<Modelo> modelo) {
		this.modelo = modelo;
	}

}
