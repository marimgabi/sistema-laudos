import { Component, OnInit, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { environment } from '../../../environments/environment';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-templates',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    RouterModule 
  ],
  templateUrl: './templates.component.html',
  styleUrls: ['./templates.component.scss']
})
export class TemplatesComponent implements OnInit {
  private http = inject(HttpClient);
  templates: any[] = [];
  displayedColumns = ['descricao', 'tipo', 'editar', 'excluir'];

  ngOnInit(): void {
    this.http.get<any[]>(`${environment.apiUrl}/template/medico`)
      .subscribe(data => this.templates = data);
  }
}
