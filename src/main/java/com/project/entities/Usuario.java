package com.project.entities;

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
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;

	@Column(name="user_creation")
	private String userCreation;

	@Temporal(TemporalType.DATE)
	@Column(name="user_date_creation")
	private Date userDateCreation;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_passwd")
	private String userPasswd;

	@Column(name="user_state")
	private BigDecimal userState;

	@Column(name="user_theme")
	private String userTheme;

	//bi-directional many-to-one association to Ordenprod
	@OneToMany(mappedBy="usuario")
	private List<Ordenprod> ordenprods;

	//bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy="usuario")
	private List<Parametro> parametros;

	//bi-directional many-to-one association to Procesosop
	@OneToMany(mappedBy="usuario")
	private List<Procesosop> procesosops;

	//bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name="lineapro_codigo")
	private Lineasprod lineasprod;

	//bi-directional many-to-one association to Maquina
	@ManyToOne
	@JoinColumn(name="maq_codigo")
	private Maquina maquina;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo")
	private Proceso proceso;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="rol_id")
	private Rol rol;

	//bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name="turno_codigo")
	private Turno turno;

	public Usuario() {
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

	public String getUserTheme() {
		return this.userTheme;
	}

	public void setUserTheme(String userTheme) {
		this.userTheme = userTheme;
	}

	public List<Ordenprod> getOrdenprods() {
		return this.ordenprods;
	}

	public void setOrdenprods(List<Ordenprod> ordenprods) {
		this.ordenprods = ordenprods;
	}

	public Ordenprod addOrdenprod(Ordenprod ordenprod) {
		getOrdenprods().add(ordenprod);
		ordenprod.setUsuario(this);

		return ordenprod;
	}

	public Ordenprod removeOrdenprod(Ordenprod ordenprod) {
		getOrdenprods().remove(ordenprod);
		ordenprod.setUsuario(null);

		return ordenprod;
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

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}