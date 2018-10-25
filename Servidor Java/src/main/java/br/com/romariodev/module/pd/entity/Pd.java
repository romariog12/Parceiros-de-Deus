package br.com.romariodev.module.pd.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pd
 *
 */
@Entity
@NamedQuery(name="Pd.findAll", query="SELECT p FROM Pd p")
public class Pd implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpd;
	private float individual;
	private float celula;
	@OneToOne
	@JoinColumn(name="Equipe_idEquipe", referencedColumnName="idEquipe")
	private Equipe equipe;
	@OneToOne
	@JoinColumn(name="lider_idlider", referencedColumnName="idlider")
	private Lider lider;
	private int semana;
	public int getSemana() {
		return semana;
	}
	public void setSemana(int semana) {
		this.semana = semana;
	}
	public Pd() {
		
	}
	@Column
	@Temporal(TemporalType.DATE)
	private Calendar data;
	public int getIdpd() {
		return idpd;
	}
	public void setIdpd(int idpd) {
		this.idpd = idpd;
	}
	public float getIndividual() {
		return individual;
	}
	public void setIndividual(float individual) {
		this.individual = individual;
	}
	
	public float getCelula() {
		return celula;
	}
	public void setCelula(float celula) {
		this.celula = celula;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe (Equipe equipe) {
		this.equipe = equipe;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Lider getLider() {
		return lider;
	}
	public void setLider(Lider lider) {
		this.lider = lider;
	}
}
