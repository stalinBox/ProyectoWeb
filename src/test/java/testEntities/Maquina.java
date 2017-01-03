package testEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the maquinas database table.
 * 
 */
@Entity
@Table(name="maquinas")
@NamedQuery(name="Maquina.findAll", query="SELECT m FROM Maquina m")
public class Maquina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="maq_codigo")
	private Integer maqCodigo;

	private String descmaq;

	private String marca;

	private String nommaquina;

	//bi-directional many-to-one association to Dispositivo
	@OneToMany(mappedBy="maquina")
	private List<Dispositivo> dispositivos;

	//bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name="lineapro_codigo")
	private Lineasprod lineasprod;

	public Maquina() {
	}

	public Integer getMaqCodigo() {
		return this.maqCodigo;
	}

	public void setMaqCodigo(Integer maqCodigo) {
		this.maqCodigo = maqCodigo;
	}

	public String getDescmaq() {
		return this.descmaq;
	}

	public void setDescmaq(String descmaq) {
		this.descmaq = descmaq;
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

	public List<Dispositivo> getDispositivos() {
		return this.dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public Dispositivo addDispositivo(Dispositivo dispositivo) {
		getDispositivos().add(dispositivo);
		dispositivo.setMaquina(this);

		return dispositivo;
	}

	public Dispositivo removeDispositivo(Dispositivo dispositivo) {
		getDispositivos().remove(dispositivo);
		dispositivo.setMaquina(null);

		return dispositivo;
	}

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
	}

}