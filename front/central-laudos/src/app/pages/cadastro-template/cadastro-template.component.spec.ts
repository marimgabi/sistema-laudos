import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroTemplateComponent } from './cadastro-template.component';

describe('CadastroTemplateComponent', () => {
  let component: CadastroTemplateComponent;
  let fixture: ComponentFixture<CadastroTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroTemplateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
