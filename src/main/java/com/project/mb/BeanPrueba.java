package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class BeanPrueba implements Serializable {

	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private ArrayList<ArrayList<ArrayList<Integer>>> array3DPrueba = new ArrayList<ArrayList<ArrayList<Integer>>>();
	private ArrayList<ArrayList<Integer>> array1 = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Object>> array2 = new ArrayList<ArrayList<Object>>();
	private boolean hExtras;
	private Date fMontaje;

	@PostConstruct
	public void init() {
		// ***** DISTRIBUCION HORAS
		array2.add(new ArrayList<Object>());
		array2.get(0).add(1.11);
		array2.get(0).add(2.22);
		array2.get(0).add(3.33);
		array2.get(0).add(4.44);

		array2.add(new ArrayList<Object>());
		array2.get(1).add(5.55);
		array2.get(1).add(6.66);
		array2.get(1).add(7.77);
		array2.get(1).add(8.88);

		array2.add(new ArrayList<Object>());
		array2.get(2).add(9.99);
		array2.get(2).add(0.01);
		array2.get(2).add(1.12);
		array2.get(2).add(1.13);

		array2.add(new ArrayList<Object>());
		array2.get(3).add(1.21);
		array2.get(3).add(1.31);
		array2.get(3).add(1.41);
		array2.get(3).add(1.51);

		array2.add(new ArrayList<Object>());
		array2.get(4).add(1.00);
		array2.get(4).add(2.00);
		array2.get(4).add(3.00);
		array2.get(4).add(4.00);

		// ***** DISTRIBUCION EXAMPLE
		array1.add(new ArrayList<Integer>());
		array1.get(0).add(111);
		array1.get(0).add(222);
		array1.get(0).add(333);
		array1.get(0).add(444);

		array1.add(new ArrayList<Integer>());
		array1.get(1).add(555);
		array1.get(1).add(666);
		array1.get(1).add(777);
		array1.get(1).add(888);

		array1.add(new ArrayList<Integer>());
		array1.get(2).add(999);
		array1.get(2).add(000);
		array1.get(2).add(112);
		array1.get(2).add(113);

		array1.add(new ArrayList<Integer>());
		array1.get(3).add(121);
		array1.get(3).add(131);
		array1.get(3).add(141);
		array1.get(3).add(151);

		array1.add(new ArrayList<Integer>());
		array1.get(4).add(100);
		array1.get(4).add(200);
		array1.get(4).add(300);
		array1.get(4).add(400);

		// CONFIGURAR ESTRUCTURA 3D
		for (int i = 0; i < 5; i++) {
			this.array3DPrueba.add(new ArrayList<ArrayList<Integer>>());
			for (int j = 0; j < 4; j++) {
				this.array3DPrueba.get(i).add(new ArrayList<Integer>());
			}
		}
		int g = 0;
		// AGREGAR LOS DATOS DE 2D a 3D
		for (int i = 0; i < array1.size(); i++) {
			for (int j = 0; j < array1.get(i).size(); j++) {
				g = array1.get(i).get(j);
				this.array3DPrueba.get(i).get(j).add(g);
			}
		}
		eventModel = new DefaultScheduleModel();
	}

	// private Calendar today() {
	// Calendar calendar = Calendar.getInstance();
	// calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
	// calendar.get(Calendar.DATE), 0, 0, 0);
	// return calendar;
	// }

	@SuppressWarnings("deprecation")
	private Calendar nextDayExtras(Calendar a) {
		if (a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
			nextDayExtras(a);
		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
			a.set(Calendar.HOUR, 8);
		}
		return a;
	}

	@SuppressWarnings("deprecation")
	private Calendar nextDay(Calendar a) {
		if (a.getTime().getDay() == 5 || a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
			nextDay(a);
		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) + 1);
			a.set(Calendar.HOUR, 8);
		}
		return a;
	}

	// METODOS
	public void withHextras(Calendar fMontajeParamE) {
		// INSERTAR EN EL MODELO
		eventModel = new DefaultScheduleModel();
		Calendar b = fMontajeParamE;
		for (int i = 0; i < array3DPrueba.size(); i++) {
			for (int j = 0; j < array3DPrueba.get(i).size(); j++) {
				// AÑADIR EVENTOS EN EL DIA
				eventModel.addEvent(new DefaultScheduleEvent("L" + (j + 1)
						+ "Convencional: "
						+ array3DPrueba.get(i).get(j).toString(), b.getTime(),
						b.getTime()));
			}
			b = nextDayExtras(b);
		}
	}

	public void withOutHextras(Calendar fMontajeParam) {
		// INSERTAR EN EL MODELO
		eventModel = new DefaultScheduleModel();
		Calendar b = fMontajeParam;
		for (int i = 0; i < array3DPrueba.size(); i++) {
			for (int j = 0; j < array3DPrueba.get(i).size(); j++) {
				// AÑADIR EVENTOS EN EL DIA
				eventModel.addEvent(new DefaultScheduleEvent("L" + (j + 1)
						+ "Convencional: "
						+ array3DPrueba.get(i).get(j).toString(), b.getTime(),
						b.getTime()));
			}
			b = nextDay(b);
		}
	}

	public Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public void generateSchedule() {
		Calendar tConvertCal = null;
		System.out.println("Verdadero o falso: " + this.hExtras);
		System.out.println("Fecha seleccionada: " + this.fMontaje);
		tConvertCal = DateToCalendar(this.fMontaje);
		System.out.println("Numero del dia elegido: " + this.fMontaje.getDay());

		if (this.fMontaje.getDay() == 0 || this.fMontaje.getDay() == 6) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"No se puede empezar a programar los fines de semana"));

		} else if (this.hExtras == true) {
			withHextras(tConvertCal);
		} else {
			withOutHextras(tConvertCal);
		}
	}

	// SETTERS AND GETTERS

	public ScheduleEvent getEvent() {
		return event;
	}

	public Date getfMontaje() {
		return fMontaje;
	}

	public void setfMontaje(Date fMontaje) {
		this.fMontaje = fMontaje;
	}

	public boolean gethExtras() {
		return hExtras;
	}

	public void sethExtras(boolean hExtras) {
		this.hExtras = hExtras;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	// EVENTOS DEL SCHEDULE
	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);
		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event moved", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());
		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event resized", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());
		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
