package testEntities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dispositivos database table.
 * 
 */
@Entity
@Table(name="dispositivos")
@NamedQuery(name="Dispositivo.findAll", query="SELECT d FROM Dispositivo d")
public class Dispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dispositivo")
	private Integer idDispositivo;

	@Column(name="mac_dispositivo")
	private String macDispositivo;

	//bi-directional many-to-one association to Maquina
	@ManyToOne
	@JoinColumn(name="id_maquinas_ref")
	private Maquina maquina;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo_ref")
	private Proceso proceso;

	public Dispositivo() {
	}

	public Integer getIdDispositivo() {
		return this.idDispositivo;
	}

	public void setIdDispositivo(Integer idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getMacDispositivo() {
		return this.macDispositivo;
	}

	public void setMacDispositivo(String macDispositivo) {
		this.macDispositivo = macDispositivo;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

}