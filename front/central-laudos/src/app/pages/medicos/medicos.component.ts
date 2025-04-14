import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MedicoService } from '../../services/medico.service';

@Component({
  selector: 'app-medicos',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './medicos.component.html',
  styleUrls: ['./medicos.component.scss']
})
export class MedicosComponent implements OnInit {
  private medicoService = inject(MedicoService);
  displayedColumns = ['nome', 'tipo', 'conselho', 'estado'];
  medicos: any[] = [];

  ngOnInit() {
    this.medicoService.listar().subscribe(res => {
      this.medicos = res;
    });
  }
}
