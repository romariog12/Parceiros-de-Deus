package br.com.romariodev.module.pd.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import br.com.romariodev.module.pd.util.CodigoMensagem;
import br.com.romariodev.module.pd.util.Utilitarios;

@RestController
@RequestMapping(path = "/pd")
public class PdController extends AbstractController {
	@Autowired
	private PdService pdService;
	@Autowired
	private AdministrativoService admService;

	@PostMapping("/cadastrarlider")
	public @ResponseBody String cadastratLider(@RequestBody Lider lider) {
		if (this.admService.cadastrarlider(lider) == CodigoMensagem.SUCESSO) {
			return "Cadastro realizado com sucesso!";
		} else if (this.admService.cadastrarlider(lider) == CodigoMensagem.CADASTRO_EXISTENTE) {
			return "Cadastro existente";
		} else {
			return "Cadastro não realizado! erro interno! \n Código:["
					+ this.admService.cadastrarlider(lider) + "]";
		}
	}
	@PostMapping("/editarlider")
	public @ResponseBody int editarLider(@RequestBody Lider lider) { 
			return this.admService.editarlider(lider);
	}

	@PostMapping(path = "/cadastrarEquipe")
	public @ResponseBody int cadastrarEquipe(@RequestBody Equipe equipe) {
		return this.admService.cadastrarEquipe(equipe);

	}
	@PostMapping(path = "/editarEquipe")
	public @ResponseBody int editarEquipe(@RequestBody Equipe equipe) {
		return this.admService.editarEquipe(equipe);

	}

	@PostMapping(path = "/excluirEquipe")
	public @ResponseBody int excluirEquipe(@RequestBody int id) {
		return this.admService.excluirEquipe(id);
	}

	@GetMapping(path = "inativarEquipe/{id}")
	public @ResponseBody int inativarEquipe(@PathVariable int id) {
		return this.admService.inativarEquipe(id);
	}

	@GetMapping(path = "ativarEquipe/{id}")
	public @ResponseBody int ativarEquipe(@PathVariable int id) {
		return this.admService.ativarEquipe(id);
	}

	@GetMapping(path = "ativarLider/{id}")
	public @ResponseBody int ativarLider(@PathVariable int id) {
		return this.admService.ativarLider(id);
	}

	@GetMapping(path = "inativarLider/{id}")
	public @ResponseBody int inativarLider(@PathVariable int id) {
		return this.admService.inativarLider(id);
	}

	@GetMapping(path = "getEquipe/{id}")
	public @ResponseBody Equipe equipe(@PathVariable int id) {
		return this.pdService.equipe(id);
	}

	@PostMapping(path = "/excluirSub")
	public @ResponseBody int excluirSub(@RequestBody int id) {
		return this.admService.excluirSub(id);
	}
	@GetMapping(path = "/sub/{id}")
	public @ResponseBody Sub sub(@PathVariable int id) {
		return this.pdService.sub(id);
	}
	@GetMapping(path = "/excluirLider/{id}")
	public @ResponseBody int excluirLider(@PathVariable int id) {
		return this.admService.excluirLider(id);
	}
	@GetMapping(path = "/inativarSub/{id}")
	public @ResponseBody int inativarSub(@PathVariable int id) {
		System.out.println("Teste");
		return this.admService.inativarSub(id);
	}

	@PostMapping(path = "/ativarSub")
	public @ResponseBody int ativarSub(@RequestBody int id) {
		return this.admService.ativarSub(id);
	}

	@PostMapping(path = "/excluirLancamentoPd")
	public @ResponseBody int excluirLancamentoPd(@RequestBody int id) {
		return this.admService.excluirLancamentoPd(id);
	}

	@PostMapping(path = "/lancarPd")
	public @ResponseBody int lancarPd(@RequestBody Pd pd) {
		return this.pdService.lancarPd(pd, pd.getSemana());
	}

	@GetMapping(path = "/listaPdCelula/{semana}/{mes}/{ano}")
	public @ResponseBody List<Pd> listaPd(@PathVariable int semana,
			@PathVariable int mes, @PathVariable int ano) {
		if (semana == 0) {
			DateFormat df = new SimpleDateFormat("W");
			semana = Integer.parseInt(df.format(new Date()));
		}
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

	@PostMapping(path = "/listaPdCompleto")
	public @ResponseBody List<Pd> listaPdCompleto(@RequestBody int semana) {
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
	@PostMapping(path = "/editarSub")
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
