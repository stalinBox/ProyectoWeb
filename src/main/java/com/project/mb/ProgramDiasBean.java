package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.project.entities.Programdia;
import com.project.utils.MapeoProgramacionDias;

@ManagedBean
@ViewScoped
public class ProgramDiasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Programdia> programDias;
	private Programdia selectedDias;
	private List<MapeoProgramacionDias> selectItemsProgramDias;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();

		// CONSULTA A LA BD SOBRE TODOS LOS PROCESOS ACTIVOS
		// HAY QUE CONSULTAR POR PROCESOS,POR ORDEN Y POR PARAMETRIZACION PARA
		// LA VISUALIZACION
		ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
		List<Programdia> proDias = programDiasDao.findAll();
		for (Programdia program : proDias) {
			MapeoProgramacionDias seleccion = new MapeoProgramacionDias(
					program.getProgdiasCodigo(), program.getCanthoras(),
					program.getCantpares(), program.getFinicio(),
					program.getFfin());
			eventModel.addEvent(new DefaultScheduleEvent(seleccion
					.getCantPares().toString() + " MONTAJE", seleccion
					.gethInicio(), seleccion.gethFin(), "montajeMTN"));
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

	public List<MapeoProgramacionDias> getSelectItemsProgramDias() {
		this.selectItemsProgramDias = new ArrayList<MapeoProgramacionDias>();
		ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
		List<Programdia> proDias = programDiasDao.findAll();
		for (Programdia program : proDias) {
			MapeoProgramacionDias seleccion = new MapeoProgramacionDias(
					program.getProgdiasCodigo(), program.getCanthoras(),
					program.getCantpares(), program.getFinicio(),
					program.getFfin());
			// Insertar aqui la consulta de todos los procesos
			System.out.println("MAPEO: " + seleccion);
			this.selectItemsProgramDias.add(seleccion);
		}

		return selectItemsProgramDias;
	}

	public void setSelectItemsProgramDias(
			List<MapeoProgramacionDias> selectItemsProgramDias) {
		this.selectItemsProgramDias = selectItemsProgramDias;
	}

}
