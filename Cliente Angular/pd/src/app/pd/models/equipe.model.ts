import { Lider } from "./lider.model";
import { Sub } from "./sub.model";


export class Equipe{
    idEquipe: number;
    nome: string;
    lider: Lider;
    subs: Sub[];
}