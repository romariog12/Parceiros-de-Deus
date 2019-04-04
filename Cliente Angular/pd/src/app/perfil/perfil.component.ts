import { Component, OnInit } from '@angular/core';
import { PdService } from '../pd.service';
import { Perfil } from '../pd/models/perfil.model';
import { ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';
import { paginacao } from '../pd/models/paginacao.model';
import { Sub } from '../pd/models/sub.model';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  public perfil: Perfil
  public mensagem;
  public paginacao: paginacao = new paginacao()
  public sequenciaPaginacao
  public paginacaoSubs = [];
  public abaAtiva = "active"
  public acao = "ativos"
  public subs: Sub
  constructor(private service: PdService, private route: ActivatedRoute) { }

  ngOnInit() {
    const idEquipe = +this.route.snapshot.paramMap.get('id')
    this.service.perfil(idEquipe, 0).subscribe(data => { this.perfil = data })
  }
  public inativarSub(sub: Sub) {
    let c = confirm("Tem certeza que deseja inativar a sub-equipe?")
    if(c){
      this.service.inativarSub(sub).subscribe(data => {
        this.mensagem = data
        if (this.mensagem == 1) {
          $("#sub-"+sub.idSub).hide(1000)
        }})
    }
    else 
      return false    
  }
  public ativarSub(sub: Sub) {
    let c = confirm("Tem certeza que deseja ativar a sub-equipe?")
    if(c){
      this.service.ativarSub(sub).subscribe(data => {
      this.mensagem = data
      if (this.mensagem == 1) {
        $("#sub-"+sub.idSub).hide(1000)
      }
      })
    }
    else
      return false;
  }
  public sub() {
    $(".nav-link").removeClass("active");
    $("#perfilSubs").addClass("active");
  };
  public administracao() {
    $(".nav-link").removeClass("active")
    $("#perfilAdministracao").addClass("active")
  };
  public subsAtivas(page: number) {
    this.mensagem = 0
    this.acao = "ativos"
    this.paginacao.anterior = 88888
    this.paginacao.proximo = 99999
    $("#abaSubsAtivas").addClass(this.abaAtiva)
    $("#abaSubsInativas").removeClass(this.abaAtiva)
    const idEquipe = +this.route.snapshot.paramMap.get('id')
    this.service.perfil(idEquipe, page).subscribe(data => {
      console.log(data)
      this.perfil = data,
      this.subs = this.paginacaoSubs['subs'],
      this.paginacao.totalPagina = this.paginacaoSubs['subs.totalPages'],
      this.paginacao.numeroPagina = this.paginacaoSubs['subs.number']
      this.paginacao.totalElementos = this.paginacaoSubs['subs.totalElements']
      this.paginacao.elementosPorPagina = this.paginacaoSubs['subs.numberOfElements']
      let p = []
      for (let index = 0; index < this.paginacao.totalPagina; index++) {
        p[index] = index
        this.sequenciaPaginacao = p;
      }
      if (this.paginacao.numeroPagina == 0) {
        this.paginacao.anterior = this.paginacao.numeroPagina
      }
      if (this.paginacao.numeroPagina + 1 == this.paginacao.totalPagina) {
        this.paginacao.proximo = this.paginacao.numeroPagina
      }
      $(".page-item").removeClass("active")
      $("#page-" + this.paginacao.numeroPagina).addClass("active")
    });
  }
  public subsInativas(page: number) {
    this.mensagem = 0
    this.acao = "inativos"
    this.paginacao.anterior = 88888
    this.paginacao.proximo = 99999
    $("#abaSubsAtivas").removeClass(this.abaAtiva)
    $("#abaSubsInativas").addClass(this.abaAtiva)
    const idEquipe = +this.route.snapshot.paramMap.get('id')
    this.service.perfil(idEquipe, page).subscribe(data => {
        this.perfil = data,
            this.subs = this.paginacaoSubs['subs'],
            this.paginacao.totalPagina = this.paginacaoSubs['totalPages'],
            this.paginacao.numeroPagina = this.paginacaoSubs['number']
        this.paginacao.totalElementos = this.paginacaoSubs['totalElements']
        this.paginacao.elementosPorPagina = this.paginacaoSubs['numberOfElements']
        let p = []
        for (let index = 0; index < this.paginacao.totalPagina; index++) {
            p[index] = index
            this.sequenciaPaginacao = p;
        }
        if (this.paginacao.numeroPagina == 0) {
            this.paginacao.anterior = this.paginacao.numeroPagina
        }
        if (this.paginacao.numeroPagina + 1 == this.paginacao.totalPagina) {
            this.paginacao.proximo = this.paginacao.numeroPagina
        }
        $(".page-item").removeClass("active")
        $("#page-" + this.paginacao.numeroPagina).addClass("active")
    });
}
public excluirSub(id:number){
  let c = confirm("Tem certeza que deseja excluir a Sub-Equipe?")
  if(c){
    this.service.excluirSub(id).subscribe(data=>{
      this.mensagem = data
      if (this.mensagem == 1) {
        $("#sub-"+id).hide(1000)
      }
    })
  }
  return false
}


}
