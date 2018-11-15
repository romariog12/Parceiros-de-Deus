import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PdComponent } from './pd/pd.component';
import { EquipeComponent } from './pd/equipe.component';
import { PdService } from './pd.service';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CadastrarEquipeComponent } from './pd/cadastrar-equipe.component';
import { FormsModule } from '@angular/forms';
import { CadastrarLiderComponent } from './pd/cadastrar-lider/cadastrar-lider.component';
import { LideresComponent } from './pd/lideres/lideres.component';
import { LancarPdComponent } from './pd/lancar-pd/lancar-pd.component';
import { SubComponent } from './pd/sub/sub.component';
import { RelatorioComponent } from './relatorio/relatorio.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PerfilComponent } from './perfil/perfil.component';
import {NgxMaskModule} from 'ngx-mask';
import { AdministracaoComponent } from './administracao/administracao.component';
import { editarLider } from './pd/lideres/editarLider.component';
import { EditarEquipeComponent } from './pd/equipe/editar-equipe.component';

@NgModule({
  declarations: [
    AppComponent,
    PdComponent,
    EquipeComponent,
    EditarEquipeComponent,
    CadastrarEquipeComponent,
    CadastrarLiderComponent,
    editarLider,
    LideresComponent,
    LancarPdComponent,
    SubComponent,
    RelatorioComponent,
    PerfilComponent,
    AdministracaoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    NgxMaskModule.forRoot()
  ],
  providers: [PdService, RelatorioComponent],
  bootstrap: [AppComponent, EquipeComponent]
})
export class AppModule { }
