import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { TranslateService } from './service/translate.service';

function initLang(translateService: TranslateService) {
  return () => translateService.load('es');
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideHttpClient(withFetch()),
    {
      provide: 'APP_INITIALIZER',
      useFactory: initLang,
      deps: [TranslateService],
      multi: true,
    },
  ],
};
