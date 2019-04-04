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
    public getEquipe: Equipe
    public abaAtiva = "active"
    public acao = "ativos"
    public subs = []
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
        let c = confirm("Tem certeza que deseja excluir definitivamente esta equipe?")
        if (c) {
        this.service.excluirEquipe(id).subscribe(data => {
            this.mensagem = data;
            if (this.mensagem == 3) {
                this.service.getEquipe(id).subscribe(data => {
                    this.getEquipe = data,
                        this.equipe.splice(0, this.equipe.length)
                    this.equipe.push(this.getEquipe)
                    this.sequenciaPaginacao.splice(0, this.sequenciaPaginacao.length)
                })

            }
            if (this.mensagem == 1) {
                $("#equipe-" + id).hide(1000)
            }
        });
    }
    return false
    }
    public equipesInativas(page: number) {
        this.mensagem = 0
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
        this.mensagem = 0
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
    public inativarEquipe(equipe: Equipe) {
        this.mensagem = 0
        let c = confirm("Tem certeza que deseja inativar esta equipe?")
        if (c) {
            this.service.inativarEquipe(equipe).subscribe(data => {
                this.mensagem = data;
                if (this.mensagem == 3) {
                    this.service.getEquipe(equipe.idEquipe).subscribe(data => {
                        this.getEquipe = data
                        for (let index = 0; index < this.getEquipe.subs.length; index++) {
                            if (this.getEquipe.subs[index].status == -1)
                                this.getEquipe.subs.splice(index)
                        }
                        this.equipe.splice(0, this.equipe.length)
                        this.equipe.push(this.getEquipe)
                        this.sequenciaPaginacao.splice(0, this.sequenciaPaginacao.length)
                    })
                }
                if (this.mensagem == 1) {
                    $("#equipe-" + equipe.idEquipe).hide(1000)
                }
            });
        }
        return false

    }
    public ativarEquipe(equipe: Equipe) {
        this.mensagem = 0
        this.service.ativarEquipe(equipe).subscribe(data => {
            this.mensagem = data;
            if (this.mensagem == 1) {
                $("#equipe-" + equipe.idEquipe).hide(1000)
            }
        });

    }

    public excluirSub(id: number) {
        let c = confirm("Tem certeza que deseja excluir a sub-equipe?")
        if(c)
            this.service.excluirSub(id).subscribe($("#sub-" + id).hide(1000));
        return false
    }
    public inativarSub(sub:Sub) {
        let c = confirm("Tem certeza que deseja inativar a sub-equipe?")
        if(c){
            this.service.inativarSub(sub).subscribe(data => {
                if (data == 1) {
                    $("#sub-" + sub.idSub).hide(1000)
                }
            })
        }
        return false
    }
}