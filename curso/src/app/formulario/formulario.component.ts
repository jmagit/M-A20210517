import { HttpClient, HttpContext, HttpContextToken } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { RESTDAOService } from '../base-code/RESTDAOService';
import { NotificationService } from '../common-services';

export const AUTH_REQUIRED = new HttpContextToken<boolean>(() => false);

@Injectable({
  providedIn: 'root'
})
export class ActoresDAOService extends RESTDAOService<any, number> {
  constructor(http: HttpClient) {
    super(http, 'actores', { context: new HttpContext().set(AUTH_REQUIRED, true) })
  }
}
@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {
  elemento: any = {id: 1, nombre: 'Pepito', apellidos: 'Grillo', edad: 99 };
  listado: Array<any> = [];
  modo: string = 'edit';

  constructor(private dao: ActoresDAOService, private notify: NotificationService) {

  }

  add() {
    this.elemento = {};
    this.modo = 'add';
  }

  edit() {
    // this.elemento = {id: 2, nombre: 'Carmelo', apellidos: 'Coton', edad: 17 };
    this.dao.get(this.elemento.id).subscribe(
      data => { this.elemento = data; this.modo='edit'; },
      err => this.notify.add(err.message)
    );
    this.dao.query().subscribe(
      data => this.listado = data,
      err => this.notify.add(err.message)
    );
  }

  send() {
    alert(JSON.stringify(this.elemento))
  }
  cancel() {
  }


  ngOnInit(): void {
  }

}
