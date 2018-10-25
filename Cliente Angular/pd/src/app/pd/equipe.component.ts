import { Component, OnInit } from "@angular/core";
import { PdService } from "../pd.service";
import { Equipe } from "./models/equipe.model";
import { Lider } from "./models/lider.model";
import { Sub } from "./models/sub.model";
import * as $ from 'jquery';

@Component({
    templateUrl: './equipe.component.html'
})
export class EquipeComponent implements OnInit {
    private lider: Lider [];
    private equipe: Equipe [];
    private sub: Sub;
    private mensagem;
    constructor(private Service: PdService){
    }
    ngOnInit(): void {
       this.Service.listaEquipe().subscribe(data=>{this.equipe=data}); 
    }
    public excluirEquipe(id: number){
        this.Service.excluirEquipe(id).subscribe(data =>{this.mensagem = data;
            if(this.mensagem == 1) {
                $("#equipe-"+id).hide(1000)
            }
            });
        
    }
    public excluirSub(id: number){
        this.Service.excluirSub(id).subscribe($("#sub-"+id).hide(1000));
      }
    
}