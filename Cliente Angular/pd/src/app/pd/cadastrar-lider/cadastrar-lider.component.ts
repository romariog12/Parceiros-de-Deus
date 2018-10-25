import { Component, OnInit } from '@angular/core';
import { PdService } from '../../pd.service';
import { Lider } from '../models/lider.model';

@Component({
  selector: 'app-cadastrar-lider',
  templateUrl: './cadastrar-lider.component.html',
  styleUrls: ['./cadastrar-lider.component.css']
})
export class CadastrarLiderComponent implements OnInit {

  constructor(private service: PdService) { }
  public lider: Lider
  public mensagem;
  ngOnInit() {
    this.lider= new Lider;
  }
  public cadastrarLider(){
    this.service.cadastrarLider(this.lider).subscribe(data=>{this.mensagem = data});
  }

}
