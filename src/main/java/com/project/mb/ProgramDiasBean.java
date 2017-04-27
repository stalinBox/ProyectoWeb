package com.project.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
import com.project.dao.ParamDao;
import com.project.dao.ParamDaoImpl;
import com.project.dao.ProgramacionDiasDao;
import com.project.dao.ProgramacionDiasDaoImpl;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;
import com.project.entities.Programdia;
import com.project.utils.ItemCodOrden;
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

	private ArrayList<Items> orderList = new ArrayList<Items>();
	private ArrayList<Items2> orderList2 = new ArrayList<Items2>();

	private Integer codOrden;

	// CONSTRUCTOR
	@PostConstruct
	public void init() {

		this.codOrden = 112;// ItemCodOrden.getCodOrden();

		this.selectedDias = new Programdia();
		this.selectedDias.setParametro(new Parametro());
		eventModel = new DefaultScheduleModel();

		// *******************ENVIAR EL PARAMETRO findByProceso(1) CONSULTADO
		// TODOS LOS PADRES ASI TAL COMO ESTA, ESTA MAL HECHO ***************

		// // LINEAS DE MONTAJE
		// ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
		// List<Object[]> proDias1 = programDiasDao.findByProceso(1);
		// for (Object[] result : proDias1) {
		// Ordenprod op = (Ordenprod) result[0];
		// Parametro pa = (Parametro) result[1];
		// Programdia pr = (Programdia) result[2];
		// eventModel.addEvent(new DefaultScheduleEvent(op
		// .getOrdenprodCodigo()
		// + "-"
		// + pa.getProceso().getTipoProceso().getTprNombre()
		// + "-"
		// + pr.getCantpares(), pr.getFinicio(), pr.getFfin(),
		// "montajeMTN"));
		// }
		//
		// // LINEAS DE APARADO
		// List<Object[]> proDias2 = programDiasDao.findByProceso(2);
		// for (Object[] result : proDias2) {
		// Ordenprod op = (Ordenprod) result[0];
		// Parametro pa = (Parametro) result[1];
		// Programdia pr = (Programdia) result[2];
		// eventModel.addEvent(new DefaultScheduleEvent(op
		// .getOrdenprodCodigo()
		// + "-"
		// + pa.getProceso().getTipoProceso().getTprNombre()
		// + "-"
		// + pr.getCantpares(), pr.getFinicio(), pr.getFfin(),
		// "aparadoAPA"));
		// }
		//
		// // LINEAS DE TROQUELADO
		// List<Object[]> proDias3 = programDiasDao.findByProceso(3);
		// for (Object[] result : proDias3) {
		// Ordenprod op = (Ordenprod) result[0];
		// Parametro pa = (Parametro) result[1];
		// Programdia pr = (Programdia) result[2];
		// eventModel.addEvent(new DefaultScheduleEvent(op
		// .getOrdenprodCodigo()
		// + "-"
		// + pa.getProceso().getTipoProceso().getTprNombre()
		// + "-"
		// + pr.getCantpares(), pr.getFinicio(), pr.getFfin(),
		// "troqueladoTRQ"));
		// }

	}

	public void btnProcesar() {

		// 1. SECCION VARIABLES
		this.eventModel = new DefaultScheduleModel();
		ScheduleDays days = new ScheduleDays();
		Calendar diaInicio = days.DateToCalendar(this.fInicio);
		Items2 orderitem2 = new Items2();
		Map<Integer, Object> mLineasCantidad = new HashMap<Integer, Object>();
		// 1. FIN SECCION VARIABLES

		// LIMPIAR VARIABLES
		this.orderList2.clear();

		// 2. VERIFICAR HORA EXTRAS Y CONTROLAR LOS FINES DE SEMANA
		if (this.hExtras == true) {
			if (diaInicio.getTime().getDay() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(
								"No se puede empezar a programar en domingo"));
			} else {
				System.out.println("Procesando...1");
				System.out.println("Codigo Orden: " + this.codOrden);

			}
		} else {
			if (diaInicio.getTime().getDay() == 0
					|| diaInicio.getTime().getDay() == 6) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"No se puede empezar a programar los fines de semana"));
			} else {
				System.out.println("Procesando...2");
				System.out.println("Codigo Orden: " + this.codOrden);

				// PARAMETROS INICIALES PARA LA PROGRAMACION DIAS

				// paramDao OBTIENE LOS PROCESO POR ORDEN
				// ParamDao paramDao = new ParamDaoImpl();
				// List<Parametro> parametros = paramDao.findAll();

				// // 1. CICLO FOR
				// for (Parametro param : parametros) {
				// // lineasTurnosDao OBTIENE EL NUMERO DE LINEAS POR PROCESO
				// LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
				// List<Integer> lineasTurnos = lineasTurnosDao
				// .getLineasByProceso(param.getProceso()
				// .getProCodigo(), iCodOrden);
				//
				// for (Integer lt : lineasTurnos) {
				// Object countLinea = lineasTurnosDao
				// .getCountTurnosByLineas(lt, iCodOrden);
				//
				// mLineasCantidad.put(lt, countLinea);
				// }
				// } // 1. CICLO FOR

				// System.out.println("Variable mLineasCantidad: "
				// + mLineasCantidad);
			}
		}
		// 2. FIN VERIFICAR HORA EXTRAS Y CONTROLAR LOS FINES DE SEMANA

		// System.out.println("Procesando...");
		// System.out.println("Codigo Orden: " + ItemCodOrden.getCodOrden());

		// VARIABLE RECOGE CODIGO DEL PROCESO Y LA MATRIZ DE DISTRIBUCION
		// Map<Integer, ArrayList<ArrayList<Object>>> mAll = new
		// TreeMap<Integer, ArrayList<ArrayList<Object>>>();

		// VARIABLE RECOGE DISTRIBUCION PARES Y DIAS
		// ArrayList<ArrayList<Object>> mProcesos = new
		// ArrayList<ArrayList<Object>>();

		// for (Parametro param : parametros) {
		// mLineasCantidad.clear();
		// LineasTurnosDao lienasTurnosDao = new LineasTurnosDaoImpl();
		// List<Integer> lineasTurnos = lienasTurnosDao.getLineasByProceso(
		// param.getProceso().getProCodigo(),
		// ContentParam.getCodOrden());
		//
		// for (Integer lt : lineasTurnos) {
		// Object countLinea = lienasTurnosDao.getCountTurnosByLineas(lt,
		// ContentParam.getCodOrden());
		// mLineasCantidad.put(lt, countLinea);
		// System.out.println("Esta cosa: " + mLineasCantidad);
		// }
		// // Items2 orderitem2 = new Items2();
		//
		// if (mLineasCantidad.isEmpty()) {
		// System.out
		// .println("No hay lineas para generar la distribucion por dias");
		// ArrayList<ArrayList<Object>> mProcesosT1 = new
		// ArrayList<ArrayList<Object>>();
		// ArrayList<ArrayList<Object>> mProcesosT2 = new
		// ArrayList<ArrayList<Object>>();
		// ArrayList<ArrayList<Object>> mProcesosT3 = new
		// ArrayList<ArrayList<Object>>();
		//
		// ArrayList<Integer> demandaT = new ArrayList<Integer>();
		// Map<Integer, Object> mLineas = new HashMap<Integer, Object>();
		// mLineas.put(4, 1);
		// Tablas tablas = new Tablas();
		//
		// List<Parametro> params = paramDao.getCpByProcesoOrden(
		// ContentParam.getCodOrden(), 3);
		// for (Parametro p : params) {
		// DetaOrdenDao detalleDao = new DetaOrdenDaoImpl();
		// List<String> detalle = detalleDao.getByOrden(ContentParam
		// .getCodOrden());
		//
		// for (String d : detalle) {
		// List<Integer> det = detalleDao.getSumByModelo(
		// ContentParam.getCodOrden(), d);
		// for (Object dt : det) {
		// demandaT.add(Integer.parseInt(dt.toString()));
		// }
		// }
		//
		// // mProcesosT1 = tablas.receivParamsPares(demandaT.get(0),
		// // p.getStandconv(), mLineas);
		// // mProcesosT2 = tablas.receivParamsPares(demandaT.get(1),
		// // p.getStandman(), mLineas);
		// // mProcesosT3 = tablas.receivParamsPares(demandaT.get(2),
		// // p.getStandauto(), mLineas);
		// // mAll.put(3, mProcesosT1);
		// // mAll.put(4, mProcesosT2);
		// // mAll.put(5, mProcesosT3);
		// System.out.println("Parametro codigo: "
		// + p.getParamCodigo());
		// orderitem2 = new Items2(p.getProceso().getProCodigo(),
		// p.getParamCodigo(), mProcesosT1);
		// this.orderList2.add(orderitem2);
		//
		// orderitem2 = new Items2(p.getProceso().getProCodigo(),
		// p.getParamCodigo(), mProcesosT2);
		// this.orderList2.add(orderitem2);
		//
		// orderitem2 = new Items2(p.getProceso().getProCodigo(),
		// p.getParamCodigo(), mProcesosT3);
		// this.orderList2.add(orderitem2);
		// }
		//
		// } else {
		// System.out.println("Codigo Parametro2: "
		// + param.getParamCodigo());
		// Tablas tablas = new Tablas();
		// // mProcesos = tablas.receivParamsPares(
		// // ContentParam.getTotalOrden(), param.getStandconv(),
		// // mLineasCantidad);
		// mAll.put(param.getProceso().getProCodigo(), mProcesos);
		//
		// orderitem2 = new Items2(param.getProceso().getProCodigo(),
		// param.getParamCodigo(), mProcesos);
		// this.orderList2.add(orderitem2);
		// }
		// }
		// generateCalendar(this.orderList2, this.fInicio);
	}

	@SuppressWarnings({ "deprecation" })
	public boolean generateCalendar(ArrayList<Items2> orderList22, Date fInicio) {

		// INICIALIZADOR
		ScheduleDays days = new ScheduleDays();

		// VARIABLES
		boolean flat = false;
		Calendar tConvertCal = null;

		Double dhora = null;
		tConvertCal = days.DateToCalendar(fInicio);

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

			Collections.reverse(orderList22);
			Object lastElem = null;
			for (Items2 o : orderList22) {
				// System.out.println("mProcesos: " + o.getmProcesos());
				// System.out.println("codParam: " + o.getCodParam());

				ArrayList<ArrayList<Object>> a = o.getmProcesos();
				System.out.println("Var a: " + a);
				// System.out.println("Tamaño de a: " + a.size());
				lastElem = a.get(1).get(a.get(1).size() - 1);

				// PARTE NUEVA
				// System.out.println("Contenido Horas: " + a.get(1));
				// System.out.println("ultimo Element de A: " + lastElem);

				// for (Object ii : a.get(1)) {
				// if (ii == lastElem) {
				//
				// System.out.println("Pares: " + a.get(0));
				// dhora = (Double) ii;
				// System.out.println("Ultimo Elemento(HORA): " + dhora);
				// // withOutHextras(a.get(0),
				// // days.prevDayApa(tConvertCal),
				// // o.getCodProceso(), dhora, o.getCodParam());
				// } else {
				// dhora = (Double) ii;
				// System.out.println("Resto Elementos(Hora): " + dhora);
				// // System.out.println("Pares: " + a.get(0));
				// }
				// // System.out.println("Horas: " + ii);
				// }
				// FIN PARTE NUEVA

				// PARTE CRITICA
				if (dhora != null) {
					if (dhora < 4) {
						withOutHextras(a.get(0), days.prevDayApa(tConvertCal),
								o.getCodProceso(), dhora, o.getCodParam());
					} else {
						withOutHextras(a.get(0), tConvertCal,
								o.getCodProceso(), dhora, o.getCodParam());
					}
					if (a.get(1).size() == 1) {
						dhora = (Double) a.get(1).get(0);
					} else {
						dhora = (Double) a.get(1).get(1);
					}
				} else {
					withOutHextras(a.get(0), tConvertCal, o.getCodProceso(),
							dhora, o.getCodParam());
					if (a.get(1).size() == 1) {
						dhora = (Double) a.get(1).get(0);
					} else {
						dhora = (Double) a.get(1).get(1);
					}
				}
				// FIN PARTE CRITICA

			}
		} else {
			// Collections.reverse(orderList22);
			// for (Items2 o : orderList22) {
			// System.out.println("mProcesos: " + o.getmProcesos());
			// System.out.println("codParam: " + o.getCodParam());
			// System.out.println("codProceso: " + o.getCodProceso());
			// ArrayList<ArrayList<Object>> a = o.getmProcesos();
			// if (dhora != null) {
			// if (dhora < 4) {
			// withHextras(a.get(0), days.prevDayApa(tConvertCal),
			// o.getCodProceso(), dhora);
			// } else {
			// withHextras(a.get(0), tConvertCal, o.getCodProceso(),
			// dhora);
			// }
			// if (a.get(1).size() == 1) {
			// dhora = (Double) a.get(1).get(0);
			// } else {
			// dhora = (Double) a.get(1).get(1);
			// }
			// } else {
			// withHextras(a.get(0), tConvertCal, o.getCodProceso(), dhora);
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
	public void withOutHextras(ArrayList<Object> arrayProceso,
			Calendar fMontajeParam, Integer codProceso, Double dhora,
			Integer codParam) {

		NumberFormat formatter = new DecimalFormat("#0.00");
		ScheduleDays days = new ScheduleDays();
		Calendar m = fMontajeParam;
		float d = 0, s = 0;
		String pal = "";
		for (Object k : arrayProceso) {
			if (codProceso == 1) {
				pal = "ASSEMBLY";
			} else if (codProceso == 2) {
				pal = "SEWING";
			} else {
				pal = "TRICKLE";
			}
			eventModel.addEvent(new DefaultScheduleEvent("L:" + pal + ":"
					+ k.toString(), m.getTime(), m.getTime()));

			// PARA GUARDAR EN LA BD
			Items orderitem = new Items(Integer.parseInt(k.toString()), dhora,
					m.getTime(), m.getTime(), codParam);
			this.orderList.add(orderitem);
			m = days.nextDay(m);
			d++;
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

		// System.out.println("Dias labadorados: " + d);
		// System.out.println("Semanas labadoradas: " + s);
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
		ruta = MyUtil.baseurl() + "inicio.xhtml";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);

			// GUARDAR
			System.out.println("ESTO SE VA A GUARDAR");
			for (Items a : orderList) {
				System.out.println(": " + a.pares);
				System.out.println(": " + a.horas);
				System.out.println(": " + a.fFin);
				System.out.println(": " + a.fInicio);
				System.out.println(": " + a.codParam);

				ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
				// Parametro param = new Parametro();
				// param.setParamCodigo(a.codParam);
				//
				// this.selectedDias.setParametro(param);
				// this.selectedDias.setCanthoras(a.horas);
				// this.selectedDias.setCantpares(a.pares);
				// this.selectedDias.setFfin(a.fFin);
				// this.selectedDias.setFinicio(a.fInicio);

				if (programDiasDao.create(this.selectedDias)) {
					msg = "Se ha añadido en programDias";
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_INFO, msg, null);
					FacesContext.getCurrentInstance().addMessage(null, message);
				} else {
					msg = "Error al añadir en programDias";
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, msg, null);
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			}
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

	// SETTERS AND GETTERS

	public List<Programdia> getProgramDias() {
		// ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
		// this.programDias = programDiasDao.findAll();
		return programDias;
	}

	public Integer getCodOrden() {
		return codOrden;
	}

	public void setCodOrden(Integer codOrden) {
		this.codOrden = codOrden;
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

	public class Items implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer pares;
		private Double horas;
		private Date fInicio;
		private Date fFin;
		private Integer codParam;

		public Items(Integer pares, Double horas, Date fInicio, Date fFin,
				Integer codParam) {
			this.pares = pares;
			this.horas = horas;
			this.fInicio = fInicio;
			this.fFin = fFin;
			this.codParam = codParam;
		}

		public Items() {
		}

		public Integer getCodParam() {
			return codParam;
		}

		public void setCodParam(Integer codParam) {
			this.codParam = codParam;
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
}
