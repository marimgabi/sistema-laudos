import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TemplateService } from '../services/templates.service';

@Component({
  selector: 'app-templates',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './templates.component.html'
})
export class TemplatesComponent implements OnInit {
  private templateService = inject(TemplateService);
  templates: any[] = [];

  ngOnInit(): void {
    this.templateService.getTemplates().subscribe(data => {
      this.templates = data;
    });
  }
}
