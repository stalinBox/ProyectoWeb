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

import com.project.dao.EmpresaDao;
import com.project.dao.EmpresaDaoImpl;
import com.project.dao.OrdenesProdDao;
import com.project.dao.OrdenesProdDaoImpl;
import com.project.entities.Empresa;
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
	private Empresa empresa;
	private List<SelectItem> selectedItemsOrdenes;
	private List<Ordenprod> ordenRepor;
	private Integer nOrden;

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		EmpresaDao empresaDao = new EmpresaDaoImpl();
		this.empresa = empresaDao.findUnique();
	}

	public void exportarPDF(ActionEvent actionEvent) throws JRException,
			IOException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("txtNomEmp", this.empresa.getEmpNombre());
		parametros.put("txtDirEmp", this.empresa.getEmpDirecc());
		parametros.put("txtTelf", this.empresa.getEmpTelf());

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/PlantillasRPT/rptCapxOrden.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper.getPath(), parametros, new JRBeanCollectionDataSource(
						this.getOrdenRepor()));

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition",
				"attachment; filename=rptCapxOrden.pdf");
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

	public void setSelectedItemsOrdenes(List<SelectItem> selectedItemsOrdenes) {
		this.selectedItemsOrdenes = selectedItemsOrdenes;
	}

	public Integer getnOrden() {
		nOrden = 134;
		return nOrden;
	}

	public void setnOrden(Integer nOrden) {
		this.nOrden = nOrden;
	}

	public List<Ordenprod> getOrdenRepor() {
		OrdenesProdDao ordenProdDao = new OrdenesProdDaoImpl();
		ordenRepor = ordenProdDao.getAllOrderByCod(nOrden);
		return ordenRepor;
	}
}
