package com.project.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import com.project.dao.LineasTurnosDao;
import com.project.dao.LineasTurnosDaoImpl;
import com.project.dao.ParamDao;
import com.project.dao.ParamDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.ProgramacionDiasDao;
import com.project.dao.ProgramacionDiasDaoImpl;
import com.project.entities.Lineasturno;
import com.project.entities.Parametro;
import com.project.entities.Proceso;
import com.project.entities.Programdia;
import com.project.utils.ItemCodOrden;
import com.project.utils.MyUtil;
import com.project.utils.NdiasLab;
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
	private Integer totalOrden;

	private Double nDias;

	// CONSTRUCTOR
	@PostConstruct
	public void init() {
		this.nDias = NdiasLab.getnDias();
		this.codOrden = ItemCodOrden.getCodOrden();
		this.totalOrden = ItemCodOrden.getTotalOrden();

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

	@SuppressWarnings("deprecation")
	public void btnProcesar() {
		eventModel = new DefaultScheduleModel();

		// 1. SECCION VARIABLES
		ScheduleDays days = new ScheduleDays();
		Calendar diaInicio = days.DateToCalendar(this.fInicio);

		// VARIABLE RECOGE CODIGO DEL PROCESO Y LA MATRIZ DE
		// DISTRIBUCION
		Map<Integer, ArrayList<ArrayList<Object>>> mAll = new TreeMap<Integer, ArrayList<ArrayList<Object>>>();

		// VARIABLE RECOGE DISTRIBUCION PARES Y DIAS
		ArrayList<ArrayList<Object>> mProcesos = new ArrayList<ArrayList<Object>>();

		// REEMPLAZO DE MLINEASCANTIDAD
		ArrayList<Mlineas> mlineas = new ArrayList<Mlineas>();

		// 1. FIN SECCION VARIABLES

		// LIMPIAR VARIABLES
		this.orderList2.clear();
		mAll.clear();
		mProcesos.clear();
		mlineas.clear();

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

				// INICIO
				ProcesoDao procesoDao = new ProcesoDaoImpl();
				// OBTIENE TODOS LOS CODIGOS DE LA TABLA PARAMETROS
				List<Proceso> pro = procesoDao.findByProcesoInLT(codOrden);

				for (Proceso p : pro) {
					// OBTIENE LAS LINEAS POR PROCESO DE LA TABLA LINEASTURNOS
					LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
					List<Integer> lineasTurnos = lineasTurnosDao
							.getLineasByProceso(p.getProCodigo(), this.codOrden);

					// RECORRE LA VARIABLE lineasTurnos OBTENIENDO LA CANTIDAD
					// DE TURNOS ASIGNADO

					for (Integer lt : lineasTurnos) {
						// GUARDA EL CODIGO DE LINEA Y LA CANTIDAD DE TURNOS
						Object countLinea = lineasTurnosDao
								.getCountTurnosByLineas(lt, this.codOrden);
						// GUARDA LA LINEA Y NUMERO DE TURNOS POR LINEA
						Mlineas mmlineas = new Mlineas(lt, countLinea);
						mlineas.add(mmlineas);
						// codigoParam.add(param.getParamCodigo());
					}

				} // FIN CICLO 1er. FOR

				// PRUEBAS VISUALIZACION

				for (Mlineas iii : mlineas) {
					System.out.println(" codLinea: " + iii.getCodLinea()
							+ " CountTurnos: " + iii.getCountLinea());
				}
				// FIN PRUEBAS VISUALIZACION

				if (mlineas.isEmpty()) {
					System.out
							.println("No hay lineas para generar la distribucion por dias");
				} else {
					// OBTIENE LOS PARAMETROS UNICOS QUE ESTAN EN LA TABLA
					// LINEASTURNOS
					ParamDao parametroDao = new ParamDaoImpl();
					List<Parametro> param = parametroDao
							.findByParamInLT(codOrden);

					for (Parametro k : param) {
						// OBTIENE LAS TUPLAS PARA LA CANTIDAD DE LINEAS
						// PROGRAMADAS EN BASE AL CODIGO DE PARAMETROS
						LineasTurnosDao ltDao = new LineasTurnosDaoImpl();
						List<Lineasturno> lineastt = ltDao.findByParam(k
								.getParamCodigo());
						Integer cantLineas = lineastt.size();
						Integer countLineas = null;
						for (Mlineas codLineas : mlineas) {
							for (Lineasturno i1 : lineastt) {
								if (codLineas
										.getCodLinea()
										.toString()
										.equals(i1.getLineasprod()
												.getLineaproCodigo().toString())) {

									countLineas = Integer.parseInt(codLineas
											.getCountLinea().toString());
								}
							}
						}

						List<Parametro> pp1 = parametroDao.findbyCodParam2(
								this.codOrden, k.getParamCodigo());

						for (Parametro j : pp1) {
							Tablas tablas = new Tablas();
							mProcesos = tablas.receivParamsPares(
									this.totalOrden, j.getStandar(),
									countLineas, this.nDias, cantLineas);

							mAll.put(j.getProceso().getProCodigo(), mProcesos);

							// ARMA EL OBJETO PARA SER INTRODUCIDO EN EL
							// SCHEDULE

							Items2 orderitem2 = new Items2(j.getProceso()
									.getProCodigo(), j.getParamCodigo(),
									mProcesos);
							this.orderList2.add(orderitem2);
						}
					}

				}
			}
		}// 2. FIN VERIFICAR HORA EXTRAS Y CONTROLAR LOS FINES DE SEMANA

		// IMPRIME LOS VALORES A DIBUJARSE
		int oo = 0;
		for (Items2 i : this.orderList2) {
			System.out.println("indice: " + oo + " CodParam: "
					+ i.getCodParam() + " CodProceso: " + i.getCodProceso()
					+ " Matriz proceso: " + i.getmProcesos());
			oo++;
		}

		generateCalendar(this.orderList2, diaInicio.getTime());
	}

	public boolean generateCalendar(ArrayList<Items2> orderList22, Date fInicio) {

		// INICIALIZADOR
		ScheduleDays days = new ScheduleDays();

		// VARIABLES
		boolean flat = false;
		Calendar tConvertCal = null;

		Object dhora = null;
		tConvertCal = days.DateToCalendar(fInicio);
		Object lastElem = null;

		// ORDENA POR CODIGO PROCESO
		// LOS PROCESOS SIEMPRE DEBEN ESTAR ORDENADOS DESDE EL MENOR AL MAYOR
		Collections.sort(orderList22, new Comparator<Items2>() {
			@Override
			public int compare(Items2 p1, Items2 p2) {
				return p1.codProceso - p2.codProceso;
			}

		});
		// ORDENA DESCENDENTEMENTE
		Collections.reverse(orderList22);

		// Object lastElem = null;
		int c = 0;
		for (Items2 o : orderList22) {

			ArrayList<ArrayList<Object>> a = o.getmProcesos();
			System.out.println(c + " - Var a: " + a);

			lastElem = a.get(1).get(a.get(1).size() - 1);
			System.out.println("Ultimo Element: " + lastElem);

			if (c == 0) {
				for (int i = 0; i < a.get(0).size(); i++) {
					System.out.println("pares: " + a.get(0).get(i));
					System.out.println("Horas: " + a.get(1).get(i));
					withOutHextras(
							Integer.parseInt(a.get(0).get(i).toString()),
							tConvertCal, o.getCodProceso(),
							Double.parseDouble(a.get(1).get(i).toString()),
							o.getCodParam());
				}

			} else {
				if (Double.parseDouble(dhora.toString()) < 4.0) {
					for (int i = 0; i < a.get(0).size(); i++) {
						withOutHextras(
								Integer.parseInt(a.get(0).get(i).toString()),
								days.prevDay(tConvertCal), o.getCodProceso(),
								Double.parseDouble(a.get(1).get(i).toString()),
								o.getCodParam());
					}
				} else {
					for (int i = 0; i < a.get(0).size(); i++) {
						withOutHextras(
								Integer.parseInt(a.get(0).get(i).toString()),
								tConvertCal, o.getCodProceso(),
								Double.parseDouble(a.get(1).get(i).toString()),
								o.getCodParam());
					}
				}
			}
			dhora = lastElem;
			c++;
		}

		// withOutHextras(a.get(0), days.prevDay(tConvertCal),
		// o.getCodProceso(), dhora, o.getCodParam());
		return flat;
	}

	// INSERTAR EN EL MODELO SIN HORAS EXTRAS
	public void withOutHextras(Integer Pares, Calendar fMontajeParam,
			Integer codProceso, Double dhora, Integer codParam) {

		NumberFormat formatter = new DecimalFormat("#0.00");
		ScheduleDays days = new ScheduleDays();
		Calendar m = fMontajeParam;

		float d = 0, s = 0;
		String pal = "";
		if (codProceso == 1) {
			pal = "MONTAJE";
		} else if (codProceso == 2) {
			pal = "APARADO";
		} else {
			pal = "TRQ";
		}
		if (m.get(Calendar.DAY_OF_WEEK) == 7) {
			m = days.nextDay2(m);
		}
		eventModel.addEvent(new DefaultScheduleEvent("L:" + pal + ":"
				+ Pares.toString(), m.getTime(), m.getTime()));

		// PARA GUARDAR EN LA BD
		Items orderitem = new Items(Pares, dhora, m.getTime(), m.getTime(),
				codParam);
		this.orderList.add(orderitem);

		m = days.nextDay(m);

		d++;

		s = (d / 5);
		formatter.format(s);

		if (s > 4) {
			this.d = "La programacion no debe sobrepasar el mes 4 semanas, ud ha programado "
					+ s + " Semanas";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(this.d, "Proceso: " + pal));
			eventModel = new DefaultScheduleModel();
		} else {
			this.d = "Se ha programado para " + s + " Semanas ";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(this.d, "Proceso: " + pal));
		}

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
			// FacesContext.getCurrentInstance().getExternalContext()
			// .redirect(ruta);

			// GUARDAR
			System.out.println("ESTO SE VA A GUARDAR");
			for (Items a : orderList) {
				System.out.println("pares: " + a.pares);
				System.out.println("horas: " + a.horas);
				System.out.println("fFin: " + a.fFin);
				System.out.println("fInicio: " + a.fInicio);
				System.out.println("codParam: " + a.codParam);

				ProgramacionDiasDao programDiasDao = new ProgramacionDiasDaoImpl();
				Parametro param = new Parametro();
				param.setParamCodigo(a.codParam);

				this.selectedDias.setParametro(param);
				this.selectedDias.setCanthoras(a.horas);
				this.selectedDias.setCantpares(a.pares);
				this.selectedDias.setFfin(a.fFin);
				this.selectedDias.setFinicio(a.fInicio);

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

	public Integer getTotalOrden() {
		return totalOrden;
	}

	public void setTotalOrden(Integer totalOrden) {
		this.totalOrden = totalOrden;
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

	public class Mlineas implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer codLinea;
		private Object countLinea;

		public Mlineas(Integer codLinea, Object countLinea) {
			this.codLinea = codLinea;
			this.countLinea = countLinea;
		}

		public Integer getCodLinea() {
			return codLinea;
		}

		public void setCodLinea(Integer codLinea) {
			this.codLinea = codLinea;
		}

		public Object getCountLinea() {
			return countLinea;
		}

		public void setCountLinea(Object countLinea) {
			this.countLinea = countLinea;
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
