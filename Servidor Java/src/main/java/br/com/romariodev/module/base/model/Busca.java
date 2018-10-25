package br.com.romariodev.module.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Busca {
	@JsonProperty("filtro")
	private String filtro;
	
	@JsonProperty("tipo")
	private String tipo;
	
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
