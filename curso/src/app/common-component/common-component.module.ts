import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BotonesCRUDComponent } from './botones-crud.component';



@NgModule({
  declarations: [
    BotonesCRUDComponent,
  ],
  exports:[
    BotonesCRUDComponent,
  ],
  imports: [
    CommonModule
  ]
})
export class CommonComponentModule { }
