package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rolmenu database table.
 * 
 */
@Entity
@NamedQuery(name="Rolmenu.findAll", query="SELECT r FROM Rolmenu r")
public class Rolmenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rolmenu_id")
	private Integer rolmenuId;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name="menu_id")
	private Menu menu;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="rol_id")
	private Rol rol;

	public Rolmenu() {
	}

	public Integer getRolmenuId() {
		return this.rolmenuId;
	}

	public void setRolmenuId(Integer rolmenuId) {
		this.rolmenuId = rolmenuId;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}