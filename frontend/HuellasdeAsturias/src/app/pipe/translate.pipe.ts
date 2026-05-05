import { Pipe, PipeTransform, inject, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { TranslateService } from '../service/translate.service';
import { effect } from '@angular/core';

@Pipe({
  name: 'translate',
  standalone: true,
  pure: false,   // impure para que se actualice al cambiar el idioma
})
export class TranslatePipe implements PipeTransform, OnDestroy {

  private readonly svc = inject(TranslateService);
  private readonly cdr = inject(ChangeDetectorRef);
  private readonly effectRef: ReturnType<typeof effect>;

  constructor() {
    // Marca el componente para re-renderizar cuando cambia el idioma
    this.effectRef = effect(() => {
      this.svc.lang(); // suscripción al signal
      this.cdr.markForCheck();
    });
  }

  transform(key: string, params?: Record<string, string | number>): string {
    return this.svc.t(key, params);
  }

  ngOnDestroy(): void {
    this.effectRef.destroy();
  }
}