export class Asistente{
    _id?: String;
    nombre: String;
    tipoDoc: String;
    numDoc: String;
    telefono: number;
    email: String;

    constructor(nombre: String, tipoDoc: String, numDoc: String, telefono: number, email: String){
        this.nombre = nombre;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.telefono = telefono;
        this.email = email;
    }

}