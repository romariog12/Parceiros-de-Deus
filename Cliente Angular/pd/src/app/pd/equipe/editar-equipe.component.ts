import { Component, OnInit } from '@angular/core';
import { PdService } from '../../pd.service';
import { Equipe } from '../models/equipe.model';
import { Lider } from '../models/lider.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-editar-equipe',
  templateUrl: './editar-equipe.component.html'
})
export class EditarEquipeComponent implements OnInit {
  public lider: Lider;
  public equipe: Equipe;
  public lideres: Lider[];
  public mensagem;
  constructor(private service: PdService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.lider = new Lider;
    let id = +this.route.snapshot.paramMap.get('id')
    this.service.getEquipe(id).subscribe(data=>{
        this.equipe = data
    });
    this.service.lideres().subscribe(data =>{this.lideres = data});
  }
 
  public editarEquipe(){
    this.service.editarEquipe(this.equipe).subscribe(data =>{this.mensagem = data});
  }

}
