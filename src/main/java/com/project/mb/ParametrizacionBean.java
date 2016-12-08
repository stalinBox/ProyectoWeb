package com.project.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.project.dao.LineasProdDao;
import com.project.dao.LineasProdDaoImpl;
import com.project.dao.TurnosDao;
import com.project.dao.TurnosDaoImpl;
import com.project.entities.Lineasprod;
import com.project.entities.TipoProceso;
import com.project.entities.Turno;
import com.project.utils.ConvertArrayToMatriz;
import com.project.utils.ConvertMatrizTranspuesta;
import com.project.utils.MyUtil;

/**
 * @author STALIN RAMÍREZ
 *
 */
@ManagedBean
@SessionScoped
public class ParametrizacionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private Date currentDate;
	private String diasLaborables;

	private String respMontaje;
	private Integer numLineasConvMont;

	private Integer[] numTurnosConvMont;
	private Integer numLineasAutMont;
	private Integer[] numTurnosAutMont;
	private Integer stdProdConvMont;
	private Integer stdProdAutMont;

	private String respAparado;
	private Integer numLineasConvApa;
	private Integer[] numTurnosConvApa;
	private Integer numLineasAutApa;
	private Integer[] numTurnosAutApa;
	private Integer stdProdConvApa;
	private Integer stdProdAutApa;

	private String respTroquelado;
	private Integer numLineasConvTroq;
	private Integer[] numTurnosConvTroq;
	private Integer numLineasAutTroq;
	private Integer[] numTurnosAutTroq;
	private Integer stdProdConvTroq;
	private Integer stdProdAutTroq;

	private ArrayList<Integer> addNumTurnosConvMont = new ArrayList<Integer>();
	private ArrayList<Integer> addNumTurnosConvApa = new ArrayList<Integer>();
	private ArrayList<Integer> addNumTurnosConvTrq = new ArrayList<Integer>();

	private ArrayList<String> lblMonConv = new ArrayList<String>();
	private ArrayList<String> lblMonAut = new ArrayList<String>();

	private ArrayList<String> lblTrqConv = new ArrayList<String>();
	private ArrayList<String> lblTrqAut = new ArrayList<String>();

	private ArrayList<String> lblApaConv = new ArrayList<String>();
	private ArrayList<String> lblApaAut = new ArrayList<String>();

	private Integer totPedido;

	private ArrayList<Integer> valoresCP = new ArrayList<Integer>();
	// ARRAYS PARA LAS CABECERAS Y COLUMNA INDICE DE LAS TABLAS
	private List<String> rowNames = new ArrayList<String>();
	private List<String> colNames = new ArrayList<String>();
	private List<String> rowNameProcesos = new ArrayList<String>();

	// ARRAYS 3D PARA VISUALIZAR EN LAS TABLAS
	private ArrayList<ArrayList<ArrayList<String>>> array3DFechas = new ArrayList<ArrayList<ArrayList<String>>>();
	private ArrayList<ArrayList<ArrayList<Integer>>> array3DDistribPares = new ArrayList<ArrayList<ArrayList<Integer>>>();
	private ArrayList<ArrayList<ArrayList<String>>> array3DDistribHoras = new ArrayList<ArrayList<ArrayList<String>>>();

	// PARA EL SCHEDULE
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private boolean hExtras;
	private boolean hExtrasTrq;
	private boolean hExtrasApa;
	private Date fMontaje;

	// PARA MOSTRAR INFO DIAS
	private float dias;
	private String d;

	// INTERFAZ PARAMETRIZACION SELECCION LINEAS PRODUCCION
	private String[] selectedLPM;
	private String[] selectedLPA;
	private String[] selectedLPT;
	private List<SelectItem> selectItemsLPMontaje;
	private List<SelectItem> selectItemsLPAparado;
	private List<SelectItem> selectItemsLPTroquelado;

	// INTERFAZ PARAMETRIZACION SELECCION TURNOS PROCESOS CONVENCIONALES
	private List<SelectItem> selectItemsTurnos;
	private String[] selectedTurnosMntConv;
	private String[] selectedTurnosTrqConv;
	private String[] selectedTurnosApaConv;

	// INTERFAZ PARAMETRIZACION SELECCION TURNOS PROCESOS AUTOMATICOS
	private String[] selectedTurnosMntAut;
	private String[] selectedTurnosTrqAut;
	private String[] selectedTurnosApaAut;

	// INICIALIZADORES
	@PostConstruct
	public void init() {

		// PARA NO PRUEBAS
		// DetaOrdenBean nb = new DetaOrdenBean();
		// this.valoresCP = DetaOrdenBean.getCp();
		// this.stdProdConvMont = this.valoresCP.get(0);
		// this.stdProdConvApa = this.valoresCP.get(1);
		// this.stdProdConvTroq = this.valoresCP.get(2);
		// this.totPedido = nb.getTotal();
		// ****************

		// PARA PRUEBAS
		this.stdProdConvMont = 362;
		this.stdProdConvApa = 381;
		this.stdProdConvTroq = 390;
		this.totPedido = 614;
		// FIN PRUEBAS

		// ESTANDARES AUTOMATICOS
		this.stdProdAutApa = 0;
		this.stdProdAutMont = 0;
		this.stdProdAutTroq = 0;

		// FECHA PRESENTE
		this.currentDate = new Date();

		// GENERAR UN NUEVO SCHEDULE
		eventModel = new DefaultScheduleModel();
	}

	// CONSTRUCTOR1
	public ParametrizacionBean() {

	}

	// CONSTRUCTOR2
	public ParametrizacionBean(ArrayList<Integer> cp, Integer b) {
		this();
		this.valoresCP = cp;
		this.totPedido = b;

		this.stdProdConvMont = cp.get(0);
		this.stdProdConvApa = cp.get(1);
		this.stdProdConvTroq = cp.get(2);

	}

	// METODOS
	public void SaveLp() {
		String msg = "Guardado las lienas convencionales";
		System.out.println("Guardado las lienas convencionales");
		msg = "Guardado las lienas convencionales";
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void ExecuteParams() {
		for (String a : this.selectedTurnosMntConv) {
			System.out.println("***selectedTurnosMntConv: " + a);
		}

		// ARRAYS FOR MONTAJE
		ArrayList<ArrayList<Object>> array0 = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> array00 = new ArrayList<ArrayList<Object>>();

		// ARRAYS FOR TROQUELADO
		ArrayList<ArrayList<Object>> arrayHTRQ = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> arrayPTRQ = new ArrayList<ArrayList<Object>>();

		// ARRAYS FOR APARADO
		ArrayList<ArrayList<Object>> arrayHAPA = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> arrayPAPA = new ArrayList<ArrayList<Object>>();

		String msg = "";
		try {
			// STORE TURNS
			TestShowData();
			System.out.println("Se guardaron turnos de Montaje");

			TestShowDataTrq();
			System.out.println("Se guardaron turnos de Troquelado");

			TestShowDataApa();
			System.out.println("Se guardaron turnos de Aparado");

			// PRINT PARAMS
			System.out.println("PARAMETROS A UTILZAR");
			// System.out.println("Dias: " + this.diasLaborables);
			System.out.println("Std Produccion: " + this.stdProdConvMont);
			System.out.println("Total Pedido: " + this.totPedido);
			System.out.println("Responsable: " + this.respMontaje);
			System.out.println("Numero de lineas: " + this.numLineasConvMont);
			System.out.println("Turnos por cada Linea: "
					+ this.addNumTurnosConvMont);

			// SEND AND GET ARRAYS
			DistribucionTables nn = new DistribucionTables();
			// MONTAJE
			array0 = nn.receivParamsPares(this.totPedido, this.stdProdConvMont,
					this.addNumTurnosConvMont);
			array00 = nn.receivParamsHoras(this.totPedido,
					this.stdProdConvMont, this.addNumTurnosConvMont);

			// TROQUELADO
			arrayPTRQ = nn.receivParamsPares(this.totPedido,
					this.stdProdConvTroq, this.addNumTurnosConvTrq);
			arrayHTRQ = nn.receivParamsHoras(this.totPedido,
					this.stdProdConvTroq, this.addNumTurnosConvTrq);

			// APARADO
			arrayPAPA = nn.receivParamsPares(this.totPedido,
					this.stdProdConvApa, this.addNumTurnosConvApa);
			arrayHAPA = nn.receivParamsHoras(this.totPedido,
					this.stdProdConvApa, this.addNumTurnosConvApa);

			System.out.println("Esto es lo que devolvio MTN(PARES): " + array0);
			System.out
					.println("Esto es lo que devolvio MTN(HORAS): " + array00);
			System.out.println("Esto es lo que devolvio TRQ(PARES): "
					+ arrayPTRQ);
			System.out.println("Esto es lo que devolvio TRQ(HORAS): "
					+ arrayHTRQ);
			System.out.println("Esto es lo que devolvio APA(PARES): "
					+ arrayPAPA);
			System.out.println("Esto es lo que devolvio APA(HORAS): "
					+ arrayHAPA);

			// CONVERT OBJECT FROM ARRAYLIST TO [][]
			ConvertMatrizTranspuesta convertTranspuesta = new ConvertMatrizTranspuesta();
			ConvertArrayToMatriz convert = new ConvertArrayToMatriz();

			// MONTAJE MATRICES *****************************
			// ARRAY PARES
			Object[][] array2 = new Object[array0.size()][];
			array2 = convert.convertArray(array0);

			// TRANSPUESTA PARES
			Object[][] matrizTPmontaje = null;
			matrizTPmontaje = convertTranspuesta
					.converMatrizTranspuesta(array2);

			// ARRAY HORAS
			Object[][] array = new Object[array00.size()][];
			array = convert.convertArray(array00);

			// TRANSPUESTA HORAS
			Object[][] matrizTHmontaje = null;
			matrizTHmontaje = convertTranspuesta.converMatrizTranspuesta(array);

			// APARADO MATRICES ****************************
			// ARRAY PARES
			Object[][] arrayApaPares = new Object[arrayPAPA.size()][];
			arrayApaPares = convert.convertArray(arrayPAPA);
			// TRANSPUESTA PARES
			Object[][] matrizTaparadoP = null;
			matrizTaparadoP = convertTranspuesta
					.converMatrizTranspuesta(arrayApaPares);
			// ARRAY HORAS
			Object[][] arrayAPARADOh = new Object[arrayHAPA.size()][];
			arrayAPARADOh = convert.convertArray(arrayHAPA);
			// TRANSPUESTA HORAS
			Object[][] matrizTaparadoH = null;
			matrizTaparadoH = convertTranspuesta
					.converMatrizTranspuesta(arrayAPARADOh);

			// TROQUELADO MATRICES ***********************
			// ARRAY PARES
			Object[][] arrayTrqPares = new Object[arrayPTRQ.size()][];
			arrayTrqPares = convert.convertArray(arrayPTRQ);
			// TRANSPUESTA PARES
			Object[][] matrizTtroquelP = null;
			matrizTtroquelP = convertTranspuesta
					.converMatrizTranspuesta(arrayTrqPares);

			// ARRAY HORAS
			Object[][] arrayTRQh = new Object[arrayHTRQ.size()][];
			arrayTRQh = convert.convertArray(arrayHTRQ);
			// TRANSPUESTA HORAS
			Object[][] matrizTtroquelH = null;
			matrizTtroquelH = convertTranspuesta
					.converMatrizTranspuesta(arrayTRQh);

			generateSchedule(matrizTPmontaje, matrizTaparadoP, matrizTtroquelP);

		} catch (Exception e) {
			e.printStackTrace();
			msg = "Error: ERROR EN LA LLAMADA PRINCIPAL";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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
	private Calendar prevDayApa(Date date) {
		Calendar a = null;
		a = DateToCalendar(date);
		if (a.getTime().getDay() == 5 || a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 1);
		}
		return a;
	}

	@SuppressWarnings("deprecation")
	private Calendar prevDayTrq(Date date) {
		Calendar a = null;
		a = DateToCalendar(date);
		if (a.getTime().getDay() == 5 || a.getTime().getDay() == 6) {
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 2);
		} else {
			a.set(Calendar.AM_PM, Calendar.PM);
			a.set(Calendar.DATE, a.get(Calendar.DATE) - 2);
		}
		return a;
	}

	public Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	// INSERTAR EN EL MODELO CON HORAS EXTRAS
	public void withHextras(Object[][] matrizT, Calendar fMontajeParam,
			Object[][] matrizTaparadoH, Calendar tConvertCalApa,
			Object[][] matrizTtroquelP, Calendar tConvertCalTrq) {

		eventModel = new DefaultScheduleModel();
		Calendar m = fMontajeParam;
		Calendar a = tConvertCalApa;
		Calendar t = tConvertCalTrq;
		float d = 0, s = 0;

		NumberFormat formatter = new DecimalFormat("#0.00");
		for (int i = 0; i < matrizT.length; i++) {
			d++;
			for (int j = 0; j < matrizT[i].length; j++) {
				eventModel.addEvent(new DefaultScheduleEvent("L" + (j + 1)
						+ " Montaje: " + matrizT[i][j].toString(), m.getTime(),
						m.getTime()));

				eventModel.addEvent(new DefaultScheduleEvent("L" + (j + 1)
						+ " Aparado: " + matrizTaparadoH[i][j].toString(), a
						.getTime(), a.getTime()));

				eventModel.addEvent(new DefaultScheduleEvent("L" + (j + 1)
						+ " Troquelado: " + matrizTtroquelP[i][j].toString(), t
						.getTime(), t.getTime()));
			}
			m = nextDayExtras(m);
			a = nextDayExtras(a);
			t = nextDayExtras(t);
		}
		s = (d / 6);
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

	// INSERTAR EN EL MODELO SIN HORAS EXTRAS
	public void withOutHextras(Object[][] matrizT, Calendar fMontajeParam,
			Object[][] matrizTaparadoP, Calendar tConvertCalApa,
			Object[][] matrizTtroquelP, Calendar tConvertCalTrq) {

		eventModel = new DefaultScheduleModel();
		Calendar m = fMontajeParam;
		Calendar a = tConvertCalApa;
		Calendar t = tConvertCalTrq;
		float d = 0, s = 0;

		NumberFormat formatter = new DecimalFormat("#0.00");
		for (int i = 0; i < matrizT.length; i++) {
			d++;
			for (int j = 0; j < matrizT[i].length; j++) {
				eventModel.addEvent(new DefaultScheduleEvent("L" + (j + 1)
						+ " Montaje: " + matrizT[i][j].toString(), m.getTime(),
						m.getTime()));

				eventModel.addEvent(new DefaultScheduleEvent("L" + (j + 1)
						+ " Aparado: " + matrizTaparadoP[i][j].toString(), a
						.getTime(), a.getTime()));

				eventModel.addEvent(new DefaultScheduleEvent("L" + (j + 1)
						+ " Troquelado: " + matrizTtroquelP[i][j].toString(), t
						.getTime(), t.getTime()));

			}
			m = nextDay(m);
			a = nextDay(a);
			t = nextDay(t);
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

	// GENERAR LOS EVENTOS EN EL CALENDARIO
	@SuppressWarnings("deprecation")
	public void generateSchedule(Object[][] matrizTPmontaje,
			Object[][] matrizTaparadoP, Object[][] matrizTtroquelP) {

		Calendar tConvertCal = null;
		Calendar tConvertCalApa = null;
		Calendar tConvertCalTrq = null;

		System.out.println("Fecha seleccionada: " + this.fMontaje);
		tConvertCal = DateToCalendar(this.fMontaje);
		tConvertCalApa = prevDayApa(this.fMontaje);
		tConvertCalTrq = prevDayTrq(this.fMontaje);

		System.out.println("Fecha Montaje: " + tConvertCal.getTime());
		System.out.println("Fecha Aparado: " + tConvertCalApa.getTime());
		System.out.println("Fecha Troquelado: " + tConvertCalTrq.getTime());

		if (this.fMontaje.getDay() == 0 || this.fMontaje.getDay() == 6) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"No se puede empezar a programar los fines de semana"));
			eventModel = new DefaultScheduleModel();
		} else if (this.hExtras == true) {
			withHextras(matrizTPmontaje, tConvertCal, matrizTaparadoP,
					tConvertCalApa, matrizTtroquelP, tConvertCalTrq);
		} else {
			withOutHextras(matrizTPmontaje, tConvertCal, matrizTaparadoP,
					tConvertCalApa, matrizTtroquelP, tConvertCalTrq);
		}
	}

	// METODO BTN REPROCESAR
	public void reprocesar() {
		String ruta = "";
		ruta = MyUtil.calzadoPath() + "ordenesProd/ordenesproduc.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	// ****************TURNOS CONV/AUT MONTAJE*******************
	public void GenerateTurnsDaysConvMont() {
		/***************/
		// MOSTRAR PARAMETROS
		this.lblMonConv.clear();
		this.numLineasConvMont = null;
		this.numLineasConvMont = this.selectedLPM.length;
		numTurnosConvMont = new Integer[numLineasConvMont];
		for (SelectItem a : this.selectItemsLPMontaje) {
			for (String b : this.selectedLPM) {
				if (a.getValue().toString().equals(b.toString())) {
					this.lblMonConv.add("Turno/Día " + a.getLabel());
				} else {
					System.out.println("Nada **");
				}
			}
		}
	}

	public void GenerateTurnsDaysAutMont() {
		this.lblMonAut.clear();
		numTurnosAutMont = new Integer[numLineasAutMont];
		for (int i = 1; i <= this.numLineasAutMont; i++) {
			this.lblMonAut.add("Turno/Día L" + i + " Automatica: ");
		}
	}

	// TURNOS CONV/AUT MONTAJE TROQUELADO
	public void GenerateTurnsDaysConvTrqt() {
		this.lblTrqConv.clear();
		numTurnosConvTroq = new Integer[numLineasConvTroq];
		for (int i = 1; i <= this.numLineasConvTroq; i++) {
			this.lblTrqConv.add("Turno/Día L" + i + " Convencional: ");
		}
	}

	public void GenerateTurnsDaysAutTrq() {
		this.lblTrqAut.clear();
		numTurnosAutTroq = new Integer[numLineasAutTroq];
		for (int i = 1; i <= this.numLineasAutTroq; i++) {
			this.lblTrqAut.add("Turno/Día L" + i + " Automatica: ");
		}
	}

	// TURNOS CONV/AUT MONTAJE APARADO
	public void GenerateTurnsDaysConvApa() {
		this.lblApaConv.clear();
		numTurnosConvApa = new Integer[numLineasConvApa];
		for (int i = 1; i <= this.numLineasConvApa; i++) {
			this.lblApaConv.add("Turno/Día L" + i + " Convencional: ");
		}
	}

	public void GenerateTurnsDaysAutApa() {
		this.lblApaAut.clear();
		numTurnosAutApa = new Integer[numLineasAutApa];
		for (int i = 1; i <= this.numLineasAutTroq; i++) {
			this.lblApaAut.add("Turno/Día L" + i + " Automatica: ");
		}
	}

	// ********* TURNOS CONV/AUT MONTAJE ******************
	public void TestShowData() {
		System.out.println("METOD MONTAJE");
		this.addNumTurnosConvMont.clear();
		for (Integer i : this.numTurnosConvMont) {
			this.addNumTurnosConvMont.add(i);
		}
		System.out.println(this.addNumTurnosConvMont);
	}

	public void TestShowDataAut() {
		System.out.println("METOD SAVE AUTO MONTAJE");
		for (Integer i : this.numTurnosAutMont) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	// PRUEBA SHOWS TURNOS CONV/AUT TROQUELADO
	public void TestShowDataTrq() {
		System.out.println("METOD TROQUELADO");
		System.out.println("extras troquelado: " + this.hExtrasTrq);
		for (Integer i : this.numTurnosConvTroq) {
			this.addNumTurnosConvTrq.add(i);
		}
		System.out.println(this.addNumTurnosConvTrq);
	}

	public void TestShowDataAutTrq() {
		for (Integer i : this.numTurnosAutTroq) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	// PRUEBA SHOWS TURNOS CONV/AUT APARADO
	public void TestShowDataApa() {
		System.out.println("METOD APARADO");
		System.out.println("extras parado: " + this.hExtrasApa);
		for (Integer i : this.numTurnosConvApa) {
			this.addNumTurnosConvApa.add(i);
		}
		System.out.println(this.addNumTurnosConvApa);
	}

	public void TestShowDataAutApa() {
		for (Integer i : this.numTurnosAutApa) {
			System.out.println("Esto esta almacenado: " + i);
		}
	}

	// SETTERS AND GETTERS

	public ArrayList<String> getLblMonConv() {
		return lblMonConv;
	}

	public List<SelectItem> getSelectItemsTurnos() {
		this.selectItemsTurnos = new ArrayList<SelectItem>();
		TurnosDao turnoDao = new TurnosDaoImpl();
		List<Turno> turnos = turnoDao.findAll();
		this.selectItemsTurnos.clear();
		for (Turno turn : turnos) {
			SelectItem selectItem = new SelectItem(turn.getTurnoCodigo(),
					turn.getNombturno());
			this.selectItemsTurnos.add(selectItem);
		}
		return selectItemsTurnos;
	}

	public void setSelectItemsTurnos(List<SelectItem> selectItemsTurnos) {
		this.selectItemsTurnos = selectItemsTurnos;
	}

	public String[] getSelectedTurnosMntConv() {
		return selectedTurnosMntConv;
	}

	public void setSelectedTurnosMntConv(String[] selectedTurnosMntConv) {
		this.selectedTurnosMntConv = selectedTurnosMntConv;
	}

	public String[] getSelectedTurnosTrqConv() {
		return selectedTurnosTrqConv;
	}

	public void setSelectedTurnosTrqConv(String[] selectedTurnosTrqConv) {
		this.selectedTurnosTrqConv = selectedTurnosTrqConv;
	}

	public String[] getSelectedTurnosApaConv() {
		return selectedTurnosApaConv;
	}

	public void setSelectedTurnosApaConv(String[] selectedTurnosApaConv) {
		this.selectedTurnosApaConv = selectedTurnosApaConv;
	}

	public String[] getSelectedTurnosMntAut() {
		return selectedTurnosMntAut;
	}

	public void setSelectedTurnosMntAut(String[] selectedTurnosMntAut) {
		this.selectedTurnosMntAut = selectedTurnosMntAut;
	}

	public String[] getSelectedTurnosTrqAut() {
		return selectedTurnosTrqAut;
	}

	public void setSelectedTurnosTrqAut(String[] selectedTurnosTrqAut) {
		this.selectedTurnosTrqAut = selectedTurnosTrqAut;
	}

	public String[] getSelectedTurnosApaAut() {
		return selectedTurnosApaAut;
	}

	public void setSelectedTurnosApaAut(String[] selectedTurnosApaAut) {
		this.selectedTurnosApaAut = selectedTurnosApaAut;
	}

	public List<SelectItem> getSelectItemsLPMontaje() {
		Lineasprod lineasLP = new Lineasprod();
		TipoProceso tipPro = new TipoProceso();
		tipPro.setTprCodigo(1);
		boolean a = false;
		lineasLP.setLineaaut(a);
		lineasLP.setTipoProceso(tipPro);

		this.selectItemsLPMontaje = new ArrayList<SelectItem>();
		LineasProdDao lpDao = new LineasProdDaoImpl();
		List<Lineasprod> lproduccion = (List<Lineasprod>) lpDao
				.selectedByMontaje(lineasLP);

		this.selectItemsLPMontaje.clear();
		for (Lineasprod lp : lproduccion) {
			SelectItem selectItem = new SelectItem(lp.getLineaproCodigo(),
					lp.getNomlinea());
			this.selectItemsLPMontaje.add(selectItem);
		}
		return selectItemsLPMontaje;
	}

	public void setSelectItemsLPMontaje(List<SelectItem> selectItemsLPMontaje) {
		this.selectItemsLPMontaje = selectItemsLPMontaje;
	}

	public List<SelectItem> getSelectItemsLPAparado() {
		Lineasprod lineasLP = new Lineasprod();
		TipoProceso tipPro = new TipoProceso();
		tipPro.setTprCodigo(2);
		boolean a = false;
		lineasLP.setLineaaut(a);
		lineasLP.setTipoProceso(tipPro);

		this.selectItemsLPAparado = new ArrayList<SelectItem>();
		LineasProdDao lpDao = new LineasProdDaoImpl();
		List<Lineasprod> lproduccion = (List<Lineasprod>) lpDao
				.selectedByAparado(lineasLP);

		this.selectItemsLPAparado.clear();
		for (Lineasprod lp : lproduccion) {
			SelectItem selectItem = new SelectItem(lp.getLineaproCodigo(),
					lp.getNomlinea());
			this.selectItemsLPAparado.add(selectItem);
		}
		return selectItemsLPAparado;
	}

	public void setSelectItemsLPAparado(List<SelectItem> selectItemsLPAparado) {
		this.selectItemsLPAparado = selectItemsLPAparado;
	}

	public List<SelectItem> getSelectItemsLPTroquelado() {
		Lineasprod lineasLP = new Lineasprod();
		TipoProceso tipPro = new TipoProceso();
		tipPro.setTprCodigo(3);
		boolean a = false;
		lineasLP.setLineaaut(a);
		lineasLP.setTipoProceso(tipPro);

		this.selectItemsLPTroquelado = new ArrayList<SelectItem>();
		LineasProdDao lpDao = new LineasProdDaoImpl();
		List<Lineasprod> lproduccion = (List<Lineasprod>) lpDao
				.selectedByTroquelado(lineasLP);

		this.selectItemsLPTroquelado.clear();
		for (Lineasprod lp : lproduccion) {
			SelectItem selectItem = new SelectItem(lp.getLineaproCodigo(),
					lp.getNomlinea());
			this.selectItemsLPTroquelado.add(selectItem);
		}
		return selectItemsLPTroquelado;
	}

	public void setSelectItemsLPTroquelado(
			List<SelectItem> selectItemsLPTroquelado) {
		this.selectItemsLPTroquelado = selectItemsLPTroquelado;
	}

	public String[] getSelectedLPM() {
		return selectedLPM;
	}

	public void setSelectedLPM(String[] selectedLPM) {
		this.selectedLPM = selectedLPM;
	}

	public String[] getSelectedLPA() {
		return selectedLPA;
	}

	public void setSelectedLPA(String[] selectedLPA) {
		this.selectedLPA = selectedLPA;
	}

	public String[] getSelectedLPT() {
		return selectedLPT;
	}

	public void setSelectedLPT(String[] selectedLPT) {
		this.selectedLPT = selectedLPT;
	}

	public ArrayList<Integer> getAddNumTurnosConvApa() {
		return addNumTurnosConvApa;
	}

	public void setAddNumTurnosConvApa(ArrayList<Integer> addNumTurnosConvApa) {
		this.addNumTurnosConvApa = addNumTurnosConvApa;
	}

	public ArrayList<Integer> getAddNumTurnosConvTrq() {
		return addNumTurnosConvTrq;
	}

	public void setAddNumTurnosConvTrq(ArrayList<Integer> addNumTurnosConvTrq) {
		this.addNumTurnosConvTrq = addNumTurnosConvTrq;
	}

	public boolean ishExtrasTrq() {
		return hExtrasTrq;
	}

	public void sethExtrasTrq(boolean hExtrasTrq) {
		this.hExtrasTrq = hExtrasTrq;
	}

	public boolean ishExtrasApa() {
		return hExtrasApa;
	}

	public void sethExtrasApa(boolean hExtrasApa) {
		this.hExtrasApa = hExtrasApa;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public float getDias() {
		return dias;
	}

	public void setDias(float dias) {
		this.dias = dias;
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

	public Date getfMontaje() {
		return fMontaje;
	}

	public void setfMontaje(Date fMontaje) {
		this.fMontaje = fMontaje;
	}

	public boolean ishExtras() {
		return hExtras;
	}

	public void sethExtras(boolean hExtras) {
		this.hExtras = hExtras;
	}

	public ArrayList<Integer> getValoresCP() {
		return valoresCP;
	}

	public void setValoresCP(ArrayList<Integer> valoresCP) {
		this.valoresCP = valoresCP;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getArray3DFechas() {
		return array3DFechas;
	}

	public void setArray3DFechas(
			ArrayList<ArrayList<ArrayList<String>>> array3dFechas) {
		array3DFechas = array3dFechas;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getArray3DDistribHoras() {
		return array3DDistribHoras;
	}

	public void setArray3DDistribHoras(
			ArrayList<ArrayList<ArrayList<String>>> array3dDistribHoras) {
		array3DDistribHoras = array3dDistribHoras;
	}

	public ArrayList<ArrayList<ArrayList<Integer>>> getArray3DDistribPares() {
		return array3DDistribPares;
	}

	public void setArray3DDistribPares(
			ArrayList<ArrayList<ArrayList<Integer>>> array3dDistribPares) {
		array3DDistribPares = array3dDistribPares;
	}

	public List<String> getRowNames() {
		return rowNames;
	}

	public void setRowNames(List<String> rowNames) {
		this.rowNames = rowNames;
	}

	public List<String> getColNames() {
		return colNames;
	}

	public void setColNames(List<String> colNames) {
		this.colNames = colNames;
	}

	public List<String> getRowNameProcesos() {
		return rowNameProcesos;
	}

	public void setRowNameProcesos(List<String> rowNameProcesos) {
		this.rowNameProcesos = rowNameProcesos;
	}

	public ArrayList<Integer> getAddNumTurnosConvMont() {
		return addNumTurnosConvMont;
	}

	public void setAddNumTurnosConvMont(ArrayList<Integer> addNumTurnosConvMont) {
		this.addNumTurnosConvMont = addNumTurnosConvMont;
	}

	public void setLblMonConv(ArrayList<String> lblMonConv) {
		this.lblMonConv = lblMonConv;
	}

	public ArrayList<String> getLblMonAut() {
		return lblMonAut;
	}

	public void setLblMonAut(ArrayList<String> lblMonAut) {
		this.lblMonAut = lblMonAut;
	}

	public ArrayList<String> getLblApaAut() {
		return lblApaAut;
	}

	public void setLblApaAut(ArrayList<String> lblApaAut) {
		this.lblApaAut = lblApaAut;
	}

	public ArrayList<String> getLblTrqConv() {
		return lblTrqConv;
	}

	public void setLblTrqConv(ArrayList<String> lblTrqConv) {
		this.lblTrqConv = lblTrqConv;
	}

	public ArrayList<String> getLblTrqAut() {
		return lblTrqAut;
	}

	public void setLblTrqAut(ArrayList<String> lblTrqAut) {
		this.lblTrqAut = lblTrqAut;
	}

	public ArrayList<String> getLblApaConv() {
		return lblApaConv;
	}

	public void setLblApaConv(ArrayList<String> lblApaConv) {
		this.lblApaConv = lblApaConv;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public Integer getNumLineasConvMont() {
		return numLineasConvMont;
	}

	public void setNumLineasConvMont(Integer numLineasConvMont) {
		this.numLineasConvMont = numLineasConvMont;
	}

	public Integer[] getNumTurnosConvMont() {
		return numTurnosConvMont;
	}

	public void setNumTurnosConvMont(Integer[] numTurnosConvMont) {
		this.numTurnosConvMont = numTurnosConvMont;
	}

	public Integer getNumLineasAutMont() {
		return numLineasAutMont;
	}

	public void setNumLineasAutMont(Integer numLineasAutMont) {
		this.numLineasAutMont = numLineasAutMont;
	}

	public Integer[] getNumTurnosAutMont() {
		return numTurnosAutMont;
	}

	public void setNumTurnosAutMont(Integer[] numTurnosAutMont) {
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

	public Integer[] getNumTurnosConvApa() {
		return numTurnosConvApa;
	}

	public void setNumTurnosConvApa(Integer[] numTurnosConvApa) {
		this.numTurnosConvApa = numTurnosConvApa;
	}

	public Integer getNumLineasAutApa() {
		return numLineasAutApa;
	}

	public void setNumLineasAutApa(Integer numLineasAutApa) {
		this.numLineasAutApa = numLineasAutApa;
	}

	public Integer[] getNumTurnosAutApa() {
		return numTurnosAutApa;
	}

	public void setNumTurnosAutApa(Integer[] numTurnosAutApa) {
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

	public Integer[] getNumTurnosConvTroq() {
		return numTurnosConvTroq;
	}

	public void setNumTurnosConvTroq(Integer[] numTurnosConvTroq) {
		this.numTurnosConvTroq = numTurnosConvTroq;
	}

	public Integer getNumLineasAutTroq() {
		return numLineasAutTroq;
	}

	public void setNumLineasAutTroq(Integer numLineasAutTroq) {
		this.numLineasAutTroq = numLineasAutTroq;
	}

	public Integer[] getNumTurnosAutTroq() {
		return numTurnosAutTroq;
	}

	public void setNumTurnosAutTroq(Integer[] numTurnosAutTroq) {
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

	public String getDiasLaborables() {
		return diasLaborables;
	}

	public void setDiasLaborables(String diasLaborables) {
		this.diasLaborables = diasLaborables;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getTotPedido() {
		return totPedido;
	}

	public void setTotPedido(Integer totPedido) {
		this.totPedido = totPedido;
	}

}
