package com.project.utils;

import java.io.Serializable;

public class ContentParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Integer codOrden;
	private static Integer totalOrden;
	private static Integer StandConvMontaje;
	private static Integer StandConvAparado;
	private static Integer StandConvTroquelado;

	private static Integer StandAutMontaje;
	private static Integer StandAutAparado;
	private static Integer StandAutTroquelado;

	private static Integer RespGenOrden;

	public static Integer getCodOrden() {
		codOrden = 10;
		return codOrden;
	}

	public static void setCodOrden(Integer codOrden) {
		ContentParam.codOrden = codOrden;
	}

	public static Integer getTotalOrden() {
		totalOrden = 190;
		return totalOrden;
	}

	public static void setTotalOrden(Integer totalOrden) {
		ContentParam.totalOrden = totalOrden;
	}

	public static Integer getStandConvMontaje() {
		return StandConvMontaje;
	}

	public static void setStandConvMontaje(Integer standConvMontaje) {
		StandConvMontaje = standConvMontaje;
	}

	public static Integer getStandConvAparado() {
		return StandConvAparado;
	}

	public static void setStandConvAparado(Integer standConvAparado) {
		StandConvAparado = standConvAparado;
	}

	public static Integer getStandConvTroquelado() {
		return StandConvTroquelado;
	}

	public static void setStandConvTroquelado(Integer standConvTroquelado) {
		StandConvTroquelado = standConvTroquelado;
	}

	public static Integer getStandAutMontaje() {
		return StandAutMontaje;
	}

	public static void setStandAutMontaje(Integer standAutMontaje) {
		StandAutMontaje = standAutMontaje;
	}

	public static Integer getStandAutAparado() {
		return StandAutAparado;
	}

	public static void setStandAutAparado(Integer standAutAparado) {
		StandAutAparado = standAutAparado;
	}

	public static Integer getStandAutTroquelado() {
		return StandAutTroquelado;
	}

	public static void setStandAutTroquelado(Integer standAutTroquelado) {
		StandAutTroquelado = standAutTroquelado;
	}

	public static Integer getRespGenOrden() {
		return RespGenOrden;
	}

	public static void setRespGenOrden(Integer respGenOrden) {
		RespGenOrden = respGenOrden;
	}

}
