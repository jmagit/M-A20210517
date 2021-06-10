import { HttpClient, HttpContext, HttpContextToken } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RESTDAOService } from '../base-code/RESTDAOService';
import { NotificationService } from '../common-services';
import { AUTH_REQUIRED } from '../security';

@Injectable({
  providedIn: 'root'
})
export class ActoresDAOService extends RESTDAOService<any, number> {
  constructor(http: HttpClient) {
    super(http, 'actores', { context: new HttpContext().set(AUTH_REQUIRED, true) })
  }
  paginado(page: number = 0): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}?page=${page}&rows=10`, this.option);
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
  }

  send() {
    alert(JSON.stringify(this.elemento))
  }
  cancel() {
  }


  ngOnInit(): void {
  }
  page = 0;
  maxPage = 0;
  list() {
    this.dao.paginado(this.page).subscribe(
      data => {
        this.listado = data.content;
        this.page = data.number;
        this.maxPage = data.totalPages;
      },
      err => this.notify.add(err.message)
    );

  }
}
