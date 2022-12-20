import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarAsistenteComponent } from './agregar-asistente.component';

describe('AgregarAsistenteComponent', () => {
  let component: AgregarAsistenteComponent;
  let fixture: ComponentFixture<AgregarAsistenteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgregarAsistenteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarAsistenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
