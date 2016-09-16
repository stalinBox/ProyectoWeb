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
	private String diasLaborables = "0";
	private String NlineasConv;
	private String NlineasAut;
	private String Nmaquinas;
	private String SpAut;
	private String SpCon;

	// PRUEBAS
	private List<Message> messages;
	private List<Message> messageDetails;
	private List<Message> filteredMessages;

	private String columnTemplate = "subject text country textLength";
	private List<ColumnModel> columns = new ArrayList<ColumnModel>();

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		currentDate = new Date();
	}

	public ParametrizacionBean() {
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

	// SETTERS AND GETTERS
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getDiasLaborables() {
		return diasLaborables;
	}

	public void setDiasLaborables(String diasLaborables) {
		this.diasLaborables = diasLaborables;
	}

	public String getNlineasConv() {
		return NlineasConv;
	}

	public void setNlineasConv(String nlineasConv) {
		NlineasConv = nlineasConv;
	}

	public String getNlineasAut() {
		return NlineasAut;
	}

	public void setNlineasAut(String nlineasAut) {
		NlineasAut = nlineasAut;
	}

	public String getNmaquinas() {
		return Nmaquinas;
	}

	public void setNmaquinas(String nmaquinas) {
		Nmaquinas = nmaquinas;
	}

	public String getSpAut() {
		return SpAut;
	}

	public void setSpAut(String spAut) {
		SpAut = spAut;
	}

	public String getSpCon() {
		return SpCon;
	}

	public void setSpCon(String spCon) {
		SpCon = spCon;
	}

	// PRUEBAS SETANDGET
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Message> getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(List<Message> messageDetails) {
		this.messageDetails = messageDetails;
	}

	public List<Message> getFilteredMessages() {
		return filteredMessages;
	}

	public void setFilteredMessages(List<Message> filteredMessages) {
		this.filteredMessages = filteredMessages;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public String getColumnTemplate() {
		return columnTemplate;
	}

	public void setColumnTemplate(String columnTemplate) {
		this.columnTemplate = columnTemplate;
	}

	// METODOS
	public void createDynamicColumns() {
		System.out.println("dias Lab: " + this.diasLaborables);

		try {
			String[] toppings = { "lineaProd" };
			Integer i = 0;
			columns.clear();
			for (String k : toppings) {
				columns.add(new ColumnModel("Línea/Día", k));
			}

			do {
				i++;
				columns.add(new ColumnModel(i.toString(), "val0"));
			} while (i <= Integer.parseInt(this.diasLaborables));

		} catch (Exception e) {
			System.out.println("ERROR AQUI TABLAS: " + e);
			columns.clear();
		}

	}

	public void updateColumns() {
		createDynamicColumns();
	}

	// CLASES ESTATICAS
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

	// CLASE MENSAJE
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
