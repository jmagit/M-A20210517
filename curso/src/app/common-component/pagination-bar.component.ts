import { Component, OnInit, OnDestroy, Output, EventEmitter, Input } from '@angular/core';
import { ContactosViewModelService } from '../contactos/servicios.service';

@Component({
  selector: 'app-pagination-bar',
  template: `
    <nav aria-label="Page navigation" [hidden]="totalpages === 0">
      <ul class="pagination">
        <li class="page-item">
          <button class="btn btn-link page-link" (click)="select.emit(0)" [class.disabled]="page === 0" aria-label="Previous"><span aria-hidden="true">&laquo;</span></button>
        </li>
        <li *ngFor="let item of pages" class="page-item" [class.active]="page === item - 1">
          <button class="btn btn-link page-link" (click)="select.emit(item - 1)">{{item}}</button>
        </li>
        <li class="page-item">
          <button class="btn btn-link page-link" (click)="select.emit(totalpages - 1)" [class.disabled]="page === totalpages - 1" aria-label="Next"><span aria-hidden="true">&raquo;</span></button>
        </li>
      </ul>
    </nav>
  `
})
export class PaginationBarComponent implements OnInit, OnDestroy {
  @Input() page = 0;
  @Input() totalpages = 0;
  @Output() select: EventEmitter<number> = new EventEmitter<number>();

  constructor(protected vm: ContactosViewModelService) { }

  get pages() { return [...new Array(this.totalpages)].map((value, index)=> index + 1); }

  ngOnInit(): void { }
  ngOnDestroy(): void {  }
}
