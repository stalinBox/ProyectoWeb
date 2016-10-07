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
	// TODO Clase Bean que genera las tablas
	// DistribucionPares/DistribucionHoras/DistribucionFechas

	private static final long serialVersionUID = 1L;
	// VARIABLES
	private Integer numDias;
	private Integer numLineas;

	// ARRAYS SIMULACRO
	private List<String> rowNamesSIM = new ArrayList<String>();
	private List<String> colNamesSIM = new ArrayList<String>();
	private List<String> rowNomProcesos = new ArrayList<String>();

	// ARRAY CON DATOS PARA VISUALIZAR
	private ArrayList<ArrayList<ArrayList<Integer>>> dataLista = new ArrayList<ArrayList<ArrayList<Integer>>>();
	private ArrayList<ArrayList<ArrayList<Integer>>> dataLista2 = new ArrayList<ArrayList<ArrayList<Integer>>>();
	private ArrayList<ArrayList<ArrayList<String>>> dataLista3 = new ArrayList<ArrayList<ArrayList<String>>>();
	private ArrayList<ArrayList<Integer>> array1 = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> array2 = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<String>> array3 = new ArrayList<ArrayList<String>>();

	@PostConstruct
	public void init() {
		// INICIALIZACION VARIABLES
		this.numLineas = 10;
		this.numDias = 10;

		// INICIALIZACION ARRAY1
		// Row 1 pos(0,i)
		array1.add(new ArrayList<Integer>());
		array1.get(0).add(111);
		array1.get(0).add(222);
		array1.get(0).add(333);
		array1.get(0).add(444);

		// Row 2 pos(1,i)
		array1.add(new ArrayList<Integer>());
		array1.get(1).add(00);
		array1.get(1).add(55);
		array1.get(1).add(66);
		array1.get(1).add(77);

		// Row 3 pos(1,i)
		array1.add(new ArrayList<Integer>());
		array1.get(2).add(11);
		array1.get(2).add(22);
		array1.get(2).add(33);
		array1.get(2).add(44);

		// INICIALIZACION ARRAY2
		// Row 1 pos(0,i)
		array2.add(new ArrayList<Integer>());
		array2.get(0).add(0);
		array2.get(0).add(0);
		array2.get(0).add(0);
		array2.get(0).add(0);

		// Row 2 pos(1,i)
		array2.add(new ArrayList<Integer>());
		array2.get(1).add(1);
		array2.get(1).add(1);
		array2.get(1).add(1);
		array2.get(1).add(1);

		// Row 3 pos(1,i)
		array2.add(new ArrayList<Integer>());
		array2.get(2).add(2);
		array2.get(2).add(2);
		array2.get(2).add(2);
		array2.get(2).add(2);

	}

	public BeanPrueba() {

	}

	public void arrShow() {
		System.out.println("ArrayList DataLista: " + this.dataLista);
	}

	public void MyDistribPares() {
		// ENCABEZADOS Y NOMBRES DE LAS FILAS/COLUMNAS
		this.rowNamesSIM.clear();
		this.colNamesSIM.clear();
		for (int i = 1; i <= this.numLineas; i++) {
			this.rowNamesSIM.add("L" + i + " Convencional");
		}
		for (int i = 1; i <= this.numDias; i++) {
			this.colNamesSIM.add("Dia " + i);
		}

		// CONFIGURAR ESTRUCTURA 3D
		for (int i = 0; i < rowNamesSIM.size(); i++) {
			dataLista.add(new ArrayList<ArrayList<Integer>>());
			for (int j = 0; j < colNamesSIM.size(); j++) {
				dataLista.get(i).add(new ArrayList<Integer>());
			}
		}

		// AGREGAR LOS DATOS DE 2D a 3D
		for (int i = 0; i < array1.size(); i++) {
			for (int j = 0; j < array1.get(i).size(); j++) {
				// System.out.print(array1.get(i).get(j) + " ");
				int g = array1.get(i).get(j);
				dataLista.get(i).get(j).add(g);
			}
		}
	}

	public void MyDistribHoras() {
		// ENCABEZADOS Y NOMBRES DE LAS FILAS/COLUMNAS
		this.rowNamesSIM.clear();
		this.colNamesSIM.clear();
		for (int i = 1; i <= this.numLineas; i++) {
			this.rowNamesSIM.add("L" + i + " Convencional");
		}
		for (int i = 1; i <= this.numDias; i++) {
			this.colNamesSIM.add("Dia " + i);
		}

		// CONFIGURAR ESTRUCTURA 3D
		for (int i = 0; i < rowNamesSIM.size(); i++) {
			dataLista2.add(new ArrayList<ArrayList<Integer>>());
			for (int j = 0; j < colNamesSIM.size(); j++) {
				dataLista2.get(i).add(new ArrayList<Integer>());
			}
		}

		// AGREGAR LOS DATOS DE 2D a 3D
		for (int i = 0; i < array2.size(); i++) {
			for (int j = 0; j < array2.get(i).size(); j++) {
				// System.out.print(array1.get(i).get(j) + " ");
				int g = array2.get(i).get(j);
				dataLista2.get(i).get(j).add(g);
			}
		}
	}

	public void MyDistribFechas() {
		// ENCABEZADOS Y NOMBRES DE LAS FILAS/COLUMNAS
		this.rowNomProcesos.clear();
		this.colNamesSIM.clear();

		this.rowNomProcesos.add("FECHA MONTAJE");
		this.rowNomProcesos.add("FECHA APARADO");
		this.rowNomProcesos.add("FECHA TROQUELADO");

		for (int i = 1; i <= this.numDias; i++) {
			this.colNamesSIM.add("Dia " + i);
		}
		// CONFIGURAR ESTRUCTURA 3D
		for (int i = 0; i < rowNomProcesos.size(); i++) {
			dataLista3.add(new ArrayList<ArrayList<String>>());
			for (int j = 0; j < colNamesSIM.size(); j++) {
				dataLista3.get(i).add(new ArrayList<String>());
			}
		}

	}

	// navegar entre paginas
	public void navegaEnlace(ActionEvent actionEvent) {
		String ruta = "";
		ruta = MyUtil.messages() + "sucess.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// navegar entre paginas
	public void volverEnlace(ActionEvent actionEvent) {
		String ruta = "";
		ruta = MyUtil.messages() + "failed.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// SETTERS AND GETTERS

	public ArrayList<ArrayList<ArrayList<Integer>>> getDataLista2() {
		return dataLista2;
	}

	public List<String> getRowNomProcesos() {
		return rowNomProcesos;
	}

	public void setRowNomProcesos(List<String> rowNomProcesos) {
		this.rowNomProcesos = rowNomProcesos;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getDataLista3() {
		return dataLista3;
	}

	public void setDataLista3(ArrayList<ArrayList<ArrayList<String>>> dataLista3) {
		this.dataLista3 = dataLista3;
	}

	public ArrayList<ArrayList<String>> getArray3() {
		return array3;
	}

	public void setArray3(ArrayList<ArrayList<String>> array3) {
		this.array3 = array3;
	}

	public void setDataLista2(
			ArrayList<ArrayList<ArrayList<Integer>>> dataLista2) {
		this.dataLista2 = dataLista2;
	}

	public ArrayList<ArrayList<Integer>> getArray2() {
		return array2;
	}

	public void setArray2(ArrayList<ArrayList<Integer>> array2) {
		this.array2 = array2;
	}

	public Integer getNumDias() {
		return numDias;
	}

	public void setNumDias(Integer numDias) {
		this.numDias = numDias;
	}

	public Integer getNumLineas() {
		return numLineas;
	}

	public void setNumLineas(Integer numLineas) {
		this.numLineas = numLineas;
	}

	public List<String> getRowNamesSIM() {
		return rowNamesSIM;
	}

	public void setRowNamesSIM(List<String> rowNamesSIM) {
		this.rowNamesSIM = rowNamesSIM;
	}

	public List<String> getColNamesSIM() {
		return colNamesSIM;
	}

	public void setColNamesSIM(List<String> colNamesSIM) {
		this.colNamesSIM = colNamesSIM;
	}

	public ArrayList<ArrayList<ArrayList<Integer>>> getDataLista() {
		return dataLista;
	}

	public void setDataLista(ArrayList<ArrayList<ArrayList<Integer>>> dataLista) {
		this.dataLista = dataLista;
	}

	public ArrayList<ArrayList<Integer>> getArray1() {
		return array1;
	}

	public void setArray1(ArrayList<ArrayList<Integer>> array1) {
		this.array1 = array1;
	}

}
