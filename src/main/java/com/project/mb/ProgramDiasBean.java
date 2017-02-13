package com.project.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import com.project.dao.DetaOrdenDao;
import com.project.dao.DetaOrdenDaoImpl;
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
import com.project.utils.ScheduleDays;
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
	private ArrayList<Items> orderList = new ArrayList<Items>();
	private ArrayList<Items2> orderList2 = new ArrayList<Items2>();

	// CONSTRUCTOR
	@PostConstruct
	public void init() {

		this.selectedDias = new Programdia();
		this.selectedDias.setParametro(new Parametro());
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

	// METODOS DML
	public void btnCreateProgramDias(ActionEvent actionEvent) {
	}

	public void btnDeleteProgramDias(ActionEvent actionEvent) {
	}

	public void btnUpdateProgramDias(ActionEvent actionEvent) {
	}

	// METODOS BOTONES
	public void btnContinuar() {
		String ruta = "";
		String msg = "";
		// ruta = MyUtil.basePath() + "inicio.xhtml";
		try {
			// FacesContext.getCurrentInstance().getExternalContext()
			// .redirect(ruta);

			// GUARDAR
			System.out.println("ESTO SE VA A GUARDAR");
			for (Items a : orderList) {
				System.out.println(": " + a.pares);
				System.out.println(": " + a.horas);
				System.out.println(": " + a.fFin);
				System.out.println(": " + a.fInicio);
				System.out.println(": " + a.codesParams);
			}
			// ProgramacionDiasDao programDiasDao = new
			// ProgramacionDiasDaoImpl();
			// if (programDiasDao.create(this.selectedDias)) {
			// msg = "Se ha añadido en programDias";
			// FacesMessage message = new FacesMessage(
			// FacesMessage.SEVERITY_INFO, msg, null);
			// FacesContext.getCurrentInstance().addMessage(null, message);
			// } else {
			// msg = "Error al añadir en programDias";
			// FacesMessage message = new FacesMessage(
			// FacesMessage.SEVERITY_ERROR, msg, null);
			// FacesContext.getCurrentInstance().addMessage(null, message);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	public void btnProcesar() {
		eventModel = new DefaultScheduleModel();
		ArrayList<Integer> codesParams = new ArrayList<Integer>();
		System.out.println("Procesando...");
		System.out.println("Codigo Orden: " + ContentParam.getCodOrden());

		ParametrizacionDao paramDao = new ParametrizacionDaoImpl();
		List<Parametro> parametros = paramDao.getProcesosbyOrden(ContentParam
				.getCodOrden());
		// VARIABLE RECOGE CODIGO DEL PROCESO Y LA MATRIZ DE DISTRIBUCION
		// Map<Integer, ArrayList<ArrayList<Object>>> mAll = new
		// TreeMap<Integer, ArrayList<ArrayList<Object>>>();

		// VARIABLE RECOGE DISTRIBUCION PARES Y DIAS
		ArrayList<ArrayList<Object>> mProcesos = new ArrayList<ArrayList<Object>>();

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
				System.out.println("Esta cosa: " + mLineasCantidad);
			}

			if (this.mLineasCantidad.isEmpty()) {
				System.out
						.println("No hay lineas para generar la distribucion por dias");
				// ArrayList<ArrayList<Object>> mProcesosT1 = new
				// ArrayList<ArrayList<Object>>();
				// ArrayList<ArrayList<Object>> mProcesosT2 = new
				// ArrayList<ArrayList<Object>>();
				// ArrayList<ArrayList<Object>> mProcesosT3 = new
				// ArrayList<ArrayList<Object>>();

				ArrayList<Integer> demandaT = new ArrayList<Integer>();
				Map<Integer, Object> mLineas = new HashMap<Integer, Object>();
				mLineas.put(4, 1);
				Tablas tablas = new Tablas();

				List<Parametro> params = paramDao.getCpByProcesoOrden(
						ContentParam.getCodOrden(), 3);
				for (Parametro p : params) {
					// System.out.println("T manual: " + p.getStandconv());
					// System.out.println("T automatico: " + p.getStandman());
					// System.out.println("T Troquel: " + p.getStandauto());

					DetaOrdenDao detalleDao = new DetaOrdenDaoImpl();
					List<String> detalle = detalleDao.getByOrden(ContentParam
							.getCodOrden());

					for (String d : detalle) {
						System.out.println(d);
						List<Integer> det = detalleDao.getSumByModelo(
								ContentParam.getCodOrden(), d);
						for (Object dt : det) {
							System.out.println("Demanda: " + dt);
							demandaT.add(Integer.parseInt(dt.toString()));
						}
					}

					for (int j = 0; j < demandaT.size(); j++) {
						ArrayList<ArrayList<Object>> mProcesosT = new ArrayList<ArrayList<Object>>();
						mProcesosT = tablas.receivParamsPares(demandaT.get(j),
								p.getStandconv(), mLineas);

						Items2 orderItem = new Items2(param.getProceso()
								.getProCodigo(), param.getParamCodigo(),
								mProcesosT);
						this.orderList2.add(orderItem);
					}

					// mProcesosT1 = tablas.receivParamsPares(demandaT.get(0),
					// p.getStandconv(), mLineas);
					//
					// mProcesosT2 = tablas.receivParamsPares(demandaT.get(1),
					// p.getStandman(), mLineas);
					//
					// mProcesosT3 = tablas.receivParamsPares(demandaT.get(2),
					// p.getStandauto(), mLineas);
					//
					// System.out.println("Tamaño demandaT: " +
					// demandaT.size());
					// System.out.println("T1: " + mProcesosT1);
					// System.out.println("T2: " + mProcesosT2);
					// System.out.println("T3: " + mProcesosT3);

					// PUNTO CRITICO poner el codigo del parametrizacion
					// Items orderitem = new
					// Items(Integer.parseInt(arrayProceso.get(0)
					// .get(i).toString()),
					// Double.parseDouble(arrayProceso.get(1)
					// .get(i).toString()), m.getTime(), m.getTime(),
					// codesParams);
					// this.orderList.add(orderitem);

					// mAll.put(param.getParamCodigo(), mProcesosT1);
					// mAll.put(param.getParamCodigo(), mProcesosT2);
					// mAll.put(param.getParamCodigo(), mProcesosT3);
				}

			} else {
				Tablas tablas = new Tablas();

				mProcesos = tablas.receivParamsPares(
						ContentParam.getTotalOrden(), param.getStandconv(),
						this.mLineasCantidad);
				// PUNTO CRITICO poner el codigo del parametrizacion
				Items2 orderItem = new Items2(
						param.getProceso().getProCodigo(),
						param.getParamCodigo(), mProcesos);
				this.orderList2.add(orderItem);
				// mAll.put(param.getParamCodigo(), mProcesos);

			}
			codesParams.add(param.getParamCodigo());
		}
		generateCalendar(orderList2, this.fInicio, codesParams);
	}

	@SuppressWarnings({ "deprecation" })
	public boolean generateCalendar(ArrayList<Items2> orderList22,
			Date fInicio, ArrayList<Integer> codesParams) {

		// INICIALIZADOR
		ScheduleDays days = new ScheduleDays();

		// VARIABLES
		boolean flat = false;
		Calendar tConvertCal = null;

		Double dhora = null;
		tConvertCal = days.DateToCalendar(fInicio);

		// ORDENA EL MAP EN FORMA DESCENDENTE
		// Map<Integer, ArrayList<ArrayList<Object>>> treeMap = new
		// TreeMap<Integer, ArrayList<ArrayList<Object>>>(
		// new Comparator<Integer>() {
		// @Override
		// public int compare(Integer o1, Integer o2) {
		// return o2.compareTo(o1);
		// }
		// });

		// TRABAJAR CON TREEMAP
		// treeMap.putAll(orderList22);
		for (Items2 i : orderList22) {
			System.out.println("??OrderList22: " + i.getCodParam());
			System.out.println("??OrderList22: " + i.getCodProceso());
			System.out.println("??OrderList22: " + i.getmProcesos());

		}

		// VISUALIZAR LA MATRIZ
		if (fInicio.getDay() == 0 || fInicio.getDay() == 6) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"No se puede empezar a programar los fines de semana"));
			eventModel = new DefaultScheduleModel();
		} else if (this.hExtras != true) {
			for (Items2 i : orderList22) {
				for (ArrayList<Object> a : i.getmProcesos()) {
					System.out.println("i: " + i.getCodProceso());
					System.out.println("tamaño de mProcesos variable a: "
							+ a.size());
					for (int ii = 0; ii < a.size(); ii++) {
						System.out.println("a: " + a.get(ii));
					}
				}
			}// fin foreach
		} else {
			// OCUPAR MAS LUEGO
			// Iterator<Integer> it = treeMap.keySet().iterator();
			// while (it.hasNext()) {
			// Integer key = (Integer) it.next();
			// ArrayList<ArrayList<Object>> a = treeMap.get(key);
			// // System.out.println("Codigo Proceso: " + key
			// // + " ->Array por proceso: " + a);
			// if (dhora != null) {
			// System.out.println(dhora);
			//
			// if (dhora < 4) {
			// withHextras(a.get(0), days.prevDayApa(tConvertCal), key);
			// } else {
			// withHextras(a.get(0), tConvertCal, key);
			// }
			//
			// if (a.get(1).size() == 1) {
			// dhora = (Double) a.get(1).get(0);
			// } else {
			// dhora = (Double) a.get(1).get(1);
			// }
			// // System.out.println("dHora: " + dhora);
			// } else {
			// withHextras(a.get(0), tConvertCal, key);
			// // System.out.println("Numero: " + a.get(1).size());
			// if (a.get(1).size() == 1) {
			// dhora = (Double) a.get(1).get(0);
			// } else {
			// dhora = (Double) a.get(1).get(1);
			// }
			// }
			// }
		}
		return flat;
	}

	// INSERTAR EN EL MODELO SIN HORAS EXTRAS
	public void withOutHextras(ArrayList<ArrayList<Object>> arrayProceso,
			Calendar fMontajeParam, Integer key, ArrayList<Integer> codesParams) {

		NumberFormat formatter = new DecimalFormat("#0.00");
		ScheduleDays days = new ScheduleDays();
		Calendar m = fMontajeParam;
		float d = 0, s = 0;
		String pal = "";

		for (int i = 0; i < arrayProceso.get(0).size(); i++) {
			System.out.println("i: " + arrayProceso.get(0).get(i));

			if (key == 1) {
				pal = "MONTAJE";
			} else if (key == 2) {
				pal = "APARADO";
			} else {
				pal = "TRQ";
			}
			eventModel.addEvent(new DefaultScheduleEvent("L:" + pal + ":"
					+ arrayProceso.get(0).get(i).toString(), m.getTime(), m
					.getTime()));

			// PARA GUARDAR EN LA BD
			Items orderitem = new Items(Integer.parseInt(arrayProceso.get(0)
					.get(i).toString()), Double.parseDouble(arrayProceso.get(1)
					.get(i).toString()), m.getTime(), m.getTime(), codesParams);
			this.orderList.add(orderitem);
			// FIN GUARDAR EN LA BD

			m = days.nextDay(m);
			d++;

		}
		// for (Object k : arrayProceso.get(0)) {
		// if (key == 1) {
		// pal = "MONTAJE";
		// } else if (key == 2) {
		// pal = "APARADO";
		// } else {
		// pal = "TRQ";
		// }
		// eventModel.addEvent(new DefaultScheduleEvent("L:" + pal + ":"
		// + k.toString(), m.getTime(), m.getTime()));
		// System.out.println("Variable dentro de k: " + k);
		// // PARA GUARDAR EN LA BD
		// // Items orderitem = new Items(Integer.parseInt(k.toString()),
		// // arrayProceso.,
		// // m.getTime(), m.getTime());
		// // this.orderList.add(orderitem);
		// // FIN GUARDAR EN LA BD
		//
		// m = days.nextDay(m);
		// d++;
		// }
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
	public void withHextras(ArrayList<Object> arrayProceso,
			Calendar fMontajeParam, Integer key) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		ScheduleDays days = new ScheduleDays();
		Calendar m = fMontajeParam;
		float d = 0, s = 0;

		for (Object k : arrayProceso) {
			eventModel.addEvent(new DefaultScheduleEvent("L:" + key + " Pares:"
					+ k.toString(), m.getTime(), m.getTime()));
			m = days.nextDayExtras(m);
			d++;
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

	public ArrayList<Items> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Items> orderList) {
		this.orderList = orderList;
	}

	public class Items implements Serializable {
		private static final long serialVersionUID = 1L;
		// Atributos de la clase
		private Integer pares;
		private Double horas;
		private Date fInicio;
		private Date fFin;
		private ArrayList<Integer> codesParams;

		public Items(Integer pares, Double horas, Date fInicio, Date fFin,
				ArrayList<Integer> codesParams) {
			this.pares = pares;
			this.horas = horas;
			this.fInicio = fInicio;
			this.fFin = fFin;
			this.codesParams = codesParams;
		}

		public Items() {
		}

		public ArrayList<Integer> getCodesParams() {
			return codesParams;
		}

		public void setCodesParams(ArrayList<Integer> codesParams) {
			this.codesParams = codesParams;
		}

		public Integer getPares() {
			return pares;
		}

		public void setPares(Integer pares) {
			this.pares = pares;
		}

		public Double getHoras() {
			return horas;
		}

		public void setHoras(Double horas) {
			this.horas = horas;
		}

		public Date getfInicio() {
			return fInicio;
		}

		public void setfInicio(Date fInicio) {
			this.fInicio = fInicio;
		}

		public Date getfFin() {
			return fFin;
		}

		public void setfFin(Date fFin) {
			this.fFin = fFin;
		}
	}

	public class Items2 implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer codProceso;
		private Integer codParam;
		private ArrayList<ArrayList<Object>> mProcesos;

		public Items2(Integer codProceso, Integer codParam,
				ArrayList<ArrayList<Object>> mProcesos) {
			this.codProceso = codProceso;
			this.codParam = codParam;
			this.mProcesos = mProcesos;
		}

		public Items2() {
		}

		public Integer getCodProceso() {
			return codProceso;
		}

		public void setCodProceso(Integer codProceso) {
			this.codProceso = codProceso;
		}

		public Integer getCodParam() {
			return codParam;
		}

		public void setCodParam(Integer codParam) {
			this.codParam = codParam;
		}

		public ArrayList<ArrayList<Object>> getmProcesos() {
			return mProcesos;
		}

		public void setmProcesos(ArrayList<ArrayList<Object>> mProcesos) {
			this.mProcesos = mProcesos;
		}
	}
}
