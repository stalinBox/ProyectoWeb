package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_codigo")
	private Integer empCodigo;

	@Column(name="emp_desc")
	private String empDesc;

	@Column(name="emp_dir")
	private String empDir;

	@Column(name="emp_nom")
	private String empNom;

	@Column(name="emp_telf")
	private String empTelf;

	public Empresa() {
	}

	public Integer getEmpCodigo() {
		return this.empCodigo;
	}

	public void setEmpCodigo(Integer empCodigo) {
		this.empCodigo = empCodigo;
	}

	public String getEmpDesc() {
		return this.empDesc;
	}

	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}

	public String getEmpDir() {
		return this.empDir;
	}

	public void setEmpDir(String empDir) {
		this.empDir = empDir;
	}

	public String getEmpNom() {
		return this.empNom;
	}

	public void setEmpNom(String empNom) {
		this.empNom = empNom;
	}

	public String getEmpTelf() {
		return this.empTelf;
	}

	public void setEmpTelf(String empTelf) {
		this.empTelf = empTelf;
	}

}