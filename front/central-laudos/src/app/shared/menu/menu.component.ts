import { Component, inject } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu',
  imports: [
    CommonModule,
    MatSidenavModule,
    MatListModule,
    RouterModule,
    MatIconModule
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {
  private authService = inject(AuthService);
  private router: Router = inject(Router);

  username = this.authService.getUsername() ?? 'Usuário';

  isExecutante = false;

  user = this.authService.getUser();
  role = this.user?.role?.role;
  tipoMedico = this.user?.medicos?.[0]?.tipo;

  showLaudos = false;

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isAdmin(): boolean {
    return this.authService.getUserRole() === 'ADMIN';
  }
  
  isMedico(): boolean {
    return this.authService.getUserRole() === 'MEDICO';
  }

  get isMedicoExecutante(): boolean {
    const user = this.authService.getUser();
    return user?.role?.role === 'MEDICO' &&
           Array.isArray(user.medicos) &&
           user.medicos[0]?.tipo === 'EXECUTANTE';
  }

  ngOnInit() {
    this.showLaudos =
    this.role === 'ADMIN' ||
    (this.role === 'MEDICO' && (this.tipoMedico === 'EXECUTANTE' || this.tipoMedico === 'SOLICITANTE'));
  }
}
