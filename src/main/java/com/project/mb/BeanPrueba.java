package com.project.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.utils.MyUtil;

@ManagedBean(name = "bean")
@ViewScoped
public class BeanPrueba implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> rowNames = new ArrayList<String>();
	private List<String> colNames = new ArrayList<String>();
	private ArrayList<ArrayList<ArrayList<String>>> data3D = new ArrayList<ArrayList<ArrayList<String>>>();

	private ArrayList<ArrayList<Integer>> dataLista = new ArrayList<ArrayList<Integer>>();

	@PostConstruct
	public void init() {
		dataLista.add(new ArrayList<Integer>());
		dataLista.get(0).add(11);
		dataLista.get(0).add(12);
		dataLista.get(0).add(13);
		dataLista.add(new ArrayList<Integer>());
		dataLista.get(1).add(11);
		dataLista.get(1).add(22);
		dataLista.get(1).add(44);
	}

	public BeanPrueba() {

	}

	public void arrShow() {

		// ESTRUCTURA FILAS/COLUMNAS DE LA MATRIZ
		rowNames.add("Linea 1");
		rowNames.add("Linea 2");
		rowNames.add("Linea 3");

		colNames.add("Dia 1");
		colNames.add("Dia 2");
		colNames.add("Dia 3");
		colNames.add("Dia 4");

		// Setup 3 dimensional structure
		for (int i = 0; i < rowNames.size(); i++) {
			data3D.add(new ArrayList<ArrayList<String>>());
			for (int j = 0; j < colNames.size(); j++) {
				data3D.get(i).add(new ArrayList<String>());
			}
		}

		// row 1, col 1, 3 items
		data3D.get(0).get(0).add("item1");
		data3D.get(0).get(0).add("item2");
		data3D.get(0).get(0).add("item3");

		// row 1, col 2, 1 items
		data3D.get(0).get(1).add("item1");

		// row 1, col 3, 2 items
		data3D.get(0).get(2).add("item1");
		data3D.get(0).get(2).add("item2");

		// row 2, col 1, 2 item
		data3D.get(1).get(0).add("item1");
		data3D.get(1).get(0).add("item2");

		// row 2, col 2, 1 item
		data3D.get(1).get(1).add("item1");

		// PRUEBA POS 2.2
		data3D.get(2).get(2).add("ALGO");

		System.out.println("ArrayMultiList 3D: " + this.data3D);
		System.out.println("ArrayList DataLista: " + this.dataLista);

	}

	public List<String> getRowNames() {
		return rowNames;
	}

	public void setRowNames(List<String> rowNames) {
		this.rowNames = rowNames;
	}

	public List<String> getColNames() {
		return colNames;
	}

	public void setColNames(List<String> colNames) {
		this.colNames = colNames;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getData3D() {
		return data3D;
	}

	public void setData3D(ArrayList<ArrayList<ArrayList<String>>> data3d) {
		data3D = data3d;
	}

	// navegar entre paginas
	public void navegaEnlace(ActionEvent actionEvent) {
		String ruta = "";
		ruta = MyUtil.messages() + "sucess.xhtml";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
