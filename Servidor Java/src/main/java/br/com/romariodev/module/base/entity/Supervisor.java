package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the supervisor database table.
 * 
 */
@Entity
@NamedQuery(name="Supervisor.findAll", query="SELECT s FROM Supervisor s")
public class Supervisor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSupervisor;

	private String registro;

	//bi-directional many-to-one association to Acompanhamento
	@OneToMany(mappedBy="supervisor")
	private List<Acompanhamento> acompanhamentos;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="Empresa_idEmpresa", referencedColumnName="idEmpresa")
	private Empresa empresa;

	public Supervisor() {
	}

	public int getIdSupervisor() {
		return this.idSupervisor;
	}

	public void setIdSupervisor(int idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

	public String getRegistro() {
		return this.registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public List<Acompanhamento> getAcompanhamentos() {
		return this.acompanhamentos;
	}

	public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}

	public Acompanhamento addAcompanhamento(Acompanhamento acompanhamento) {
		getAcompanhamentos().add(acompanhamento);
		acompanhamento.setSupervisor(this);

		return acompanhamento;
	}

	public Acompanhamento removeAcompanhamento(Acompanhamento acompanhamento) {
		getAcompanhamentos().remove(acompanhamento);
		acompanhamento.setSupervisor(null);

		return acompanhamento;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}