import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeccionArtistas } from './seccion-artistas';

describe('SeccionArtistas', () => {
  let component: SeccionArtistas;
  let fixture: ComponentFixture<SeccionArtistas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeccionArtistas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeccionArtistas);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
