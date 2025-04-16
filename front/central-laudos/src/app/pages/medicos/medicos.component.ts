import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MedicoService } from '../../services/medico.service';
import { UsuarioService } from '../../services/usuario.service';
import { RouterModule } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-medicos',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    RouterModule
  ],
  templateUrl: './medicos.component.html',
  styleUrls: ['./medicos.component.scss']
})
export class MedicosComponent implements OnInit {
  private medicoService = inject(MedicoService);
  private usuarioService = inject(UsuarioService);
  private snackBar = inject(MatSnackBar);
  displayedColumns = ['nome', 'tipo', 'conselho', 'estado', 'excluir'];
  medicos: any[] = [];

  ngOnInit() {
    this.medicoService.listar().subscribe(res => {
      this.medicos = res;
    });
  }

  inativateUsuario(usuarioId: number) {
    this.usuarioService.inativate(usuarioId).subscribe({
      next: () => {
        this.snackBar.open('Médico inativado com sucesso!', 'Fechar', { duration: 3000 });
        this.medicos = this.medicos.filter(m => m.user?.id !== usuarioId);
      },
      error: (err) => {
        const msg = err?.error?.message || 'Erro ao inativar médico.';
        this.snackBar.open(msg, 'Fechar', { duration: 5000 });
      }
    });
  }
}
