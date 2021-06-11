import { Component, OnInit } from '@angular/core';
import { ActoresComponent } from '../actores';
import { CalculadoraComponent } from '../calculadora/calculadora.component';
import { ContactosComponent } from '../contactos';
import { DemosComponent } from '../demos/demos.component';
import { FormularioComponent } from '../formulario/formulario.component';
import { HomeComponent } from '../main/home/home.component';

@Component({
  selector: 'app-dinamico',
  templateUrl: './dinamico.component.html',
  styleUrls: ['./dinamico.component.css']
})
export class DinamicoComponent implements OnInit {
  menu = [
    {texto: 'inicio', componente: HomeComponent},
    {texto: 'demos', componente: DemosComponent },
    {texto: 'calculadora', componente: CalculadoraComponent },
    {texto: 'formulario', componente: FormularioComponent },
    {texto: 'contactos', componente: ContactosComponent },
    {texto: 'actores', componente: ActoresComponent },
  ];
  actual = this.menu[0].componente;

  constructor() { }

  seleccionar(indice: number) {
    this.actual = this.menu[indice].componente;
  }

  ngOnInit(): void {
  }

}
