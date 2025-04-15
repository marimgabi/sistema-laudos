import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaudoVisualizacaoComponent } from './laudo-visualizacao.component';

describe('LaudoVisualizacaoComponent', () => {
  let component: LaudoVisualizacaoComponent;
  let fixture: ComponentFixture<LaudoVisualizacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LaudoVisualizacaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaudoVisualizacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
