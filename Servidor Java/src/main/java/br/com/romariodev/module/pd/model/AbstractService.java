package br.com.romariodev.module.pd.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class AbstractService {
	protected PdRepository pdRepository;
	protected LiderRepository liderRepository;
	protected EquipeRepository equipeRepository;
	protected SubRepository subRepository;

	@Autowired
	AbstractService(PdRepository pdRepository,
			EquipeRepository equipeRepository, LiderRepository liderRepository,
			SubRepository subRepository) {
		this.pdRepository = pdRepository;
		this.equipeRepository = equipeRepository;
		this.liderRepository = liderRepository;
		this.subRepository = subRepository;
	}

	public Iterable<Sub> subs() {
		return this.subRepository.findByStatus(1);
	}
	public Iterable<Sub> subsInativas() {
		return this.subRepository.findByStatus(-1);
	}


	public Iterable<Equipe> equipes(Pageable p) {

		return this.equipeRepository.findByStatus(1, p);
	}
	public Iterable<Equipe> equipes() {

		return this.equipeRepository.findByStatus(1);
	}

	public Iterable<Equipe> equipesInativas(Pageable p) {

		return this.equipeRepository.findByStatus(-1, p);
	}

	public Page<Lider> lideres(Pageable p) {
		return this.liderRepository.findByStatus(1, p);
	}
	public Iterable<Lider> lideres() {
		return this.liderRepository.findByStatus(1);
	}
	public Lider lider(int id) {
		return this.liderRepository.findByIdlider(id);
	}

	public Page<Lider> lideresInativos(Pageable p) {
		return this.liderRepository.findByStatus(-1, p);
	}

	public List<Pd> listaPd(Iterable<Pd> pd) {
		List<Pd> listaPd = new ArrayList<Pd>();
		for (Pd Pd : pd) {
			Utilitarios util = new Utilitarios();
			String nomeLider = util.pegaPrimeiroNome(Pd.getLider().getNome());
			Pd.getLider().setNome(nomeLider);
			if (Pd.getLider().getConjugue() != null) {
				String nomeLiderConjugue = util.pegaPrimeiroNome(Pd.getLider()
						.getConjugue().getNome());
				Pd.getLider().setNome(nomeLider + " e " + nomeLiderConjugue);
			}
			listaPd.add(Pd);
		}
		return listaPd;
	}

	public Equipe equipe(int id) {
		return this.equipeRepository.findByIdEquipe(id);
	}

}
