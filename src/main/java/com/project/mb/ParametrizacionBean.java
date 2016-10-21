package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ParametrizacionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private Date currentDate;
	private String diasLaborables;

	private String respMontaje;
	private Integer numLineasConvMont;

	private Integer[] numTurnosConvMont;
	private Integer numLineasAutMont;
	private Integer[] numTurnosAutMont;
	private Integer stdProdConvMont;
	private Integer stdProdAutMont;

	private String respAparado;
	private Integer numLineasConvApa;
	private Integer[] numTurnosConvApa;
	private Integer numLineasAutApa;
	private Integer[] numTurnosAutApa;
	private Integer stdProdConvApa;
	private Integer stdProdAutApa;

	private String respTroquelado;
	private Integer numLineasConvTroq;
	private Integer[] numTurnosConvTroq;
	private Integer numLineasAutTroq;
	private Integer[] numTurnosAutTroq;
	private Integer stdProdConvTroq;
	private Integer stdProdAutTroq;

	private ArrayList<Integer> addNumTurnosConvMont = new ArrayList<Integer>();
	private ArrayList<String> lblMonConv = new ArrayList<String>();
	private ArrayList<String> lblMonAut = new ArrayList<String>();

	private ArrayList<String> lblTrqConv = new ArrayList<String>();
	private ArrayList<String> lblTrqAut = new ArrayList<String>();

	private ArrayList<String> lblApaConv = new ArrayList<String>();
	private ArrayList<String> lblApaAut = new ArrayList<String>();

	private Integer totPedido;

	// ARRAYS PARA LAS CABECERAS Y COLUMNA INDICE DE LAS TABLAS
	private List<String> rowNames = new ArrayList<String>();
	private List<String> colNames = new ArrayList<String>();
	private List<String> rowNameProcesos = new ArrayList<String>();

	// ARRAYS 3D PARA VISUALIZAR EN LAS TABLAS
	private ArrayList<ArrayList<ArrayList<String>>> array3DFechas = new ArrayList<ArrayList<ArrayList<String>>>();
	private ArrayList<ArrayList<ArrayList<Integer>>> array3DDistribPares = new ArrayList<ArrayList<ArrayList<Integer>>>();
	private ArrayList<ArrayList<ArrayList<String>>> array3DDistribHoras = new ArrayList<ArrayList<ArrayList<String>>>();

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		DetaOrdenBean nb = new DetaOrdenBean();
		/**************
		 * esto es el codigo real this.stdProdConvMont = nb.getCp(); //
		 * this.totPedido = nb.getTotal();
		 */

		// PARA PRUEBAS
		this.stdProdConvMont = 200;
		this.totPedido = 1150;
		// FIN PRUEBAS

		this.currentDate = new Date();
		this.stdProdConvApa = 0;
		this.stdProdConvTroq = 0;
		this.stdProdAutApa = 0;
		this.stdProdAutMont = 0;
		this.stdProdAutTroq = 0;
	}

	// CONSTRUCTOR
	public ParametrizacionBean() {

	}

	public ParametrizacionBean(Integer a, Integer b) {
		this();
		this.stdProdConvMont = a;
		this.totPedido = b;

		System.out.println("***** Standar Montaje: " + this.stdProdConvMont);
		System.out.println("***** Total Pedidio: " + this.totPedido);
	}

	public void ExecuteParams() {
		ArrayList<ArrayList<Integer>> array0 = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Object>> array00 = new ArrayList<ArrayList<Object>>();
		String msg = "";
		try {
			// GUARDAR LOS TURNOS
			TestShowData();

			System.out.println("PARAMETROS A UTILZAR");
			System.out.println("Dias: " + this.diasLaborables);
			System.out.println("Std Produccion: " + this.stdProdConvMont);
			System.out.println("Total Pedido: " + this.totPedido);
			System.out.println("Responsable: " + this.respMontaje);
			System.out.println("Numero de lineas: " + this.numLineasConvMont);
			System.out.println("Turnos por cada Linea: "
					+ this.addNumTurnosConvMont);

			// PARA ENVIAR Y RECIBIR MATRICES
			DistribucionTables nn = new DistribucionTables();
			array0 = nn.receivParamsPares(this.totPedido, this.stdProdConvMont,
					this.addNumTurnosConvMont);

			array00 = nn.receivParamsHoras(this.totPedido,
					this.stdProdConvMont, this.addNumTurnosConvMont);

			System.out.println("Esto es lo que devolvio (PARES): " + array0);
			System.out.println("Esto es lo que devolvio (HORAS): " + array00);

			// PARA MOSTRAR TABLAS (SHOW TABLAS)
			MyDistribFechas();
			MyDistribPares(array0);
			MyDistribHoras(array00);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Error: HACEN FALTA MAS DIAS PARA PROCESAR LA ORDEN";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// METODOS GENERAR TABLAS
	public void MyDistribPares(ArrayList<ArrayList<Integer>> array0) {
		this.array3DDistribPares.clear();
		// ENCABEZADOS Y NOMBRES DE LAS FILAS/COLUMNAS
		this.rowNames.clear();
		this.colNames.clear();

		for (int i = 1; i <= this.numLineasConvMont; i++) {
			this.rowNames.add("L" + i + " Convencional");
		}
		for (int i = 1; i <= Integer.parseInt(this.diasLaborables); i++) {
			this.colNames.add("Dia " + i);
		}

		// CONFIGURAR ESTRUCTURA 3D
		for (int i = 0; i < rowNames.size(); i++) {
			this.array3DDistribPares.add(new ArrayList<ArrayList<Integer>>());
			for (int j = 0; j < colNames.size(); j++) {
				this.array3DDistribPares.get(i).add(new ArrayList<Integer>());
			}
		}

		// AGREGAR LOS DATOS DE 2D a 3D
		for (int i = 0; i < array0.size(); i++) {
			for (int j = 0; j < array0.get(i).size(); j++) {
				// System.out.print(array1.get(i).get(j) + " ");
				int g = array0.get(i).get(j);
				this.array3DDistribPares.get(i).get(j).add(g);
			}
		}
		System.out.println("Estooooooo: " + array3DDistribPares);
	}

	public void MyDistribHoras(ArrayList<ArrayList<Object>> array00) {
		this.array3DDistribHoras.clear();
		// ENCABEZADOS Y NOMBRES DE LAS FILAS/COLUMNAS
		this.rowNames.clear();
		this.colNames.clear();
		for (int i = 1; i <= this.numLineasConvMont; i++) {
			this.rowNames.add("L" + i + " Convencional");
		}
		for (int i = 1; i <= Integer.parseInt(this.diasLaborables); i++) {
			this.colNames.add("Dia " + i);
		}

		// CONFIGURAR ESTRUCTURA 3D
		for (int i = 0; i < rowNames.size(); i++) {
			this.array3DDistribHoras.add(new ArrayList<ArrayList<String>>());
			for (int j = 0; j < colNames.size(); j++) {
				this.array3DDistribHoras.get(i).add(new ArrayList<String>());
			}
		}

		// AGREGAR LOS DATOS DE 2D a 3D
		for (int i = 0; i < array00.size(); i++) {
			for (int j = 0; j < array00.get(i).size(); j++) {
				// System.out.print(array1.get(i).get(j) + " ");
				Object g = array00.get(i).get(j);
				this.array3DDistribHoras.get(i).get(j).add(String.valueOf(g));
			}
		}
	}

	public void MyDistribFechas() {
		this.array3DFechas.clear();
		// ENCABEZADOS Y NOMBRES DE LAS FILAS/COLUMNAS
		this.rowNameProcesos.clear();
		this.colNames.clear();

		this.rowNameProcesos.add("FECHA MONTAJE");
		this.rowNameProcesos.add("FECHA APARADO");
		this.rowNameProcesos.add("FECHA TROQUELADO");

		for (int i = 1; i <= Integer.parseInt(this.diasLaborables); i++) {
			this.colNames.add("Dia " + i);
		}
		// CONFIGURAR ESTRUCTURA 3D
		for (int i = 0; i < rowNameProcesos.size(); i++) {
			this.array3DFechas.add(new ArrayList<ArrayList<String>>());
			for (int j = 0; j < colNames.size(); j++) {
				this.array3DFechas.get(i).add(new ArrayList<String>());
			}
		}
	}

	// TURNOS CONV/AUT MONTAJE
	public void GenerateTurnsDaysConvMont() {
		numTurnosConvMont = new Integer[numLineasConvMont];
		this.lblMonConv.clear();
		for (int i = 1; i <= this.numLineasConvMont; i++) {
			this.lblMonConv.add("Turno/Día L" + i + " Convencional: ");
		}
	}

	public void GenerateTurnsDaysAutMont() {
		this.lblMonAut.clear();
		numTurnosAutMont = new Integer[numLineasAutMont];
		for (int i = 1; i <= this.numLineasAutMont; i++) {
			this.lblMonAut.add("Turno/Día L" + i + " Automatica: ");
		}
	}

	// TURNOS CONV/AUT MONTAJE TROQUELADO
	public void GenerateTurnsDaysConvTrqt() {
		this.lblTrqConv.clear();
		numTurnosConvTroq = new Integer[numLineasConvTroq];
		for (int i = 1; i <= this.numLineasConvTroq; i++) {
			this.lblTrqConv.add("Turno/Día L" + i + " Convencional: ");
		}
	}

	public void GenerateTurnsDaysAutTrq() {
		this.lblTrqAut.clear();
		numTurnosAutTroq = new Integer[numLineasAutTroq];
		for (int i = 1; i <= this.numLineasAutTroq; i++) {
			this.lblTrqAut.add("Turno/Día L" + i + " Automatica: ");
		}
	}

	// TURNOS CONV/AUT MONTAJE APARADO
	public void GenerateTurnsDaysConvApa() {
		this.lblApaConv.clear();
		numTurnosConvApa = new Integer[numLineasConvApa];
		for (int i = 1; i <= this.numLineasConvApa; i++) {
			this.lblApaConv.add("Turno/Día L" + i + " Convencional: ");
		}
	}

	public void GenerateTurnsDaysAutApa() {
		this.lblApaAut.clear();
		numTurnosAutApa = new Integer[numLineasAutApa];
		for (int i = 1; i <= this.numLineasAutTroq; i++) {
			this.lblApaAut.add("Turno/Día L" + i + " Automatica: ");
		}
	}

	// ********* PRUEBA SHOWS TURNOS CONV/AUT MONTAJE ******************
	public void TestShowData() {
		System.out.println("METOD");
		this.addNumTurnosConvMont.clear();
		for (Integer i : this.numTurnosConvMont) {
			this.addNumTurnosConvMont.add(i);
		}

		System.out.println(this.addNumTurnosConvMont);
	}

	public void TestShowDataAut() {
		System.out.println("METOD SAVE AUTO MONTAJE");
		for (Integer i : numTurnosAutMont) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	// PRUEBA SHOWS TURNOS CONV/AUT TROQUELADO
	public void TestShowDataTrq() {
		System.out.println("METOD SAVE TROQUELADO");
		for (Integer i : numTurnosConvTroq) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	public void TestShowDataAutTrq() {
		System.out.println("METOD SAVE AUTO TROQUELADO");
		for (Integer i : numTurnosAutTroq) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	// PRUEBA SHOWS TURNOS CONV/AUT APARADO
	public void TestShowDataApa() {
		System.out.println("METOD SAVE APARADO");
		for (Integer i : numTurnosConvApa) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	public void TestShowDataAutApa() {
		System.out.println("METOD SAVE AUTO APARADO");
		for (Integer i : numTurnosAutApa) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	// SETTERS AND GETTERS

	public ArrayList<String> getLblMonConv() {
		return lblMonConv;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getArray3DFechas() {
		return array3DFechas;
	}

	public void setArray3DFechas(
			ArrayList<ArrayList<ArrayList<String>>> array3dFechas) {
		array3DFechas = array3dFechas;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getArray3DDistribHoras() {
		return array3DDistribHoras;
	}

	public void setArray3DDistribHoras(
			ArrayList<ArrayList<ArrayList<String>>> array3dDistribHoras) {
		array3DDistribHoras = array3dDistribHoras;
	}

	public ArrayList<ArrayList<ArrayList<Integer>>> getArray3DDistribPares() {
		return array3DDistribPares;
	}

	public void setArray3DDistribPares(
			ArrayList<ArrayList<ArrayList<Integer>>> array3dDistribPares) {
		array3DDistribPares = array3dDistribPares;
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

	public List<String> getRowNameProcesos() {
		return rowNameProcesos;
	}

	public void setRowNameProcesos(List<String> rowNameProcesos) {
		this.rowNameProcesos = rowNameProcesos;
	}

	public ArrayList<Integer> getAddNumTurnosConvMont() {
		return addNumTurnosConvMont;
	}

	public void setAddNumTurnosConvMont(ArrayList<Integer> addNumTurnosConvMont) {
		this.addNumTurnosConvMont = addNumTurnosConvMont;
	}

	public void setLblMonConv(ArrayList<String> lblMonConv) {
		this.lblMonConv = lblMonConv;
	}

	public ArrayList<String> getLblMonAut() {
		return lblMonAut;
	}

	public void setLblMonAut(ArrayList<String> lblMonAut) {
		this.lblMonAut = lblMonAut;
	}

	public ArrayList<String> getLblApaAut() {
		return lblApaAut;
	}

	public void setLblApaAut(ArrayList<String> lblApaAut) {
		this.lblApaAut = lblApaAut;
	}

	public ArrayList<String> getLblTrqConv() {
		return lblTrqConv;
	}

	public void setLblTrqConv(ArrayList<String> lblTrqConv) {
		this.lblTrqConv = lblTrqConv;
	}

	public ArrayList<String> getLblTrqAut() {
		return lblTrqAut;
	}

	public void setLblTrqAut(ArrayList<String> lblTrqAut) {
		this.lblTrqAut = lblTrqAut;
	}

	public ArrayList<String> getLblApaConv() {
		return lblApaConv;
	}

	public void setLblApaConv(ArrayList<String> lblApaConv) {
		this.lblApaConv = lblApaConv;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public Integer getNumLineasConvMont() {
		return numLineasConvMont;
	}

	public void setNumLineasConvMont(Integer numLineasConvMont) {
		this.numLineasConvMont = numLineasConvMont;
	}

	public Integer[] getNumTurnosConvMont() {
		return numTurnosConvMont;
	}

	public void setNumTurnosConvMont(Integer[] numTurnosConvMont) {
		this.numTurnosConvMont = numTurnosConvMont;
	}

	public Integer getNumLineasAutMont() {
		return numLineasAutMont;
	}

	public void setNumLineasAutMont(Integer numLineasAutMont) {
		this.numLineasAutMont = numLineasAutMont;
	}

	public Integer[] getNumTurnosAutMont() {
		return numTurnosAutMont;
	}

	public void setNumTurnosAutMont(Integer[] numTurnosAutMont) {
		this.numTurnosAutMont = numTurnosAutMont;
	}

	public Integer getStdProdConvMont() {
		return stdProdConvMont;
	}

	public void setStdProdConvMont(Integer stdProdConvMont) {
		this.stdProdConvMont = stdProdConvMont;
	}

	public Integer getStdProdAutMont() {
		return stdProdAutMont;
	}

	public void setStdProdAutMont(Integer stdProdAutMont) {
		this.stdProdAutMont = stdProdAutMont;
	}

	public String getRespAparado() {
		return respAparado;
	}

	public void setRespAparado(String respAparado) {
		this.respAparado = respAparado;
	}

	public Integer getNumLineasConvApa() {
		return numLineasConvApa;
	}

	public void setNumLineasConvApa(Integer numLineasConvApa) {
		this.numLineasConvApa = numLineasConvApa;
	}

	public Integer[] getNumTurnosConvApa() {
		return numTurnosConvApa;
	}

	public void setNumTurnosConvApa(Integer[] numTurnosConvApa) {
		this.numTurnosConvApa = numTurnosConvApa;
	}

	public Integer getNumLineasAutApa() {
		return numLineasAutApa;
	}

	public void setNumLineasAutApa(Integer numLineasAutApa) {
		this.numLineasAutApa = numLineasAutApa;
	}

	public Integer[] getNumTurnosAutApa() {
		return numTurnosAutApa;
	}

	public void setNumTurnosAutApa(Integer[] numTurnosAutApa) {
		this.numTurnosAutApa = numTurnosAutApa;
	}

	public Integer getStdProdConvApa() {
		return stdProdConvApa;
	}

	public void setStdProdConvApa(Integer stdProdConvApa) {
		this.stdProdConvApa = stdProdConvApa;
	}

	public Integer getStdProdAutApa() {
		return stdProdAutApa;
	}

	public void setStdProdAutApa(Integer stdProdAutApa) {
		this.stdProdAutApa = stdProdAutApa;
	}

	public String getRespTroquelado() {
		return respTroquelado;
	}

	public void setRespTroquelado(String respTroquelado) {
		this.respTroquelado = respTroquelado;
	}

	public Integer getNumLineasConvTroq() {
		return numLineasConvTroq;
	}

	public void setNumLineasConvTroq(Integer numLineasConvTroq) {
		this.numLineasConvTroq = numLineasConvTroq;
	}

	public Integer[] getNumTurnosConvTroq() {
		return numTurnosConvTroq;
	}

	public void setNumTurnosConvTroq(Integer[] numTurnosConvTroq) {
		this.numTurnosConvTroq = numTurnosConvTroq;
	}

	public Integer getNumLineasAutTroq() {
		return numLineasAutTroq;
	}

	public void setNumLineasAutTroq(Integer numLineasAutTroq) {
		this.numLineasAutTroq = numLineasAutTroq;
	}

	public Integer[] getNumTurnosAutTroq() {
		return numTurnosAutTroq;
	}

	public void setNumTurnosAutTroq(Integer[] numTurnosAutTroq) {
		this.numTurnosAutTroq = numTurnosAutTroq;
	}

	public Integer getStdProdConvTroq() {
		return stdProdConvTroq;
	}

	public void setStdProdConvTroq(Integer stdProdConvTroq) {
		this.stdProdConvTroq = stdProdConvTroq;
	}

	public Integer getStdProdAutTroq() {
		return stdProdAutTroq;
	}

	public void setStdProdAutTroq(Integer stdProdAutTroq) {
		this.stdProdAutTroq = stdProdAutTroq;
	}

	public String getRespMontaje() {
		return respMontaje;
	}

	public void setRespMontaje(String respMontaje) {
		this.respMontaje = respMontaje;
	}

	public String getDiasLaborables() {
		return diasLaborables;
	}

	public void setDiasLaborables(String diasLaborables) {
		this.diasLaborables = diasLaborables;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getTotPedido() {
		return totPedido;
	}

	public void setTotPedido(Integer totPedido) {
		this.totPedido = totPedido;
	}

}
