import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BotonesCRUDComponent } from './botones-crud.component';
import { PaginationBarComponent } from './pagination-bar.component';



@NgModule({
  declarations: [
    BotonesCRUDComponent, PaginationBarComponent,
  ],
  exports:[
    BotonesCRUDComponent, PaginationBarComponent,
  ],
  imports: [
    CommonModule
  ]
})
export class CommonComponentModule { }
