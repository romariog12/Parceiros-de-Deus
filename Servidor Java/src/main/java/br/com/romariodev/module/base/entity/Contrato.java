package br.com.romariodev.module.base.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the contrato database table.
 * 
 */
@Entity
@NamedQuery(name="Contrato.findAll", query="SELECT c FROM Contrato c")
public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idContrato;

	private String bolsa;

	private String carga;

	@Temporal(TemporalType.DATE)
	private Date fim;

	@Temporal(TemporalType.DATE)
	private Date inicio;

	//bi-directional many-to-one association to Estagio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estagio_idEstagio", nullable = false)
	private Estagio estagio;

	public Contrato() {
	}

	public int getIdContrato() {
		return this.idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public String getBolsa() {
		return this.bolsa;
	}

	public void setBolsa(String bolsa) {
		this.bolsa = bolsa;
	}

	public String getCarga() {
		return this.carga;
	}

	public void setCarga(String carga) {
		this.carga = carga;
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

	public Estagio getEstagio() {
		return this.estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

}