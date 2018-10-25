package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the estagio database table.
 * 
 */
@Entity
@NamedQuery(name="Estagio.findAll", query="SELECT e FROM Estagio e")
public class Estagio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEstagio;

	@Temporal(TemporalType.DATE)
	private Date recisao;

	//bi-directional many-to-one association to Acompanhamento
	@OneToMany(mappedBy="estagio")
	private List<Acompanhamento> acompanhamentos;

	//bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy="estagio")
	private List<Contrato> contratos;

	//bi-directional many-to-one association to Aluno
	@ManyToOne
	@JoinColumn(name="Aluno_idAluno", referencedColumnName="idAluno")
	private Aluno aluno;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="Empresa_idEmpresa", referencedColumnName="idEmpresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Instituicao
	@ManyToOne
	@JoinColumn(name="Instituicao_idInstituicao", referencedColumnName="idInstituicao")
	private Instituicao instituicao;

	//bi-directional many-to-one association to Planoatividade
	@OneToMany(mappedBy="estagio")
	private List<Planoatividade> planoatividades;

	//bi-directional many-to-one association to Relatorio
	@OneToMany(mappedBy="estagio")
	private List<Relatorio> relatorios;

	public Estagio() {
	}
	public int getIdEstagio() {
		return this.idEstagio;
	}

	public void setIdEstagio(int idEstagio) {
		this.idEstagio = idEstagio;
	}

	public Date getRecisao() {
		return this.recisao;
	}

	public void setRecisao(Date recisao) {
		this.recisao = recisao;
	}

	public List<Acompanhamento> getAcompanhamentos() {
		return this.acompanhamentos;
	}

	public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}

	public Acompanhamento addAcompanhamento(Acompanhamento acompanhamento) {
		getAcompanhamentos().add(acompanhamento);
		acompanhamento.setEstagio(this);

		return acompanhamento;
	}

	public Acompanhamento removeAcompanhamento(Acompanhamento acompanhamento) {
		getAcompanhamentos().remove(acompanhamento);
		acompanhamento.setEstagio(null);

		return acompanhamento;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Contrato addContrato(Contrato contrato) {
		getContratos().add(contrato);
		contrato.setEstagio(this);

		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setEstagio(null);

		return contrato;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Instituicao getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public List<Planoatividade> getPlanoatividades() {
		return this.planoatividades;
	}

	public void setPlanoatividades(List<Planoatividade> planoatividades) {
		this.planoatividades = planoatividades;
	}

	public Planoatividade addPlanoatividade(Planoatividade planoatividade) {
		getPlanoatividades().add(planoatividade);
		planoatividade.setEstagio(this);

		return planoatividade;
	}

	public Planoatividade removePlanoatividade(Planoatividade planoatividade) {
		getPlanoatividades().remove(planoatividade);
		planoatividade.setEstagio(null);

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
		relatorio.setEstagio(this);

		return relatorio;
	}

	public Relatorio removeRelatorio(Relatorio relatorio) {
		getRelatorios().remove(relatorio);
		relatorio.setEstagio(null);

		return relatorio;
	}

}