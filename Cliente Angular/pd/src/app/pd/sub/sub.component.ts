import { Component, OnInit } from '@angular/core';
import { PdService } from '../../pd.service';
import { Equipe } from '../models/equipe.model';
import { Lider } from '../models/lider.model';
import { Sub } from '../models/sub.model';
import { ActivatedRoute } from '@angular/router';
import { paginacao } from '../models/paginacao.model';

@Component({
  selector: 'app-sub',
  templateUrl: './sub.component.html',
  styleUrls: ['./sub.component.css']
})
export class SubComponent implements OnInit {

  constructor(private service: PdService,private route: ActivatedRoute) { }
  public sub:Sub = new Sub();
  public equipe: Equipe[];
  public lider: Lider[];
  public mensagem;
  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id')
    if(id){
      this.service.sub(id).subscribe(data=>{
        this.sub = data
      })
    }
    this.service.equipes().subscribe(data =>{this.equipe = data})
    this.service.lideres().subscribe(data=> {this.lider = data})
  }
  public cadastrarSub(){
    this.service.cadastrarSub(this.sub).subscribe(data =>{
      this.mensagem = data
    });
  }
}
