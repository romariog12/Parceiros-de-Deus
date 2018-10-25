import { Component, OnInit } from '@angular/core';
import { PdService } from '../pd.service';
import { Equipe } from './models/equipe.model';
import { Lider } from './models/lider.model';
import { Pd } from './models/pd.model';

@Component({
  selector: 'app-pd',
  templateUrl: './pd.component.html',
  styleUrls: ['./pd.component.css']
})
export class PdComponent implements OnInit {
  lider: Lider = new Lider;
  equipe: Equipe = new Equipe;
  pd: Pd = new Pd;
  constructor(private service: PdService) { }

  ngOnInit() {
  }
  cadastrarEquipe(): void{
    this.service.cadastrarEquipe(this.equipe)
  }
  cadastrarLider(): void{
    this.service.cadastrarLider(this.lider);
  }
  cadastrarPd():void{
    this.service.cadastrarPd(this.pd);
  } 

}
