import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Monumentos } from './monumentos';

describe('Monumentos', () => {
  let component: Monumentos;
  let fixture: ComponentFixture<Monumentos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Monumentos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Monumentos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
