import { OnInit, Component } from "@angular/core";
import { Lider } from "../models/lider.model";
import { PdService } from "../../pd.service";
import { ActivatedRoute } from "@angular/router";

@Component({
    selector: 'app-editar-lider',
    templateUrl: './editarLider.component.html'
})
export class editarLider implements OnInit {
    public mensagem
    public lider: Lider
    public constructor(private service: PdService, private route: ActivatedRoute) { }
    ngOnInit(): void {
        const id = +this.route.snapshot.paramMap.get('id')
        this.service.lider(id).subscribe(data=>{
            this.lider = data
        })
        throw new Error("Method not implemented.");
    }
    public editarLider() {
        this.service.editarLider(this.lider).subscribe(data => {
            this.mensagem = data
        })
    }

}