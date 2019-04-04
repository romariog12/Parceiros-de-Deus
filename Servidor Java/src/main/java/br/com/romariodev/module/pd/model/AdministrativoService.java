package br.com.romariodev.module.pd.model;

import java.util.Calendar;
import java.util.List;

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
import br.com.romariodev.module.pd.util.CodigoMensagem;

@Service
@RestController
public class AdministrativoService extends AbstractService {

	AdministrativoService(PdRepository pdRepository,
			EquipeRepository equipeRepository, LiderRepository liderRepository,
			SubRepository subRepository) {
		super(pdRepository, equipeRepository, liderRepository, subRepository);
	}

	public int novoRelatorio(int semana, int mes, int ano) {
		Iterable<Equipe> equipe = this.equipeRepository.findAll();
		Calendar data = Calendar.getInstance();
		if (semana != 0 && mes != 0 && ano != 0)
			data.set(ano, mes - 1, Calendar.DAY_OF_MONTH);
		try {
			for (Equipe e : equipe) {
				Pd pd = new Pd();
				Lider lider = e.getLider();
				if (lider.getLiderCelula() == 1 && lider.getStatus() == 1) {
					pd.setCelula(0.00f);
					pd.setIndividual(0.00f);
					pd.setData(data);
					pd.setEquipe(e);
					pd.setLider(lider);
					pd.setSemana(semana);
					this.pdRepository.save(pd);
				}
				if (!e.getSubs().isEmpty()) {
					List<Sub> subs = e.getSubs();
					LancarPdSub(subs, data, semana, e);
				}
			}
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int restartarRelatorio(int semana, int mes, int ano) {
		if (mes == 0) {
			Calendar data = Calendar.getInstance();
			mes = data.get(Calendar.MONTH) + 1;
			ano = data.get(Calendar.YEAR);
		}
		List<Pd> pd = this.pdRepository.listaPd(semana, mes, ano);
		this.pdRepository.deleteAll(pd);
		this.novoRelatorio(semana, mes, ano);
		return CodigoMensagem.SUCESSO;
	}

	public int excluirLancamentoPd(int id) {
		try {
			Pd pd = this.pdRepository.findByIdpd(id);
			this.pdRepository.delete(pd);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int cadastrarSub(Sub sub) {
		try {
			sub.setStatus(1);
			this.subRepository.save(sub);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int editarSub(Sub sub) {
		try {
			this.subRepository.save(sub);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int excluirSub(int id) {
		try {
			Sub sub = this.subRepository.findByIdSub(id);
			if (!sub.getSubs().isEmpty()) {
				return CodigoMensagem.ERRO_CONSISTENCIA;
			} else {
				List<Pd> pd = this.pdRepository.findByLiderIdlider(sub
						.getLider().getIdlider());
				this.pdRepository.deleteAll(pd);
				this.subRepository.delete(sub);
				return CodigoMensagem.SUCESSO;
			}
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int inativarSub(Sub sub) {
		try {
			Sub subRepository = this.subRepository.findByIdSub(sub.getIdSub());
			if (!subRepository.getSubs().isEmpty()) {
				for (Sub s : subRepository.getSubs()) {
					if (s.getStatus() == 1)
						;
					return CodigoMensagem.ERRO_CONSISTENCIA;
				}
			}
			subRepository.setStatus(-1);
			this.subRepository.save(subRepository);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int ativarSub(Sub sub) {
		try {
			Sub subRepository = this.subRepository.findByIdSub(sub.getIdSub());
			subRepository.setStatus(1);
			this.subRepository.save(subRepository);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int cadastrarlider(Lider lider) {
		try {
			this.liderRepository.save(lider);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int editarlider(Lider lider) {
		try {
			this.liderRepository.save(lider);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int cadastrarEquipe(Equipe equipe) {
		try {
			this.equipeRepository.save(equipe);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int editarEquipe(Equipe equipe) {
		try {
			this.equipeRepository.save(equipe);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int excluirEquipe(int id) {
		try {
			Equipe equipe = this.equipeRepository.findByIdEquipe(id);
			if (!equipe.getSubs().isEmpty()) {
				return CodigoMensagem.ERRO_CONSISTENCIA;
			} else {

				List<Pd> pd = this.pdRepository.findByEquipeIdEquipe(id);
				this.pdRepository.deleteAll(pd);
				this.equipeRepository.delete(equipe);
				return CodigoMensagem.SUCESSO;
			}
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public int excluirLider(int id) {
		Lider lider = this.liderRepository.findByIdlider(id);
		Equipe equipe = this.equipeRepository.findByLiderIdlider(id);
		if (equipe != null) {
			return CodigoMensagem.ERRO_CONSISTENCIA;
		}
		List<Pd> pd = this.pdRepository.findByLiderIdlider(id);
		if (!pd.isEmpty())
			this.pdRepository.deleteAll(pd);
		this.liderRepository.delete(lider);
		return CodigoMensagem.SUCESSO;
	}

	public int inativarLider(Lider lider) {
		try {
			Lider liderRepository = this.liderRepository.findByIdlider(lider.getIdlider());
			Equipe equipe = equipeRepository.findByLiderIdlider(lider.getIdlider());
			if (equipe != null) {
				if (inativarEquipe(equipe) == CodigoMensagem.ERRO_CONSISTENCIA)
					return CodigoMensagem.ERRO_CONSISTENCIA;

				liderRepository.setStatus(-1);
				this.liderRepository.save(liderRepository);
				return CodigoMensagem.SUCESSO_DEPENDENCIAS;
			}
			liderRepository.setStatus(-1);
			this.liderRepository.save(liderRepository);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}

	}

	public int ativarLider(Lider lider) {
		try {
			Lider liderRepository = this.liderRepository.findByIdlider(lider.getIdlider());
			lider.setStatus(1);
			this.liderRepository.save(liderRepository);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}

	}

	public int inativarEquipe(Equipe eq) {
		try {
			Equipe equipe = this.equipeRepository.findByIdEquipe(eq.getIdEquipe());
			if (!equipe.getSubs().isEmpty()) {
				for (Sub sub : equipe.getSubs()) {
					if (sub.getStatus() == 1)
						return CodigoMensagem.ERRO_CONSISTENCIA;
				}
			}
			equipe.setStatus(-1);
			this.equipeRepository.save(equipe);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}

	}

	public int ativarEquipe(Equipe equipe) {
		try {
			Equipe equipeRepository = this.equipeRepository.findByIdEquipe(equipe.getIdEquipe());
			equipeRepository.setStatus(1);
			this.equipeRepository.save(equipeRepository);
			return CodigoMensagem.SUCESSO;
		} catch (Exception e) {
			return CodigoMensagem.ERRO;
		}
	}

	public void LancarPdSub(List<Sub> subs, Calendar data, int semana, Equipe e) {
		for (Sub s : subs) {
			if (!s.getSubs().isEmpty()) {
				List<Sub> subs144 = s.getSubs();
				LancarPdSub(subs144, data, semana, e);
			}
			Pd pdSub = new Pd();
			Lider liderSub = s.getLider();
			if (liderSub.getStatus() == 1) {
				pdSub.setCelula(0.00f);
				pdSub.setIndividual(0.00f);
				pdSub.setData(data);
				pdSub.setEquipe(e);
				pdSub.setLider(liderSub);
				pdSub.setSemana(semana);
				this.pdRepository.save(pdSub);
			}
		}

	}
}
