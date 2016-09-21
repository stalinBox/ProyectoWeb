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
	private Integer cpProdConvMontaje = 361;
	private Integer cpProdAutMontaje = 0;

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
		tblDistrib();
		createDynamicColumnsDistrib();
		tblHoras();
		createDynamicColumnsHoras();
	}

	// SETTERS AND GETTERS

	public Date getCurrentDate() {
		return currentDate;
	}

	public Integer getCpProdConvMontaje() {
		return cpProdConvMontaje;
	}

	public void setCpProdConvMontaje(Integer cpProdConvMontaje) {
		this.cpProdConvMontaje = cpProdConvMontaje;
	}

	public Integer getCpProdAutMontaje() {
		return cpProdAutMontaje;
	}

	public void setCpProdAutMontaje(Integer cpProdAutMontaje) {
		this.cpProdAutMontaje = cpProdAutMontaje;
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

	// SECCION METODOS ***************

	// METODO QUE RECIBE FECHAS
	public void updateColumnsDistrib() {
		createDynamicColumnsDistrib();
	}

	// METODO QUE RECIBE DISTRIBUCION
	public void updateColumnsFechas() {
		createDynamicColumnsFechas();
	}

	// METODO QUE RECIBE HORAS
	public void updateColumnsHoras() {
		createDynamicColumnsHoras();
	}

	// METODO MOSTRAR ITNERDAZ TABLA FECHA
	public void tblFecha() {
		String[] labelRowColumnCeroF = new String[] { "Montaje", "Aparado",
				"Troquelado" };

		if (messagesFechas == null) {
			messagesFechas = new ArrayList<MessageFechas>();
			for (int i = 0; i < labelRowColumnCeroF.length; i++) {
				MessageFechas message = new MessageFechas();
				// MENSAJES PARA LAS FILAS DE LAS COLUMAS
				message.setLblFechas("Fecha " + labelRowColumnCeroF[i]);
				message.setFechas("06/06/06");
				messagesFechas.add(message);
			}
		}
	}

	// METODO MOSTRAR INTERFAZ DISTRIBUCION PARES
	public void tblDistrib() {
		String[] labelRowColumnCeroL = new String[] { "Linea Conv. ",
				"Linea Auto. " };

		if (messagesDistrib == null) {
			messagesDistrib = new ArrayList<MessageDistrib>();
			for (int i = 1; i <= 5; i++) {
				MessageDistrib message = new MessageDistrib();
				// MENSAJES PARA LAS FILAS DE LAS COLUMAS
				message.setLblLineas("L" + i + "  Convencional");
				message.setCapDia("Numeros");
				messagesDistrib.add(message);
			}
		}
	}

	// METODO MOSTRAR INTERFAZ HORAS
	public void tblHoras() {
		String[] labelRowColumnCeroL = new String[] { "Linea Conv. ",
				"Linea Auto. " };

		if (messagesHoras == null) {
			messagesHoras = new ArrayList<MessageHoras>();
			for (int i = 1; i <= 5; i++) {
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
						// columns.add(new ColumnModel("Línea/Día", k));
						columnDistrib
								.add(new ColumnModelDistrib("Línea/Día", k));
					}

					// COLUMNA REPETITIVA
					do {
						i++;
						columnsDistrib.add(new ColumnModelDistrib(i.toString(),
								"capDia"));
						// column.add(new ColumnModel(i.toString(), "val0"));
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
						// columns.add(new ColumnModel("Línea/Día", k));
						columnFechas.add(new ColumnModelFechas("Línea/Día", k));
					}

					// COLUMNA REPETITIVA
					do {
						i++;
						columnsFechas.add(new ColumnModelFechas(i.toString(),
								"fechas"));
						// column.add(new ColumnModel(i.toString(), "val0"));
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

		public MessageFechas() {
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
		private String capDia;

		public MessageDistrib() {
		}

		public String getLblLineas() {
			return lblLineas;
		}

		public void setLblLineas(String lblLineas) {
			this.lblLineas = lblLineas;
		}

		public String getCapDia() {
			return capDia;
		}

		public void setCapDia(String capDia) {
			this.capDia = capDia;
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
}
