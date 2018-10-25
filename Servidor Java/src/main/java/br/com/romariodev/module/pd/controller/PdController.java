package br.com.romariodev.module.pd.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.romariodev.module.pd.model.PdService;
import br.com.romariodev.module.pd.model.Perfil;

@RestController
@RequestMapping(path="/pd")
public class PdController extends AbstractController{
	@Autowired
	private PdService pdService;
	@PostMapping("/cadastrarlider")
	public @ResponseBody int cadastratLider(@RequestBody Lider lider) { 
		return this.pdService.cadastrarlider(lider);
	}
	@PostMapping(path="/cadastrarEquipe")
	public @ResponseBody int cadastrarEquipe(@RequestBody Equipe equipe) {
		return 	this.pdService.cadastrarEquipe(equipe);
		
	}
	@PostMapping(path="/excluirEquipe")
	public @ResponseBody int excluirEquipe(@RequestBody int id){
		return this.pdService.excluirEquipe(id);
	}
	@GetMapping(path="getEquipe/{id}")
	public @ResponseBody Equipe getEquipe(@PathVariable int id){
		return this.pdService.getEquipe(id);
	}
	@PostMapping(path="/excluirSub")
	public @ResponseBody int excluirSub(@RequestBody int id){
		return this.pdService.excluirSub(id);
	}
	@PostMapping(path="/inativarSub")
	public @ResponseBody int inativarSub(@RequestBody int id){
		return this.pdService.inativarSub(id);
	}
	@PostMapping(path="/ativarSub")
	public @ResponseBody int ativarSub(@RequestBody int id){
		return this.pdService.ativarSub(id);
	}
	//Lançar Parceiros de Deus
	@PostMapping(path="/cadastrarPd")
	public @ResponseBody int cadastrarPd(@RequestBody Pd pd) {
		pd.setData(Calendar.getInstance());
		return this.pdService.cadastrarPd(pd);
	}
	@PostMapping(path="/excluirLancamentoPd")
	public @ResponseBody int excluirLancamentoPd(@RequestBody int id){
		return this.pdService.excluirLancamentoPd(id);
	}
	@PostMapping(path="/lancarPd")
	public @ResponseBody int lancarPd(@RequestBody Pd pd) {
		Calendar data = Calendar.getInstance();
		return this.pdService.lancarPd(pd, data, pd.getSemana());
	}
	@PostMapping(path="/listaPdCelula")
	public @ResponseBody List<Pd> listaPd(@RequestBody int semana){
		if(semana == 0){
		DateFormat df = new SimpleDateFormat("W");
		semana = Integer.parseInt(df.format( new Date()));
		}
		return this.pdService.listaPdCelula(semana, Calendar.getInstance());
	}	
	@PostMapping(path="/listaPdCompleto")
	public @ResponseBody List<Pd> listaPdCompleto(@RequestBody int semana){
		if(semana == 0){
		DateFormat df = new SimpleDateFormat("W");
		semana = Integer.parseInt(df.format( new Date()));
		}
		return this.pdService.listaPdCompleto(semana, Calendar.getInstance());
	}
	@PostMapping(path="/listaPdEquipePorCiclo")
	public @ResponseBody List<Pd> listaPdEquipePorMes(@RequestBody int semana){
		if(semana == 0){
		DateFormat df = new SimpleDateFormat("W");
		semana = Integer.parseInt(df.format( new Date()));
		}
		return this.pdService.listaPdEquipePorCiclo(semana, Calendar.getInstance());
	}
	@GetMapping(path="/listaPdPorMes")
	public @ResponseBody List<Pd> listaPdPorMes(){
		return this.pdService.listaPdPorMes(Calendar.getInstance());
	}
	@GetMapping(path="/listaPdPorMesEquipe")
	public @ResponseBody List<Pd> listaPdPorMesEquipe(){
		return this.pdService.listaPdPorMesEquipe(Calendar.getInstance());
	}
	@GetMapping(path="/listaPdIndividualPorMes")
	public @ResponseBody List<Pd> listaPdIndividualPorMes(){
		return this.pdService.listaPdIndividualPorMes(Calendar.getInstance());
	}
	@GetMapping(path="/lideres")
	public @ResponseBody Iterable<Lider> lideres(){
		return this.pdService.lideres();
	}
	@GetMapping(path="/equipes")
	public @ResponseBody Iterable<Equipe> equipes(){
		return this.pdService.equipes();
	}
	@GetMapping(path="/subs")
	public @ResponseBody Iterable<Sub> subs(){
		return this.pdService.subs();
	}
	@PostMapping(path="/cadastrarSub")
	public @ResponseBody int cadastrarSub(@RequestBody Sub sub) {
		return this.pdService.cadastrarSub(sub);
	}
	@PostMapping(path="/novoRelatorio")
	public @ResponseBody int novoRelatorio(@RequestBody int semana){
		return this.pdService.novoRelatorio(semana);
	}
	@GetMapping(path="/cicloAtual")
	public @ResponseBody int cicloAtual(){
		DateFormat df = new SimpleDateFormat("W");
		return Integer.parseInt(df.format( new Date()));
	}
	@GetMapping(path="/perfil/{idEquipe}")
	public @ResponseBody Perfil perfil( @RequestBody @PathVariable int idEquipe){
		return this.pdService.perfil(idEquipe);
	}
}
