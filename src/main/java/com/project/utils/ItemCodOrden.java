package com.project.utils;

import java.io.Serializable;

public class ItemCodOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Integer codOrden;

	private static Integer totalOrden;

	public static Integer getCodOrden() {
		ItemCodOrden.codOrden = 122;
		return codOrden;
	}

	public static void setCodOrden(Integer codOrden) {
		ItemCodOrden.codOrden = codOrden;
	}

	public static Integer getTotalOrden() {
		ItemCodOrden.totalOrden = 190;
		return totalOrden;
	}

	public static void setTotalOrden(Integer totalOrden) {
		ItemCodOrden.totalOrden = totalOrden;
	}

}
