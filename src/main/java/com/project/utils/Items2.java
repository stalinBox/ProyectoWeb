package com.project.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class Items2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer codProceso;
	private Integer codParam;
	private Integer codLinea;
	private Integer codMod;
	private ArrayList<ArrayList<Object>> mProcesos;

	public Items2(Integer codProceso, Integer codLinea, Integer codParam,
			Integer codMod, ArrayList<ArrayList<Object>> mProcesos) {
		this.codProceso = codProceso;
		this.codLinea = codLinea;
		this.codParam = codParam;
		this.mProcesos = mProcesos;
		this.codMod = codMod;
	}

	public Items2() {
	}

	public Integer getCodMod() {
		return codMod;
	}

	public void setCodMod(Integer codMod) {
		this.codMod = codMod;
	}

	public Integer getCodLinea() {
		return codLinea;
	}

	public void setCodLinea(Integer codLinea) {
		this.codLinea = codLinea;
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