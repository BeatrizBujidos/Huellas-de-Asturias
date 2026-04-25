import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObraDetalle } from './obra-detalle';

describe('ObraDetalle', () => {
  let component: ObraDetalle;
  let fixture: ComponentFixture<ObraDetalle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObraDetalle]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ObraDetalle);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
