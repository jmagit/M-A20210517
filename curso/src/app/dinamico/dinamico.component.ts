import { Component, OnInit } from '@angular/core';
import { CalculadoraComponent } from '../calculadora/calculadora.component';
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
    {texto: 'formulario', componente: FormularioComponent },
    {texto: 'inicio', componente: HomeComponent},
    {texto: 'demos', componente: DemosComponent },
    {texto: 'calculadora', componente: CalculadoraComponent },
  ];
  actual = this.menu[0].componente;

  constructor() { }

  seleccionar(indice: number) {
    this.actual = this.menu[indice].componente;
  }

  ngOnInit(): void {
  }

}
