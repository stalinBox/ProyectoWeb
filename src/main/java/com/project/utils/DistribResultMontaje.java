package com.project.utils;

import java.util.ArrayList;

public class DistribResultMontaje {

	public ArrayList<Items3> generateDistribDiasMnt(
			ArrayList<Items5> orderList22) {

		// VARIABLES
		ArrayList<Items3> orderListFinal = new ArrayList<Items3>();
		ArrayList<ArrayList<Object>> mProcesosFinal = new ArrayList<ArrayList<Object>>();

		Integer codProceso = 0;
		Integer codTpLinea = 0;

		Integer lastElem = 0;
		Integer sumFirstLast = 0;
		Integer acumElem = 0;
		Integer firstElem = 0;
		Integer newMatriz2 = 0;
		Integer lastElemDif = 0;
		Integer IEsp = 0;
		Integer IEsp2 = 0;
		Object newMatriz = null;
		orderListFinal.clear();

		int j = 0;
		for (Items5 i : orderList22) {
			mProcesosFinal.clear();
			ArrayList<Object> dias = new ArrayList<Object>();
			ArrayList<Object> horas = new ArrayList<Object>();

			if (j == 0) {
				// PRIMER PROCESO
				Items3 orderitemFinal = new Items3(i.getCodProceso(),
						i.getCodLinea(), i.getCodParam(), i.getCodMod(),
						i.getStandar(), i.getmProcesos());
				orderListFinal.add(orderitemFinal);

				// VARIABLES PARA COMPARAR EL CODPRO Y CODTPL
				codProceso = i.getCodProceso();
				codTpLinea = i.getCodLinea();

				// ULTIMO ELEMENTO A COMPARAR CON EL SEGUNDO
				lastElem = (Integer) i.getmProcesos().get(0)
						.get(i.getmProcesos().get(0).size() - 1);
			} else if (i.getCodProceso().equals(codProceso)
					&& i.getCodLinea().equals(codTpLinea)) {

				// *************PROCESOS IGUALES*****************
				System.out.println("******PROCESOS IGUALES******");

				if (lastElemDif != 0) {
					System.out.println("lastElemDif NO ES CERO: ");
					Items3 orderitemFinal = new Items3(i.getCodProceso(),
							i.getCodLinea(), i.getCodParam(), i.getCodMod(),
							i.getStandar(), i.getmProcesos());
					orderListFinal.add(orderitemFinal);

					// VARIABLES PARA COMPARAR EL CODPRO Y CODTPL
					codProceso = i.getCodProceso();
					codTpLinea = i.getCodLinea();

					// ULTIMO ELEMENTO A COMPARAR CON EL SEGUNDO
					lastElem = (Integer) i.getmProcesos().get(0)
							.get(i.getmProcesos().get(0).size() - 1);

					lastElemDif = 0;
					System.out.println("SIN HACER NADA");
					System.out.println("algo" + i.getmProcesos().get(0));
					sumFirstLast = 0;
				} else {
					System.out.println("lastElemDif ES CERO: ");
					firstElem = (Integer) i.getmProcesos().get(0).get(0);
					if (i.getmProcesos().get(0).size() > 1) {
						System.out.println("Matriz ORIGINAL: "
								+ i.getmProcesos().get(0));

						if (sumFirstLast != 0) {
							System.out.println("firstElem: " + firstElem);
							System.out.println("lastElem: " + lastElem);
							System.out.println("1 VAR sumFirstLast:"
									+ sumFirstLast);
							System.out.println("standar: "
									+ i.getStandar().toString());

							if (Integer.parseInt(lastElem.toString()) < Integer
									.parseInt(i.getStandar().toString())) {
								newMatriz = Integer.parseInt(i.getStandar()
										.toString()) - sumFirstLast;
							} else {
								newMatriz = Integer.parseInt(i.getStandar()
										.toString());
							}
							System.out.println("var newMatriz: " + newMatriz);
							dias.add(Math.abs(Integer.parseInt(newMatriz
									.toString())));
							System.out.println("var dias1: " + dias);
							for (int ij = 0; ij < i.getmProcesos().get(0)
									.size(); ij++) {
								if (ij == i.getmProcesos().get(0).size() - 1) {
									break;
								} else {
									dias.add(i.getmProcesos().get(0).get(ij));
								}
							}
							newMatriz2 = Integer.parseInt(i.getmProcesos()
									.get(0)
									.get(i.getmProcesos().get(1).size() - 1)
									.toString())
									- Integer.parseInt(newMatriz.toString());

							// AGREGAR A LA MATRIZ FINAL
							dias.add(Math.abs(Integer.parseInt(newMatriz2
									.toString())));
							FragmentNumber2 mDias = new FragmentNumber2();
							horas = mDias.Number(i.getStandar(), dias);
							mProcesosFinal.add(dias);
							mProcesosFinal.add(horas);
							Items3 orderitemFinal = new Items3(
									i.getCodProceso(), i.getCodLinea(),
									i.getCodParam(), i.getCodMod(),
									i.getStandar(),
									new ArrayList<ArrayList<Object>>(
											mProcesosFinal));
							orderListFinal.add(orderitemFinal);
							System.out.println("Matriz RESULTANTE: " + dias);
							lastElem = (Integer) (dias.get(dias.size() - 1));
							System.out.println("new LastElem: " + lastElem);
							sumFirstLast = 0;
						} else {
							System.out.println("firstElem: " + firstElem);
							System.out.println("lastElem: " + lastElem);
							System.out.println("2 VAR sumFirstLast:"
									+ sumFirstLast);
							System.out.println("standar: "
									+ i.getStandar().toString());

							if (Integer.parseInt(lastElem.toString()) < Integer
									.parseInt(i.getStandar().toString())) {
								newMatriz = Integer.parseInt(i.getStandar()
										.toString()) - lastElem;
							} else {
								newMatriz = Integer.parseInt(i.getStandar()
										.toString());
							}
							System.out.println("var newMatriz: " + newMatriz);
							dias.add(Math.abs(Integer.parseInt(newMatriz
									.toString())));
							System.out.println("var dias1: " + dias);

							for (int ij = 1; ij < i.getmProcesos().get(0)
									.size() - 1; ij++) {
								if (ij == i.getmProcesos().get(0).size() - 1) {
									break;
								} else {
									dias.add(i.getmProcesos().get(0).get(ij));
								}
							}

							newMatriz2 = Math
									.abs(((Integer) i.getStandar() - (Integer) dias
											.get(0)))
									+ (Integer) i
											.getmProcesos()
											.get(0)
											.get(i.getmProcesos().get(0).size() - 1);

							System.out.println("new Matriz2: " + newMatriz2);

							// AGREGAR A LA MATRIZ FINAL
							dias.add(Math.abs(Integer.parseInt(newMatriz2
									.toString())));

							// ********** PARTE CRITICA
							IEsp = 0;
							IEsp2 = 0;
							for (int d = 0; d < dias.size(); d++) {
								IEsp += (Integer) dias.get(d);
							}
							for (int d = 0; d < i.getmProcesos().get(0).size(); d++) {
								IEsp2 += (Integer) i.getmProcesos().get(0)
										.get(d);
							}

							System.out.println("Dias: " + dias);
							System.out.println("Matriz Ori:"
									+ i.getmProcesos().get(0));
							System.out.println("IEsp: " + IEsp);
							System.out.println("IEsp2: " + IEsp2);
							if (IEsp != IEsp2) {
								dias.clear();
								System.out.println("var newMatriz: "
										+ newMatriz);
								dias.add(Math.abs(Integer.parseInt(newMatriz
										.toString())));

								for (int ij = 0; ij < i.getmProcesos().get(0)
										.size(); ij++) {
									if (ij == i.getmProcesos().get(0).size() - 1) {
										break;
									} else {
										dias.add(i.getmProcesos().get(0)
												.get(ij));
									}
								}

								newMatriz2 = Math.abs(((Integer) dias.get(0))
										- (Integer) i
												.getmProcesos()
												.get(0)
												.get(i.getmProcesos().get(0)
														.size() - 1));

								System.out
										.println("new Matriz2: " + newMatriz2);

								// AGREGAR A LA MATRIZ FINAL
								dias.add(Math.abs(Integer.parseInt(newMatriz2
										.toString())));
							}
							// ********** FIN PARTE CRITICA

							System.out.println("Matriz RESULTANTE: " + dias);
							FragmentNumber2 mDias = new FragmentNumber2();
							horas = mDias.Number(i.getStandar(), dias);
							mProcesosFinal.add(dias);
							mProcesosFinal.add(horas);
							Items3 orderitemFinal = new Items3(
									i.getCodProceso(), i.getCodLinea(),
									i.getCodParam(), i.getCodMod(),
									i.getStandar(),
									new ArrayList<ArrayList<Object>>(
											mProcesosFinal));
							orderListFinal.add(orderitemFinal);
							lastElem = (Integer) (dias.get(dias.size() - 1));
							System.out.println("new LastElem: " + lastElem);
						}

					} else {
						System.out.println("Matriz UNICA: "
								+ i.getmProcesos().get(0));
						sumFirstLast = lastElem + firstElem;
						if (acumElem >= i.getStandar()) {
							// ALGO
							System.out.println("PASA ALGO...!!??");
						} else {
							// AGREGAR LA MISMA TUPLA
							dias.add(Math.abs(firstElem));
							FragmentNumber2 mDias = new FragmentNumber2();
							horas = mDias.Number(i.getStandar(), dias);
							mProcesosFinal.add(dias);
							mProcesosFinal.add(horas);
							Items3 orderitemFinal = new Items3(
									i.getCodProceso(), i.getCodLinea(),
									i.getCodParam(), i.getCodMod(),
									i.getStandar(),
									new ArrayList<ArrayList<Object>>(
											mProcesosFinal));
							orderListFinal.add(orderitemFinal);
						}
						lastElem = (Integer) i.getmProcesos().get(0)
								.get(i.getmProcesos().get(0).size() - 1);
					}

					// GUARDAR PARA LA SIGUIENTE VUELTA
					codProceso = i.getCodProceso();
					codTpLinea = i.getCodLinea();
					// FIN PROCESOS IGUALES
				}
				System.out
						.println("-------------------------------------------");
			} else {
				// *************PROCESOS DIFERENTES*****************
				System.out.println("******PROCESOS DIFERENTES*****");
				firstElem = (Integer) i.getmProcesos().get(0).get(0);
				lastElem = 0;
				if (i.getmProcesos().get(0).size() > 1) {
					System.out.println("Matriz ORIGINAL: "
							+ i.getmProcesos().get(0));
				} else {
					System.out.println("Matriz UNICA: "
							+ i.getmProcesos().get(0));
					System.out.println("**LastElem: " + lastElem);
					System.out.println("**FirsElem: " + firstElem);
					sumFirstLast = lastElem + firstElem;
					if (acumElem >= i.getStandar()) {
						// ALGO
						System.out.println("PASA ALGO...!!??");
					} else {
						// AGREGAR LA MISMA TUPLA
						dias.add(Math.abs(firstElem));
						System.out.println("** Dias: " + dias);
						FragmentNumber2 mDias = new FragmentNumber2();
						horas = mDias.Number(i.getStandar(), dias);
						mProcesosFinal.add(dias);
						mProcesosFinal.add(horas);
						Items3 orderitemFinal = new Items3(
								i.getCodProceso(),
								i.getCodLinea(),
								i.getCodParam(),
								i.getCodMod(),
								i.getStandar(),
								new ArrayList<ArrayList<Object>>(mProcesosFinal));
						orderListFinal.add(orderitemFinal);
					}
					// lastElem = (Integer) i.getmProcesos().get(0)
					// .get(i.getmProcesos().get(0).size() - 1);
					lastElemDif = (Integer) i.getmProcesos().get(0)
							.get(i.getmProcesos().get(0).size() - 1);

				}
				// GUARDAR PARA LA SIGUIENTE VUELTA
				codProceso = i.getCodProceso();
				codTpLinea = i.getCodLinea();
				System.out.println("---------------------------------------");
				// FIN PROCESOS DIFERENTES
			}
			j++;
		}

		return orderListFinal;
	}
}
