import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatIconModule } from '@angular/material/icon';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-cadastro-laudo',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatIconModule
  ],
  templateUrl: './cadastro-laudo.component.html',
  styleUrls: ['./cadastro-laudo.component.scss']
})
export class CadastroLaudoComponent implements OnInit {
  private http = inject(HttpClient);
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  form!: FormGroup;
  sexos: string[] = [];
  solicitantes: any[] = [];
  templates: any[] = [];

  ngOnInit(): void {
    this.form = this.fb.group({
      nomePaciente: new FormControl('', Validators.required),
      sexo: new FormControl('', Validators.required),
      dataNascimento: new FormControl('', Validators.required),
      dataLaudo: new FormControl(new Date(), Validators.required),
      medicoSolicitante: new FormControl('', Validators.required),
      conteudo: new FormControl('', Validators.required)
    });

    this.http.get<string[]>(`${environment.apiUrl}/info/enum/sexo`)
      .subscribe(data => this.sexos = data);

    this.http.get<any[]>(`${environment.apiUrl}/medico/solicitante`)
      .subscribe(data => this.solicitantes = data);

    this.http.get<any[]>(`${environment.apiUrl}/template/medico`)
      .subscribe(data => this.templates = data);
  }

  adicionarTemplate(conteudo: string) {
    const atual = this.form.get('conteudo')?.value || '';
    const novoConteudo = atual + (atual ? '\n' : '') + conteudo;
    this.form.get('conteudo')?.setValue(novoConteudo);
  }

  submit(): void {
    if (this.form.invalid) return;

    const payload = {
      ...this.form.value,
      medicoSolicitante: { id: this.form.value.medicoSolicitante }
    };

    this.http.post(`${environment.apiUrl}/laudo`, payload).subscribe({
      next: () => {
        this.snackBar.open('Laudo cadastrado com sucesso!', 'Fechar', { duration: 3000 });
        this.router.navigate(['/laudos']);
      },
      error: (err) => {
        const msg = err?.error?.message || 'Erro ao cadastrar laudo.';
        this.snackBar.open(msg, 'Fechar', { duration: 4000 });
      }
    });
  }

  cancelar(): void {
    this.router.navigate(['/laudos']);
  }
}
