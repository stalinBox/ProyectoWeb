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

	@Column(name="emp_direcc")
	private String empDirecc;

	@Column(name="emp_logo")
	private byte[] empLogo;

	@Column(name="emp_nombre")
	private String empNombre;

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

	public String getEmpDirecc() {
		return this.empDirecc;
	}

	public void setEmpDirecc(String empDirecc) {
		this.empDirecc = empDirecc;
	}

	public byte[] getEmpLogo() {
		return this.empLogo;
	}

	public void setEmpLogo(byte[] empLogo) {
		this.empLogo = empLogo;
	}

	public String getEmpNombre() {
		return this.empNombre;
	}

	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}

	public String getEmpTelf() {
		return this.empTelf;
	}

	public void setEmpTelf(String empTelf) {
		this.empTelf = empTelf;
	}

}