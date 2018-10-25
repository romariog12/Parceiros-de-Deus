import { Component, OnInit } from '@angular/core';
import { PdService } from '../pd.service';
import { Pd } from '../pd/models/pd.model';
import * as $ from 'jquery';
import { cicloAtual } from '../pd/models/ciclo.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.css']
})
export class RelatorioComponent implements OnInit {
  public pd: Pd[]
  public pdMensal: Pd[]
  public ciclo
  public semana
  public data
  public count: number
  public individualSuccess
  public lancar
  public individualWarning
  public cicloAtual: cicloAtual  = new cicloAtual
  public relatorio: boolean = false
  public completo = false;
  public totalIndividual: number =  0;
  public totalCelula: number =  0;
  constructor(private service: PdService) { }

  ngOnInit() {
    this.reiniciaTotal();
    this.service.cicloAtual().subscribe(data =>{
      this.ciclo = data,
      this.semana = this.ciclo,
       $("#"+this.semana).html("Atual <span class='badge badge-warning'>"+this.semana+"</span>"),
       $("#"+this.semana).addClass("active")});       
    this.service.listaPdEquipePorCiclo(0).subscribe(data=>{
      this.pd = data, 
      this.count = this.pd[0].semana,
      this.data = this.pd[0].data;
      for (let index = 0; index < this.pd.length; index++) {
        this.totalCelula += this.pd[index].celula;
        this.totalIndividual += this.pd[index].individual;
        
      }
    });
  }
  public pdCiclo(semana){
    this.reiniciaTotal();
    this.completo = false
    this.relatorio = false
    this.ciclo = semana;
    this.service.listaPdEquipePorCiclo(semana).subscribe(data=> {
      this.pd = data;
      for (let index = 0; index < this.pd.length; index++) {
        this.totalCelula += this.pd[index].celula;
        this.totalIndividual += this.pd[index].individual;
        
      }
    })
    $(".nav-link").removeClass("active")
    $("#"+this.ciclo).addClass("active")
    this.individualSuccess = ""
    this.individualWarning = ""
    this.lancar = "<button class='btn btn-sm btn-outline-warning'<img routerLink='lancarPd/{{pd.equipe.idEquipe}}/{{ciclo}}' src='../../assets/lancar.png'/></button>"
  }
  pdCompletoPorCiclo(semana){
    this.service.listaPdCelula(semana).subscribe(data=> {this.pd = data})
    this.completo = true
  }
  public excluirLancamentoPd(id){
    this.service.excluirLancamentoPd(id).subscribe(data =>{$("#pd-"+id).hide(1000)})
  }
  public novoRelatorio(semana){
    this.service.novoRelatorio(semana).subscribe(console.log)
  }
  public pdMes(){
    this.reiniciaTotal();
    this.completo = true
    this.service.listaPdPorMes().subscribe(data =>{
      this.pd=data;
      for (let index = 0; index < this.pd.length; index++) {
        this.totalCelula += this.pd[index].celula;
        this.totalIndividual += this.pd[index].individual;
        
      }
    })
    this.relatorio = true
    $(".nav-link").removeClass("active")
    $("#mesInteiro").addClass("active")
    this.individualSuccess = ""
    this.individualWarning = ""
    this.lancar = ""
    this.ciclo = 0;
  }
  public pdMesEquipe(){
    this.reiniciaTotal();
    this.completo = false
    this.relatorio = true
    this.service.listaPdPorMesEquipe().subscribe(data =>{
      this.pd=data;
      for (let index = 0; index < this.pd.length; index++) {
        this.totalCelula += this.pd[index].celula;
        this.totalIndividual += this.pd[index].individual;
      }
    })
    $(".nav-link").removeClass("active")
    $("#mesEquipe").addClass("active")
    this.individualSuccess = ""
    this.individualWarning = ""
    this.lancar = ""
    this.ciclo = 0;
  }
  public pdIndividualPorMes(){
    this.reiniciaTotal();
    this.completo = true
    this.relatorio = true
    this.service.listaPdIndividualPorMes().subscribe(data=>{
      this.pd=data;
      for (let index = 0; index < this.pd.length; index++) {
        this.totalCelula += this.pd[index].celula;
        this.totalIndividual += this.pd[index].individual; 
      }
    })
    $(".nav-link").removeClass("active")
    $("#mesIndividual").addClass("active")
    this.individualSuccess = " alert-link"
    this.individualWarning = " alert-link"
    
    this.ciclo = 0;
  }
  public reiniciaTotal(){
    this.totalCelula = 0;
    this.totalIndividual = 0;
  }
}
