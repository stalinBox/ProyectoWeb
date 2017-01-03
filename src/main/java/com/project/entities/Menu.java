package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menu_id")
	private Integer menuId;

	@Column(name="m_id")
	private Integer mId;

	@Column(name="menu_estado")
	private BigDecimal menuEstado;

	@Column(name="menu_icono")
	private String menuIcono;

	@Column(name="menu_nombre")
	private String menuNombre;

	@Column(name="menu_orden")
	private Integer menuOrden;

	@Column(name="menu_url")
	private String menuUrl;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name="menu_nivel")
	private Menu menu;

	//bi-directional many-to-one association to Menu
	@OneToMany(mappedBy="menu")
	private List<Menu> menus;

	//bi-directional many-to-one association to Rolmenu
	@OneToMany(mappedBy="menu")
	private List<Rolmenu> rolmenus;

	public Menu() {
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMId() {
		return this.mId;
	}

	public void setMId(Integer mId) {
		this.mId = mId;
	}

	public BigDecimal getMenuEstado() {
		return this.menuEstado;
	}

	public void setMenuEstado(BigDecimal menuEstado) {
		this.menuEstado = menuEstado;
	}

	public String getMenuIcono() {
		return this.menuIcono;
	}

	public void setMenuIcono(String menuIcono) {
		this.menuIcono = menuIcono;
	}

	public String getMenuNombre() {
		return this.menuNombre;
	}

	public void setMenuNombre(String menuNombre) {
		this.menuNombre = menuNombre;
	}

	public Integer getMenuOrden() {
		return this.menuOrden;
	}

	public void setMenuOrden(Integer menuOrden) {
		this.menuOrden = menuOrden;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Menu addMenus(Menu menus) {
		getMenus().add(menus);
		menus.setMenu(this);

		return menus;
	}

	public Menu removeMenus(Menu menus) {
		getMenus().remove(menus);
		menus.setMenu(null);

		return menus;
	}

	public List<Rolmenu> getRolmenus() {
		return this.rolmenus;
	}

	public void setRolmenus(List<Rolmenu> rolmenus) {
		this.rolmenus = rolmenus;
	}

	public Rolmenu addRolmenus(Rolmenu rolmenus) {
		getRolmenus().add(rolmenus);
		rolmenus.setMenu(this);

		return rolmenus;
	}

	public Rolmenu removeRolmenus(Rolmenu rolmenus) {
		getRolmenus().remove(rolmenus);
		rolmenus.setMenu(null);

		return rolmenus;
	}

}