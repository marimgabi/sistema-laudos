import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { UsuarioService } from '../../services/usuario.service';
import { RouterModule } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    RouterModule
  ],
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {
  private usuarioService = inject(UsuarioService);
  private snackBar = inject(MatSnackBar);
  displayedColumns = ['email', 'username', 'role', 'excluir'];
  usuarios: any[] = [];

  ngOnInit() {
    this.usuarioService.listar().subscribe(res => {
      this.usuarios = res;
    });
  }

  inativateUsuario(id: number) {
    this.usuarioService.inativate(id).subscribe({
      next: () => {
        this.snackBar.open('Usuário inativado com sucesso!', 'Fechar', { duration: 3000 });
        this.usuarios = this.usuarios.filter(u => u.id !== id);
      },
      error: (err) => {
        const msg = err?.error?.message || 'Erro ao inativar usuário.';
        this.snackBar.open(msg, 'Fechar', { duration: 5000 });
      }
    });
  }
}
