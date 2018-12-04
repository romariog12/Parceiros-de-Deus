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
  public abaAtiva = "active";
  public acao = "ativos";
  public paginacaoLideres = [];
  public totalPagina;
  public numeroPagina;
  public paginacao = [];
  public totalElementos;
  public anterior: number = 88888;
  public proximo: number = 99999;
  constructor(private service: PdService) { }
  lider: Lider[];
  ngOnInit() {
    if (this.acao == "inativos")
      this.lideresInativos(0)
    else
      this.lideresAtivos(0)
  }
  public lideresInativos(page: number) {
    this.acao = "inativos"
    this.anterior = 88888
    this.proximo = 99999
    $("#abaLideresAtivos").removeClass(this.abaAtiva)
    $("#abaLideresInativos").addClass(this.abaAtiva)
    this.service.lideresInativos(page).subscribe(data => {
      this.paginacaoLideres = data,
        this.lider = this.paginacaoLideres['content'],
        this.totalPagina = this.paginacaoLideres['totalPages'],
        this.numeroPagina = this.paginacaoLideres['number'];
      this.totalElementos = this.paginacaoLideres['totalElements']
      let p = []
      for (let index = 0; index < this.totalPagina; index++) {
        p[index] = index
        this.paginacao = p;
      }
      if (this.numeroPagina == 0) {
        this.anterior = this.numeroPagina
      }
      if (this.numeroPagina + 1 == this.totalPagina) {
        this.proximo = this.numeroPagina
      }
      $(".page-item").removeClass("active")
      $("#page-" + this.numeroPagina).addClass("active")
    });

  }
  public lideresAtivos(page: number) {
    this.acao = "ativos"
    $("#abaLideresAtivos").addClass(this.abaAtiva)
    $("#abaLideresInativos").removeClass(this.abaAtiva)
    this.service.listaLider(page).subscribe(data => {
      this.paginacaoLideres = data
      this.lider = this.paginacaoLideres['content']
      this.totalPagina = this.paginacaoLideres['totalPages']
      this.numeroPagina = this.paginacaoLideres['number']
      this.totalElementos = this.paginacaoLideres['totalElements']
      let p = []
      for (let index = 0; index < this.totalPagina; index++) {
        p[index] = index
        this.paginacao = p;
      };
      if (this.numeroPagina == 0) {
        this.anterior = this.numeroPagina
      }
      if (this.numeroPagina + 1 == this.totalPagina) {
        this.proximo = this.numeroPagina
      }
      $(".page-item").removeClass("active")
      $("#page-" + this.numeroPagina).addClass("active")
      this.anterior = 88888
      this.proximo = 99999
    });
  }
  public inativarLider(id: number) {
    let c = confirm("Tem certeza que deseja inativar este líder?")
    if (c) {
    return this.service.inativarLider(id).subscribe(
      data => {
        this.mensagem = data;
        if (this.mensagem == 1 || this.mensagem == 5) {
          $("#lider-" + id).hide(1000)
        }
      })
    }
    else
      return false
  }

  public ativarLider(id: number) {
    let c = confirm("Tem certeza que deseja ativar este líder?")
    if (c) {
      return this.service.ativarLider(id).subscribe(
        data => {
          this.mensagem = data;
          if (this.mensagem == 1) {
            $("#lider-" + id).hide(1000)
          }
        })
    }
    else
      return false
  }
  public excluirLider(id: number) {
    let c = confirm("Tem certeza que deseja excluir definitivamente este líder?")
    if(c){
      return this.service.excluirLider(id).subscribe(
        data => {
          this.mensagem = data;
          console.log(this.mensagem);
  
          if (this.mensagem == 1) {
            $("#lider-" + id).hide(1000)
          }
        })
    }
   return false;
  }
}
