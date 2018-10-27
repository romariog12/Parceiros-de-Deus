package br.com.romariodev.module.pd.model;

import java.util.Calendar;
import java.util.List;

import br.com.romariodev.module.pd.entity.Equipe;
import br.com.romariodev.module.pd.entity.Lider;
import br.com.romariodev.module.pd.entity.Pd;
import br.com.romariodev.module.pd.entity.Sub;
import br.com.romariodev.module.pd.repository.EquipeRepository;
import br.com.romariodev.module.pd.repository.LiderRepository;
import br.com.romariodev.module.pd.repository.PdRepository;
import br.com.romariodev.module.pd.repository.SubRepository;
import br.com.romariodev.module.pd.util.Mensagens;

public class AdministrativoService extends AbstractService {

	AdministrativoService(PdRepository pdRepository,
			EquipeRepository equipeRepository, LiderRepository liderRepository,
			SubRepository subRepository) {
		super(pdRepository, equipeRepository, liderRepository, subRepository);
	}
	
	public int novoRelatorio(int semana){
		Iterable<Equipe> equipe = this.equipeRepository.findAll();
		try{
			for (Equipe e : equipe) {
				Pd pd = new Pd();
				Lider lider = e.getLider();
				if(lider.getLiderCelula() == 1)
				{
					pd.setCelula(0.00f);
					pd.setIndividual(0.00f);
					pd.setData(Calendar.getInstance());
					pd.setEquipe(e);
					pd.setLider(lider);
					pd.setSemana(semana);
					this.pdRepository.save(pd);
				}
				if(!e.getSubs().isEmpty()){
					Iterable<Sub> subs = e.getSubs();
					for (Sub sub : subs)
					{
						Pd pdSub = new Pd();
						Lider liderSub = sub.getLider();
						pdSub.setCelula(0.00f);
						pdSub.setIndividual(0.00f);
						pdSub.setData(Calendar.getInstance());
						pdSub.setEquipe(e);
						pdSub.setLider(liderSub);
						pdSub.setSemana(semana);
						this.pdRepository.save(pdSub);
					}
				}
			}
			return Mensagens.SUCESSO;
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}
	public int excluirLancamentoPd(int id){
		try{
			Pd pd = this.pdRepository.findByIdpd(id);
			this.pdRepository.delete(pd);
			return Mensagens.SUCESSO;	
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}
	public int cadastrarSub(Sub sub) {
		try{
			this.subRepository.save(sub);
			return Mensagens.SUCESSO;
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}
	public int excluirSub(int id){
		try{
			Sub sub = this.subRepository.findByIdSub(id);
 	    	List<Pd> pd = this.pdRepository.findByLiderIdlider(sub.getLider().getIdlider());
			this.pdRepository.deleteAll(pd);
			this.subRepository.delete(sub);
			return 	Mensagens.SUCESSO;                    
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}
	public int inativarSub(int id){
		try{
			this.subRepository.inativarSub(id);
			return 	Mensagens.SUCESSO;                    
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}
	public int ativarSub(int id){
		try{
			this.subRepository.ativarSub(id);
			return 	Mensagens.SUCESSO;                    
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}
	public int cadastrarlider(Lider lider) {
		try{
			this.liderRepository.save(lider);
			return Mensagens.SUCESSO;
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}
	public int cadastrarEquipe(Equipe equipe) {
		try{
			this.equipeRepository.save(equipe);
			return Mensagens.SUCESSO;
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}
	public int excluirEquipe(int id){
		try{
			Equipe equipe = this.equipeRepository.findByIdEquipe(id);
			if(!equipe.getSubs().isEmpty()){
				return Mensagens.ERRO_CONSISTENCIA;
			}
			else{

				List<Pd> pd = this.pdRepository.findByEquipeIdEquipe(id);
				this.pdRepository.deleteAll(pd);
				this.equipeRepository.delete(equipe);
				return 	Mensagens.SUCESSO;
			}	
		}catch(Exception e){
			return Mensagens.ERRO;
		}
	}

}
