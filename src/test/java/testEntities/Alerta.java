package testEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the alertas database table.
 * 
 */
@Entity
@Table(name="alertas")
@NamedQuery(name="Alerta.findAll", query="SELECT a FROM Alerta a")
public class Alerta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="alerta_codigo")
	private Integer alertaCodigo;

	private String activo;

	private String color;

	private String descripcion;

	//bi-directional many-to-one association to ProcAlerta
	@OneToMany(mappedBy="alerta")
	private List<ProcAlerta> procAlertas;

	public Alerta() {
	}

	public Integer getAlertaCodigo() {
		return this.alertaCodigo;
	}

	public void setAlertaCodigo(Integer alertaCodigo) {
		this.alertaCodigo = alertaCodigo;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ProcAlerta> getProcAlertas() {
		return this.procAlertas;
	}

	public void setProcAlertas(List<ProcAlerta> procAlertas) {
		this.procAlertas = procAlertas;
	}

	public ProcAlerta addProcAlerta(ProcAlerta procAlerta) {
		getProcAlertas().add(procAlerta);
		procAlerta.setAlerta(this);

		return procAlerta;
	}

	public ProcAlerta removeProcAlerta(ProcAlerta procAlerta) {
		getProcAlertas().remove(procAlerta);
		procAlerta.setAlerta(null);

		return procAlerta;
	}

}