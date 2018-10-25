package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the instituicao database table.
 * 
 */
@Entity
@NamedQuery(name="Instituicao.findAll", query="SELECT i FROM Instituicao i")
public class Instituicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idInstituicao;

	private String endereco;

	private String responsavelEstagio;

	//bi-directional many-to-one association to Estagio
	@OneToMany(mappedBy="instituicao")
	private List<Estagio> estagios;

	//bi-directional many-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="Usuario_idUsuario", referencedColumnName="idUsuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Orientador
	@OneToMany(mappedBy="instituicao")
	private List<Orientador> orientadors;

	public Instituicao() {
	}

	public int getIdInstituicao() {
		return this.idInstituicao;
	}

	public void setIdInstituicao(int idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getResponsavelEstagio() {
		return this.responsavelEstagio;
	}

	public void setResponsavelEstagio(String responsavelEstagio) {
		this.responsavelEstagio = responsavelEstagio;
	}
	public List<Estagio> getEstagios() {
		return this.estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public Estagio addEstagio(Estagio estagio) {
		getEstagios().add(estagio);
		estagio.setInstituicao(this);

		return estagio;
	}

	public Estagio removeEstagio(Estagio estagio) {
		getEstagios().remove(estagio);
		estagio.setInstituicao(null);

		return estagio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Orientador> getOrientadors() {
		return this.orientadors;
	}

	public void setOrientadors(List<Orientador> orientadors) {
		this.orientadors = orientadors;
	}

	public Orientador addOrientador(Orientador orientador) {
		getOrientadors().add(orientador);
		orientador.setInstituicao(this);

		return orientador;
	}

	public Orientador removeOrientador(Orientador orientador) {
		getOrientadors().remove(orientador);
		orientador.setInstituicao(null);

		return orientador;
	}

}