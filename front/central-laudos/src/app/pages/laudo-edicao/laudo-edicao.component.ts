import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-laudo-edicao',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatSnackBarModule
  ],
  templateUrl: './laudo-edicao.component.html',
  styleUrls: ['./laudo-edicao.component.scss']
})
export class LaudoEdicaoComponent implements OnInit {
  private http = inject(HttpClient);
  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  form!: FormGroup;
  sexos: string[] = [];
  solicitantes: any[] = [];
  id!: string;

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id')!;

    this.form = this.fb.group({
      nomePaciente: new FormControl('', Validators.required),
      sexo: new FormControl('', Validators.required),
      dataNascimento: new FormControl('', Validators.required),
      dataLaudo: new FormControl('', Validators.required),
      medicoSolicitante: new FormControl('', Validators.required),
      conteudo: new FormControl('', Validators.required)
    });

    this.http.get<string[]>(`${environment.apiUrl}/info/enum/sexo`).subscribe(data => this.sexos = data);
    this.http.get<any[]>(`${environment.apiUrl}/medico/solicitante`).subscribe(data => this.solicitantes = data);

    this.http.get<any>(`${environment.apiUrl}/laudo/${this.id}`).subscribe(laudo => {
      this.form.patchValue({
        nomePaciente: laudo.nomePaciente,
        sexo: laudo.sexo,
        dataNascimento: laudo.dataNascimento,
        dataLaudo: laudo.dataLaudo,
        medicoSolicitante: laudo.medicoSolicitante?.id,
        conteudo: laudo.conteudo
      });
    });
  }

  submit(): void {
    if (this.form.invalid) return;

    const payload = {
      ...this.form.value,
      medicoSolicitante: { id: this.form.value.medicoSolicitante }
    };

    this.http.put(`${environment.apiUrl}/laudo/${this.id}`, payload).subscribe({
      next: () => {
        this.snackBar.open('Laudo atualizado com sucesso!', 'Fechar', { duration: 3000 });
        this.router.navigate(['/laudos']);
      },
      error: (err) => {
        const msg = err?.error?.message || 'Erro ao atualizar laudo.';
        this.snackBar.open(msg, 'Fechar', { duration: 4000 });
      }
    });
  }

  cancelar(): void {
    this.router.navigate(['/laudos']);
  }
}
