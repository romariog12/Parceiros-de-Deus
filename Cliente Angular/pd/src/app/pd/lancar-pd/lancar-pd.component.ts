import { Component, OnInit } from '@angular/core';
import { PdService } from '../../pd.service';
import { Pd } from '../models/pd.model';
import { Equipe } from '../models/equipe.model';
import { Lider } from '../models/lider.model';
import { Router, ActivatedRoute, RouterStateSnapshot } from '@angular/router';
import { RelatorioComponent } from '../../relatorio/relatorio.component';

@Component({
  selector: 'app-lancar-pd',
  templateUrl: './lancar-pd.component.html',
  styleUrls: ['./lancar-pd.component.css']
})
export class LancarPdComponent implements OnInit {
  public pd: Pd = new Pd;
  public PD: Pd[]
  public equipes: Equipe[]
  public lideres: Lider[]
  public id
  public equipe: Equipe;
  public mensagem
  public ciclo
  public mes;
  public ano


  constructor(private service: PdService, private route: ActivatedRoute, private router:Router, private relatorio: RelatorioComponent) { }

  ngOnInit() {
    this.service.listaLider().subscribe(data=>{this.lideres = data})
    const id = +this.route.snapshot.paramMap.get('id')
    const ciclo = +this.route.snapshot.paramMap.get('ciclo')
    this.mes = this.route.snapshot.paramMap.get('mes')
    this.ano = this.route.snapshot.paramMap.get('ano')
    this.id = id
    this.ciclo = ciclo
    this.service.getEquipe(id).subscribe(data=>{this.equipe = data})
    this.service.listaPdCelula(ciclo, +this.mes, +this.ano).subscribe(data=> {this.PD = data})
  }
  public cadastrarPd(pd){
    this.pd.equipe = this.equipe
    this.pd.lider = pd.lider
    this.pd.celula = pd.celula
    this.pd.individual = pd.individual
    this.pd.semana = this.ciclo
    if(this.mes != 0 && this.ano != 0)
      this.pd.data = new Date(this.ano+"-"+this.mes+"-01")
    else
      this.pd.data = new Date
    this.service.cadastrarPd(this.pd).subscribe(data=>{this.mensagem = data})
    console.log(this.pd)
  }
  public novoRelatorio(semana){
    this.service.novoRelatorio(semana,0 ,0).subscribe(console.log)
  }
  hoje: number = Date.now()
}
