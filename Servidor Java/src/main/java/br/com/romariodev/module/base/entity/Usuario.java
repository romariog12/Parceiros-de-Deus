package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario; 
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="login", nullable = false)
	private String login;
	
	@Column(name="nome", nullable = false)
	private String nome;
    
	@Column(name="senha", nullable = false)
	private String senha;

	@Column(name="telefone", nullable = false)
	private String telefone;

	//bi-directional many-to-one association to Aluno
	@OneToOne(mappedBy="usuario")
	private Aluno aluno;

	//bi-directional many-to-one association to Empresa
	@OneToOne(mappedBy="usuario")
	private Empresa empresa;

	//bi-directional many-to-one association to Instituicao
	@OneToOne(mappedBy="usuario")
	private Instituicao instituicaos;

	//bi-directional many-to-one association to Orientador
	@OneToOne(mappedBy="usuario")
	private Orientador orientador;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}