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

import com.project.dao.DetaOrdenDao;
import com.project.dao.DetaOrdenDaoImpl;
import com.project.dao.DistribDetaDao;
import com.project.dao.DistribDetaDaoImpl;
import com.project.dao.LineasTurnosDao;
import com.project.dao.LineasTurnosDaoImpl;
import com.project.dao.ModelosDao;
import com.project.dao.ModelosDaoImpl;
import com.project.dao.ParamDao;
import com.project.dao.ParamDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.ProgramacionDiasDao;
import com.project.dao.ProgramacionDiasDaoImpl;
import com.project.entities.Lineasturno;
import com.project.entities.Modelo;
import com.project.entities.Parametro;
import com.project.entities.Proceso;
import com.project.entities.Programdia;
import com.project.utils.DistribAparado;
import com.project.utils.DistribMontaje;
import com.project.utils.DistribResultAparado;
import com.project.utils.DistribResultMontaje;
import com.project.utils.DistribResultTroquelado;
import com.project.utils.ItemCodOrden;
import com.project.utils.Items2;
import com.project.utils.Items3;
import com.project.utils.Items4;
import com.project.utils.Items5;
import com.project.utils.MallObject;
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

	public void btnProcesar() {
		eventModel = new DefaultScheduleModel();

		// 1. SECCION VARIABLES
		ScheduleDays days = new ScheduleDays();
		Calendar diaInicio = days.DateToCalendar(this.fInicio);

		// VARIABLE RECOGE CODIGO DEL PROCESO Y LA MATRIZ DE
		// DISTRIBUCION
		// Map<Integer, ArrayList<ArrayList<Object>>> mAll = new
		// TreeMap<Integer, ArrayList<ArrayList<Object>>>();

		// VARIABLE RECOGE DISTRIBUCION PARES Y DIAS
		ArrayList<ArrayList<Object>> mProcesos = new ArrayList<ArrayList<Object>>();

		// REEMPLAZO DE MLINEASCANTIDAD
		ArrayList<Mlineas> mlineas = new ArrayList<Mlineas>();
		ArrayList<MallObject> objectMal = new ArrayList<MallObject>();
		// 1. FIN SECCION VARIABLES

		// LIMPIAR VARIABLES
		this.orderList2.clear();
		// mAll.clear();
		mProcesos.clear();
		mlineas.clear();

		// 2. VERIFICAR HORA EXTRAS Y CONTROLAR LOS FINES DE SEMANA
		if (this.hExtras == true) {
			if (diaInicio.get(Calendar.DAY_OF_WEEK) == 1) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(
								"No se puede empezar a programar en domingo"));
			} else {
				System.out.println("Procesando...1");
				System.out.println("Codigo Orden: " + this.codOrden);

			}
		} else {

			if (diaInicio.get(Calendar.DAY_OF_WEEK) == 7
					|| diaInicio.get(Calendar.DAY_OF_WEEK) == 1) {
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

				// OBTIENE LOS PROCESOS INSTANCIADOS EN LA TABLA (BD)
				// LINEASTURNOS
				List<Proceso> pro = procesoDao.findByProcesoInLT(codOrden);

				// ORDENA LA VARIABLE "pro" ASCENDENTEMENTE
				Collections.sort(pro, new Comparator<Proceso>() {
					@Override
					public int compare(Proceso p1, Proceso p2) {
						return p1.getProCodigo() - p2.getProCodigo();
					}

				});
				// ORDENA LA VARIABLE "pro" DESCENDENTEMENTE
				Collections.reverse(pro);
				Items2 orderitem2 = new Items2();
				Mlineas mmlineas = null;
				// VARIABLE QUE RECOGE LOS TOTALES
				// EMPEZAR A TRABAJAR CON LOS CODIGOS ORDENADOS
				Integer contGeneral = 0;
				for (Proceso p : pro) {
					System.out.println("///CODIGO SOLO TROQ: "
							+ p.getProCodigo());

					// CONSULTAR LOS TOTALES A TRABAJAR
					// CONSULTA DE MODELOS
					ModelosDao modelosDao = new ModelosDaoImpl();
					List<Modelo> modelo = modelosDao
							.findByDistrib(this.codOrden);
					Object detalle = 0;

					for (Modelo mo : modelo) {
						DetaOrdenDao detalleDao = new DetaOrdenDaoImpl();
						detalle = detalleDao.sumByMod(mo.getModCodigo(),
								this.codOrden);
						// System.out
						// .println("Cantidades por modelos en el DETALLE**: "
						// + detalle.toString());
					}

					// OBTIENE LAS LINEAS POR PROCESO DE LA TABLA
					// LINEASTURNOS

					LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
					List<Integer> lineasTurnos = lineasTurnosDao
							.getLineasByProceso(p.getProCodigo(), this.codOrden);

					// RECORRE LA VARIABLE lineasTurnos OBTENIENDO LA
					// CANTIDAD
					// DE TURNOS ASIGNADO
					for (Integer lt : lineasTurnos) {
						// GUARDA EL CODIGO DE LINEA Y LA CANTIDAD DE TURNOS
						Object countLinea = lineasTurnosDao
								.getCountTurnosByLineas(lt, this.codOrden,
										p.getProCodigo());

						// GUARDA LA LINEA Y NUMERO DE TURNOS POR LINEA
						mmlineas = new Mlineas(lt, countLinea);
						mlineas.add(mmlineas);
					}

					// PRUEBAS VISUALIZACION
					for (Mlineas iii : mlineas) {
						System.out.println(" codLinea: " + iii.getCodLinea()
								+ " CountTurnos: " + iii.getCountLinea());
					}
					// FIN PRUEBAS VISUALIZACION

					if (mlineas.isEmpty()) {
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												"No hay lineas para generar la distribucion por dias",
												" "));

					} else {
						// CONSULTA DE MODELOS
						for (Modelo mo : modelo) {
							DetaOrdenDao detalleDao = new DetaOrdenDaoImpl();
							detalle = detalleDao.sumByMod(mo.getModCodigo(),
									this.codOrden);
							// System.out
							// .println("**Cantidades por modelos en el DETALLE**: "
							// + detalle.toString());
							// OBTIENE LOS PARAMETROS UNICOS QUE ESTAN EN LA
							// TABLA LINEASTURNOS
							ParamDao parametroDao = new ParamDaoImpl();
							List<Parametro> param = parametroDao
									.findByParamInLT(codOrden, p.getProCodigo());

							for (Parametro k : param) {
								// OBTIENE LAS TUPLAS PARA LA CANTIDAD DE
								// LINEAS
								// PROGRAMADAS EN BASE AL CODIGO DE
								// PARAMETROS
								LineasTurnosDao ltDao = new LineasTurnosDaoImpl();
								List<Lineasturno> lineastt = ltDao
										.findByParam(k.getParamCodigo());

								// TAMAÑO DE LAS LINEAS PARA SABER CUENTAS
								// EXISTEN
								Integer cantLineas = lineastt.size();

								Integer countLineas = null;
								for (Mlineas codLineas : mlineas) {
									for (Lineasturno i1 : lineastt) {
										if (codLineas
												.getCodLinea()
												.toString()
												.equals(i1.getLineasprod()
														.getLineaproCodigo()
														.toString())) {

											countLineas = Integer
													.parseInt(codLineas
															.getCountLinea()
															.toString());
										}
									}
								}

								// System.out.println("Variable  countLineas: "
								// + countLineas);
								List<Parametro> pp1 = parametroDao
										.findbyCodParam2(this.codOrden,
												k.getParamCodigo());

								for (Parametro j : pp1) {
									Tablas tablas = new Tablas();

									// ANTIGUO CON SUMATORIA
									DistribDetaDao distribDao = new DistribDetaDaoImpl();
									Object sumatoria = distribDao
											.getSumByProTip(codOrden, j
													.getProceso()
													.getProCodigo(), j
													.getTipLinea()
													.getCodigoTiplinea(), mo
													.getModCodigo());

									if (sumatoria != null) {
										// System.out
										// .println("***SUMATORIA POR MODELOS EN LAS LINEAS Y PROCESOS: "
										// + sumatoria.toString());

										// GENERA LA MATRIZ(DISTRIBUCION DE
										// DIAS) PARA AGREGAR AL
										// OBJETO
										mProcesos = tablas.receivParamsPares(
												Integer.parseInt(sumatoria
														.toString()), j
														.getStandar(),
												countLineas, this.nDias,
												cantLineas);

										// mAll.put(j.getProceso().getProCodigo(),
										// mProcesos);

										// PRUEBAS
										MallObject mal = new MallObject(
												Integer.parseInt(sumatoria
														.toString()),
												j.getStandar(), countLineas,
												cantLineas, j.getProceso()
														.getProCodigo(), j
														.getTipLinea()
														.getCodigoTiplinea(),
												mo.getModCodigo(),
												j.getParamCodigo());
										objectMal.add(mal);
										// FIN PRUEBAS

										// ARMA EL OBJETO PARA SER
										// INTRODUCIDO EN EL
										// SCHEDULE
										if (j.getProceso().getProCodigo() == 3) {
											orderitem2 = new Items2(j
													.getProceso()
													.getProCodigo(), j
													.getTipLinea()
													.getCodigoTiplinea(),
													j.getParamCodigo(),
													mo.getModCodigo(),
													j.getStandar(), mProcesos);

											this.orderList2.add(orderitem2);
										}
									} else {
										continue;
									} // FIN ELSE CONTINUE
								} // FIN ULTIMO CICLO POR
							} // FIN SEGUNDO CICLO
						}// FIN PRIMER CICLO

					}// FIN ELSE MLINEAS EMPTY()
					contGeneral++;
				}// FIN CICLO GENERAL

			}// 2do. FIN ELSE
		}// 2. FIN VERIFICAR HORA EXTRAS Y CONTROLAR LOS FINES DE SEMANA

		// PRUEBAS VISUALIZACION

		/**
		 * VISUALIZACION MATRIZ INICIAL
		 * */
		// System.out.println("------ ITEMS 2 ------");
		// Integer ca1 = 0;
		// for (Items2 k : orderList2) {
		// System.out.println("*ITEMS 2.1*: indice: " + ca1 + " CodModelo: "
		// + k.getCodMod() + " CodParam: " + k.getCodParam()
		// + " CodProceso: " + k.getCodProceso() + " CodLinea: "
		// + k.getCodLinea() + " Standar: " + k.getStandar()
		// + " Matriz proceso: " + k.getmProcesos());
		// ca1++;
		// }

		/**
		 * VISUALIZAR LUEGO DE HABER TERMINADO LA DISTRIBUCION
		 * */
		// for (MallObject j : objectMal) {
		// System.out.println("Sumatoria: " + j.getSumatoria() + " standar: "
		// + j.getStand() + " CountLineas: " + j.getCountLineas()
		// + " CantLineas: " + j.getCantLineas() + " codPro: "
		// + j.getCodPro() + " codTPL: " + j.getCodTpl() + " codMod: "
		// + j.getCodMod() + " codParam: " + j.getCodParam());
		// }

		// FIN PRUEBAS VISUALIZACION

		// VARIABLES
		ArrayList<Items3> result = new ArrayList<Items3>();
		ArrayList<Items5> resultR5Apa = new ArrayList<Items5>();
		ArrayList<Items5> resultR5Mnt = new ArrayList<Items5>();
		ArrayList<Items3> resultRApa = new ArrayList<Items3>();
		ArrayList<Items3> resultRMnt = new ArrayList<Items3>();

		/**
		 * TODO ----------TROQUELADO
		 * */
		// GENERA LA DISTRIBUCION DE DIAS PARA TROQUELADO DESDE LA MATRIZ
		// INICIAL
		DistribResultTroquelado dis = new DistribResultTroquelado();
		result = dis.generateDistribDiasTrq(orderList2);

		/**
		 * TODO ----------APARADO
		 * */

		// ENVIAR PARA ARMAR LA **MATRIZ BASE** DEL PROCESO DE APARADO
		DistribAparado distribAparado = new DistribAparado();
		resultR5Apa = distribAparado.generateDistribDiasAparado(result,
				objectMal);

		// ENVIAR PARA ARMAR LA **MATRIZ RESULTADO** DEL PROCESO APARADO
		DistribResultAparado disApa = new DistribResultAparado();
		resultRApa = disApa.generateDistribDiasApa(resultR5Apa);

		/**
		 * TODO ----------AGREGAR A LA MATRIZ RESULTANTE DE APARADO A LA
		 * PRINCIPAL
		 * */
		// AGREGAR LA DISTRIBUCION FINAL DE APARADO EN LA DE TROQUELADO

		for (Items3 k : resultRApa) {
			Items3 ItemsAparado = new Items3(k.getCodProceso(),
					k.getCodLinea(), k.getCodParam(), k.getCodMod(),
					k.getStandar(), k.getmProcesos());
			result.add(ItemsAparado);
		}

		/**
		 * TODO ----------MONTAJE
		 * */
		// ENVIAR PARA ARMAR LA **MATRIZ BASE** DEL PROCESO DE MONTAJE
		DistribMontaje distribMontaje = new DistribMontaje();
		resultR5Mnt = distribMontaje.generateDistribDiasMontaje(resultRApa,
				objectMal);

		// ENVIAR PARA ARMAR LA **MATRIZ RESULTADO** DEL PROCESO MONTAJE
		DistribResultMontaje disMnt = new DistribResultMontaje();
		resultRMnt = disMnt.generateDistribDiasMnt(resultR5Mnt);

		/**
		 * TODO ----------AGREGAR A LA MATRIZ RESULTANTE DE MONTAJE A LA
		 * PRINCIPAL
		 * */
		// AGREGAR LA DISTRIBUCION FINAL DE MONTAJE EN LA DE TROQUELADO
		for (Items3 k : resultRMnt) {
			Items3 soloItems = new Items3(k.getCodProceso(), k.getCodLinea(),
					k.getCodParam(), k.getCodMod(), k.getStandar(),
					k.getmProcesos());
			result.add(soloItems);
		}

		System.out.println("----- DISTRIBUCION FINAL GENERAL: -----");
		Integer ca14 = 0;
		for (Items3 k : result) {
			System.out.println("*RESULTANTE GENERAL*: indice: " + ca14
					+ " CodModelo: " + k.getCodMod() + " CodParam: "
					+ k.getCodParam() + " CodProceso: " + k.getCodProceso()
					+ " CodLinea: " + k.getCodLinea() + " Standar: "
					+ k.getStandar() + " Matriz proceso: " + k.getmProcesos());
			ca14++;
		}

		// GENERAR EL CALENDARIO
		if (generateCalendar(result, diaInicio.getTime()) == true) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Calendario Generado", " "));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Calendario Generado Exitosamente", ""));
		}
	}

	public boolean generateCalendar(ArrayList<Items3> orderList22, Date fInicio) {

		// INICIALIZADOR
		ScheduleDays days = new ScheduleDays();

		// VARIABLES
		boolean flat = false;
		Calendar tConvertCal = null;
		Calendar tConvertCal2 = null;
		Calendar tConvertCal3 = null;
		Double lastHora = 0.00;
		Integer lastElem = 0;

		int iCountLap = 0;
		Integer cpro = 0;
		Integer lpro = 0;

		// ENVIAR PARA DIBUJAR EN EL SCHEDULE
		for (Items3 o : orderList22) {
			if (iCountLap == 0) {
				/**
				 * TODO PRIMER PROCESO
				 * */
				System.out.println("PRIMER PROCESO");

				// INICIALIZAR FECHAS
				tConvertCal = days.DateToCalendar(fInicio);

				// ENVIAR PARA DIBUJAR
				for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
					withOutHextras(
							Integer.parseInt(o.getmProcesos().get(0).get(i)
									.toString()),
							tConvertCal,
							o.getCodProceso(),
							Double.parseDouble(o.getmProcesos().get(1).get(i)
									.toString()), o.getCodParam(),
							o.getCodLinea());
				}

				// IGUALAR DATOS
				cpro = o.getCodProceso();
				lpro = o.getCodLinea();
				lastElem = (Integer) o.getmProcesos().get(0)
						.get(o.getmProcesos().get(0).size() - 1);
				lastHora = (Double) o.getmProcesos().get(1)
						.get(o.getmProcesos().get(1).size() - 1);

				System.out.println("Matriz pares: " + o.getmProcesos().get(0));
				System.out.println("**Ultimo elemento: " + lastElem);
				System.out.println("**Ultima hora: " + lastHora);
				System.out.println("**cod Proceso: " + cpro);
				System.out.println("**cod Linea: " + lpro);
				System.out.println("Fecha: " + tConvertCal.getTime());
				System.out.println("------");

			} else if (cpro.equals((Integer) o.getCodProceso())) {
				/**
				 * TODO PROCESOS IGUALES
				 * */

				if (lpro.equals((Integer) o.getCodLinea())) {
					/**
					 * TODO TIPO DE LINEAS IGUALES
					 * */
					if (o.getCodProceso().equals(3)) {
						// TROQUELADO
						tConvertCal = days.DateToCalendar(fInicio);
						tConvertCal = days.nextDay(tConvertCal);
						tConvertCal2 = tConvertCal;

						System.out.println("TCONVERTCAL TROQUELADO: "
								+ tConvertCal2.getTime());

						if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
							tConvertCal = days.nextDay(tConvertCal);
						} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
							tConvertCal = days.nextDay2(tConvertCal);
						}

						// DIBUJAR
						for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
							withOutHextras(
									Integer.parseInt(o.getmProcesos().get(0)
											.get(i).toString()),
									tConvertCal,
									o.getCodProceso(),
									Double.parseDouble(o.getmProcesos().get(1)
											.get(i).toString()),
									o.getCodParam(), o.getCodLinea());
						}
						tConvertCal = days.DateToCalendar(fInicio);
						tConvertCal = days.nextDay(tConvertCal);
						tConvertCal2 = tConvertCal;
						System.out.println("TCONVERTCAL TROQUELADO 2: "
								+ tConvertCal2.getTime());
					} else if (o.getCodProceso().equals(2)) {
						// APARADO
						if (tConvertCal3 == null) {
							tConvertCal = days.prevDay(tConvertCal);
							if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
								tConvertCal = days.nextDay(tConvertCal);
							} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
								tConvertCal = days.nextDay2(tConvertCal);
							}
							// DIBUJAR
							for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
								withOutHextras(
										Integer.parseInt(o.getmProcesos()
												.get(0).get(i).toString()),
										tConvertCal,
										o.getCodProceso(),
										Double.parseDouble(o.getmProcesos()
												.get(1).get(i).toString()),
										o.getCodParam(), o.getCodLinea());
							}

						} else {
							tConvertCal = tConvertCal3;
							tConvertCal = days.prevDay(tConvertCal);
							if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
								tConvertCal = days.nextDay(tConvertCal);
							} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
								tConvertCal = days.nextDay2(tConvertCal);
							}
							// DIBUJAR
							for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
								withOutHextras(
										Integer.parseInt(o.getmProcesos()
												.get(0).get(i).toString()),
										tConvertCal,
										o.getCodProceso(),
										Double.parseDouble(o.getmProcesos()
												.get(1).get(i).toString()),
										o.getCodParam(), o.getCodLinea());
							}
							tConvertCal3 = null;
						}

					} else if (o.getCodProceso().equals(1)) {
						// MONTAJE
						// tConvertCal = days.prevDay(tConvertCal);
						if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
							tConvertCal = days.nextDay(tConvertCal);
						} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
							tConvertCal = days.nextDay2(tConvertCal);
						}
						// DIBUJAR
						for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
							withOutHextras(
									Integer.parseInt(o.getmProcesos().get(0)
											.get(i).toString()),
									tConvertCal,
									o.getCodProceso(),
									Double.parseDouble(o.getmProcesos().get(1)
											.get(i).toString()),
									o.getCodParam(), o.getCodLinea());
						}
					}

				} else {
					/**
					 * TODO TOPO DE LINEAS DIFERENTES
					 * */
					if (o.getCodProceso().equals(3)) {
						// TROQUELADO
						tConvertCal = days.DateToCalendar(fInicio);

						if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
							tConvertCal = days.nextDay(tConvertCal);
						} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
							tConvertCal = days.nextDay2(tConvertCal);
						}
						// DIBUJAR
						for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
							withOutHextras(
									Integer.parseInt(o.getmProcesos().get(0)
											.get(i).toString()),
									tConvertCal,
									o.getCodProceso(),
									Double.parseDouble(o.getmProcesos().get(1)
											.get(i).toString()),
									o.getCodParam(), o.getCodLinea());
						}
					} else if (o.getCodProceso().equals(2)) {
						// APARADO
						if (tConvertCal2 != null) {
							tConvertCal = tConvertCal2;
							tConvertCal = days.nextDay(tConvertCal);
							//
							if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
								tConvertCal = days.nextDay(tConvertCal);
							} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
								tConvertCal = days.nextDay2(tConvertCal);
							}
							// // DIBUJAR
							for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
								withOutHextras(
										Integer.parseInt(o.getmProcesos()
												.get(0).get(i).toString()),
										tConvertCal,
										o.getCodProceso(),
										Double.parseDouble(o.getmProcesos()
												.get(1).get(i).toString()),
										o.getCodParam(), o.getCodLinea());
							}
							tConvertCal3 = null;
							tConvertCal2 = null;
						}

					} else if (o.getCodProceso().equals(1)) {
						// MONTAJE
						tConvertCal = days.nextDay(tConvertCal);
						//
						if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
							tConvertCal = days.nextDay(tConvertCal);
						} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
							tConvertCal = days.nextDay2(tConvertCal);
						}
						// DIBUJAR
						for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
							withOutHextras(
									Integer.parseInt(o.getmProcesos().get(0)
											.get(i).toString()),
									tConvertCal,
									o.getCodProceso(),
									Double.parseDouble(o.getmProcesos().get(1)
											.get(i).toString()),
									o.getCodParam(), o.getCodLinea());
						}
					}
				}

				// IGUALAR DATOS
				cpro = o.getCodProceso();
				lpro = o.getCodLinea();
				lastElem = (Integer) o.getmProcesos().get(0)
						.get(o.getmProcesos().get(0).size() - 1);
				lastHora = (Double) o.getmProcesos().get(1)
						.get(o.getmProcesos().get(1).size() - 1);

				System.out.println("Matriz pares: " + o.getmProcesos().get(0));
				System.out.println("**Ultimo elemento: " + lastElem);
				System.out.println("**Ultima hora: " + lastHora);
				System.out.println("**cod Proceso: " + cpro);
				System.out.println("**cod Linea: " + lpro);
				System.out.println("Fecha: " + tConvertCal.getTime());
				System.out.println("------");
			} else {
				/**
				 * TODO PROCESOS DIFERENTES
				 * */
				if (o.getCodProceso().equals(3)) {
					// TROQUELADO
					tConvertCal = days.DateToCalendar(fInicio);

					if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
						tConvertCal = days.nextDay(tConvertCal);
					} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
						tConvertCal = days.nextDay2(tConvertCal);
					}
					// DIBUJAR
					for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
						withOutHextras(
								Integer.parseInt(o.getmProcesos().get(0).get(i)
										.toString()),
								tConvertCal,
								o.getCodProceso(),
								Double.parseDouble(o.getmProcesos().get(1)
										.get(i).toString()), o.getCodParam(),
								o.getCodLinea());
					}
				} else if (o.getCodProceso().equals(2)) {
					// APARADO
					tConvertCal = days.DateToCalendar(fInicio);
					tConvertCal = days.nextDay(tConvertCal);
					tConvertCal3 = tConvertCal;

					if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
						tConvertCal = days.nextDay(tConvertCal);
					} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
						tConvertCal = days.nextDay2(tConvertCal);
					}
					// DIBUJAR
					for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
						withOutHextras(
								Integer.parseInt(o.getmProcesos().get(0).get(i)
										.toString()),
								tConvertCal,
								o.getCodProceso(),
								Double.parseDouble(o.getmProcesos().get(1)
										.get(i).toString()), o.getCodParam(),
								o.getCodLinea());
					}
				} else if (o.getCodProceso().equals(1)) {
					// MONTAJE
					tConvertCal = days.DateToCalendar(fInicio);
					tConvertCal = days.nextDay2(tConvertCal);
					tConvertCal3 = tConvertCal;

					if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 1) {
						tConvertCal = days.nextDay(tConvertCal);
					} else if (tConvertCal.get(Calendar.DAY_OF_WEEK) == 7) {
						tConvertCal = days.nextDay2(tConvertCal);
					}

					// DIBUJAR
					for (int i = 0; i < o.getmProcesos().get(0).size(); i++) {
						withOutHextras(
								Integer.parseInt(o.getmProcesos().get(0).get(i)
										.toString()),
								tConvertCal,
								o.getCodProceso(),
								Double.parseDouble(o.getmProcesos().get(1)
										.get(i).toString()), o.getCodParam(),
								o.getCodLinea());
					}
				}

				// IGUALAR DATOS
				cpro = o.getCodProceso();
				lpro = o.getCodLinea();
				lastElem = (Integer) o.getmProcesos().get(0)
						.get(o.getmProcesos().get(0).size() - 1);
				lastHora = (Double) o.getmProcesos().get(1)
						.get(o.getmProcesos().get(1).size() - 1);

				System.out.println("Matriz pares: " + o.getmProcesos().get(0));
				System.out.println("**Ultimo elemento: " + lastElem);
				System.out.println("**Ultima hora: " + lastHora);
				System.out.println("**cod Proceso: " + cpro);
				System.out.println("**cod Linea: " + lpro);
				System.out.println("Fecha: " + tConvertCal.getTime());
				System.out.println("------");
			}
			iCountLap++;
		} // FIN 1er. CICLO
		return flat;
	}// FIN FUNCION

	// DIBUJAR EN EL SHCEDULE
	public void withOutHextras(Integer Pares, Calendar fMontajeParam,
			Integer codProceso, Double dhora, Integer codParam, Integer codLinea) {

		NumberFormat formatter = new DecimalFormat("#0.00");
		ScheduleDays days = new ScheduleDays();
		Calendar m = fMontajeParam;

		// System.out.println("Fecha recibida1: " + m.getTime());

		float d = 0, s = 0;
		String pal = "";
		String Lin = "";
		switch (codProceso) {
		case 1:
			pal = "MONT";
			break;
		case 2:
			pal = "APAR";
			break;
		case 3:
			pal = "TROQ";
			break;
		}

		switch (codLinea) {
		case 3:
			Lin = "CONV";
			break;
		case 4:
			Lin = "MAN";
			break;
		case 9:
			Lin = "AUT";
			break;
		case 7:
			Lin = "TRQ";
			break;
		case 2:
			Lin = "INY";
			break;
		case 10:
			Lin = "STB";
			break;
		}

		if (m.get(Calendar.DAY_OF_WEEK) == 7) {
			m = days.nextDay2(m);
		}
		// System.out.println("Fecha recibida2 GRAFICA: " + m.getTime());
		eventModel.addEvent(new DefaultScheduleEvent(pal + ":" + "L:" + Lin
				+ ":" + Pares.toString(), m.getTime(), m.getTime()));

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
			// FacesContext.getCurrentInstance().addMessage(null,
			// new FacesMessage(this.d, "Proceso: " + pal));
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
			// FacesContext.getCurrentInstance().addMessage(null,
			// new FacesMessage(this.d));
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
