package br.com.romariodev.module.pd.model;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import br.com.romariodev.module.pd.entity.Equipe;
import br.com.romariodev.module.pd.entity.Lider;
import br.com.romariodev.module.pd.entity.Pd;
import br.com.romariodev.module.pd.entity.Sub;
import br.com.romariodev.module.pd.repository.EquipeRepository;
import br.com.romariodev.module.pd.repository.LiderRepository;
import br.com.romariodev.module.pd.repository.PdRepository;
import br.com.romariodev.module.pd.repository.SubRepository;
import br.com.romariodev.module.pd.util.Utilitarios;

@Service
@RestController
public class PdService {
	private static int SUCESSO = 1;
	private static int ERRO = 2;
	private static int ERRO_CONSISTENCIA = 3;
	private PdRepository pdRepository;
	private LiderRepository liderRepository;
	private EquipeRepository equipeRepository;
	private SubRepository subRepository;
	
	@Autowired
	PdService(
			PdRepository pdRepository,
			EquipeRepository equipeRepository, 
			LiderRepository liderRepository, 
			SubRepository subRepository){
		this.pdRepository = pdRepository;
		this.equipeRepository = equipeRepository;
		this.liderRepository = liderRepository;
		this.subRepository = subRepository;
	}
	
	public int cadastrarPd(Pd pd) {
		try{
			this.pdRepository.save(pd);
			return PdService.SUCESSO;
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public int lancarPd(Pd pd, Calendar data, int semana){
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		try{
			this.pdRepository.pdSemanal(semana, pd.getIndividual(), pd.getCelula(), pd.getLider().getIdlider(), mes+1, ano);
			return PdService.SUCESSO;
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public List<Pd> listaPdCelula(int semana, Calendar data){
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPd(semana, mes +1, ano);
		return listaPd(pd);
	}
	public List<Pd> listaPdCompleto(int semana, Calendar data){
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPd(semana, mes +1, ano);
		return listaPd(pd);
	}
	public List<Pd> listaPdEquipePorCiclo(int semana, Calendar data){
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPdPorEquipe(semana, mes +1, ano);
		return  listaPd(pd);
	}
	public List<Pd> listaPdPorMes(Calendar data){
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPdPorMes(mes +1, ano);
		return listaPd(pd);
	}
	public List<Pd> listaPdPorMesEquipe(Calendar data){
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		return this.pdRepository.listaPdPorMesEquipe(mes +1, ano);
	}
	public List<Pd> listaPdIndividualPorMes(Calendar data){
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		return this.pdRepository.listaPdIndividualPorMes(mes +1, ano);
	}
	public int cadastrarlider(Lider lider) {
		try{
			this.liderRepository.save(lider);
			return PdService.SUCESSO;
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public int cadastrarEquipe(Equipe equipe) {
		try{
			this.equipeRepository.save(equipe);
			return PdService.SUCESSO;
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public Equipe getEquipe(int id){
		return this.equipeRepository.findByIdEquipe(id);
	}
	public int excluirEquipe(int id){
		try{
			Equipe equipe = this.equipeRepository.findByIdEquipe(id);
			if(!equipe.getSubs().isEmpty()){
				return PdService.ERRO_CONSISTENCIA;
			}
			else{

				List<Pd> pd = this.pdRepository.findByEquipeIdEquipe(id);
				this.pdRepository.deleteAll(pd);
				this.equipeRepository.delete(equipe);
				return 	PdService.SUCESSO;
			}	
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public int excluirLancamentoPd(int id){
		try{
			Pd pd = this.pdRepository.findByIdpd(id);
			this.pdRepository.delete(pd);
			return PdService.SUCESSO;	
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public Iterable<Lider> lideres(){
		Iterable<Lider> lideres = this.liderRepository.findAll();
		return  listaLideres(lideres);
	}
	public List<Lider> listaLideres(Iterable<Lider> lideres)
	{
		List<Lider> listaLideres = new ArrayList<Lider>();
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
	public Iterable<Equipe> equipes(){
		
		return this.equipeRepository.findAll();
	}
	public Iterable<Sub> subs(){
		return this.subRepository.findAll() ;
	}
	public int cadastrarSub(Sub sub) {
		try{
			this.subRepository.save(sub);
			return PdService.SUCESSO;
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public int excluirSub(int id){
		try{
			Sub sub = this.subRepository.findByIdSub(id);
 	    	List<Pd> pd = this.pdRepository.findByLiderIdlider(sub.getLider().getIdlider());
			this.pdRepository.deleteAll(pd);
			this.subRepository.delete(sub);
			return 	PdService.SUCESSO;                    
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public int inativarSub(int id){
		try{
			this.subRepository.inativarSub(id);
			return 	PdService.SUCESSO;                    
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	public int ativarSub(int id){
		try{
			this.subRepository.ativarSub(id);
			return 	PdService.SUCESSO;                    
		}catch(Exception e){
			return PdService.ERRO;
		}
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
			return PdService.SUCESSO;
		}catch(Exception e){
			return PdService.ERRO;
		}
	}
	
	public Perfil  perfil(int idEquipe)
	{
		Calendar data = Calendar.getInstance();
		Equipe equipe = this.equipeRepository.findByIdEquipe(idEquipe);
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPdPorIdEquipe(equipe.getIdEquipe(), mes+1, ano);
		Perfil perfil = new Perfil();
		perfil.setEquipe(equipe);
		perfil.setPd(pd);
		return perfil;
	}
}
