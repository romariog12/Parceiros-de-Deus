import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 
import { EquipeComponent } from './pd/equipe.component';
import { CadastrarEquipeComponent } from './pd/cadastrar-equipe.component';
import { LideresComponent } from './pd/lideres/lideres.component';
import { CadastrarLiderComponent } from './pd/cadastrar-lider/cadastrar-lider.component';
import { LancarPdComponent } from './pd/lancar-pd/lancar-pd.component';
import { SubComponent } from './pd/sub/sub.component';
import { RelatorioComponent } from './relatorio/relatorio.component';
import { PerfilComponent } from './perfil/perfil.component';
import { AdministracaoComponent } from './administracao/administracao.component';
import { editarLider } from './pd/lideres/editarLider.component';
import { EditarEquipeComponent } from './pd/equipe/editar-equipe.component';

const routes: Routes = [
  { path: 'equipes', component: EquipeComponent },
  { path: 'equipes/cadastrarEquipe', component: CadastrarEquipeComponent },
  { path: 'lideres', component: LideresComponent },
  { path: 'lideres/cadastrarLider', component: CadastrarLiderComponent },
  { path: 'equipes/lancarPd/:id', component: LancarPdComponent },
  { path: 'listaPd/lancarPd/:id/:ciclo/:mes/:ano', component: LancarPdComponent },
  { path: 'equipes/editarSub/:idEquipe/:id', component: SubComponent },
  { path: 'equipes/perfil/:idEquipe/cadastrarSub', component: SubComponent },
  { path: 'listaPd', component: RelatorioComponent },
  { path: 'equipes/perfil/:id', component: PerfilComponent },
  { path: 'administracao', component: AdministracaoComponent },
  { path: 'editarLider/:id', component: editarLider },
  { path: 'editarEquipe/:id', component: CadastrarEquipeComponent },
  { path: 'editarSub/:idEquipe/:id', component: SubComponent }

];
@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ],
  declarations: []
})

export class AppRoutingModule { }
