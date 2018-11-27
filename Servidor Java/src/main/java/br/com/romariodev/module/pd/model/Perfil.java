/**
 * 
 */
package br.com.romariodev.module.pd.model;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.romariodev.module.pd.entity.Equipe;
import br.com.romariodev.module.pd.entity.Pd;
import br.com.romariodev.module.pd.entity.Sub;

/**
 * @author Romário Macedo
 *
 */
public class Perfil {

	private Equipe equipe;
	private Page<Sub> subs;
	private List<Pd> pd;
	
	public Page<Sub> getSubs() {
		return subs;
	}
	public void setSubs(Page<Sub> sub) {
		this.subs = sub;
	}
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
