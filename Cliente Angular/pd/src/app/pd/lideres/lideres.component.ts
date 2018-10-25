import { Component, OnInit } from '@angular/core';
import { PdService } from '../../pd.service';
import { Lider } from '../models/lider.model';

@Component({
  selector: 'app-lideres',
  templateUrl: './lideres.component.html',
  styleUrls: ['./lideres.component.css']
})
export class LideresComponent implements OnInit {

  constructor(private service: PdService) { }
  lider: Lider[];
  ngOnInit() {
    this.service.listaLider().subscribe(data=>{this.lider=data});
  }

}
