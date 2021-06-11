import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { ActoresViewModelService } from './servicios.service';

@Component({
  selector: 'app-actores',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresComponent implements OnInit {
  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.list();
  }
}

@Component({
  selector: 'app-actores-list',
  templateUrl: './tmpl-list.sin-rutas.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresListComponent implements OnInit, OnDestroy {
  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void { }
  ngOnDestroy(): void {  }
}

@Component({
  selector: 'app-actores-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresAddComponent implements OnInit {
  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void { }
}

@Component({
  selector: 'app-actores-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresEditComponent implements OnInit, OnDestroy {
  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void { }
  ngOnDestroy(): void { }
}

@Component({
  selector: 'app-actores-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresViewComponent implements OnInit, OnDestroy {
  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void { }
  ngOnDestroy(): void { }
}

/*
@Component({
  selector: 'app-actores-list',
  templateUrl: './tmpl-list.con-rutas.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresListComponent implements OnInit {
  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void {
    this.vm.list();
  }
}

@Component({
  selector: 'app-actores-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresAddComponent implements OnInit {
  constructor(protected vm: ActoresViewModelService) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void {
    this.VM.add();
  }
}

@Component({
  selector: 'app-actores-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(protected vm: ActoresViewModelService,
    protected route: ActivatedRoute, protected router: Router) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
        const id = parseInt(params?.get('id') ?? '');
        if (id) {
          this.vm.edit(id);
        } else {
          this.router.navigate(['/404.html']);
        }
      });
  }
  ngOnDestroy(): void {
    this.obs$.unsubscribe();
  }
}

@Component({
  selector: 'app-actores-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css']
})
export class ActoresViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(protected vm: ActoresViewModelService,
    protected route: ActivatedRoute, protected router: Router) { }
  public get VM(): ActoresViewModelService { return this.vm; }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
        const id = parseInt(params?.get('id') ?? '');
        if (id) {
          this.vm.view(id);
        } else {
          this.router.navigate(['/404.html']);
        }
      });
  }
  ngOnDestroy(): void {
    this.obs$.unsubscribe();
  }
}
*/


export const ACTORES_COMPONENTES = [
  ActoresComponent, ActoresListComponent, ActoresAddComponent,
  ActoresEditComponent, ActoresViewComponent,
];
