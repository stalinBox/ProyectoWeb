package testEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the procesosop database table.
 * 
 */
@Entity
@NamedQuery(name="Procesosop.findAll", query="SELECT p FROM Procesosop p")
public class Procesosop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="processop_cod")
	private Integer processopCod;

	@Temporal(TemporalType.DATE)
	@Column(name="f_actual")
	private Date fActual;

	@Temporal(TemporalType.DATE)
	@Column(name="f_estim")
	private Date fEstim;

	//bi-directional many-to-one association to Lugare
	@ManyToOne
	@JoinColumn(name="lugar_codigo_dest", insertable = false, updatable = false)
	private Lugare lugare1;

	//bi-directional many-to-one association to Lugare
	@ManyToOne
	@JoinColumn(name="lugar_codigo_orig", insertable = false, updatable = false)
	private Lugare lugare2;

	//bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name="ordenprod_codigo", insertable = false, updatable = false)
	private Ordenprod ordenprod;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo", insertable = false, updatable = false)
	private Proceso proceso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="user_id_resp", insertable = false, updatable = false)
	private Usuario usuario;

	//bi-directional many-to-one association to Programturno
	@OneToMany(mappedBy="procesosop")
	private List<Programturno> programturnos;

	public Procesosop() {
	}

	public Integer getProcessopCod() {
		return this.processopCod;
	}

	public void setProcessopCod(Integer processopCod) {
		this.processopCod = processopCod;
	}

	public Date getFActual() {
		return this.fActual;
	}

	public void setFActual(Date fActual) {
		this.fActual = fActual;
	}

	public Date getFEstim() {
		return this.fEstim;
	}

	public void setFEstim(Date fEstim) {
		this.fEstim = fEstim;
	}

	public Lugare getLugare1() {
		return this.lugare1;
	}

	public void setLugare1(Lugare lugare1) {
		this.lugare1 = lugare1;
	}

	public Lugare getLugare2() {
		return this.lugare2;
	}

	public void setLugare2(Lugare lugare2) {
		this.lugare2 = lugare2;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Programturno> getProgramturnos() {
		return this.programturnos;
	}

	public void setProgramturnos(List<Programturno> programturnos) {
		this.programturnos = programturnos;
	}

	public Programturno addProgramturno(Programturno programturno) {
		getProgramturnos().add(programturno);
		programturno.setProcesosop(this);

		return programturno;
	}

	public Programturno removeProgramturno(Programturno programturno) {
		getProgramturnos().remove(programturno);
		programturno.setProcesosop(null);

		return programturno;
	}

}