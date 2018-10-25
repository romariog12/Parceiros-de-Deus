package br.com.romariodev.module.pd.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Lider
 *
 */
@Entity
@NamedQuery(name="Lider.findAll", query="SELECT l FROM Lider l")
public class Lider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idlider;
	private String nome;
	private String hierarquia;
	private int liderCelula =  1;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLiderCelula() {
		return liderCelula;
	}
	public void setLiderCelula(int liderCelula) {
		this.liderCelula = liderCelula;
	}
	@OneToOne
	private Lider lider;
	@OneToOne(mappedBy="lider")
	private Lider conjugue;
	
	public Lider getConjugue() {
		return conjugue;
	}
	public void setLider(Lider lider) {
		this.lider = lider;
	}
	public void setConjugue(Lider conjugue) {
		this.conjugue = conjugue;
	}
	public int getIdlider() {
		return idlider;
	}
	public void setIdlider(int idlider) {
		this.idlider = idlider;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getHierarquia() {
		return hierarquia;
	}
	public void setHierarquia(String hierarquia) {
		this.hierarquia = hierarquia;
	}
}
