package com.respaldo;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_creation")
	private String userCreation;

	@Temporal(TemporalType.DATE)
	@Column(name = "user_date_creation")
	private Date userDateCreation;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_passwd")
	private String userPasswd;

	@Column(name = "user_state")
	private BigDecimal userState;

	// bi-directional many-to-one association to Ordenprod
	@OneToMany(mappedBy = "usuario1")
	private List<Ordenprod> ordenprods1;

	// bi-directional many-to-one association to Ordenprod
	@OneToMany(mappedBy = "usuario2")
	private List<Ordenprod> ordenprods2;

	// bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy = "usuario")
	private List<Parametro> parametros;

	// bi-directional many-to-one association to Procesosop
	@OneToMany(mappedBy = "usuario")
	private List<Procesosop> procesosops;

	// bi-directional many-to-one association to Rol
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "rol_id", nullable = false, insertable = false, updatable = false)
	private Rol rol1;

	// bi-directional many-to-one association to Rol
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "rol_id", insertable = false, updatable = false)
	private Rol rol2;

	public Usuario() {
		this.rol1 = new Rol();
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserCreation() {
		return this.userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

	public Date getUserDateCreation() {
		return this.userDateCreation;
	}

	public void setUserDateCreation(Date userDateCreation) {
		this.userDateCreation = userDateCreation;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return this.userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public BigDecimal getUserState() {
		return this.userState;
	}

	public void setUserState(BigDecimal userState) {
		this.userState = userState;
	}

	public List<Ordenprod> getOrdenprods1() {
		return this.ordenprods1;
	}

	public void setOrdenprods1(List<Ordenprod> ordenprods1) {
		this.ordenprods1 = ordenprods1;
	}

	public Ordenprod addOrdenprods1(Ordenprod ordenprods1) {
		getOrdenprods1().add(ordenprods1);
		ordenprods1.setUsuario1(this);

		return ordenprods1;
	}

	public Ordenprod removeOrdenprods1(Ordenprod ordenprods1) {
		getOrdenprods1().remove(ordenprods1);
		ordenprods1.setUsuario1(null);

		return ordenprods1;
	}

	public List<Ordenprod> getOrdenprods2() {
		return this.ordenprods2;
	}

	public void setOrdenprods2(List<Ordenprod> ordenprods2) {
		this.ordenprods2 = ordenprods2;
	}

	public Ordenprod addOrdenprods2(Ordenprod ordenprods2) {
		getOrdenprods2().add(ordenprods2);
		ordenprods2.setUsuario2(this);

		return ordenprods2;
	}

	public Ordenprod removeOrdenprods2(Ordenprod ordenprods2) {
		getOrdenprods2().remove(ordenprods2);
		ordenprods2.setUsuario2(null);

		return ordenprods2;
	}

	public List<Parametro> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getParametros().add(parametro);
		parametro.setUsuario(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setUsuario(null);

		return parametro;
	}

	public List<Procesosop> getProcesosops() {
		return this.procesosops;
	}

	public void setProcesosops(List<Procesosop> procesosops) {
		this.procesosops = procesosops;
	}

	public Procesosop addProcesosop(Procesosop procesosop) {
		getProcesosops().add(procesosop);
		procesosop.setUsuario(this);

		return procesosop;
	}

	public Procesosop removeProcesosop(Procesosop procesosop) {
		getProcesosops().remove(procesosop);
		procesosop.setUsuario(null);

		return procesosop;
	}

	public Rol getRol1() {
		return this.rol1;
	}

	public void setRol1(Rol rol1) {
		this.rol1 = rol1;
	}

	public Rol getRol2() {
		return this.rol2;
	}

	public void setRol2(Rol rol2) {
		this.rol2 = rol2;
	}

}