import { Component, OnInit } from '@angular/core';
import { PdService } from '../../pd.service';
import { Equipe } from '../models/equipe.model';
import { Lider } from '../models/lider.model';
import { Sub } from '../models/sub.model';

@Component({
  selector: 'app-sub',
  templateUrl: './sub.component.html',
  styleUrls: ['./sub.component.css']
})
export class SubComponent implements OnInit {

  constructor(private service: PdService) { }
  public sub: Sub = new Sub();
  public equipe: Equipe[];
  public lider: Lider[];
  ngOnInit() {
    this.service.listaEquipe().subscribe(data =>{this.equipe = data});
    this.service.listaLider().subscribe(data=> {this.lider = data})
  }
  public cadastrarSub(){
    this.service.cadastrarSub(this.sub).subscribe(data =>{console.log()});
  }

}
