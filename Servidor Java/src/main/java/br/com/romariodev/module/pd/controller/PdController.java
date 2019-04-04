package br.com.romariodev.module.pd.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.romariodev.module.base.controller.AbstractController;
import br.com.romariodev.module.pd.entity.Equipe;
import br.com.romariodev.module.pd.entity.Lider;
import br.com.romariodev.module.pd.entity.Pd;
import br.com.romariodev.module.pd.entity.Sub;
import br.com.romariodev.module.pd.model.AdministrativoService;
import br.com.romariodev.module.pd.model.PdService;
import br.com.romariodev.module.pd.model.Perfil;
import br.com.romariodev.module.pd.util.Utilitarios;

@RestController
@RequestMapping(path = "/pd")
public class PdController extends AbstractController {
	@Autowired
	private PdService pdService;
	@Autowired
	private AdministrativoService admService;

	@PostMapping("/cadastrarlider")
	public @ResponseBody int cadastratLider(@RequestBody Lider lider) {
		lider.setStatus(1);
		return this.admService.cadastrarlider(lider);
	}
	@PutMapping("/editarlider")
	public @ResponseBody int editarLider(@RequestBody Lider lider) { 
			return this.admService.editarlider(lider);
	}

	@PostMapping(path = "/cadastrarEquipe")
	public @ResponseBody int cadastrarEquipe(@RequestBody Equipe equipe) {
		equipe.setStatus(1);
		return this.admService.cadastrarEquipe(equipe);

	}
	@PutMapping(path = "/editarEquipe")
	public @ResponseBody int editarEquipe(@RequestBody Equipe equipe) {
		return this.admService.editarEquipe(equipe);

	}

	@DeleteMapping(path = "/excluirEquipe/{id}")
	public @ResponseBody int excluirEquipe(@PathVariable int id) {
		return this.admService.excluirEquipe(id);
	}

	@PutMapping(path = "inativarEquipe")
	public @ResponseBody int inativarEquipe(@RequestBody Equipe equipe) {
		return this.admService.inativarEquipe(equipe);
	}

	@PutMapping(path = "ativarEquipe")
	public @ResponseBody int ativarEquipe(@RequestBody Equipe equipe) {
		return this.admService.ativarEquipe(equipe);
	}

	@PutMapping(path = "ativarLider")
	public @ResponseBody int ativarLider(@RequestBody Lider lider) {
		return this.admService.ativarLider(lider);
	}

	@PutMapping(path = "inativarLider")
	public @ResponseBody int inativarLider(@RequestBody Lider lider) {
		return this.admService.inativarLider(lider);
	}

	@GetMapping(path = "getEquipe/{id}")
	public @ResponseBody Equipe equipe(@PathVariable int id) {
		return this.pdService.equipe(id);
	}

	@DeleteMapping(path = "/excluirSub/{id}")
	public @ResponseBody int excluirSub(@PathVariable int id) {
		return this.admService.excluirSub(id);
	}
	@GetMapping(path = "/sub/{id}")
	public @ResponseBody Sub sub(@PathVariable int id) {
		return this.pdService.sub(id);
	}
	@DeleteMapping(path = "/excluirLider/{id}")
	public @ResponseBody int excluirLider(@PathVariable int id) {
		return this.admService.excluirLider(id);
	}
	@PutMapping(path = "/inativarSub")
	public @ResponseBody int inativarSub(@RequestBody Sub sub) {
		System.out.println("Teste");
		return this.admService.inativarSub(sub);
	}

	@PutMapping(path = "/ativarSub")
	public @ResponseBody int ativarSub(@RequestBody Sub sub) {
		return this.admService.ativarSub(sub);
	}

	@DeleteMapping(path = "/excluirLancamentoPd/{id}")
	public @ResponseBody int excluirLancamentoPd(@PathVariable int id) {
		return this.admService.excluirLancamentoPd(id);
	}

	@PostMapping(path = "/lancarPd")
	public @ResponseBody int lancarPd(@RequestBody Pd pd) {
		return this.pdService.lancarPd(pd, pd.getSemana());
	}

	@GetMapping(path = "/listaPdCelula/{semana}/{mes}/{ano}")
	public @ResponseBody List<Pd> listaPd(@PathVariable int semana,
			@PathVariable int mes, @PathVariable int ano) {
		return this.pdService.listaPdCelula(semana, mes, ano);
	}
	@GetMapping(path = "/restartarRelatorio/{semana}/{mes}/{ano}")
	public @ResponseBody int restartarRelatorio(@PathVariable int semana,
			@PathVariable int mes, @PathVariable int ano) {
		if (semana == 0) {
			DateFormat df = new SimpleDateFormat("W");
			semana = Integer.parseInt(df.format(new Date()));
		}
		return this.admService.restartarRelatorio(semana, mes, ano);
	}

	@GetMapping(path = "/listaPdCompleto/{id}")
	public @ResponseBody List<Pd> listaPdCompleto(@PathVariable int semana) {
		if (semana == 0) {
			DateFormat df = new SimpleDateFormat("W");
			semana = Integer.parseInt(df.format(new Date()));
		}
		return this.pdService.listaPdCompleto(semana, Calendar.getInstance());
	}

