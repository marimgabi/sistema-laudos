import { Component, OnInit, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AuthService } from '../../auth.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-laudos',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatSnackBarModule,
    RouterModule
  ],
  templateUrl: './laudos.component.html',
  styleUrls: ['./laudos.component.scss']
})
export class LaudosComponent implements OnInit {
  private http = inject(HttpClient);
  private authService = inject(AuthService);
  private snackBar = inject(MatSnackBar);
  private router = inject(Router);

  laudos: any[] = [];
  displayedColumns = ['nomePaciente', 'dataLaudo', 'medicoSolicitante', 'acoes'];
  isMedicoExecutante = false;

  userRole = '';
showSolicitante = false;
showExecutante = false;

  ngOnInit(): void {
    const user = this.authService.getUser();
    this.userRole = user?.role?.role;
    const tipoMedico = user?.medicos?.[0]?.tipo;
  
    this.isMedicoExecutante = this.userRole === 'MEDICO' && tipoMedico === 'EXECUTANTE';
  
    this.showSolicitante = this.userRole === 'ADMIN' || this.isMedicoExecutante;
    this.showExecutante = this.userRole === 'ADMIN' || tipoMedico === 'SOLICITANTE';
  
    this.displayedColumns = ['nomePaciente', 'dataLaudo'];
    if (this.showSolicitante) this.displayedColumns.push('medicoSolicitante');
    if (this.showExecutante) this.displayedColumns.push('medicoExecutante');
    if (this.isMedicoExecutante) {
      this.displayedColumns.push('editar', 'excluir');
    }

    this.http.get<any[]>(`${environment.apiUrl}/laudo`).subscribe({
      next: (data) => this.laudos = data,
      error: () => {
        this.snackBar.open('Erro ao carregar laudos', 'Fechar', { duration: 3000 });
      }
    });

}

  editar(id: number) {
    this.router.navigate([`/laudos/${id}/editar`]);
  }

  cadastrar() {
    this.router.navigate(['/cadastro-laudo']);
  }

  inativar(id: number) {
    this.http.get(`${environment.apiUrl}/laudo/${id}/inativate`, {}).subscribe({
      next: () => {
        this.snackBar.open('Laudo inativado com sucesso!', 'Fechar', { duration: 3000 });
        this.laudos = this.laudos.filter(l => l.id !== id);
      },
      error: (err) => {
        const msg = err?.error?.message || 'Erro ao inativar laudo.';
        this.snackBar.open(msg, 'Fechar', { duration: 5000 });
      }
    });
  }
}


