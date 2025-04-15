import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { environment } from '../../../environments/environment';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-laudo-visualizacao',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule
  ],
  templateUrl: './laudo-visualizacao.component.html',
  styleUrls: ['./laudo-visualizacao.component.scss']
})
export class LaudoVisualizacaoComponent implements OnInit {
  private http = inject(HttpClient);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);
  private authService = inject(AuthService);
  private sanitizer = inject(DomSanitizer);

  laudo: any = null;
  isMedicoExecutante = false;
  isHtml = false;
  conteudoSeguro: SafeHtml = '';

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    const user = this.authService.getUser();
    this.isMedicoExecutante =
      user?.role?.role === 'MEDICO' && user?.medicos?.[0]?.tipo === 'EXECUTANTE';

    if (id) {
      this.http.get(`${environment.apiUrl}/laudo/${id}`).subscribe({
        next: (data) => {
          this.laudo = data;
          this.isHtml = /<\/?[a-z][\s\S]*>/i.test(this.laudo.conteudo);
          this.conteudoSeguro = this.isHtml
            ? this.sanitizer.bypassSecurityTrustHtml(this.laudo.conteudo)
            : this.laudo.conteudo;
        },
        error: () => {
          this.snackBar.open('Erro ao carregar o laudo', 'Fechar', { duration: 3000 });
          this.router.navigate(['/laudos']);
        }
      });
    }
  }

  voltar() {
    this.router.navigate(['/laudos']);
  }

  editar() {
    this.router.navigate([`/laudos/${this.laudo.id}/editar`]);
  }
}
