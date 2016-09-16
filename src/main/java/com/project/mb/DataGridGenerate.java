package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		dynamicHeaders = new String[] { "Id", "Region ", "1", "2", "3" };

		// Set rows
		dynamicList = new ArrayList<List<String>>();
		dynamicList.add(Arrays.asList(new String[] { "1", "asjgh", "nada",
				"nada2", "nada 3" }));
		dynamicList
				.add(Arrays.asList(new String[] { "2", "Americas", "nada" }));
		dynamicList.add(Arrays.asList(new String[] { "3", "Asia", "nada" }));
		dynamicList.add(Arrays.asList(new String[] { "4", "Africa" }));

	}

	private void populateDynamicDataTable() {

		// Context and Expression Factory
		FacesContext fCtx = FacesContext.getCurrentInstance();
		ELContext elCtx = fCtx.getELContext();
		ExpressionFactory ef = fCtx.getApplication().getExpressionFactory();

		// Create <h:dataTable value="#{datatableManagedBean.dynamicList}"
		// var="dynamicRow">.
		HtmlDataTable dynamicDataTable = new HtmlDataTable();
		ValueExpression ve = ef.createValueExpression(elCtx,
				"#{datatableManagedBean.dynamicList}", List.class);
		dynamicDataTable.setValueExpression("value", ve);
		dynamicDataTable.setVar("dynamicRow");

		// Iterate over columns
		for (int i = 0; i < dynamicList.get(0).size(); i++) {

			// Create <h:column>.
			HtmlColumn column = new HtmlColumn();
			dynamicDataTable.getChildren().add(column);

			// Create <h:outputText value="dynamicHeaders[i]"> for <f:facet
			// name="header"> of column.
			HtmlOutputText header = new HtmlOutputText();
			header.setValue(dynamicHeaders[i]);
			column.setHeader(header);

			// Create <h:outputText value="#{dynamicRow[" + i + "]}"> for the
			// body of column.
			HtmlOutputText output = new HtmlOutputText();
			ve = ef.createValueExpression(elCtx, "#{dynamicRow[" + i + "]}",
					String.class);
			output.setValueExpression("value", ve);
			column.getChildren().add(output);

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
