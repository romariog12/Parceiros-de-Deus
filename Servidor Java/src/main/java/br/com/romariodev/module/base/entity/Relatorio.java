package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the relatorio database table.
 * 
 */
@Entity
@NamedQuery(name="Relatorio.findAll", query="SELECT r FROM Relatorio r")
public class Relatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRelatorio;

	private String p1;

	//bi-directional many-to-one association to Acompanhamento
	@ManyToOne
	@JoinColumn(name="Acompanhamento_idAcompanhamento", referencedColumnName="idAcompanhamento")
	private Acompanhamento acompanhamento;

	//bi-directional many-to-one association to Estagio
	@ManyToOne
	private Estagio estagio;

	public Relatorio() {
	}

	public int getIdRelatorio() {
		return this.idRelatorio;
	}

	public void setIdRelatorio(int idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

	public String getP1() {
		return this.p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
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