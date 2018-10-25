import { Component, OnInit } from '@angular/core';
import { PdService } from '../../pd.service';
import { Pd } from '../models/pd.model';
import { Equipe } from '../models/equipe.model';
import { Lider } from '../models/lider.model';
import { Router, ActivatedRoute, RouterStateSnapshot } from '@angular/router';

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

  constructor(private service: PdService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit() {
    this.service.listaLider().subscribe(data=>{this.lideres = data})
    const id = +this.route.snapshot.paramMap.get('id')
    const ciclo = +this.route.snapshot.paramMap.get('ciclo')
    this.id = id
    this.ciclo = ciclo
    this.service.getEquipe(id).subscribe(data=>{this.equipe = data})
    this.service.listaPdCelula(ciclo).subscribe(data=> {this.PD = data})
  }
  public cadastrarPd(pd){
    this.pd.equipe = this.equipe
    this.pd.lider = pd.lider
    this.pd.celula = pd.celula
    this.pd.individual = pd.individual
    this.pd.semana = this.ciclo
    this.service.cadastrarPd(this.pd).subscribe(data=>{this.mensagem = data})
    console.log(this.pd)
  }
  public novoRelatorio(semana){
    this.service.novoRelatorio(semana).subscribe(console.log)
  }
  hoje: number = Date.now()
}
