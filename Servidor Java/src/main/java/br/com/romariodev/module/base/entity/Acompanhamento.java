package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the acompanhamento database table.
 * 
 */
@Entity
@NamedQuery(name="Acompanhamento.findAll", query="SELECT a FROM Acompanhamento a")
public class Acompanhamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAcompanhamento;

	@Temporal(TemporalType.DATE)
	private Date fim;

	@Temporal(TemporalType.DATE)
	private Date inicio;

	private byte situacao;

	//bi-directional many-to-one association to Estagio
	@ManyToOne
	private Estagio estagio;

	//bi-directional many-to-one association to Orientador
	@ManyToOne
	private Orientador orientador;

	//bi-directional many-to-one association to Supervisor
	@ManyToOne
	private Supervisor supervisor;

	//bi-directional many-to-one association to Planoatividade
	@OneToMany(mappedBy="acompanhamento")
	private List<Planoatividade> planoatividades;

	//bi-directional many-to-one association to Relatorio
	@OneToMany(mappedBy="acompanhamento")
	private List<Relatorio> relatorios;

	public Acompanhamento() {
	}

	

	public int getIdAcompanhamento() {
		return idAcompanhamento;
	}

	public void setIdAcompanhamento(int idAcompanhamento) {
		this.idAcompanhamento = idAcompanhamento;
	}

	public Date getFim() {
		return this.fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public byte getSituacao() {
		return this.situacao;
	}

	public void setSituacao(byte situacao) {
		this.situacao = situacao;
	}

	public Estagio getEstagio() {
		return this.estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public Orientador getOrientador() {
		return this.orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	public Supervisor getSupervisor() {
		return this.supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public List<Planoatividade> getPlanoatividades() {
		return this.planoatividades;
	}

	public void setPlanoatividades(List<Planoatividade> planoatividades) {
		this.planoatividades = planoatividades;
	}

	public Planoatividade addPlanoatividade(Planoatividade planoatividade) {
		getPlanoatividades().add(planoatividade);
		planoatividade.setAcompanhamento(this);

		return planoatividade;
	}

	public Planoatividade removePlanoatividade(Planoatividade planoatividade) {
		getPlanoatividades().remove(planoatividade);
		planoatividade.setAcompanhamento(null);

		return planoatividade;
	}

	public List<Relatorio> getRelatorios() {
		return this.relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public Relatorio addRelatorio(Relatorio relatorio) {
		getRelatorios().add(relatorio);
		relatorio.setAcompanhamento(this);

		return relatorio;
	}

	public Relatorio removeRelatorio(Relatorio relatorio) {
		getRelatorios().remove(relatorio);
		relatorio.setAcompanhamento(null);

		return relatorio;
	}

}