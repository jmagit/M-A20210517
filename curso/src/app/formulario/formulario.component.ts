import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {
  elemento: any = {id: 1, nombre: 'Pepito', apellidos: 'Grillo', edad: 99 };

  constructor() {

  }

  add() {
    this.elemento = {};
  }

  edit() {
    this.elemento = {id: 2, nombre: 'Carmelo', apellidos: 'Coton', edad: 17 };
  }

  send() {
    alert(JSON.stringify(this.elemento))
  }
  cancel() {
  }


  ngOnInit(): void {
  }

}
