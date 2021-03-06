package br.com.romariodev.module.pd.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.romariodev.module.pd.entity.Equipe;
import br.com.romariodev.module.pd.entity.Pd;
import br.com.romariodev.module.pd.entity.Sub;
import br.com.romariodev.module.pd.repository.EquipeRepository;
import br.com.romariodev.module.pd.repository.LiderRepository;
import br.com.romariodev.module.pd.repository.PdRepository;
import br.com.romariodev.module.pd.repository.SubRepository;
import br.com.romariodev.module.pd.util.CodigoMensagem;

@Service
public class PdService extends AbstractService {

	PdService(PdRepository pdRepository, EquipeRepository equipeRepository,
			LiderRepository liderRepository, SubRepository subRepository) {
		super(pdRepository, equipeRepository, liderRepository, subRepository);
	}

	public int lancarPd(Pd pd, int semana) {

		int mes = pd.getData().get(Calendar.MONTH);
		int ano = pd.getData().get(Calendar.YEAR);
		try {
			//this.pdRepository.findById(new Long(pd.getIdpd()));
			this.pdRepository.pdSemanal(semana, pd.getIndividual(),
					pd.getCelula(), pd.getLider().getIdlider(), mes + 1, ano);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public List<Pd> listaPdCelula(int semana, int mes, int ano) {
		if (semana == 0) {
			DateFormat df = new SimpleDateFormat("W");
			semana = Integer.parseInt(df.format(new Date()));
		}
		if (mes == 0) {
			Calendar data = Calendar.getInstance();
			mes = data.get(Calendar.MONTH) + 1;
			ano = data.get(Calendar.YEAR);
		}
		return this.pdRepository.listaPd(semana, mes, ano);
	}

	public List<Pd> listaPdCompleto(int semana, Calendar data) {
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		return this.pdRepository.listaPd(semana, mes + 1, ano);
	}

	public List<Pd> listaPdEquipePorCiclo(int semana, int mes, int ano) {
		if ((mes == 0) || (ano == 0)) {
			Calendar data = Calendar.getInstance();
			mes = data.get(Calendar.MONTH) + 1;
			ano = data.get(Calendar.YEAR);
		}
		return this.pdRepository.listaPdPorEquipe(semana, mes, ano);
	}

	public List<Pd> listaPdPorMes(int mes, int ano) {
		if ((mes == 0) || (ano == 0)) {
			Calendar data = Calendar.getInstance();
			mes = data.get(Calendar.MONTH) + 1;
			ano = data.get(Calendar.YEAR);
		}
		return this.pdRepository.listaPdPorMes(mes, ano);
	}

	public List<Pd> listaPdPorMesEquipe(int mes, int ano) {
		if ((mes == 0) || (ano == 0)) {
			Calendar data = Calendar.getInstance();
			mes = data.get(Calendar.MONTH) + 1;
			ano = data.get(Calendar.YEAR);
		}
		return this.pdRepository.listaPdPorMesEquipe(mes, ano);
	}

	public List<Pd> listaPdIndividualPorMes(int mes, int ano) {
		if (mes == 0 && ano == 0) {
			Calendar data = Calendar.getInstance();
			mes = data.get(Calendar.MONTH)+1;
			ano = data.get(Calendar.YEAR);
		}
		return this.pdRepository.listaPdIndividualPorMes(mes, ano);
	}

	public Perfil perfil(int idEquipe, int page) {
		Calendar data = Calendar.getInstance();
		Equipe equipe = this.equipeRepository.findByIdEquipe(idEquipe);
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		List<Pd> pd = this.pdRepository.listaPdPorIdEquipe(
				equipe.getIdEquipe(), mes + 1, ano);
		Page<Sub> subs = this.subRepository.findByEquipeIdEquipe(equipe.getIdEquipe(), PageRequest.of(page, 10));
		Perfil perfil = new Perfil();
		perfil.setEquipe(equipe);
		perfil.setPd(pd);
		perfil.setSubs(subs);
		return perfil;
	}

	public Sub sub(int id) {
		return this.subRepository.findByIdSub(id);
	}

}
