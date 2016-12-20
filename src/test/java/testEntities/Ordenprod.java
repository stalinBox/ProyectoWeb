package testEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the ordenprod database table.
 * 
 */
@Entity
@NamedQuery(name = "Ordenprod.findAll", query = "SELECT o FROM Ordenprod o")
public class Ordenprod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordenprod_codigo")
	private Integer ordenprodCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "f_actual")
	private Date fActual;

	@Temporal(TemporalType.DATE)
	@Column(name = "f_estim")
	private Date fEstim;

	@Temporal(TemporalType.DATE)
	@Column(name = "f_final")
	private Date fFinal;

	// bi-directional many-to-one association to Costosindi
	@OneToMany(mappedBy = "ordenprod")
	private List<Costosindi> costosindis;

	// bi-directional many-to-one association to Detalleorden
	@OneToMany(mappedBy = "ordenprod")
	private List<Detalleorden> detalleordens;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "user_id_soli", insertable = false, updatable = false)
	private Cliente cliente;

	// bi-directional many-to-one association to Lugare
	@ManyToOne
	@JoinColumn(name = "lugar_codigo_dest", insertable = false, updatable = false)
	private Lugare lugare;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "user_id_resp", insertable = false, updatable = false)
	private Usuario usuario1;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "user_id_soli", insertable = false, updatable = false)
	private Usuario usuario2;

	// bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy = "ordenprod")
	private List<Parametro> parametros;

	// bi-directional many-to-one association to Procesosop
	@OneToMany(mappedBy = "ordenprod")
	private List<Procesosop> procesosops;

	public Ordenprod() {
	}

	public Integer getOrdenprodCodigo() {
		return this.ordenprodCodigo;
	}

	public void setOrdenprodCodigo(Integer ordenprodCodigo) {
		this.ordenprodCodigo = ordenprodCodigo;
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

	public Date getFFinal() {
		return this.fFinal;
	}

	public void setFFinal(Date fFinal) {
		this.fFinal = fFinal;
	}

	public List<Costosindi> getCostosindis() {
		return this.costosindis;
	}

	public void setCostosindis(List<Costosindi> costosindis) {
		this.costosindis = costosindis;
	}

	public Costosindi addCostosindi(Costosindi costosindi) {
		getCostosindis().add(costosindi);
		costosindi.setOrdenprod(this);

		return costosindi;
	}

	public Costosindi removeCostosindi(Costosindi costosindi) {
		getCostosindis().remove(costosindi);
		costosindi.setOrdenprod(null);

		return costosindi;
	}

	public List<Detalleorden> getDetalleordens() {
		return this.detalleordens;
	}

	public void setDetalleordens(List<Detalleorden> detalleordens) {
		this.detalleordens = detalleordens;
	}

	public Detalleorden addDetalleorden(Detalleorden detalleorden) {
		getDetalleordens().add(detalleorden);
		detalleorden.setOrdenprod(this);

		return detalleorden;
	}

	public Detalleorden removeDetalleorden(Detalleorden detalleorden) {
		getDetalleordens().remove(detalleorden);
		detalleorden.setOrdenprod(null);

		return detalleorden;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Lugare getLugare() {
		return this.lugare;
	}

	public void setLugare(Lugare lugare) {
		this.lugare = lugare;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public List<Parametro> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getParametros().add(parametro);
		parametro.setOrdenprod(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setOrdenprod(null);

		return parametro;
	}

	public List<Procesosop> getProcesosops() {
		return this.procesosops;
	}

	public void setProcesosops(List<Procesosop> procesosops) {
		this.procesosops = procesosops;
	}

	public Procesosop addProcesosop(Procesosop procesosop) {
		getProcesosops().add(procesosop);
		procesosop.setOrdenprod(this);

		return procesosop;
	}

	public Procesosop removeProcesosop(Procesosop procesosop) {
		getProcesosops().remove(procesosop);
		procesosop.setOrdenprod(null);

		return procesosop;
	}

}