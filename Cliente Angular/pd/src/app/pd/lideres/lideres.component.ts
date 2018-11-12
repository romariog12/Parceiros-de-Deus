import { Component, OnInit } from '@angular/core';
import { PdService } from '../../pd.service';
import { Lider } from '../models/lider.model';
import * as $ from 'jquery';

@Component({
  selector: 'app-lideres',
  templateUrl: './lideres.component.html',
  styleUrls: ['./lideres.component.css']
})
export class LideresComponent implements OnInit {
  private mensagem;
  public abaAtiva = "active"
  public acao = "ativos"
  constructor(private service: PdService) { }
  lider: Lider[];
  ngOnInit() {
    this.lideresAtivos()
  }
  public lideresInativos(){
    this.acao = "inativos"
    $("#abaLideresAtivos").removeClass(this.abaAtiva)
    $("#abaLideresInativos").addClass(this.abaAtiva)
    this.service.lideresInativos().subscribe(data => { this.lider = data });
  }
  public lideresAtivos(){
    this.acao = "ativos"
    $("#abaLideresAtivos").addClass(this.abaAtiva)
    $("#abaLideresInativos").removeClass(this.abaAtiva)
    this.service.listaLider().subscribe(data => { this.lider = data });
  }
  public inativarLider(id: number) {
    return this.service.inativarLider(id).subscribe(
      data => {
        this.mensagem = data;
        if (this.mensagem == 1) {
          $("#lider-" + id).hide(1000)
        }
      })
  }
  public ativarLider(id: number) {
    return this.service.ativarLider(id).subscribe(
      data => {
        this.mensagem = data;
        if (this.mensagem == 1) {
          $("#lider-" + id).hide(1000)
        }
      })
  }
  public excluirLider(id: number) {
    return this.service.excluirLider(id).subscribe(
      data => {
        this.mensagem = data;
        if (this.mensagem == 1) {
          $("#lider-" + id).hide(1000)
        }
      })
  }
}
