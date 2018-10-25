import { Component, OnInit } from '@angular/core';
import { PdService } from '../pd.service';
import { Equipe } from './models/equipe.model';
import { Lider } from './models/lider.model';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-cadastrar-equipe',
  templateUrl: './cadastrar-equipe.component.html',
  styleUrls: ['./cadastrar-equipe.component.css']
})
export class CadastrarEquipeComponent implements OnInit {
  public lider: Lider;
  public equipe: Equipe;
  public listaLider: Lider[];
  public mensagem;
  constructor(private service: PdService) { }

  ngOnInit(): void {
    this.lider = new Lider;
    this.equipe = new Equipe;
    this.service.listaLider().subscribe(data =>{this.listaLider = data});
  }
 
  public cadastrarEquipe(){
    this.service.cadastrarEquipe(this.equipe).subscribe(data =>{this.mensagem = data});
  }

}
