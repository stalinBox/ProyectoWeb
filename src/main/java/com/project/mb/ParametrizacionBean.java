package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ParametrizacionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private Date currentDate;
	private String diasLaborables = "";

	private String respMontaje = "";
	private Integer numLineasConvMont = 0;
	private Integer numTurnosConvMont = 0;
	private Integer numLineasAutMont = 0;
	private Integer numTurnosAutMont = 0;
	private Integer stdProdConvMont = 361;
	private Integer stdProdAutMont = 0;

	private String respAparado = "";
	private Integer numLineasConvApa = 0;
	private Integer numTurnosConvApa = 0;
	private Integer numLineasAutApa = 0;
	private Integer numTurnosAutApa = 0;
	private Integer stdProdConvApa = 0;
	private Integer stdProdAutApa = 0;

	private String respTroquelado = "";
	private Integer numLineasConvTroq = 0;
	private Integer numTurnosConvTroq = 0;
	private Integer numLineasAutTroq = 0;
	private Integer numTurnosAutTroq = 0;
	private Integer stdProdConvTroq = 0;
	private Integer stdProdAutTroq = 0;

	private Integer totPedido = 614;

	private List<MessageDistrib> messagesDistrib;
	private List<ColumnModelDistrib> columnsDistrib = new ArrayList<ColumnModelDistrib>();
	private List<ColumnModelDistrib> columnDistrib = new ArrayList<ColumnModelDistrib>();

	private List<MessageFechas> messagesFechas;
	private List<ColumnModelFechas> columnsFechas = new ArrayList<ColumnModelFechas>();
	private List<ColumnModelFechas> columnFechas = new ArrayList<ColumnModelFechas>();

	private List<MessageHoras> messagesHoras;
	private List<ColumnModelHoras> columnsHoras = new ArrayList<ColumnModelHoras>();
	private List<ColumnModelHoras> columnHoras = new ArrayList<ColumnModelHoras>();

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		currentDate = new Date();
	}

	public ParametrizacionBean() {
		tblFecha();
		createDynamicColumnsFechas();
	}

	// SECCION METODOS TABLAS***************
	public void createTablas() {
		updateColumnsFechas();
		updateColumnsDistrib(this.numLineasConvMont, this.stdProdConvMont);
		updateColumnsHoras(this.numLineasConvMont);
	}

	// METODO QUE RECIBE FECHAS
	public void updateColumnsFechas() {
		createDynamicColumnsFechas();
	}

	// METODO QUE RECIBE DISTRIBUCION PARES
	public void updateColumnsDistrib(Integer numLineasConvMont,
			Integer stdProdConv) {
		tblDistrib(numLineasConvMont, stdProdConv);
		createDynamicColumnsDistrib();
	}

	// METODO QUE RECIBE HORAS
	public void updateColumnsHoras(Integer numLineasConvMont) {
		tblHoras(numLineasConvMont);
		createDynamicColumnsHoras();
	}

	// METODO MOSTRAR INTERFAZ TABLA FECHA
	public void tblFecha() {
		String[] labelRowColumnCeroF = new String[] { "Montaje", "Aparado",
				"Troquelado" };

		if (messagesFechas == null) {
			messagesFechas = new ArrayList<MessageFechas>();
			for (int i = 0; i < labelRowColumnCeroF.length; i++) {
				MessageFechas message = new MessageFechas();
				// MENSAJES PARA LAS FILAS DE LAS COLUMAS
				message.setLblFechas("Fecha " + labelRowColumnCeroF[i]);
				// message.setFechas("06/06/06");
				message.setFf(currentDate);
				messagesFechas.add(message);
			}
		}
	}

	// METODO MOSTRAR INTERFAZ DISTRIBUCION PARES
	public void tblDistrib(Integer numLineasConvMont, Integer stdProdConv) {
		Integer[] numerosRep = new Integer[] { 12, 13, 14, 15 };

		Integer[][] numRep = new Integer[][] { { 12, 13, 14, 15 },
				{ 16, 17, 18, 19 } };

		for (int i = 0; i < numRep.length; i++) {
			for (int j = 0; j < numRep[0].length; j++) {
				System.out.println(numRep[i][j]);
			}
		}

		if (messagesDistrib == null) {
			messagesDistrib = new ArrayList<MessageDistrib>();
			for (int i = 1; i <= numLineasConvMont; i++) {
				MessageDistrib message = new MessageDistrib();
				// MENSAJES PARA LAS FILAS DE LAS COLUMAS
				message.setLblLineas("L" + i + "  Convencional");
				message.setCap(44);
				messagesDistrib.add(message);
			}
		}
	}

	// METODO MOSTRAR INTERFAZ HORAS
	public void tblHoras(Integer numLineasConvMont) {
		if (messagesHoras == null) {
			messagesHoras = new ArrayList<MessageHoras>();
			for (int i = 1; i <= numLineasConvMont; i++) {
				MessageHoras message = new MessageHoras();
				// MENSAJES PARA LAS FILAS DE LAS COLUMAS
				message.setLblLineasHoras("L" + i + "  Convencional");
				message.setCapDiaHoras("Horas");
				messagesHoras.add(message);
			}
		}
	}

	// METODO PARA CREAR LA TABLA DISTRIBUCION PARES
	public void createDynamicColumnsDistrib() {

		int bb = 0;
		if (!(this.diasLaborables.isEmpty())) {
			bb = Integer.parseInt(this.diasLaborables);
			if (bb == 0) {
				columnsDistrib.clear();
				columnDistrib.clear();
			} else {
				try {
					String[] toppings = { "lblLineas" };
					Integer i = 0;
					columnsDistrib.clear();
					columnDistrib.clear();

					// PRIMERA COLUMNA ESTATICA
					for (String k : toppings) {
						columnDistrib
								.add(new ColumnModelDistrib("Línea/Día", k));
					}

					// COLUMNA REPETITIVA
					do {
						i++;
						columnsDistrib.add(new ColumnModelDistrib(i.toString(),
								"capDiaConv"));
					} while (i < bb);

				} catch (Exception e) {
					System.out.println("ERROR AQUI TABLAS: " + e);
					columnsDistrib.clear();
					columnDistrib.clear();
				}
			}
		} else {
			columnsDistrib.clear();
			columnDistrib.clear();
		}
	}

	// METODO PARA CREAR LA TABLA FECHAS
	public void createDynamicColumnsFechas() {
		int bb = 0;
		if (!(this.diasLaborables.isEmpty())) {
			bb = Integer.parseInt(this.diasLaborables);
			if (bb == 0) {
				columnsFechas.clear();
				columnFechas.clear();
			} else {
				try {
					String[] toppings = { "lblFechas" };
					Integer i = 0;
					columnsFechas.clear();
					columnFechas.clear();
					// PRIMERA COLUMNA ESTATICA
					for (String k : toppings) {
						columnFechas.add(new ColumnModelFechas("Línea/Día", k));
					}

					// COLUMNA REPETITIVA
					do {
						i++;
						columnsFechas.add(new ColumnModelFechas(i.toString(),
								"ff"));
					} while (i < bb);

				} catch (Exception e) {
					System.out.println("ERROR AQUI TABLAS: " + e);
					columnsFechas.clear();
					columnFechas.clear();
				}
			}
		} else {
			columnsFechas.clear();
			columnFechas.clear();
		}
	}

	// METODO PARA CREAR LA TABLA HORAS
	public void createDynamicColumnsHoras() {
		int bb = 0;
		if (!(this.diasLaborables.isEmpty())) {
			bb = Integer.parseInt(this.diasLaborables);
			if (bb == 0) {
				columnsHoras.clear();
				columnHoras.clear();
			} else {
				try {
					String[] toppings = { "lblLineasHoras" };
					Integer i = 0;
					columnsHoras.clear();
					columnHoras.clear();
					// PRIMERA COLUMNA ESTATICA
					for (String k : toppings) {
						// columns.add(new ColumnModel("Línea/Día", k));
						columnHoras.add(new ColumnModelHoras("Línea/Día", k));
					}

					// COLUMNA REPETITIVA
					do {
						i++;
						columnsHoras.add(new ColumnModelHoras(i.toString(),
								"capDiaHoras"));
						// column.add(new ColumnModel(i.toString(), "val0"));
					} while (i < bb);

				} catch (Exception e) {
					System.out.println("ERROR AQUI TABLAS: " + e);
					columnsHoras.clear();
					columnHoras.clear();
				}
			}
		} else {
			columnsHoras.clear();
			columnHoras.clear();
		}
	}

	// SECCION CLASES TABLA FECHAS*****************
	public static class ColumnModelFechas implements Serializable {
		private static final long serialVersionUID = 1L;
		private String header;
		private String property;

		public ColumnModelFechas(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}

	public class MessageFechas implements Serializable {
		private static final long serialVersionUID = 1L;

		private String lblFechas;
		private String fechas;
		private Date ff;

		public MessageFechas() {
		}

		public Date getFf() {
			return ff;
		}

		public void setFf(Date ff) {
			this.ff = ff;
		}

		public String getLblFechas() {
			return lblFechas;
		}

		public void setLblFechas(String lblFechas) {
			this.lblFechas = lblFechas;
		}

		public String getFechas() {
			return fechas;
		}

		public void setFechas(String fechas) {
			this.fechas = fechas;
		}
	}

	// SECCION CLASES TABLA DISTRIBUCION PARES*****************
	public static class ColumnModelDistrib implements Serializable {
		private static final long serialVersionUID = 1L;
		private String header;
		private String property;

		public ColumnModelDistrib(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}

	public class MessageDistrib implements Serializable {
		private static final long serialVersionUID = 1L;

		private String lblLineas;
		private Integer capDia;
		private Integer capDiaConv;
		private Integer cap;

		public MessageDistrib() {
		}

		public String getLblLineas() {
			return lblLineas;
		}

		public Integer getCap() {
			return cap;
		}

		public void setCap(Integer cap) {
			this.cap = cap;
		}

		public void setLblLineas(String lblLineas) {
			this.lblLineas = lblLineas;
		}

		public Integer getCapDia() {
			return capDia;
		}

		public void setCapDia(Integer capDia) {
			this.capDia = capDia;
		}

		public Integer getCapDiaConv() {
			return capDiaConv;
		}

		public void setCapDiaConv(Integer capDiaConv) {
			this.capDiaConv = capDiaConv;
		}

	}

	// SECCION CLASES TABLA DISTRIBUCION HORAS*****************
	public static class ColumnModelHoras implements Serializable {
		private static final long serialVersionUID = 1L;
		private String header;
		private String property;

		public ColumnModelHoras(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}

	public class MessageHoras implements Serializable {
		private static final long serialVersionUID = 1L;

		private String lblLineasHoras;
		private String capDiaHoras;

		public MessageHoras() {
		}

		public String getLblLineasHoras() {
			return lblLineasHoras;
		}

		public void setLblLineasHoras(String lblLineasHoras) {
			this.lblLineasHoras = lblLineasHoras;
		}

		public String getCapDiaHoras() {
			return capDiaHoras;
		}

		public void setCapDiaHoras(String capDiaHoras) {
			this.capDiaHoras = capDiaHoras;
		}

	}

	// SETTERS AND GETTERS

	public Date getCurrentDate() {
		return currentDate;
	}

	public Integer getNumLineasConvMont() {
		return numLineasConvMont;
	}

	public void setNumLineasConvMont(Integer numLineasConvMont) {
		this.numLineasConvMont = numLineasConvMont;
	}

	public Integer getNumTurnosConvMont() {
		return numTurnosConvMont;
	}

	public void setNumTurnosConvMont(Integer numTurnosConvMont) {
		this.numTurnosConvMont = numTurnosConvMont;
	}

	public Integer getNumLineasAutMont() {
		return numLineasAutMont;
	}

	public void setNumLineasAutMont(Integer numLineasAutMont) {
		this.numLineasAutMont = numLineasAutMont;
	}

	public Integer getNumTurnosAutMont() {
		return numTurnosAutMont;
	}

	public void setNumTurnosAutMont(Integer numTurnosAutMont) {
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

	public Integer getNumTurnosConvApa() {
		return numTurnosConvApa;
	}

	public void setNumTurnosConvApa(Integer numTurnosConvApa) {
		this.numTurnosConvApa = numTurnosConvApa;
	}

	public Integer getNumLineasAutApa() {
		return numLineasAutApa;
	}

	public void setNumLineasAutApa(Integer numLineasAutApa) {
		this.numLineasAutApa = numLineasAutApa;
	}

	public Integer getNumTurnosAutApa() {
		return numTurnosAutApa;
	}

	public void setNumTurnosAutApa(Integer numTurnosAutApa) {
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

	public Integer getNumTurnosConvTroq() {
		return numTurnosConvTroq;
	}

	public void setNumTurnosConvTroq(Integer numTurnosConvTroq) {
		this.numTurnosConvTroq = numTurnosConvTroq;
	}

	public Integer getNumLineasAutTroq() {
		return numLineasAutTroq;
	}

	public void setNumLineasAutTroq(Integer numLineasAutTroq) {
		this.numLineasAutTroq = numLineasAutTroq;
	}

	public Integer getNumTurnosAutTroq() {
		return numTurnosAutTroq;
	}

	public void setNumTurnosAutTroq(Integer numTurnosAutTroq) {
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

	public List<MessageHoras> getMessagesHoras() {
		return messagesHoras;
	}

	public void setMessagesHoras(List<MessageHoras> messagesHoras) {
		this.messagesHoras = messagesHoras;
	}

	public List<ColumnModelHoras> getColumnsHoras() {
		return columnsHoras;
	}

	public void setColumnsHoras(List<ColumnModelHoras> columnsHoras) {
		this.columnsHoras = columnsHoras;
	}

	public List<ColumnModelHoras> getColumnHoras() {
		return columnHoras;
	}

	public void setColumnHoras(List<ColumnModelHoras> columnHoras) {
		this.columnHoras = columnHoras;
	}

	public List<MessageDistrib> getMessagesDistrib() {
		return messagesDistrib;
	}

	public void setMessagesDistrib(List<MessageDistrib> messagesDistrib) {
		this.messagesDistrib = messagesDistrib;
	}

	public List<ColumnModelDistrib> getColumnsDistrib() {
		return columnsDistrib;
	}

	public void setColumnsDistrib(List<ColumnModelDistrib> columnsDistrib) {
		this.columnsDistrib = columnsDistrib;
	}

	public List<ColumnModelDistrib> getColumnDistrib() {
		return columnDistrib;
	}

	public void setColumnDistrib(List<ColumnModelDistrib> columnDistrib) {
		this.columnDistrib = columnDistrib;
	}

	public List<MessageFechas> getMessagesFechas() {
		return messagesFechas;
	}

	public void setMessagesFechas(List<MessageFechas> messagesFechas) {
		this.messagesFechas = messagesFechas;
	}

	public List<ColumnModelFechas> getColumnsFechas() {
		return columnsFechas;
	}

	public void setColumnsFechas(List<ColumnModelFechas> columnsFechas) {
		this.columnsFechas = columnsFechas;
	}

	public List<ColumnModelFechas> getColumnFechas() {
		return columnFechas;
	}

	public void setColumnFechas(List<ColumnModelFechas> columnFechas) {
		this.columnFechas = columnFechas;
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
}
