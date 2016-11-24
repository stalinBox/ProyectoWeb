package testEntities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the maquinas database table.
 * 
 */
@Entity
@Table(name = "maquinas")
@NamedQuery(name = "Maquina.findAll", query = "SELECT m FROM Maquina m")
public class Maquina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maq_codigo")
	private Integer maqCodigo;

	private String marca;

	private String nommaquina;

	// bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name = "lineapro_codigo", nullable = false, insertable = false, updatable = false)
	private Lineasprod lineasprod;

	public Maquina() {
	}

	public Integer getMaqCodigo() {
		return this.maqCodigo;
	}

	public void setMaqCodigo(Integer maqCodigo) {
		this.maqCodigo = maqCodigo;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNommaquina() {
		return this.nommaquina;
	}

	public void setNommaquina(String nommaquina) {
		this.nommaquina = nommaquina;
	}

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
	}

}