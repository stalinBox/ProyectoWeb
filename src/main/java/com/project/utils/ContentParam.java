package com.project.utils;

import java.io.Serializable;

public class ContentParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Integer codOrden;
	private static Integer standConvMontaje;
	private static Integer StandAutMontaje;
	private static Integer standConvTroquelado;
	private static Integer StandAutTroquelado;
	private static Integer standConvAparado;
	private static Integer StandAutAparado;
	private static Integer totalOrden;
	private static Integer respGenOrden;

	public static Integer getCodOrden() {
		return codOrden;
	}

	public static void setCodOrden(Integer codOrden) {
		ContentParam.codOrden = codOrden;
	}

	public static Integer getStandConvMontaje() {
		return standConvMontaje;
	}

	public static void setStandConvMontaje(Integer standConvMontaje) {
		ContentParam.standConvMontaje = standConvMontaje;
	}

	public static Integer getStandAutMontaje() {
		return StandAutMontaje;
	}

	public static void setStandAutMontaje(Integer standAutMontaje) {
		StandAutMontaje = standAutMontaje;
	}

	public static Integer getStandConvTroquelado() {
		return standConvTroquelado;
	}

	public static void setStandConvTroquelado(Integer standConvTroquelado) {
		ContentParam.standConvTroquelado = standConvTroquelado;
	}

	public static Integer getStandAutTroquelado() {
		return StandAutTroquelado;
	}

	public static void setStandAutTroquelado(Integer standAutTroquelado) {
		StandAutTroquelado = standAutTroquelado;
	}

	public static Integer getStandConvAparado() {
		return standConvAparado;
	}

	public static void setStandConvAparado(Integer standConvAparado) {
		ContentParam.standConvAparado = standConvAparado;
	}

	public static Integer getStandAutAparado() {
		return StandAutAparado;
	}

	public static void setStandAutAparado(Integer standAutAparado) {
		StandAutAparado = standAutAparado;
	}

	public static Integer getTotalOrden() {
		return totalOrden;
	}

	public static void setTotalOrden(Integer totalOrden) {
		ContentParam.totalOrden = totalOrden;
	}

	public static Integer getRespGenOrden() {
		return respGenOrden;
	}

	public static void setRespGenOrden(Integer respGenOrden) {
		ContentParam.respGenOrden = respGenOrden;
	}

}
