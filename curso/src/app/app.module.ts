import { LOCALE_ID, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { environment } from 'src/environments/environment';

// Cargar idioma --------------------------------------------------------
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';
import localeEsExtra from '@angular/common/locales/extra/es';

registerLocaleData(localeEs, 'es', localeEsExtra);
// ---------------------------------------------------------------------

import { ERROR_LEVEL, LoggerService, MyCoreModule } from 'src/my-core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonServicesModule } from './common-services';
import { MainModule } from './main';
import { BotonesComponent, DemosComponent } from './demos/demos.component';
import { DinamicoComponent } from './dinamico/dinamico.component';
import { CalculadoraComponent } from './calculadora/calculadora.component';

@NgModule({
  declarations: [
    AppComponent,
    DemosComponent, BotonesComponent, DinamicoComponent, CalculadoraComponent,
  ],
  imports: [
    BrowserModule, FormsModule,
    MainModule, CommonServicesModule, MyCoreModule,
    AppRoutingModule
  ],
  providers: [
    LoggerService,
    { provide: ERROR_LEVEL, useValue: environment.ERROR_LEVEL },
    { provide: LOCALE_ID, useValue: 'es-ES' },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
