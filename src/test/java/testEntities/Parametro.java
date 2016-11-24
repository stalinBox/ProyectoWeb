package testEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the parametros database table.
 * 
 */
@Entity
@Table(name="parametros")
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="param_codigo")
	private Integer paramCodigo;

	private Integer standauto;

	private Integer standconv;

	//bi-directional many-to-one association to Lineasturno
	@ManyToOne
	@JoinColumn(name="ltcodigo", nullable = false, insertable = false, updatable = false)
	private Lineasturno lineasturno;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo", nullable = false, insertable = false, updatable = false)
	private Proceso proceso;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="user_id_resp", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;

	//bi-directional many-to-one association to Programdia
	@OneToMany(mappedBy="parametro")
	private List<Programdia> programdias;

	public Parametro() {
	}

	public Integer getParamCodigo() {
		return this.paramCodigo;
	}

	public void setParamCodigo(Integer paramCodigo) {
		this.paramCodigo = paramCodigo;
	}

	public Integer getStandauto() {
		return this.standauto;
	}

	public void setStandauto(Integer standauto) {
		this.standauto = standauto;
	}

	public Integer getStandconv() {
		return this.standconv;
	}

	public void setStandconv(Integer standconv) {
		this.standconv = standconv;
	}

	public Lineasturno getLineasturno() {
		return this.lineasturno;
	}

	public void setLineasturno(Lineasturno lineasturno) {
		this.lineasturno = lineasturno;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Programdia> getProgramdias() {
		return this.programdias;
	}

	public void setProgramdias(List<Programdia> programdias) {
		this.programdias = programdias;
	}

	public Programdia addProgramdia(Programdia programdia) {
		getProgramdias().add(programdia);
		programdia.setParametro(this);

		return programdia;
	}

	public Programdia removeProgramdia(Programdia programdia) {
		getProgramdias().remove(programdia);
		programdia.setParametro(null);

		return programdia;
	}

}