import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { loginRedirectGuard } from './guards/login-redirect.guard';
import { MedicosComponent } from './pages/medicos/medicos.component';


export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'medicos', component: MedicosComponent }
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
  }
];