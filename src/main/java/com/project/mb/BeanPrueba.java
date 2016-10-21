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
import org.primefaces.model.LazyScheduleModel;
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

	@PostConstruct
	public void init() {
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

		System.out.println("Array de ejemplo: " + array1);
		// CONFIGURAR ESTRUCTURA 3D
		for (int i = 0; i <= 3; i++) {
			this.array3DPrueba.add(new ArrayList<ArrayList<Integer>>());
			for (int j = 0; j <= 3; j++) {
				this.array3DPrueba.get(i).add(new ArrayList<Integer>());
			}
		}
		// AGREGAR LOS DATOS DE 2D a 3D
		for (int i = 0; i < array1.size(); i++) {
			for (int j = 0; j < array1.get(i).size(); j++) {
				int g = array1.get(i).get(j);
				this.array3DPrueba.get(i).get(j).add(g);
			}
		}
		System.out.println("Array 3D pruebas: " + array3DPrueba);

		// INSERTAR EN EL MODELO
		eventModel = new DefaultScheduleModel();
		//
		// eventModel.addEvent(new DefaultScheduleEvent("CHUMAAA1",
		// nextDay9Am(),
		// nextDay11Am()));
		//
		// eventModel.addEvent(new DefaultScheduleEvent("CHUMAAA2",
		// nextDay9Am(),
		// nextDay11Am()));
		//
		// eventModel.addEvent(new DefaultScheduleEvent("CHUMAAA3",
		// nextDay9Am(),
		// nextDay11Am()));

		// RECORRIDO DE UN ARRAY BIDIMIMENCIONAL
		for (int i = 0; i < array1.size(); i++) {
			for (int j = 0; j < array1.get(i).size(); j++) {
				System.out.print(array1.get(i).get(j) + " ");

				// AÑADIR EVENTOS EN EL DIA

				eventModel.addEvent(new DefaultScheduleEvent(array1.get(i)
						.get(j).toString(), nextDay6Am(), previousDay8Pm()));

			}
		}
	}

	private Calendar today() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);
		return calendar;
	}

	private Date previousDay8Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 8);

		return t.getTime();
	}

	private Date nextDay6Am() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 6);

		return t.getTime();
	}

	// SETTERS AND GETTERS
	public ScheduleEvent getEvent() {
		return event;
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
