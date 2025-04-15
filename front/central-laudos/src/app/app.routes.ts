import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { loginRedirectGuard } from './guards/login-redirect.guard';
import { MedicosComponent } from './pages/medicos/medicos.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';
import { CadastroMedicoComponent } from './pages/cadastro-medico/cadastro-medico.component';
import { CadastroUsuarioComponent } from './pages/cadastro-usuario/cadastro-usuario.component';
import { TemplatesComponent } from './pages/templates/templates.component';
import { TemplateViewComponent } from './pages/template-view/template-view.component';
import { CadastroTemplateComponent } from './pages/cadastro-template/cadastro-template.component';
import { CadastroLaudoComponent } from './pages/cadastro-laudo/cadastro-laudo.component';
import { LaudosComponent } from './pages/laudos/laudos.component';
import { LaudoVisualizacaoComponent } from './pages/laudo-visualizacao/laudo-visualizacao.component';
import { LaudoEdicaoComponent } from './pages/laudo-edicao/laudo-edicao.component';



export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'medicos', component: MedicosComponent },
      { path: 'usuarios', component: UsuariosComponent },
      { path: 'cadastro-medico', component: CadastroMedicoComponent },
      { path: 'cadastro-usuario', component: CadastroUsuarioComponent },
      { path: 'templates', component: TemplatesComponent },
      { path: 'templates/:id', component: TemplateViewComponent },
      { path: 'cadastro-template', component: CadastroTemplateComponent },
      { path: 'templates/:id/editar', component: CadastroTemplateComponent },
      { path: 'cadastro-laudo', component: CadastroLaudoComponent },
      { path: 'laudos', component: LaudosComponent },
      { path: 'cadastro-laudo', component: CadastroLaudoComponent },
      { path: 'laudos/:id', component: LaudoVisualizacaoComponent },  
      { path: 'laudos/:id/editar', component: LaudoEdicaoComponent },

    ]
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [loginRedirectGuard]
  },
  {
    path: '**',
    redirectTo: 'login'
  },
  {
    path: 'medicos',
    component: MedicosComponent
  },
  {
    path: 'cadastro-medico',
    component: CadastroMedicoComponent
  },
  {
    path: 'templates/:id',
    component: TemplateViewComponent
  },
  {
    path: 'cadastro-laudo',
    component: CadastroLaudoComponent
  },
  {
    path: 'laudos/:id',
    component: LaudoVisualizacaoComponent
  },
  {
    path: 'laudo/:id/editar',
    component: LaudoEdicaoComponent
  }
  
];