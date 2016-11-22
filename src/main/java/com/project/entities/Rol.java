package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rol_id")
	private Integer rolId;

	@Column(name="rol_descripcion")
	private String rolDescripcion;

	@Column(name="rol_nombre")
	private String rolNombre;

	@Column(name="rol_stado")
	private BigDecimal rolStado;

	//bi-directional many-to-one association to Rolmenu
	@OneToMany(mappedBy="rol1")
	private List<Rolmenu> rolmenus1;

	//bi-directional many-to-one association to Rolmenu
	@OneToMany(mappedBy="rol2")
	private List<Rolmenu> rolmenus2;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="rol1")
	private List<Usuario> usuarios1;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="rol2")
	private List<Usuario> usuarios2;

	public Rol() {
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

	public List<Rolmenu> getRolmenus1() {
		return this.rolmenus1;
	}

	public void setRolmenus1(List<Rolmenu> rolmenus1) {
		this.rolmenus1 = rolmenus1;
	}

	public Rolmenu addRolmenus1(Rolmenu rolmenus1) {
		getRolmenus1().add(rolmenus1);
		rolmenus1.setRol1(this);

		return rolmenus1;
	}

	public Rolmenu removeRolmenus1(Rolmenu rolmenus1) {
		getRolmenus1().remove(rolmenus1);
		rolmenus1.setRol1(null);

		return rolmenus1;
	}

	public List<Rolmenu> getRolmenus2() {
		return this.rolmenus2;
	}

	public void setRolmenus2(List<Rolmenu> rolmenus2) {
		this.rolmenus2 = rolmenus2;
	}

	public Rolmenu addRolmenus2(Rolmenu rolmenus2) {
		getRolmenus2().add(rolmenus2);
		rolmenus2.setRol2(this);

		return rolmenus2;
	}

	public Rolmenu removeRolmenus2(Rolmenu rolmenus2) {
		getRolmenus2().remove(rolmenus2);
		rolmenus2.setRol2(null);

		return rolmenus2;
	}

	public List<Usuario> getUsuarios1() {
		return this.usuarios1;
	}

	public void setUsuarios1(List<Usuario> usuarios1) {
		this.usuarios1 = usuarios1;
	}

	public Usuario addUsuarios1(Usuario usuarios1) {
		getUsuarios1().add(usuarios1);
		usuarios1.setRol1(this);

		return usuarios1;
	}

	public Usuario removeUsuarios1(Usuario usuarios1) {
		getUsuarios1().remove(usuarios1);
		usuarios1.setRol1(null);

		return usuarios1;
	}

	public List<Usuario> getUsuarios2() {
		return this.usuarios2;
	}

	public void setUsuarios2(List<Usuario> usuarios2) {
		this.usuarios2 = usuarios2;
	}

	public Usuario addUsuarios2(Usuario usuarios2) {
		getUsuarios2().add(usuarios2);
		usuarios2.setRol2(this);

		return usuarios2;
	}

	public Usuario removeUsuarios2(Usuario usuarios2) {
		getUsuarios2().remove(usuarios2);
		usuarios2.setRol2(null);

		return usuarios2;
	}

}