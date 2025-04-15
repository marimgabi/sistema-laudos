import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-cadastro-template',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatSnackBarModule
  ],
  templateUrl: './cadastro-template.component.html',
  styleUrls: ['./cadastro-template.component.scss']
})
export class CadastroTemplateComponent implements OnInit {
  private http = inject(HttpClient);
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private snackBar = inject(MatSnackBar);

  form!: FormGroup;
  tiposTemplate: string[] = [];
  templateId: number | null = null;

  ngOnInit(): void {
    this.form = this.fb.group({
      descricao: new FormControl('', Validators.required),
      conteudo: new FormControl('', Validators.required),
      tipo: new FormControl('', Validators.required)
    });

    this.http.get<string[]>(`${environment.apiUrl}/info/enum/tipo-template`)
      .subscribe(tipos => this.tiposTemplate = tipos);

    this.templateId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.templateId) {
      this.http.get<any>(`${environment.apiUrl}/template/${this.templateId}`)
        .subscribe(data => {
          this.form.patchValue({
            descricao: data.descricao,
            conteudo: data.conteudo,
            tipo: data.tipo
          });
        });
    }
  }

  submit(): void {
    if (this.form.invalid) return;

    const payload = this.form.value;

    if (this.templateId) {
      this.http.put(`${environment.apiUrl}/template/${this.templateId}`, payload).subscribe({
        next: () => {
          this.snackBar.open('Template atualizado com sucesso!', 'Fechar', { duration: 3000 });
          this.router.navigate(['/templates']);
        },
        error: (err) => {
          const msg = err?.error?.message || 'Erro ao atualizar template.';
          this.snackBar.open(msg, 'Fechar', { duration: 4000 });
        }
      });
    } else {
      this.http.post(`${environment.apiUrl}/template`, payload).subscribe({
        next: () => {
          this.snackBar.open('Template cadastrado com sucesso!', 'Fechar', { duration: 3000 });
          this.router.navigate(['/templates']);
        },
        error: (err) => {
          const msg = err?.error?.message || 'Erro ao cadastrar template.';
          this.snackBar.open(msg, 'Fechar', { duration: 4000 });
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/templates']);
  }
}
