package br.com.romariodev.module.pd.model;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import br.com.romariodev.module.pd.entity.Equipe;
import br.com.romariodev.module.pd.entity.Pd;
import br.com.romariodev.module.pd.repository.EquipeRepository;
import br.com.romariodev.module.pd.repository.LiderRepository;
import br.com.romariodev.module.pd.repository.PdRepository;
import br.com.romariodev.module.pd.repository.SubRepository;
import br.com.romariodev.module.pd.util.Mensagens;

@Service
@RestController
public class PdService extends AbstractService {

	PdService(PdRepository pdRepository, EquipeRepository equipeRepository,
			LiderRepository liderRepository, SubRepository subRepository) {
		super(pdRepository, equipeRepository, liderRepository, subRepository);
	}

	public int lancarPd(Pd pd, Calendar data, int semana) {
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		try {
			this.pdRepository.pdSemanal(semana, pd.getIndividual(),
					pd.getCelula(), pd.getLider().getIdlider(), mes + 1, ano);
			return Mensagens.SUCESSO;
		} catch (Exception e) {
			return Mensagens.ERRO;
		}
	}

	public List<Pd> listaPdCelula(int semana, Calendar data) {
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPd(semana, mes + 1, ano);
		return listaPd(pd);
	}

	public List<Pd> listaPdCompleto(int semana, Calendar data) {
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPd(semana, mes + 1, ano);
		return listaPd(pd);
	}

	public List<Pd> listaPdEquipePorCiclo(int semana, Calendar data) {
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPdPorEquipe(semana, mes + 1, ano);
		return listaPd(pd);
	}

	public List<Pd> listaPdPorMes(Calendar data) {
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPdPorMes(mes + 1, ano);
		return listaPd(pd);
	}

	public List<Pd> listaPdPorMesEquipe(Calendar data) {
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		return this.pdRepository.listaPdPorMesEquipe(mes + 1, ano);
	}

	public List<Pd> listaPdIndividualPorMes(Calendar data) {
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPdIndividualPorMes(mes + 1, ano);
		return listaPd(pd);
	}

	public Perfil perfil(int idEquipe) {
		Calendar data = Calendar.getInstance();
		Equipe equipe = this.equipeRepository.findByIdEquipe(idEquipe);
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPdPorIdEquipe(
				equipe.getIdEquipe(), mes + 1, ano);
		Perfil perfil = new Perfil();
		perfil.setEquipe(equipe);
		perfil.setPd(pd);
		return perfil;
	}
}
