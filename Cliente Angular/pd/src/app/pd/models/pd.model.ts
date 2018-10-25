import { Equipe } from "./equipe.model";
import { Lider } from "./lider.model";

export class Pd{
    idpd: number;
    individual: number;
    celula: number;
    data: Date;
    equipe: Equipe;
    lider: Lider;
    semana: number
}