/**
 * 
 */
package br.com.romariodev.module.pd.model;

import java.util.List;

import br.com.romariodev.module.pd.entity.Equipe;
import br.com.romariodev.module.pd.entity.Pd;

/**
 * @author Romário Macedo
 *
 */
public class Perfil {

	private Equipe equipe;
	private List<Pd> pd;
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public List<Pd> getPd() {
		return pd;
	}
	public void setPd(List<Pd> pd) {
		this.pd = pd;
	}
}
