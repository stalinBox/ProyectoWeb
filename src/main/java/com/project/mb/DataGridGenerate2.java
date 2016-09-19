package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DataGridGenerate2 implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Message> messages;
	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private List<ColumnModel> column = new ArrayList<ColumnModel>();
	private String diasLaborables = "";

	// SETTERS AND GETTERS

	public List<Message> getMessages() {
		return messages;
	}

	public List<ColumnModel> getColumn() {
		return column;
	}

	public void setColumn(List<ColumnModel> column) {
		this.column = column;
	}

	public String getDiasLaborables() {
		return diasLaborables;
	}

	public void setDiasLaborables(String diasLaborables) {
		this.diasLaborables = diasLaborables;
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

	public DataGridGenerate2() {
		if (messages == null) {
			messages = new ArrayList<Message>();

			for (int i = 0; i < 3; i++) {
				Message message = new Message();
				message.setDeliveryStatus("successfull");
				message.setLnpd("L " + i);
				message.setLineaProd("L " + i);
				messages.add(message);
			}
		}
		createDynamicColumns();
	}

	public void createDynamicColumns() {
		int bb = 0;
		if (!(this.diasLaborables.isEmpty())) {
			bb = Integer.parseInt(this.diasLaborables);
			if (bb == 0) {
				columns.clear();
				column.clear();
			} else {
				try {
					String[] toppings = { "lineaProd" };
					Integer i = 0;
					columns.clear();
					column.clear();
					for (String k : toppings) {
						columns.add(new ColumnModel("Línea/Día", k));
					}
					do {
						i++;
						columns.add(new ColumnModel(i.toString(), "val0"));
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

	public void updateColumns() {
		createDynamicColumns();
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

		private String subject;
		private String text;
		private String textLength;
		private String country;
		private String deliveryStatus;

		private Double val0;
		private Double val1;

		private String lnpd;

		private String lineaProd;

		public Message() {
			textLength = Math.random() * 10 + "";
		}

		public String getLineaProd() {
			return lineaProd;
		}

		public void setLineaProd(String lineaProd) {
			this.lineaProd = lineaProd;
		}

		public String getLnpd() {
			return lnpd;
		}

		public void setLnpd(String lnpd) {
			this.lnpd = lnpd;
		}

		public final String getSubject() {
			return subject;
		}

		public final void setSubject(String subject) {
			this.subject = subject;
		}

		public final String getText() {
			return text;
		}

		public final void setText(String text) {
			this.text = text;
		}

		public String getTextLength() {
			return textLength;
		}

		public void setTextLength(String textLength) {
			this.textLength = textLength;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getDeliveryStatus() {
			return deliveryStatus;
		}

		public void setDeliveryStatus(String deliveryStatus) {
			this.deliveryStatus = deliveryStatus;
		}

		public Double getVal0() {
			return val0;
		}

		public void setVal0(Double val0) {
			this.val0 = val0;
		}

		public Double getVal1() {
			return val1;
		}

		public void setVal1(Double val1) {
			this.val1 = val1;
		}

	}
}
