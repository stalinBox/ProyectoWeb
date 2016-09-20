package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DataGridGenerate implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Message> messages;
	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private List<ColumnModel> column = new ArrayList<ColumnModel>();
	private Integer numLineasC = 1;
	private String diasLaborables;

	// SETTERS AND GETTERS
	public List<Message> getMessages() {
		return messages;
	}

	public Integer getNumLineasC() {
		return numLineasC;
	}

	public void setNumLineasC(Integer numLineasC) {
		this.numLineasC = numLineasC;
	}

	public List<ColumnModel> getColumn() {
		return column;
	}

	public void setColumn(List<ColumnModel> column) {
		this.column = column;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	// SECCION DE METODOS, CONSTRUCTORES CLASES

	// CONTRUCTOR DE LA CLASE***************
	public DataGridGenerate() {

		tblDistrib();
		createDynamicColumnsDistrib(this.diasLaborables);
	}

	public void updateColumns(String diasLaborables) {
		createDynamicColumns(diasLaborables);
		this.diasLaborables = diasLaborables;
	}

	public void updateColumnsDistrib(String diasLaborables) {
		createDynamicColumnsDistrib(diasLaborables);
		this.diasLaborables = diasLaborables;
	}

	public void tblFecha() {
		String[] labelRowColumnCeroF = new String[] { "Montaje", "Aparado",
				"Troquelado" };

		if (messages == null) {
			messages = new ArrayList<Message>();
			for (int i = 0; i < labelRowColumnCeroF.length; i++) {
				Message message = new Message();
				// MENSAJES PARA LAS FILAS DE LAS COLUMAS
				message.setLblFechas("Fecha " + labelRowColumnCeroF[i]);
				message.setFechas("06/06/06");
				messages.add(message);
			}
		}
	}

	public void createDynamicColumns(String diasLaborables) {
		int bb = 0;
		if (!(diasLaborables.isEmpty())) {
			bb = Integer.parseInt(diasLaborables);
			if (bb == 0) {
				columns.clear();
				column.clear();
			} else {
				try {
					String[] toppings = { "lblFechas" };
					Integer i = 0;
					columns.clear();
					column.clear();
					// PRIMERA COLUMNA ESTATICA
					for (String k : toppings) {
						// columns.add(new ColumnModel("Línea/Día", k));
						column.add(new ColumnModel("Línea/Día", k));
					}

					// COLUMNA REPETITIVA
					do {
						i++;
						columns.add(new ColumnModel(i.toString(), "fechas"));
						// column.add(new ColumnModel(i.toString(), "val0"));
					} while (i < bb);

				} catch (Exception e) {
					System.out.println("ERROR AQUI TABLAS: " + e);
					columns.clear();
					column.clear();
				}
			}
		} else {
			columns.clear();
			column.clear();
		}
	}

	// TABLA DISTRIBUCION
	public void tblDistrib() {
		String[] labelRowColumnCeroL = new String[] { "Linea Conv. ",
				"Linea Auto. " };

		if (messages == null) {
			messages = new ArrayList<Message>();
			for (int i = 1; i <= this.numLineasC; i++) {
				Message message = new Message();
				// MENSAJES PARA LAS FILAS DE LAS COLUMAS
				message.setLblLineas("L" + i + "  Convencional");
				message.setCapDia("Numeros");
				messages.add(message);
			}
		}
	}

	public void createDynamicColumnsDistrib(String diasLaborables) {
		int bb = 0;
		if (!(diasLaborables.isEmpty())) {
			bb = Integer.parseInt(diasLaborables);
			if (bb == 0) {
				columns.clear();
				column.clear();
			} else {
				try {
					String[] toppings = { "lblLineas" };
					Integer i = 0;
					columns.clear();
					column.clear();

					// PRIMERA COLUMNA ESTATICA
					for (String k : toppings) {
						// columns.add(new ColumnModel("Línea/Día", k));
						column.add(new ColumnModel("Línea/Día", k));
					}

					// COLUMNA REPETITIVA
					do {
						i++;
						columns.add(new ColumnModel(i.toString(), "capDia"));
						// column.add(new ColumnModel(i.toString(), "val0"));
					} while (i < bb);

				} catch (Exception e) {
					System.out.println("ERROR AQUI TABLAS: " + e);
					columns.clear();
					column.clear();
				}
			}
		} else {
			columns.clear();
			column.clear();
		}
	}

	public static class ColumnModel implements Serializable {
		private static final long serialVersionUID = 1L;
		private String header;
		private String property;

		public ColumnModel(String header, String property) {
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

	public class Message implements Serializable {
		private static final long serialVersionUID = 1L;

		private String lblFechas;
		private String fechas;
		private String lblLineas;
		private String capDia;

		public Message() {
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
}
