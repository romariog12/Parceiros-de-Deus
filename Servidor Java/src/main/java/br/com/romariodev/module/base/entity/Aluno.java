package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aluno database table.
 * 
 */
@Entity
@NamedQuery(name="Aluno.findAll", query="SELECT a FROM Aluno a")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAluno; 

	private String cpf;

	private String curso;

	private String matricula;

	//bi-directional many-to-one association to Instituicao
	@OneToOne
	@JoinColumn(name="Instituicao_idInstituicao", referencedColumnName="idInstituicao")
	private Instituicao instituicao;

	//bi-directional many-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="Usuario_idUsuario", referencedColumnName="idUsuario")
	private Usuario usuario;
	public Aluno() {
	}

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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