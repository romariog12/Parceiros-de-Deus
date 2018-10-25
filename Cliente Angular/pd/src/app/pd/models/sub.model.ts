import { Lider } from "./lider.model";
import { Equipe } from "./equipe.model";

export class Sub {
    idSub: number;
    nome: String;
    lider: Lider;
    equipe: Equipe;
    subs: Sub[];
    status: number;
}