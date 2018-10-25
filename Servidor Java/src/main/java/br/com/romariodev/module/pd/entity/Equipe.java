package br.com.romariodev.module.pd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Equipe
 *
 */
@Entity
public class Equipe implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEquipe;
	private String nome;
	@ManyToOne
	@JoinColumn(name="lider_idLider", referencedColumnName="idLider")
	private Lider lider;
	@OneToMany(mappedBy="equipe")
	private List<Sub> subs;
	private int status;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Equipe () {
		
	}
	
	public int getIdEquipe() {
		return idEquipe;
	}
	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Lider getLider() {
		return lider;
	}
	public void setLider(Lider lider) {
		this.lider = lider;
	}

	public List<Sub> getSubs() {
		return subs;
	}

	public void setSubs(List<Sub> subs) {
		this.subs = subs;
	}
	
}
