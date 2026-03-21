import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapaMonumentos } from './mapa-monumentos';

describe('MapaMonumentos', () => {
  let component: MapaMonumentos;
  let fixture: ComponentFixture<MapaMonumentos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MapaMonumentos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MapaMonumentos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
