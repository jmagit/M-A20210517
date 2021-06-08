import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Unsubscribable } from 'rxjs';
import { NotificationService, NotificationType } from '../common-services';

@Component({
  selector: 'app-botones',
  template: `
  <div>
    <input type="button" *ngIf="hasOK" [value]="OK" (click)="onOK.emit(null)" >
    <input type="button" *ngIf="hasCancel" [value]="Cancel" (click)="onCancel.emit(null)" >
  </div>
  `
})
export class BotonesComponent implements OnInit {
  @Input() OK = 'Aceptar';
  @Input() Cancel = 'Cancelar';
  @Output() onOK = new EventEmitter<any>();
  @Output() onCancel = new EventEmitter<any>();

  get hasOK() { return this.onOK.observers.length > 0; }
  get hasCancel() { return this.onCancel.observers.length > 0; }

  constructor() { }

  ngOnInit() { }
}
@Component({
  selector: 'app-demos',
  templateUrl: './demos.component.html',
  styleUrls: ['./demos.component.css']
})
export class DemosComponent implements OnInit, OnDestroy {
  private suscriptor: Unsubscribable | undefined;

  private nombre: string = 'mundo';
  listado = [
    { id: 1, nombre: 'Toledo'},
    { id: 2, nombre: 'albacete'},
    { id: 3, nombre: 'CUENCA'},
    { id: 4, nombre: 'Ciudad real'},
  ];
  idProvincia = 2;

  resultado: string | null = null;
  visible = true;
  estetica = { importante: true, error: false, urgente: true };
  fontsize = 24;

  constructor(public vm: NotificationService) { }

  public get Nombre(): string { return this.nombre; }
  public set Nombre(valor: string) {
    if(this.nombre === valor) return;
    this.nombre = valor;
  }

  public saluda(): void {
    this.resultado = `Hola ${this.nombre}`;
  }
  public despide(): void {
    this.resultado = `Adios ${this.nombre}`;
  }
  public di(algo: string): void {
    this.resultado = `Dice ${algo}`;
  }

  cambia() {
    this.visible = !this.visible;
    this.estetica.importante = !this.estetica.importante;
    this.estetica.error = !this.estetica.error;
  }

  calculo(a: number, b: number): number {
    return a + b;
  }

  add(provincia: string) {
    const id = this.listado.length ? this.listado[this.listado.length - 1].id + 1 : 1;
    this.listado.push({ id, nombre: provincia });
    this.idProvincia = id;
  }

  ngOnInit(): void {
    this.suscriptor = this.vm.Notificacion.subscribe(n => {
      if (n.Type !== NotificationType.error) { return; }
      window.alert(`Suscripcion: ${n.Message}`);
      this.vm.remove(this.vm.Listado.length - 1);
    });
  }
  ngOnDestroy(): void {
    if (this.suscriptor) {
      this.suscriptor.unsubscribe();
    }
  }

  idiomas = [
    { codigo: 'es', region: 'España' },
    { codigo: 'pt', region: 'Portuges' },
    { codigo: 'en-US', region: 'USA' }
  ];
  idioma = this.idiomas[0].codigo;
  calculos: any[] = [];
  valCalculadora = 666;

  ponResultado(origen: string, valor: any) {
    this.calculos.push({
      pos: this.calculos.length + 1,
      origen,
      valor
    });
  }

}
