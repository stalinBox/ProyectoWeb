package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;

import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.UsuarioDao;
import com.project.dao.UsuarioDaoImpl;
import com.project.entities.Modelo;

@ManagedBean(name = "datatableManagedBean")
@ViewScoped
public class DataGridGenerate implements Serializable {

	private static final long serialVersionUID = 1L;

	private static List<List<String>> dynamicList; // Simulate fake DB.
	private static String[] dynamicHeaders; // Optional.
	private HtmlPanelGroup dynamicDataTableGroup; // Placeholder.

	// Actions
	// -----------------------------------------------------------------------------------

	private void loadDynamicList() {

		// Set headers (optional).
		dynamicHeaders = new String[] { "Fechas", "Dia1 ", "Dia 2", "Dia 3",
				"Dia 4" };

		// Set rows
		dynamicList = new ArrayList<List<String>>();
		dynamicList.add(Arrays.asList(new String[] { "Fecha Montaje", "asjgh",
				"nada", "nada2", "nada 3" }));
		dynamicList.add(Arrays.asList(new String[] { "Fecha Troquelado",
				"Americas", "nada" }));
		dynamicList.add(Arrays.asList(new String[] { "Fecha Aparado", "Asia",
				"nada" }));

	}

	private void populateDynamicDataTable() {

		// Context and Expression Factory
		FacesContext fCtx = FacesContext.getCurrentInstance();
		ELContext elCtx = fCtx.getELContext();
		ExpressionFactory ef = fCtx.getApplication().getExpressionFactory();

		// Create <p:dataTable value="#{datatableManagedBean.dynamicList}"
		// var="dynamicRow">.

		DataTable dynamicDataTable = new DataTable();
		ValueExpression ve = ef.createValueExpression(elCtx,
				"#{datatableManagedBean.dynamicList}", List.class);
		dynamicDataTable.setValueExpression("value", ve);
		dynamicDataTable.setVar("dynamicRow");
		dynamicDataTable.setEditable(true);
		dynamicDataTable.setEditMode("cell");
		dynamicDataTable.setEmptyMessage("nada");
		// Iterate over columns
		for (int i = 0; i < dynamicList.get(0).size(); i++) {

			// Create <p:column>.
			Column column = new Column();
			dynamicDataTable.getChildren().add(column);

			// Create <h:outputText value="dynamicHeaders[i]"> for <f:facet
			// name="header"> of column.
			// HtmlOutputText header = new HtmlOutputText();
			// header.setValue(dynamicHeaders[i]);
			// column.setHeader(header);
			column.setHeaderText(dynamicHeaders[i]);

			// Create <h:outputText value="#{dynamicRow[" + i + "]}"> for the
			// body of column.

			CellEditor cellEditor = new CellEditor();
			dynamicDataTable.getChildren().add(cellEditor);

			HtmlOutputText output = new HtmlOutputText();
			InputText inm = new InputText();

			ve = ef.createValueExpression(elCtx, "#{dynamicRow[" + i + "]}",
					String.class);
			output.setValueExpression("value", ve);
			inm.setValueExpression("vlalue", ve);
			column.getChildren().add(output);
			column.getChildren().add(inm);

		}

		// Add the datatable to <h:panelGroup
		// binding="#{datatableManagedBean.dynamicDataTableGroup}">.
		dynamicDataTableGroup = new HtmlPanelGroup();
		dynamicDataTableGroup.getChildren().add(dynamicDataTable);

	}

	// Getters
	// -----------------------------------------------------------------------------------

	public HtmlPanelGroup getDynamicDataTableGroup() {
		// This will be called once in the first RESTORE VIEW phase.
		if (dynamicDataTableGroup == null) {
			loadDynamicList(); // Preload dynamic list.
			populateDynamicDataTable(); // Populate editable datatable.
		}

		return dynamicDataTableGroup;
	}

	public List<List<String>> getDynamicList() {
		return dynamicList;
	}

	// Setters
	// -----------------------------------------------------------------------------------

	public void setDynamicDataTableGroup(HtmlPanelGroup dynamicDataTableGroup) {
		this.dynamicDataTableGroup = dynamicDataTableGroup;
	}
}
