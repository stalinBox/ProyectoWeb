package testEntities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the costosindi database table.
 * 
 */
@Entity
@NamedQuery(name="Costosindi.findAll", query="SELECT c FROM Costosindi c")
public class Costosindi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="costos_codigo")
	private Integer costosCodigo;

	private double cifpresu;

	private double cifreal;

	private double manobrapresu;

	private double manobrareal;

	//bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name="ordenprod_codigo")
	private Ordenprod ordenprod;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo")
	private Proceso proceso;

	public Costosindi() {
	}

	public Integer getCostosCodigo() {
		return this.costosCodigo;
	}

	public void setCostosCodigo(Integer costosCodigo) {
		this.costosCodigo = costosCodigo;
	}

	public double getCifpresu() {
		return this.cifpresu;
	}

	public void setCifpresu(double cifpresu) {
		this.cifpresu = cifpresu;
	}

	public double getCifreal() {
		return this.cifreal;
	}

	public void setCifreal(double cifreal) {
		this.cifreal = cifreal;
	}

	public double getManobrapresu() {
		return this.manobrapresu;
	}

	public void setManobrapresu(double manobrapresu) {
		this.manobrapresu = manobrapresu;
	}

	public double getManobrareal() {
		return this.manobrareal;
	}

	public void setManobrareal(double manobrareal) {
		this.manobrareal = manobrareal;
	}

	public Ordenprod getOrdenprod() {
		return this.ordenprod;
	}

	public void setOrdenprod(Ordenprod ordenprod) {
		this.ordenprod = ordenprod;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

}