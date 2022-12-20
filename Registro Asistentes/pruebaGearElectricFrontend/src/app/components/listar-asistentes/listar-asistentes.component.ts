import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Asistente } from 'src/app/models/asistente';
import { AsistenteService } from 'src/app/services/asistente.service';

@Component({
  selector: 'app-listar-asistentes',
  templateUrl: './listar-asistentes.component.html',
  styleUrls: ['./listar-asistentes.component.css']
})
export class ListarAsistentesComponent implements OnInit {

  listaAsistentes: Asistente[] = [];
  columnaFiltro = '';
  filtroAsistentes = '';

  constructor(private _asistenteService: AsistenteService,
              private toastr: ToastrService) { }

  ngOnInit(): void {
    this.obtenerAsistentes();
  }

  obtenerAsistentes() {
    this._asistenteService.getAsistentes().subscribe(data => {
      this.listaAsistentes = data;
    }, error => {
      console.log(error);
    })
  }

  borrarAsistente(id: any) {
    this._asistenteService.deleteAsistente(id).subscribe(data => {
      this.toastr.error('El asistente se ha borrado de la lista', 'Asistente borrado');
      this.obtenerAsistentes();
    }, error => {
      console.log(error);
    })
  }

}
