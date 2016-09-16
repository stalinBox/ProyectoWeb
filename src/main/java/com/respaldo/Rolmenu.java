package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the rolmenu database table.
 * 
 */
@Entity
@NamedQuery(name = "Rolmenu.findAll", query = "SELECT r FROM Rolmenu r")
public class Rolmenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rolmenu_id")
	private Integer rolmenuId;

	// bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	private Menu menu1;

	// bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	private Menu menu2;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "rol_id", insertable = false, updatable = false)
	private Rol rol1;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "rol_id", insertable = false, updatable = false)
	private Rol rol2;

	public Rolmenu() {
	}

	public Integer getRolmenuId() {
		return this.rolmenuId;
	}

	public void setRolmenuId(Integer rolmenuId) {
		this.rolmenuId = rolmenuId;
	}

	public Menu getMenu1() {
		return this.menu1;
	}

	public void setMenu1(Menu menu1) {
		this.menu1 = menu1;
	}

	public Menu getMenu2() {
		return this.menu2;
	}

	public void setMenu2(Menu menu2) {
		this.menu2 = menu2;
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