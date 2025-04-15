import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-cadastro-usuario',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatSnackBarModule,
    MatIconModule
  ],
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.scss']
})
export class CadastroUsuarioComponent implements OnInit {
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  // Cria o formulário com os campos básicos
  form = this.fb.group({
    email: new FormControl<string | null>('', [Validators.required, Validators.email]),
    username: new FormControl<string | null>('', Validators.required),
    password: new FormControl<string | null>('', Validators.required),
    // Campo role do usuário, que será travado em ADMIN (id = 1)
    role: new FormControl<number | null>(null, Validators.required)
  });

  ngOnInit(): void {
    // Define o perfil fixo como ADMIN (id 1) logo que o formulário é criado.
    this.form.get('role')?.setValue(1);
  }

  submit(): void {
    if (this.form.invalid) return;

    const value = this.form.value;

    const payload = {
      email: value.email,
      username: value.username,
      password: value.password,
      role: {
        id: value.role
      },
      medico: null
    };

    this.http.post(`${environment.apiUrl}/auth/register`, payload).subscribe({
      next: () => {
        this.snackBar.open('Usuário cadastrado com sucesso!', 'Fechar', { duration: 3000 });
        this.router.navigate(['/usuarios']);
      },
      error: (err) => {
        const msg = err?.error?.message || 'Erro ao cadastrar usuário.';
        this.snackBar.open(msg, 'Fechar', { duration: 5000 });
      }
    });
  }

  cancelar() {
    this.router.navigate(['/usuarios']);
  }
}
