import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Equipe } from './pd/models/equipe.model';
import { Lider } from './pd/models/lider.model';
import { Pd } from './pd/models/pd.model';
import { Sub } from './pd/models/sub.model';
import { Perfil } from './pd/models/perfil.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const httpUrl = 'http://localhost:8080/';
@Injectable()
export class PdService {
  constructor(private http: HttpClient) { }
  public cadastrarEquipe (equipe){
    return this.http.post<Equipe>(httpUrl+'pd/cadastrarEquipe',equipe, httpOptions);
  }
  public editarEquipe (equipe){
    return this.http.post<Equipe>(httpUrl+'pd/editarEquipe',equipe, httpOptions);
  }
  public cadastrarLider(lider){
    return this.http.post<Lider>(httpUrl+'pd/cadastrarlider', lider, httpOptions);
  }
  public cadastrarPd(pd){
   return this.http.post(httpUrl+'pd/lancarPd', pd, httpOptions);
  }
  public listaPd(semana: number){
    return this.http.post<Pd[]>(httpUrl+'pd/listaPdCompleto', semana)
  }
  public listaPdCelula(semana: number,mes: number,ano: number){
    return this.http.get<Pd[]>(httpUrl+'pd/listaPdCelula/'+semana+"/"+mes+"/"+ano)
  }
  public restartarRelatorio(semana: number,mes: number,ano: number){
    return this.http.get(httpUrl+'pd/restartarRelatorio/'+semana+"/"+mes+"/"+ano)
  }
  public listaPdEquipePorCiclo(semana: number,mes: number,ano: number){
    return this.http.get<Pd[]>(httpUrl+'pd/listaPdEquipePorCiclo/'+semana+"/"+mes+"/"+ano)
  }
  public listaPdPorMes(mes,ano){
    console.log(mes)
    return this.http.get<Pd[]>(httpUrl+'pd/listaPdPorMes/'+mes+'/'+ano)
  }
  public listaPdPorMesEquipe(mes,ano){
    return this.http.get<Pd[]>(httpUrl+'pd/listaPdPorMesEquipe/'+mes+'/'+ano)
  }
  public listaPdIndividualPorMes(mes, ano){
    return this.http.get<Pd[]>(httpUrl+'pd/listaPdIndividualPorMes/'+mes+'/'+ano)
  }
  public cadastrarSub (sub){
    return this.http.post<Sub>(httpUrl+'pd/cadastrarSub',sub, httpOptions);
  }
  public editarSub (sub){
    return this.http.post<Sub>(httpUrl+'pd/editarSub',sub, httpOptions);
  }
  public getEquipe(id){
    return this.http.get<Equipe>(httpUrl+'pd/getEquipe/'+id)
  }
  public listaEquipe(page){
    return this.http.get<Equipe[]>(httpUrl+'pd/equipes/'+page)
  }
  public equipes(){
    return this.http.get<Equipe[]>(httpUrl+'pd/equipes')
  }
  public subs(){
    return this.http.get<Sub[]>(httpUrl+'pd/subs')
  }
  public equipesInativas(page){
    return this.http.get<Equipe[]>(httpUrl+'pd/equipesInativas/'+page)
  }
  public listaLider(page:number){
    return this.http.get<Lider[]>(httpUrl+'pd/lideres/'+page)
  }
  public lideres(){
    return this.http.get<Lider[]>(httpUrl+'pd/lideres')
  }
  public editarLider(lider){
    return this.http.post(httpUrl+'pd/editarlider',lider)
  }
  public lider(id){
    return this.http.get<Lider>(httpUrl+'pd/lider/'+id)
  }
  public sub(id){
    return this.http.get<Sub>(httpUrl+'pd/sub/'+id)
  }
  public lideresInativos(page){
    return this.http.get<Lider[]>(httpUrl+'pd/lideresInativos/'+page)
  }
  public excluirLider(id){
    return this.http.get(httpUrl+'pd/excluirLider/'+id)
  }
  public excluirEquipe(id){
    return this.http.post(httpUrl+'pd/excluirEquipe',id)
  }
  public inativarEquipe(id){
    return this.http.get(httpUrl+'pd/inativarEquipe/'+id)
  }
  public ativarEquipe(id){
    return this.http.get(httpUrl+'pd/ativarEquipe/'+id)
  }
  public inativarLider(id){
    return this.http.get(httpUrl+'pd/inativarLider/'+id)
  }
  public ativarLider(id){
    return this.http.get(httpUrl+'pd/ativarLider/'+id)
  }
  public excluirSub(id){
    return this.http.get(httpUrl+'pd/excluirSub/'+id)
  }
  public inativarSub(id){
    return this.http.get(httpUrl+'pd/inativarSub/'+id)
  }
  public ativarSub(id){
    return this.http.post(httpUrl+'pd/ativarSub',id) 
  }
  public novoRelatorio(semana, mes, ano){
    return this.http.get(httpUrl+'pd/novoRelatorio/'+semana+"/"+mes+"/"+ano)
  }
  public excluirLancamentoPd(id){
    return this.http.post(httpUrl+'pd/excluirLancamentoPd', id, httpOptions);
  }
  public cicloAtual(){
    return this.http.get(httpUrl+'pd/cicloAtual')
  }
  public perfil(idEquipe,page){
    return this.http.get<Perfil>(httpUrl+'pd/perfil/'+idEquipe+'/'+page)
  }
}