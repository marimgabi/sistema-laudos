import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { environment } from '../../../environments/environment';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';


@Component({
  selector: 'app-template-view',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './template-view.component.html',
  styleUrls: ['./template-view.component.scss']
})
export class TemplateViewComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private http = inject(HttpClient);
  private sanitizer = inject(DomSanitizer);
  private location = inject(Location);


  template: any = null;
  safeHtml: SafeHtml | null = null;

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.http.get(`${environment.apiUrl}/template/${id}`).subscribe((data: any) => {
        this.template = data;

        if (this.template.tipo === 'HTML') {
          this.safeHtml = this.sanitizer.bypassSecurityTrustHtml(this.template.conteudo);
        }
      });
    }
  }

  voltar(): void {
    this.location.back();
  }
  
}
