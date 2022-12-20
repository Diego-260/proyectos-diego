import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})

export class FilterPipe implements PipeTransform {

  transform(value: any, args: String[]): any {
    if (args[0] == '' || args[1] == '' || args[1].length < 2) return value;

    const resultAsistentes = [];
    let registro = '';
    for (const asistente of value) {

      if (args[0] == 'nombre') {
        registro = asistente.nombre;
      } else if(args[0] == 'numDoc'){
        registro = asistente.numDoc;
      }else{
        registro = asistente.email;
      }

      if (registro.toLowerCase().indexOf(args[1].toLowerCase()) > -1) {
        resultAsistentes.push(asistente);
      }
    }
    return resultAsistentes;
  }

}
