package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orientador database table.
 * 
 */
@Entity
@NamedQuery(name="Orientador.findAll", query="SELECT o FROM Orientador o")
public class Orientador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrientador;

	private String matricula;

	//bi-directional many-to-one association to Acompanhamento
	@OneToMany(mappedBy="orientador")
	private List<Acompanhamento> acompanhamentos;

	//bi-directional many-to-one association to Instituicao
	@ManyToOne
	@JoinColumn(name="Instituicao_idInstituicao", referencedColumnName="idInstituicao")
	private Instituicao instituicao;

	//bi-directional many-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="Usuario_idUsuario", referencedColumnName="idUsuario")
	private Usuario usuario;

	public Orientador() {
	}

	public int getIdOrientador() {
		return this.idOrientador;
	}

	public void setIdOrientador(int idOrientador) {
		this.idOrientador = idOrientador;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Acompanhamento> getAcompanhamentos() {
		return this.acompanhamentos;
	}

	public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}

	public Acompanhamento addAcompanhamento(Acompanhamento acompanhamento) {
		getAcompanhamentos().add(acompanhamento);
		acompanhamento.setOrientador(this);

		return acompanhamento;
	}

	public Acompanhamento removeAcompanhamento(Acompanhamento acompanhamento) {
		getAcompanhamentos().remove(acompanhamento);
		acompanhamento.setOrientador(null);

		return acompanhamento;
	}

	public Instituicao getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}