package com.project.utils;

import java.io.Serializable;

public class ItemCodOrden implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Integer codOrden;

	public static Integer getCodOrden() {
		return codOrden;
	}

	public static void setCodOrden(Integer codOrden) {
		ItemCodOrden.codOrden = 112;
		// ItemCodOrden.codOrden = codOrden;
	}

}
