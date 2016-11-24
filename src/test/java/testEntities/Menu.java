package testEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id")
	private Integer menuId;

	@Column(name = "menu_estado")
	private BigDecimal menuEstado;

	@Column(name = "menu_icono")
	private String menuIcono;

	@Column(name = "menu_nivel")
	private Integer menuNivel;

	@Column(name = "menu_nombre")
	private String menuNombre;

	@Column(name = "menu_orden")
	private Integer menuOrden;

	@Column(name = "menu_url")
	private String menuUrl;

	// bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name = "m_id", nullable = false, insertable = false, updatable = false)
	private Menu menu1;

	// bi-directional many-to-one association to Menu
	@OneToMany(mappedBy = "menu1")
	private List<Menu> menus1;

	// bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name = "m_id", nullable = false, insertable = false, updatable = false)
	private Menu menu2;

	// bi-directional many-to-one association to Menu
	@OneToMany(mappedBy = "menu2")
	private List<Menu> menus2;

	// bi-directional many-to-one association to Rolmenu
	@OneToMany(mappedBy = "menu1")
	private List<Rolmenu> rolmenus1;

	// bi-directional many-to-one association to Rolmenu
	@OneToMany(mappedBy = "menu2")
	private List<Rolmenu> rolmenus2;

	public Menu() {
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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

	public Integer getMenuNivel() {
		return this.menuNivel;
	}

	public void setMenuNivel(Integer menuNivel) {
		this.menuNivel = menuNivel;
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

	public Menu getMenu1() {
		return this.menu1;
	}

	public void setMenu1(Menu menu1) {
		this.menu1 = menu1;
	}

	public List<Menu> getMenus1() {
		return this.menus1;
	}

	public void setMenus1(List<Menu> menus1) {
		this.menus1 = menus1;
	}

	public Menu addMenus1(Menu menus1) {
		getMenus1().add(menus1);
		menus1.setMenu1(this);

		return menus1;
	}

	public Menu removeMenus1(Menu menus1) {
		getMenus1().remove(menus1);
		menus1.setMenu1(null);

		return menus1;
	}

	public Menu getMenu2() {
		return this.menu2;
	}

	public void setMenu2(Menu menu2) {
		this.menu2 = menu2;
	}

	public List<Menu> getMenus2() {
		return this.menus2;
	}

	public void setMenus2(List<Menu> menus2) {
		this.menus2 = menus2;
	}

	public Menu addMenus2(Menu menus2) {
		getMenus2().add(menus2);
		menus2.setMenu2(this);

		return menus2;
	}

	public Menu removeMenus2(Menu menus2) {
		getMenus2().remove(menus2);
		menus2.setMenu2(null);

		return menus2;
	}

	public List<Rolmenu> getRolmenus1() {
		return this.rolmenus1;
	}

	public void setRolmenus1(List<Rolmenu> rolmenus1) {
		this.rolmenus1 = rolmenus1;
	}

	public Rolmenu addRolmenus1(Rolmenu rolmenus1) {
		getRolmenus1().add(rolmenus1);
		rolmenus1.setMenu1(this);

		return rolmenus1;
	}

	public Rolmenu removeRolmenus1(Rolmenu rolmenus1) {
		getRolmenus1().remove(rolmenus1);
		rolmenus1.setMenu1(null);

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
		rolmenus2.setMenu2(this);

		return rolmenus2;
	}

	public Rolmenu removeRolmenus2(Rolmenu rolmenus2) {
		getRolmenus2().remove(rolmenus2);
		rolmenus2.setMenu2(null);

		return rolmenus2;
	}

}