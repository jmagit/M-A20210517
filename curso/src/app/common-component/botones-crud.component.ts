import { Component, OnInit, OnDestroy, Output, EventEmitter } from '@angular/core';
import { ContactosViewModelService } from '../contactos/servicios.service';

@Component({
  selector: 'app-botones-crud',
  template: `
    <div class="btn-group" role="group">
      <button class="btn btn-info" *ngIf="hasView" (click)="view.emit(null)"><i class="fas fa-eye"></i></button>
      <button class="btn btn-success" *ngIf="hasEdit" (click)="edit.emit(null)"><i class="fas fa-pen"></i></button>
      <button class="btn btn-danger" *ngIf="hasDelete" (click)="delete.emit(null)"><i class="far fa-trash-alt"></i></button>
    </div>
  `
})
export class BotonesCRUDComponent implements OnInit, OnDestroy {
  @Output() view: EventEmitter<any> = new EventEmitter<any>();
  @Output() edit: EventEmitter<any> = new EventEmitter<any>();
  @Output() delete: EventEmitter<any> = new EventEmitter<any>();

  get hasView(): boolean { return this.view.observers.length > 0; }
  get hasEdit(): boolean { return this.edit.observers.length > 0; }
  get hasDelete(): boolean { return this.delete.observers.length > 0; }

  constructor(protected vm: ContactosViewModelService) { }
  ngOnInit(): void { }
  ngOnDestroy(): void {  }
}