	@GetMapping(path = "/listaPdEquipePorCiclo/{semana}/{mes}/{ano}")
	public @ResponseBody List<Pd> listaPdEquipePorMes(@PathVariable int semana,
			@PathVariable int mes, @PathVariable int ano) {
		if (semana == 0) {
			DateFormat df = new SimpleDateFormat("W");
			semana = Integer.parseInt(df.format(new Date()));
		}
		if (this.pdService.listaPdEquipePorCiclo(semana, mes, ano) == null
				&& semana <= Utilitarios.SEMANA) {
			this.admService.novoRelatorio(semana, mes, ano);
		}

		return this.pdService.listaPdEquipePorCiclo(semana, mes, ano);
	}

	@GetMapping(path = "/listaPdPorMes/{mes}/{ano}")
	public @ResponseBody List<Pd> listaPdPorMes(@PathVariable int mes,
			@PathVariable int ano) {
		System.out.println(mes);
		return this.pdService.listaPdPorMes(mes, ano);
	}

	@GetMapping(path = "/listaPdPorMesEquipe/{mes}/{ano}")
	public @ResponseBody List<Pd> listaPdPorMesEquipe(@PathVariable int mes,
			@PathVariable int ano) {
		return this.pdService.listaPdPorMesEquipe(mes, ano);
	}

	@GetMapping(path = "/listaPdIndividualPorMes/{mes}/{ano}")
	public @ResponseBody List<Pd> listaPdIndividualPorMes(
			@PathVariable int mes, @PathVariable int ano) {
		return this.pdService.listaPdIndividualPorMes(mes, ano);
	}

	@GetMapping(path = "/lideres/{page}")
	public @ResponseBody Iterable<Lider> lideres(@PathVariable int page, Pageable p) {
		if(page == 88888)
			return this.pdService.lideres(p.previousOrFirst());
		if(page == 99999)
			return this.pdService.lideres(p.next());
		return this.pdService.lideres(PageRequest.of(page, 10));
	}
	@GetMapping(path = "/lideres")
	public @ResponseBody Iterable<Lider> lideres() {
		return this.pdService.lideres();
	}

	@GetMapping(path = "/lider/{id}")
	public @ResponseBody Lider lider(@PathVariable int id) {
		return this.pdService.lider(id);
	}
	@GetMapping(path = "/lideresInativos/{page}")
	public @ResponseBody Iterable<Lider> lideresInativos(@PathVariable int page, Pageable p) {
		if(page == 88888)
			return this.pdService.lideresInativos(p.previousOrFirst());
		if(page == 99999)
			return this.pdService.lideresInativos(p.next());

		return this.pdService.lideresInativos(PageRequest.of(page, 10));
	}

	@GetMapping(path = "/equipes/{page}")
	public @ResponseBody Iterable<Equipe> equipes(@PathVariable int page, Pageable p) {
		if(page == 88888)
			return this.pdService.equipes(p.previousOrFirst());
		if(page == 99999)
			return this.pdService.equipes(p.next());
		
		return this.pdService.equipes(PageRequest.of(page, 10));
	}
	
	@GetMapping(path = "/equipes")
	public @ResponseBody Iterable<Equipe> equipes() {
		return this.pdService.equipes();
	}
	
	@GetMapping(path = "/equipesInativas/{page}")
	public @ResponseBody Iterable<Equipe> equipesInativas(@PathVariable int page, Pageable p) {
		if(page == 88888)
			return this.pdService.equipesInativas(p.previousOrFirst());
		if(page == 99999)
			return this.pdService.equipesInativas(p.next());
		return this.pdService.equipesInativas(PageRequest.of(page, 10));
	}

	@GetMapping(path = "/subs")
	public @ResponseBody Iterable<Sub> subs() {
		return this.pdService.subs();
	}

	@GetMapping(path = "/subsInativas")
	public @ResponseBody Iterable<Sub> subsInativas() {
		return this.pdService.subsInativas();
	}

	@PostMapping(path = "/cadastrarSub")
	public @ResponseBody int cadastrarSub(@RequestBody Sub sub) {
		return this.admService.cadastrarSub(sub);
	}
	@PutMapping(path = "/editarSub")
	public @ResponseBody int editarSub(@RequestBody Sub sub) {
		return this.admService.editarSub(sub);
	}

	@GetMapping(path = "/novoRelatorio/{semana}/{mes}/{ano}")
	public @ResponseBody int novoRelatorio(@PathVariable int semana,
			@PathVariable int mes, @PathVariable int ano) {
		return this.admService.novoRelatorio(semana, mes, ano);
	}

	@GetMapping(path = "/cicloAtual")
	public @ResponseBody int cicloAtual() {
		DateFormat df = new SimpleDateFormat("W");
		return Integer.parseInt(df.format(new Date()));
	}

	@GetMapping(path = "/perfil/{idEquipe}/{page}")
	public @ResponseBody Perfil perfil(@PathVariable int idEquipe, @PathVariable int page) {
		return this.pdService.perfil(idEquipe, page);
	}
}
