package br.com.romariodev.module.pd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Sub
 *
 */
@Entity
public class Sub implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSub;
	@Column
	private String nome;
	@OneToOne
	@JoinColumn(name="lider_idlider", referencedColumnName="idlider")
	private Lider lider;
	@ManyToOne
	private Equipe equipe;
	@ManyToOne
	private Sub sub;
	@OneToMany(mappedBy="sub")
	private List<Sub> subs;
	private int status;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Sub(){
	}
	
	public List<Sub> getSubs() {
		return subs;
	}

	public void setSubs(List<Sub> subs) {
		this.subs = subs;
	}

	public int getIdSub() {
		return idSub;
	}
	public void setIdSub(int idSub) {
		this.idSub = idSub;
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
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public void setSub(Sub sub) {
		this.sub = sub;
	}
	
}
