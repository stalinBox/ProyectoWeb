package testEntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the modelos database table.
 * 
 */
@Entity
@Table(name = "modelos")
@NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m")
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mod_codigo")
	private Integer modCodigo;

	@Column(name = "mod_cod_mod")
	private String modCodMod;

	@Column(name = "mod_nombre")
	private String modNombre;

	@Column(name = "mod_piezas")
	private Integer modPiezas;

	// bi-directional many-to-one association to Confproceso
	@OneToMany(mappedBy = "modelo")
	private List<Confproceso> confprocesos;

	// bi-directional many-to-one association to Detalleorden
	@OneToMany(mappedBy = "modelo")
	private List<Detalleorden> detalleordens;

	// bi-directional many-to-one association to ModTrqTal
	@OneToMany(mappedBy = "modelo")
	private List<ModTrqTal> modTrqTals;

	// bi-directional many-to-one association to Programturno
	@OneToMany(mappedBy = "modelo")
	private List<Programturno> programturnos;

	public Modelo() {
	}

	public Integer getModCodigo() {
		return this.modCodigo;
	}

	public void setModCodigo(Integer modCodigo) {
		this.modCodigo = modCodigo;
	}

	public String getModCodMod() {
		return this.modCodMod;
	}

	public void setModCodMod(String modCodMod) {
		this.modCodMod = modCodMod;
	}

	public String getModNombre() {
		return this.modNombre;
	}

	public void setModNombre(String modNombre) {
		this.modNombre = modNombre;
	}

	public Integer getModPiezas() {
		return this.modPiezas;
	}

	public void setModPiezas(Integer modPiezas) {
		this.modPiezas = modPiezas;
	}

	public List<Confproceso> getConfprocesos() {
		return this.confprocesos;
	}

	public void setConfprocesos(List<Confproceso> confprocesos) {
		this.confprocesos = confprocesos;
	}

	public Confproceso addConfproceso(Confproceso confproceso) {
		getConfprocesos().add(confproceso);
		confproceso.setModelo(this);

		return confproceso;
	}

	public Confproceso removeConfproceso(Confproceso confproceso) {
		getConfprocesos().remove(confproceso);
		confproceso.setModelo(null);

		return confproceso;
	}

	public List<Detalleorden> getDetalleordens() {
		return this.detalleordens;
	}

	public void setDetalleordens(List<Detalleorden> detalleordens) {
		this.detalleordens = detalleordens;
	}

	public Detalleorden addDetalleorden(Detalleorden detalleorden) {
		getDetalleordens().add(detalleorden);
		detalleorden.setModelo(this);

		return detalleorden;
	}

	public Detalleorden removeDetalleorden(Detalleorden detalleorden) {
		getDetalleordens().remove(detalleorden);
		detalleorden.setModelo(null);

		return detalleorden;
	}

	public List<ModTrqTal> getModTrqTals() {
		return this.modTrqTals;
	}

	public void setModTrqTals(List<ModTrqTal> modTrqTals) {
		this.modTrqTals = modTrqTals;
	}

	public ModTrqTal addModTrqTal(ModTrqTal modTrqTal) {
		getModTrqTals().add(modTrqTal);
		modTrqTal.setModelo(this);

		return modTrqTal;
	}

	public ModTrqTal removeModTrqTal(ModTrqTal modTrqTal) {
		getModTrqTals().remove(modTrqTal);
		modTrqTal.setModelo(null);

		return modTrqTal;
	}

	public List<Programturno> getProgramturnos() {
		return this.programturnos;
	}

	public void setProgramturnos(List<Programturno> programturnos) {
		this.programturnos = programturnos;
	}

	public Programturno addProgramturno(Programturno programturno) {
		getProgramturnos().add(programturno);
		programturno.setModelo(this);

		return programturno;
	}

	public Programturno removeProgramturno(Programturno programturno) {
		getProgramturnos().remove(programturno);
		programturno.setModelo(null);

		return programturno;
	}

}