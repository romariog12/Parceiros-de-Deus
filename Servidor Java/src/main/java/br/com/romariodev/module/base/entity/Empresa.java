package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * @author romario.portela
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEmpresa;

	private String cnpj;

	private String endereco;

	//bi-directional many-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="Usuario_idUsuario", referencedColumnName="idUsuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Estagio
	@OneToMany(mappedBy="empresa")
	private List<Estagio> estagios;

	//bi-directional many-to-one association to Supervisor
	@OneToMany(mappedBy="empresa")
	private List<Supervisor> supervisors;

	public Empresa() {
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Estagio> getEstagios() {
		return this.estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public Estagio addEstagio(Estagio estagio) {
		getEstagios().add(estagio);
		estagio.setEmpresa(this);

		return estagio;
	}

	public Estagio removeEstagio(Estagio estagio) {
		getEstagios().remove(estagio);
		estagio.setEmpresa(null);

		return estagio;
	}

	public List<Supervisor> getSupervisors() {
		return this.supervisors;
	}

	public void setSupervisors(List<Supervisor> supervisors) {
		this.supervisors = supervisors;
	}

	public Supervisor addSupervisor(Supervisor supervisor) {
		getSupervisors().add(supervisor);
		supervisor.setEmpresa(this);

		return supervisor;
	}

	public Supervisor removeSupervisor(Supervisor supervisor) {
		getSupervisors().remove(supervisor);
		supervisor.setEmpresa(null);

		return supervisor;
	}

}