import { Component, OnInit } from '@angular/core';
import { PdService } from '../pd.service';
import { Perfil } from '../pd/models/perfil.model';
import { ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
 public perfil : Perfil
 public mensagem;
 public aba: string = "subsAba"
  constructor(private service:PdService,private route: ActivatedRoute) { }

  ngOnInit() {
    const idEquipe = +this.route.snapshot.paramMap.get('id')
    this.service.perfil(idEquipe).subscribe(data=>{this.perfil = data, console.log(this.perfil);
    })
  }
  public inativarSub(id){
    this.service.inativarSub(id).subscribe(data=>{this.mensagem = data;
      if(this.mensagem == 1) {
        $("#sub-"+id).hide(1000)
      }
    })
  }
    public ativarSub(id){
      this.service.ativarSub(id).subscribe(data=>{this.mensagem = data;
        if(this.mensagem == 1) {
          $("#sub-"+id).hide(1000)
        }
    })
  }
  public sub(){
    this.aba = "subsAba"
    $(".nav-link").removeClass("active");
    $("#perfilSubs").addClass("active");
  };
  public administracao(){
    this.aba = "administracaoAba"
    $(".nav-link").removeClass("active")
    $("#perfilAdministracao").addClass("active")
  };
  

}
