package br.com.romariodev.module.pd.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.romariodev.module.pd.entity.Equipe;
import br.com.romariodev.module.pd.entity.Lider;
import br.com.romariodev.module.pd.entity.Pd;
import br.com.romariodev.module.pd.entity.Sub;
import br.com.romariodev.module.pd.repository.EquipeRepository;
import br.com.romariodev.module.pd.repository.LiderRepository;
import br.com.romariodev.module.pd.repository.PdRepository;
import br.com.romariodev.module.pd.repository.SubRepository;
import br.com.romariodev.module.pd.util.Utilitarios;



public class AbstractService{
	protected PdRepository pdRepository;
	protected LiderRepository liderRepository;
	protected EquipeRepository equipeRepository;
	protected SubRepository subRepository;
	
	@Autowired
	AbstractService(
			PdRepository pdRepository,
			EquipeRepository equipeRepository, 
			LiderRepository liderRepository, 
			SubRepository subRepository){
		this.pdRepository = pdRepository;
		this.equipeRepository = equipeRepository;
		this.liderRepository = liderRepository;
		this.subRepository = subRepository;
	}

	public Iterable<Sub> subs(){
		return this.subRepository.findAll() ;
	}

	public Iterable<Equipe> equipes(){
		return this.equipeRepository.findAll();
	}
	public Iterable<Lider> lideres()
	{
		List<Lider> listaLideres = new ArrayList<Lider>();
		Iterable<Lider> lideres = this.liderRepository.findAll();
		for(Lider lider: lideres){
			if(lider.getConjugue() != null)
				lider.setNome(lider.getNome()+" e "+lider.getConjugue().getNome());
			listaLideres.add(lider);
		}
		return listaLideres;
	}
	public List<Pd> listaPd(Iterable<Pd> pd){
		List<Pd> listaPd = new ArrayList<Pd>();
		for(Pd Pd : pd){
			Utilitarios util = new Utilitarios();
			String nomeLider = util.pegaPrimeiroNome(Pd.getLider().getNome());
			Pd.getLider().setNome(nomeLider);
			if(Pd.getLider().getConjugue() != null){
				String nomeLiderConjugue = util.pegaPrimeiroNome(Pd.getLider().getConjugue().getNome());
				Pd.getLider().setNome(nomeLider+" e "+nomeLiderConjugue);
			}
			listaPd.add(Pd);
		}
		return listaPd;
	}

	public Equipe getEquipe(int id){
		return this.equipeRepository.findByIdEquipe(id);
	}

}
