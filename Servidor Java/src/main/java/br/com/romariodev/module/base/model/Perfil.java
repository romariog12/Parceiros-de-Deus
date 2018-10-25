package br.com.romariodev.module.base.model;

import java.util.List;

import br.com.romariodev.module.base.entity.Estagio;

public class Perfil {
	
	private List<Estagio> estagio;

	public List<Estagio> getEstagio() {
		return estagio;
	}
	public void setEstagio(List<Estagio> estagio) {
		this.estagio = estagio;
	}
}
