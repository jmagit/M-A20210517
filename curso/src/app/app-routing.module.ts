import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActoresListComponent, ActoresAddComponent, ActoresEditComponent, ActoresViewComponent } from './actores';
import { CalculadoraComponent } from './calculadora/calculadora.component';
import { ContactosAddComponent, ContactosEditComponent, ContactosListComponent, ContactosViewComponent } from './contactos';
import { DemosComponent } from './demos/demos.component';
import { HomeComponent } from './main/home/home.component';
import { PageNotFoundComponent } from './main/page-not-found/page-not-found.component';
import { AuthGuard } from './security';

"contactos/1/ped"
const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full'},
  {path: 'inicio', component: HomeComponent },
  {path: 'demos', component: DemosComponent },
  {path: 'chisme/de/hacer/numeros', component: CalculadoraComponent, data: { pageTitle: 'Calculadora' } },
  {path: 'contactos', component: ContactosListComponent, data: { pageTitle: 'Contactos' } },
  {path: 'contactos/add', component: ContactosAddComponent },
  {path: 'contactos/:id/edit', component: ContactosEditComponent },
  {path: 'contactos/:id', component: ContactosViewComponent },
  {path: 'contactos/:id/:kk', component: ContactosViewComponent },
  {path: 'actores', children: [
    {path: '', component: ActoresListComponent },
    {path: 'add', component: ActoresAddComponent },
    {path: ':id/edit', component: ActoresEditComponent },
    {path: ':id', component: ActoresViewComponent },
    {path: ':id/:kk', component: ActoresViewComponent },
  ], canActivate: [ AuthGuard ]},
  {path: 'umberto/langforth', redirectTo:'/contactos/4' },
  {path: 'config', loadChildren: () => import('./config/config.module').then(mod => mod.ConfigModule)},
  {path: '404.html', component: PageNotFoundComponent },
  {path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
