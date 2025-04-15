import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroLaudoComponent } from './cadastro-laudo.component';

describe('CadastroLaudoComponent', () => {
  let component: CadastroLaudoComponent;
  let fixture: ComponentFixture<CadastroLaudoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastroLaudoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroLaudoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
