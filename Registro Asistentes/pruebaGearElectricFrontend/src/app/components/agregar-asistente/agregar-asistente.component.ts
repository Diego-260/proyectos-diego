import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Asistente } from 'src/app/models/asistente';
import { AsistenteService } from 'src/app/services/asistente.service';

@Component({
  selector: 'app-agregar-asistente',
  templateUrl: './agregar-asistente.component.html',
  styleUrls: ['./agregar-asistente.component.css']
})
export class AgregarAsistenteComponent implements OnInit {
  asistenteForm: FormGroup;
  id: String | null;
  titulo = 'Agregar asistente';
  textoBoton = 'REGISTRAR NUEVO ASISTENTE';
  editar = false;

  constructor(private fb: FormBuilder,
              private router: Router,
              private toastr: ToastrService,
              private _asistenteService: AsistenteService,
              private aRouter: ActivatedRoute) { 
    this.asistenteForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.maxLength(100)]],
      tipoDoc: ['',Validators.required],
      numDoc: ['', [Validators.required, Validators.maxLength(30)]],
      telefono: ['', [Validators.required, Validators.maxLength(10), Validators.pattern("^[0-9]*$")]],
      email: ['', [Validators.required, Validators.email]]
    })
    this.id = this.aRouter.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    this.esEditar();
  }

  agregarAsistente(){
    const ASISTENTE: Asistente = {
      nombre: this.asistenteForm.get('nombre')?.value,
      tipoDoc: this.asistenteForm.get('tipoDoc')?.value,
      numDoc: this.asistenteForm.get('numDoc')?.value,
      telefono: parseInt(this.asistenteForm.get('telefono')?.value),
      email: this.asistenteForm.get('email')?.value,
    }

    if(this.id !== null){
      //editar asistente
      this._asistenteService.editAsistente(this.id, ASISTENTE).subscribe(data =>{
        this.toastr.info('Se ha actualizado la información del asistente', 'Asistente actualizado');
        this.router.navigate(['/listar-asistentes']);
      }, error => {
        console.log(error);
        this.asistenteForm.reset();
      })
    }else{
      //crear asistente
      this._asistenteService.createAsistente(ASISTENTE).subscribe(data => {
        this.toastr.success('Se ha registrado un asistente al evento', 'Asistente agregado');
        this.router.navigate(['/listar-asistentes']);
      }, error => {
        console.log(error);
        this.asistenteForm.reset();
      })
    }   
  }

  esEditar(){
    if(this.id !== null){
      this.titulo = 'Editar asistente';
      this.textoBoton = 'EDITAR';
      this.editar = true;
      this._asistenteService.getAsistente(this.id).subscribe(data =>{
        this.asistenteForm.setValue({
          nombre: data.nombre,
          tipoDoc: data.tipoDoc,
          numDoc: data.numDoc,
          telefono: data.telefono,
          email: data.email
        })
      })
    }
  }
  
  public inputValidatorNumber(event: any) {
    //console.log(event.target.value);
    const pattern = /^[0-9]*$/;   
    //let inputChar = String.fromCharCode(event.charCode)
    if (!pattern.test(event.target.value)) {
      event.target.value = event.target.value.replace(/[^0-9]/g, "");
      // invalid character, prevent input
    }
  }

}