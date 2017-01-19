package com.project.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.project.dao.LineasTurnosDao;
import com.project.dao.LineasTurnosDaoImpl;
import com.project.dao.ParametrizacionDao;
import com.project.dao.ParametrizacionDaoImpl;
import com.project.dao.ProgramacionDiasDao;
import com.project.dao.ProgramacionDiasDaoImpl;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;
import com.project.entities.Programdia;
import com.project.utils.ContentParam;
import com.project.utils.MyUtil;
import com.project.utils.Tablas;

@ManagedBean
@ViewScoped
public class ProgramDiasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Programdia> programDias;
	private Programdia selectedDias;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	private Date fInicio;
	private boolean hExtras;
	private String d;

	private Map<Integer, Object> mLineasCantidad = new HashMap<Integer, Object>();

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

	// METODOS BOTONES
	public void btnReProcesar() {
		String ruta = "";
		ruta = MyUtil.calzadoPath() + "ordenesProd/insertOrder.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void btnContinuar() {
		String ruta = "";
		ruta = MyUtil.calzadoPath() + "programacionTurnos/programturnos.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnProcesar() {
		eventModel = new DefaultScheduleModel();
		System.out.println("Procesando...");
		System.out.println("Codigo Orden: " + ContentParam.getCodOrden());

		ParametrizacionDao paramDao = new ParametrizacionDaoImpl();
		List<Parametro> parametros = paramDao.getProcesosbyOrden(ContentParam
				.getCodOrden());

		for (Parametro param : parametros) {
			this.mLineasCantidad.clear();
			LineasTurnosDao lienasTurnosDao = new LineasTurnosDaoImpl();
			List<Integer> lineasTurnos = lienasTurnosDao.getLineasByProceso(
					param.getProceso().getProCodigo(),
					ContentParam.getCodOrden());

			for (Integer lt : lineasTurnos) {
				Object countLinea = lienasTurnosDao.getCountTurnosByLineas(lt,
						ContentParam.getCodOrden());
				this.mLineasCantidad.put(lt, countLinea);
			}

			if (this.mLineasCantidad.isEmpty()) {
				System.out
						.println("No hay lineas para generar la distribucion por dias");
			} else {
				Tablas tablas = new Tablas();
				tablas.receivParamsPares(ContentParam.getTotalOrden(),
						param.getStandconv(), this.mLineasCantidad);
			}

		}

		// // DistribucionTables tablas = new DistribucionTables();
		// // ArrayList<ArrayList<Object>> array0 =
		// // tablas.receivParamsPares(
		// // ContentParam.getTotalOrden(), p.getStandconv(),
		// // arrayTurnos);
		// //
		// // System.out.println("ARRAY " + array0);
		//
		// // // CONVERT OBJECT FROM ARRAYLIST TO [][]
		// // ConvertMatrizTranspuesta convertTranspuesta = new
		// // ConvertMatrizTranspuesta();
		// // ConvertArrayToMatriz convert = new ConvertArrayToMatriz();
		// //
		// // // ARRAY PARES
		// // Object[][] array2 = new Object[array0.size()][];
		// // array2 = convert.convertArray(array0);
		// //
		// // // TRANSPUESTA PARES
		// // ArrayList<Integer> matriz = new ArrayList<Integer>();
		// // matriz = convertTranspuesta.converMatrizTranspuesta(array2);
		//
		// // this.mAll.put(p.getProceso().getProCodigo(), array0);
		// // Muestra en el schedule
		// // generateSchedule(matrizTPmontaje, p.getProceso()
		// // .getTipoProceso().getTprNombre());
		// //}
		// }
		// RECORRIENDO PARA VER LAS MATRICES DEL HASHMAP
		// Iterator<Integer> it = mAll.keySet().iterator();
		// while (it.hasNext()) {
		// Integer key = (Integer) it.next();
		// ArrayList<Integer> a = mAll.get(key);
		// System.out.println("Codigo Param: " + key + " -> Valor: " + a);
		// }
	}

	// GENERAR LOS EVENTOS EN EL CALENDARIO
	@SuppressWarnings("deprecation")
	public void generateSchedule(Object[][] matrizTPmontaje, String nomProceso) {

		// VARIABLES PARA FECHAS
		Calendar tConvertCal = null;
		Calendar tConvertCalApa = null;
		Calendar tConvertCalTrq = null;
		tConvertCal = DateToCalendar(this.fInicio);

		if (nomProceso.equals("MONTAJE")) {
			System.out.println("Fecha Montaje--: " + tConvertCal.getTime());

			// Verificar que no se programe en fines de semana
			if (this.fInicio.getDay() == 0 || this.fInicio.getDay() == 6) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"No se puede empezar a programar los fines de semana"));
				eventModel = new DefaultScheduleModel();
			} else if (this.hExtras == true) {
				withHextras(matrizTPmontaje, tConvertCal, nomProceso);
			} else {
				withOutHextras(matrizTPmontaje, tConvertCal, nomProceso);
			}

		} else if (nomProceso.equals("APARADO")) {
			tConvertCalApa = prevDayApa(tConvertCal);
			System.out.println("Fecha Aparado**: " + tConvertCalApa.getTime());

			if (this.hExtras == true) {
				withHextras(matrizTPmontaje, tConvertCalApa, nomProceso);
			} else {
				withOutHextras(matrizTPmontaje, tConvertCalApa, nomProceso);
			}

		} else if (nomProceso.equals("TROQUELADO")) {
			tConvertCalTrq = prevDayTrq(tConvertCal);
			System.out.println("Fecha Troquelado//: "
					+ tConvertCalTrq.getTime());
			if (this.hExtras == true) {
				withHextras(matrizTPmontaje, tConvertCalTrq, nomProceso);
			} else {
				withOutHextras(matrizTPmontaje, tConvertCalTrq, nomProceso);
			}
		} else {
			System.out.println("ALGUNA VARIABLE NO FUE BUENA");
		}

	}

	// INSERTAR EN EL MODELO SIN HORAS EXTRAS
	public void withOutHextras(Object[][] matrizT, Calendar fMontajeParam,
			String nomProceso) {

		Calendar m = fMontajeParam;
		float d = 0, s = 0;

		NumberFormat formatter = new DecimalFormat("#0.00");
		for (int i = 0; i < matrizT.length; i++) {
			d++;
			for (int j = 0; j < matrizT[i].length; j++) {
				eventModel.addEvent(new DefaultScheduleEvent(nomProceso + " "
						+ matrizT[i][j].toString(), m.getTime(), m.getTime()));
			}
			m = nextDay(m);
		}
		s = (d / 5);
		formatter.format(s);

		if (s > 4) {
			this.d = "La programacion no debe sobrepasar el mes 4 semanas, ud ha programado "
					+ s + " Semanas";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(this.d));
			eventModel = new DefaultScheduleModel();
		} else {
			this.d = "Se ha programado para " + s + " Semanas ";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(this.d));
		}

		System.out.println("Dias labadorados: " + d);
		System.out.println("Semanas labadoradas: " + s);
	}

	// INSERTAR EN EL MODELO CON HORAS EXTRAS
	public void withHextras(Object[][] matrizT, Calendar fMontajeParam,
			String nomProceso) {

		Calendar m = fMontajeParam;
		float d = 0, s = 0;

		NumberFormat formatter = new DecimalFormat("#0.00");
		for (int i = 0; i < matrizT.length; i++) {
			d++;
			for (int j = 0; j < matrizT[i].length; j++) {
				eventModel.addEvent(new DefaultScheduleEvent(nomProceso + " "
						+ matrizT[i][j].toString(), m.getTime(), m.getTime()));

			}
			m = nextDayExtras(m);
		}
		s = (d / 6);
		formatter.format(s);

		if (s > 4) {
			this.d = "La programacion no debe sobrepasar el mes 4 semanas, ud ha programado "
					+ s + " Semanas";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(this.d));
		} else {
			this.d = "Se ha programado para " + s + " Semanas ";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(this.d));
		}

		System.out.println("Dias labadorados: " + d);
		System.out.println("Semanas labadoradas: " + s);
	}

	// ***************RECORRER DIAS EN EL CALENDAR*************
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

	@SuppressWarnings("deprecation")
	private Calendar prevDayApa(Calendar a) {
		if (a.getTime().getDay() == 5 || a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
			prevDayApa(a);
		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
			a.set(Calendar.HOUR, 8);
		}
		return a;
	}

	@SuppressWarnings("deprecation")
	private Calendar prevDayTrq(Calendar a) {
		if (a.getTime().getDay() == 5 || a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
			prevDayTrq(a);

		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 2);
			a.set(Calendar.HOUR, 8);
		}
		return a;
	}

	public Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
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
		// ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
		// this.programDias = programDiasDao.findAll();
		return programDias;
	}

	public Map<Integer, Object> getmLineasCantidad() {
		return mLineasCantidad;
	}

	public void setmLineasCantidad(Map<Integer, Object> mLineasCantidad) {
		this.mLineasCantidad = mLineasCantidad;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public Date getfInicio() {
		return fInicio;
	}

	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	public boolean ishExtras() {
		return hExtras;
	}

	public void sethExtras(boolean hExtras) {
		this.hExtras = hExtras;
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
