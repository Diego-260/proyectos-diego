import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//componentes
import { AgregarAsistenteComponent } from './components/agregar-asistente/agregar-asistente.component';
import { ListarAsistentesComponent } from './components/listar-asistentes/listar-asistentes.component';

const routes: Routes = [
  {path: '', component: AgregarAsistenteComponent},
  {path: 'listar-asistentes', component: ListarAsistentesComponent},
  {path: 'editar-asistente/:id', component: AgregarAsistenteComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
