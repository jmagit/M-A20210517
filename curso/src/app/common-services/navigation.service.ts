import { Router, NavigationEnd, ActivationStart } from '@angular/router';
import { LoggerService } from 'src/my-core';

import { Injectable } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Injectable({ providedIn: 'root' })
export class NavigationService {
  private history: Array<string> = [];
  constructor(private router: Router, private title: Title, logger: LoggerService) {
    router.events.subscribe(e => {
      if (e instanceof ActivationStart) {
        let ev: ActivationStart = e as ActivationStart;
        if (ev.snapshot?.data?.pageTitle) {
          this.title.setTitle(ev.snapshot.data.pageTitle);
        } else {
          this.title.setTitle('Curso de Angular');
        }
      }
      if (e instanceof NavigationEnd) {
        let ev: NavigationEnd = e as NavigationEnd;
        this.history.push(ev.url);
        logger.log(`${e.url}`);
      }
    });
  }
  back(delta: number = 1) {
    while(delta && this.history.length > 1) {
      this.history.pop();
      delta--;
    }
    const url = this.history.pop();
    if(url)
      this.router.navigateByUrl(url);
  }
}
