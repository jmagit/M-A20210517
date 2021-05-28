import { Component } from '@angular/core';
// import { ERROR_LEVEL, LoggerService } from 'src/my-core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  // providers: [
  //   LoggerService,
  //   { provide: ERROR_LEVEL, useValue: 4 },
  // ],
})
export class AppComponent {
  title = 'hola toledo';

  // constructor(out: LoggerService) {
  //   out.error('Es un error');
  //   out.warn('Es un warn');
  //   out.info('Es un info');
  //   out.log('Es un log');
  // }
}
