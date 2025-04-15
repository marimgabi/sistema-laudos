import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-cadastro-medico',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatSnackBarModule,
    MatIconModule
  ],
  templateUrl: './cadastro-medico.component.html',
  styleUrls: ['./cadastro-medico.component.scss']
})
export class CadastroMedicoComponent implements OnInit {
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  form = this.fb.group({
    email: new FormControl<string | null>('', [Validators.required, Validators.email]),
    username: new FormControl<string | null>('', Validators.required),
    password: new FormControl<string | null>('', Validators.required),
    role: new FormControl<number | null>(null, Validators.required),
    nome: new FormControl<string | null>('', Validators.required),
    tipo: new FormControl<string | null>('', Validators.required),
    conselhoTipo: new FormControl<string | null>('', Validators.required),
    conselhoNumero: new FormControl<string | null>('', Validators.required),
    estado: new FormControl<number | null>(null, Validators.required),
  });

  roles: any[] = [];
  tipos: string[] = [];
  estados: any[] = [];

  ngOnInit(): void {
    this.http.get<any[]>(`${environment.apiUrl}/info/roles`).subscribe(data => this.roles = data);
    this.http.get<string[]>(`${environment.apiUrl}/info/enum/tipo-medico`).subscribe(data => this.tipos = data);
    this.http.get<any[]>(`${environment.apiUrl}/estado`).subscribe(data => this.estados = data);
    this.form.get('role')?.setValue(2);
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
      medico: {
        nome: value.nome,
        tipo: value.tipo,
        conselho: {
          tipo: value.conselhoTipo,
          numero: value.conselhoNumero,
          estado: {
            id: value.estado
          }
        }
      }
    };

    this.http.post(`${environment.apiUrl}/auth/register`, payload).subscribe({
      next: () => {
        this.snackBar.open('Médico cadastrado com sucesso!', 'Fechar', { duration: 3000 });
        this.router.navigate(['/medicos']);
      },
      error: (err) => {
        const msg = err?.error?.message || 'Erro ao cadastrar médico.';
        this.snackBar.open(msg, 'Fechar', { duration: 5000 });
      }
    });
  }

  cancelar() {
    this.router.navigate(['/medicos']);
  }
}
