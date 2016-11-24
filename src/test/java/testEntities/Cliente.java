package testEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name = "clientes")
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cliente")
	private Integer codCliente;

	private String apellidocli;

	private String descripcioncli;

	private String direccion;

	private String nombrecli;

	private String telefono;

	// bi-directional many-to-one association to Ordenprod
	@OneToMany(mappedBy = "cliente")
	private List<Ordenprod> ordenprods;

	public Cliente() {
	}

	public Integer getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getApellidocli() {
		return this.apellidocli;
	}

	public void setApellidocli(String apellidocli) {
		this.apellidocli = apellidocli;
	}

	public String getDescripcioncli() {
		return this.descripcioncli;
	}

	public void setDescripcioncli(String descripcioncli) {
		this.descripcioncli = descripcioncli;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombrecli() {
		return this.nombrecli;
	}

	public void setNombrecli(String nombrecli) {
		this.nombrecli = nombrecli;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Ordenprod> getOrdenprods() {
		return this.ordenprods;
	}

	public void setOrdenprods(List<Ordenprod> ordenprods) {
		this.ordenprods = ordenprods;
	}

	public Ordenprod addOrdenprod(Ordenprod ordenprod) {
		getOrdenprods().add(ordenprod);
		ordenprod.setCliente(this);

		return ordenprod;
	}

	public Ordenprod removeOrdenprod(Ordenprod ordenprod) {
		getOrdenprods().remove(ordenprod);
		ordenprod.setCliente(null);

		return ordenprod;
	}

}