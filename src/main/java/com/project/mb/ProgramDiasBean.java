package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.project.dao.ProgramacionDiasDao;
import com.project.dao.ProgramacionDiasDaoImpl;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;
import com.project.entities.Programdia;

@ManagedBean
@ViewScoped
public class ProgramDiasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Programdia> programDias;
	private Programdia selectedDias;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();

		// *******************ENVIAR EL PARAMETRO findByProceso(1) CONSULTADO
		// TODOS LOS PADRES ASI TAL COMO ESTA, ESTA MAL HECHO ***************

		// LINEAS DE MONTAJE
		ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
		List<Object[]> proDias1 = programDiasDao.findByProceso(1);
		for (Object[] result : proDias1) {
			Ordenprod op = (Ordenprod) result[0];
			Parametro pa = (Parametro) result[1];
			Programdia pr = (Programdia) result[2];
			eventModel.addEvent(new DefaultScheduleEvent(op
					.getOrdenprodCodigo()
					+ "-"
					+ pa.getProceso().getTipoProceso().getTprNombre()
					+ "-"
					+ pr.getCantpares(), pr.getFinicio(), pr.getFfin(),
					"montajeMTN"));
		}

		// LINEAS DE APARADO
		List<Object[]> proDias2 = programDiasDao.findByProceso(2);
		for (Object[] result : proDias2) {
			Ordenprod op = (Ordenprod) result[0];
			Parametro pa = (Parametro) result[1];
			Programdia pr = (Programdia) result[2];
			eventModel.addEvent(new DefaultScheduleEvent(op
					.getOrdenprodCodigo()
					+ "-"
					+ pa.getProceso().getTipoProceso().getTprNombre()
					+ "-"
					+ pr.getCantpares(), pr.getFinicio(), pr.getFfin(),
					"aparadoAPA"));
		}

		// LINEAS DE TROQUELADO
		List<Object[]> proDias3 = programDiasDao.findByProceso(3);
		for (Object[] result : proDias3) {
			Ordenprod op = (Ordenprod) result[0];
			Parametro pa = (Parametro) result[1];
			Programdia pr = (Programdia) result[2];
			eventModel.addEvent(new DefaultScheduleEvent(op
					.getOrdenprodCodigo()
					+ "-"
					+ pa.getProceso().getTipoProceso().getTprNombre()
					+ "-"
					+ pr.getCantpares(), pr.getFinicio(), pr.getFfin(),
					"troqueladoTRQ"));
		}

	}

	// INSERTAR EN EL MODELO SIN HORAS EXTRAS
	public void withOutHextras() {

	}

	// METODOS DML
	public void btnCreateProgramDias(ActionEvent actionEvent) {
	}

	public void btnDeleteProgramDias(ActionEvent actionEvent) {
	}

	public void btnUpdateProgramDias(ActionEvent actionEvent) {
	}

	// SETTERS AND GETTERS

	public List<Programdia> getProgramDias() {
		ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
		this.programDias = programDiasDao.findAll();
		return programDias;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void setProgramDias(List<Programdia> programDias) {
		this.programDias = programDias;
	}

	public Programdia getSelectedDias() {
		return selectedDias;
	}

	public void setSelectedDias(Programdia selectedDias) {
		this.selectedDias = selectedDias;
	}

}
