import { Component, OnInit } from "@angular/core";
import { PdService } from "../pd.service";
import { Equipe } from "./models/equipe.model";
import { Lider } from "./models/lider.model";
import { Sub } from "./models/sub.model";
import * as $ from 'jquery';
import { paginacao } from "./models/paginacao.model";

@Component({
    templateUrl: './equipe.component.html'
})
export class EquipeComponent implements OnInit {
    private lider: Lider[];
    private equipe: Equipe[];
    private sub: Sub;
    public abaAtiva = "active"
    public acao = "ativos"
    private mensagem;
    public paginacao: paginacao = new paginacao()
    public paginacaoEquipes = [];
    public sequenciaPaginacao
    constructor(private service: PdService) {
    }
    ngOnInit(): void {
        if (this.acao == "inativos")
            this.equipesInativas(0)
        else
            this.equipesAtivas(0)
    }
    public excluirEquipe(id: number) {
        this.service.excluirEquipe(id).subscribe(data => {
        this.mensagem = data;
            if (this.mensagem == 1) {
                $("#equipe-" + id).hide(1000)
            }
        });
    }
    public equipesInativas(page: number) {
        this.acao = "inativos"
        this.paginacao.anterior = 88888
        this.paginacao.proximo = 99999
        $("#abaEquipesAtivas").removeClass(this.abaAtiva)
        $("#abaEquipesInativas").addClass(this.abaAtiva)
        this.service.equipesInativas(page).subscribe(data => {
            this.paginacaoEquipes = data,
            this.equipe = this.paginacaoEquipes['content'],
            this.paginacao.totalPagina = this.paginacaoEquipes['totalPages'],
            this.paginacao.numeroPagina = this.paginacaoEquipes['number']
            this.paginacao.totalElementos = this.paginacaoEquipes['totalElements']
            this.paginacao.elementosPorPagina = this.paginacaoEquipes['numberOfElements']
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
    public equipesAtivas(page: number) {
        this.acao = "ativos"
        this.paginacao.anterior = 88888
        this.paginacao.proximo = 99999
        $("#abaEquipesAtivas").addClass(this.abaAtiva)
        $("#abaEquipesInativas").removeClass(this.abaAtiva)
        this.service.listaEquipe(page).subscribe(data => {
        this.paginacaoEquipes = data,
            this.equipe = this.paginacaoEquipes['content'],
            this.paginacao.totalPagina = this.paginacaoEquipes['totalPages'],
            this.paginacao.numeroPagina = this.paginacaoEquipes['number']
            this.paginacao.totalElementos = this.paginacaoEquipes['totalElements']
            this.paginacao.elementosPorPagina = this.paginacaoEquipes['numberOfElements']
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
    public inativarEquipe(id: number) {
        this.service.inativarEquipe(id).subscribe(data => {
        this.mensagem = data;
            if (this.mensagem == 1) {
                $("#equipe-" + id).hide(1000)
            }
        });

    }
    public ativarEquipe(id: number) {
        this.service.ativarEquipe(id).subscribe(data => {
        this.mensagem = data;
            if (this.mensagem == 1) {
                $("#equipe-" + id).hide(1000)
            }
        });

    }

    public excluirSub(id: number) {
        this.service.excluirSub(id).subscribe($("#sub-" + id).hide(1000));
    }

}