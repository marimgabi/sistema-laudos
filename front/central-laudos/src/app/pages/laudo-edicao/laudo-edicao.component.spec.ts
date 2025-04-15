import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaudoEdicaoComponent } from './laudo-edicao.component';

describe('LaudoEdicaoComponent', () => {
  let component: LaudoEdicaoComponent;
  let fixture: ComponentFixture<LaudoEdicaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LaudoEdicaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaudoEdicaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
