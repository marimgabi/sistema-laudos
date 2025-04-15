import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaudosComponent } from './laudos.component';

describe('LaudosComponent', () => {
  let component: LaudosComponent;
  let fixture: ComponentFixture<LaudosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LaudosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaudosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
