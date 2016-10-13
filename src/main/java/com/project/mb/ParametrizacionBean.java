package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

	private Integer totPedido = 614;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.currentDate = new Date();

		this.stdProdConvMont = 361;
		this.totPedido = 614;

		this.stdProdConvApa = 0;
		this.stdProdConvTroq = 0;
		this.stdProdAutApa = 0;
		this.stdProdAutMont = 0;
		this.stdProdAutTroq = 0;
	}

	// CONSTRUCTOR
	public ParametrizacionBean() {

	}

	public void ExecuteParams() {
		// DistribucionTables nn = new DistribucionTables(100, 2000);
		System.out.println("PARAMETROS A UTILZAR");
		System.out.println("Dias: " + this.diasLaborables);
		System.out.println("Std Produccion: " + this.stdProdConvMont);
		System.out.println("Total Pedido: " + this.totPedido);
		System.out.println("Responsable: " + this.respMontaje);
		System.out.println("Numero de lineas: " + this.numLineasConvMont);
		System.out.println("Turnos por cada Linea: "
				+ this.addNumTurnosConvMont);

		// EJECUCION DE PROCESO

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

	// ********************** SECCION METODOS TABLAS******************
	public void createTablas() {
		updateColumnsFechas();
		updateColumnsDistrib();
		updateColumnsHoras();
	}

	// METODO QUE RECIBE FECHAS
	public void updateColumnsFechas() {

	}

	// METODO QUE RECIBE DISTRIBUCION PARES
	public void updateColumnsDistrib() {

	}

	// METODO QUE RECIBE HORAS
	public void updateColumnsHoras() {

	}

	// SETTERS AND GETTERS

	public ArrayList<String> getLblMonConv() {
		return lblMonConv;
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
