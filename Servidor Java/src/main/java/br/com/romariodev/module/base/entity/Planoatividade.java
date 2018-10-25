package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;



/**
 * The persistent class for the planoatividade database table.
 * 
 */
@Entity
@NamedQuery(name="Planoatividade.findAll", query="SELECT p FROM Planoatividade p")
public class Planoatividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPlanoAtividade;

	private Blob atividade;

	//bi-directional many-to-one association to Acompanhamento
	@ManyToOne
	@JoinColumn(name="Acompanhamento_idAcompanhamento", referencedColumnName="idAcompanhamento")
	private Acompanhamento acompanhamento;

	//bi-directional many-to-one association to Estagio
	@ManyToOne
	private Estagio estagio;

	public Planoatividade() {
	}

	public int getIdPlanoAtividade() {
		return this.idPlanoAtividade;
	}

	public void setIdPlanoAtividade(int idPlanoAtividade) {
		this.idPlanoAtividade = idPlanoAtividade;
	}

	public Blob getAtividade() {
		return this.atividade;
	}

	public void setAtividade(Blob atividade) {
		this.atividade = atividade;
	}

	public Acompanhamento getAcompanhamento() {
		return this.acompanhamento;
	}

	public void setAcompanhamento(Acompanhamento acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	public Estagio getEstagio() {
		return this.estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

}