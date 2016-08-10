package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Rol")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id")
	private Integer rolId;

	@Column(name = "rol_descripcion")
	private String rolDescripcion;

	@Column(name = "rol_nombre")
	private String rolNombre;

	@Column(name = "rol_stado")
	private BigDecimal rolStado;

	// bi-directional many-to-one association to Rolmenu
	@OneToMany(mappedBy = "rol")
	private List<Rolmenu> rolmenus;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "rol")
	private List<Usuario> usuarios;

	public Rol() {
		this.rolId = 0;
	}

	public Integer getRolId() {
		return this.rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public String getRolDescripcion() {
		return this.rolDescripcion;
	}

	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}

	public String getRolNombre() {
		return this.rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public BigDecimal getRolStado() {
		return this.rolStado;
	}

	public void setRolStado(BigDecimal rolStado) {
		this.rolStado = rolStado;
	}

	public List<Rolmenu> getRolmenus() {
		return this.rolmenus;
	}

	public void setRolmenus(List<Rolmenu> rolmenus) {
		this.rolmenus = rolmenus;
	}

	public Rolmenu addRolmenus(Rolmenu rolmenus) {
		getRolmenus().add(rolmenus);
		rolmenus.setRol(this);

		return rolmenus;
	}

	public Rolmenu removeRolmenus(Rolmenu rolmenus) {
		getRolmenus().remove(rolmenus);
		rolmenus.setRol(null);

		return rolmenus;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRol(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRol(null);

		return usuario;
	}

}