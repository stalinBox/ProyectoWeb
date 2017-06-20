package com.project.utils;

public class MallObject {
	private Integer sumatoria;
	private Integer stand;
	private Integer countLineas;
	private Integer cantLineas;
	private Integer codPro;
	private Integer codTpl;
	private Integer codMod;

	public MallObject(Integer sumatoria, Integer stand, Integer countLineas,
			Integer cantLineas, Integer codPro, Integer codTpl, Integer codMod) {
		this.sumatoria = sumatoria;
		this.stand = stand;
		this.countLineas = countLineas;
		this.cantLineas = cantLineas;
		this.codPro = codPro;
		this.codTpl = codTpl;
		this.codMod = codMod;
	}

	public Integer getCodMod() {
		return codMod;
	}

	public void setCodMod(Integer codMod) {
		this.codMod = codMod;
	}

	public Integer getCodPro() {
		return codPro;
	}

	public void setCodPro(Integer codPro) {
		this.codPro = codPro;
	}

	public Integer getCodTpl() {
		return codTpl;
	}

	public void setCodTpl(Integer codTpl) {
		this.codTpl = codTpl;
	}

	public Integer getSumatoria() {
		return sumatoria;
	}

	public void setSumatoria(Integer sumatoria) {
		this.sumatoria = sumatoria;
	}

	public Integer getStand() {
		return stand;
	}

	public void setStand(Integer stand) {
		this.stand = stand;
	}

	public Integer getCountLineas() {
		return countLineas;
	}

	public void setCountLineas(Integer countLineas) {
		this.countLineas = countLineas;
	}

	public Integer getCantLineas() {
		return cantLineas;
	}

	public void setCantLineas(Integer cantLineas) {
		this.cantLineas = cantLineas;
	}

}
