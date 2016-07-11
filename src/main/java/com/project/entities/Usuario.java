package com.project.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "Usuario")
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

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="rol_id")
	private Rol rol;

	public Usuario() {
		this.userId = 0;
		this.rol = new Rol();
	}
	
	public Usuario(Rol rol, String userName,String userPasswd, String userEmail,BigDecimal userState,String userCreation, Date userDateCreation){
		this.rol = rol;
		this.userName = userName;
		this.userPasswd = userPasswd;
		this.userEmail = userEmail;
		this.userState = userState;
		this.userCreation = userCreation;
		this.userDateCreation = userDateCreation;
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

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}