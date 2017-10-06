package com.project.reportsBean;

import java.io.Serializable;
import java.util.Date;

public class CapxOrdenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer codOrden;
	private String nomCli;
	private String respOrden;
	private String modNombre;
	private Integer talNumero;
	private String nomEmpresa;
	private String dirEmpresa;
	private String telEmpresa;
	private Date fOrden;
	private Integer cantidad;
	private String logEmpresa;
	private String logFAPS;

	private String codProceso;
	private String tpLinea;
	private Integer standar;

	public CapxOrdenEntity(Integer codOrden, String nomCli, String respOrden,
			String modNombre, Integer talNumero, String nomEmpresa,
			String dirEmpresa, String telEmpresa, Date fOrden,
			Integer cantidad, String logEmpresa, String logFAPS) {

		this.codOrden = codOrden;
		this.nomCli = nomCli;
		this.respOrden = respOrden;
		this.modNombre = modNombre;
		this.talNumero = talNumero;
		this.nomEmpresa = nomEmpresa;
		this.dirEmpresa = dirEmpresa;
		this.telEmpresa = telEmpresa;
		this.fOrden = fOrden;
		this.cantidad = cantidad;
		this.logEmpresa = logEmpresa;
		this.logFAPS = logFAPS;
	}

	public String getCodProceso() {
		return codProceso;
	}

	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}

	public String getTpLinea() {
		return tpLinea;
	}

	public void setTpLinea(String tpLinea) {
		this.tpLinea = tpLinea;
	}

	public Integer getStandar() {
		return standar;
	}

	public void setStandar(Integer standar) {
		this.standar = standar;
	}

	public Integer getCodOrden() {
		return codOrden;
	}

	public void setCodOrden(Integer codOrden) {
		this.codOrden = codOrden;
	}

	public String getNomCli() {
		return nomCli;
	}

	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}

	public String getRespOrden() {
		return respOrden;
	}

	public void setRespOrden(String respOrden) {
		this.respOrden = respOrden;
	}

	public String getModNombre() {
		return modNombre;
	}

	public void setModNombre(String modNombre) {
		this.modNombre = modNombre;
	}

	public Integer getTalNumero() {
		return talNumero;
	}

	public void setTalNumero(Integer talNumero) {
		this.talNumero = talNumero;
	}

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	public String getDirEmpresa() {
		return dirEmpresa;
	}

	public void setDirEmpresa(String dirEmpresa) {
		this.dirEmpresa = dirEmpresa;
	}

	public String getTelEmpresa() {
		return telEmpresa;
	}

	public void setTelEmpresa(String telEmpresa) {
		this.telEmpresa = telEmpresa;
	}

	public Date getfOrden() {
		return fOrden;
	}

	public void setfOrden(Date fOrden) {
		this.fOrden = fOrden;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getLogEmpresa() {
		return logEmpresa;
	}

	public void setLogEmpresa(String logEmpresa) {
		this.logEmpresa = logEmpresa;
	}

	public String getLogFAPS() {
		return logFAPS;
	}

	public void setLogFAPS(String logFAPS) {
		this.logFAPS = logFAPS;
	}

}