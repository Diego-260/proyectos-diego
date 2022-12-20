const Asistente = require("../models/Asistente");

exports.crearAsistente = async (req, res) => {

    try {
        let asistente;

        //Crear el asistente
        asistente = new Asistente(req.body);

        await asistente.save();
        res.send(asistente);
        
    } catch (error) {
        console.log(error);
        res.status(500).send('Hubo un error');
    }

}

exports.obtenerAsistentes = async (req, res) => {

    try {
        const asistentes = await Asistente.find();
        res.json(asistentes);
    } catch (error) {
        console.log(error);
        res.status(500).send('Hubo un error');
    }
}

exports.actualizarAsistente = async (req, res) => {

    try {
        const {nombre, tipoDoc, numDoc, telefono, email} = req.body;
        let asistente = await Asistente.findById(req.params.id);

        if(!asistente){
            res.status.json({ msg: "El asistente no existe"})
        }

        asistente.nombre = nombre;
        asistente.tipoDoc = tipoDoc;
        asistente.numDoc = numDoc;
        asistente.telefono = telefono;
        asistente.email = email;

        asistente = await Asistente.findOneAndUpdate({ _id: req.params.id}, asistente, {new: true});
        res.json(asistente);

    } catch (error) {
        console.log(error);
        res.status(500).send('Hubo un error');
    }
}

exports.obtenerAsistente = async (req, res) => {

    try {
        let asistente = await Asistente.findById(req.params.id);

        if(!asistente){
            res.status.json({ msg: "El asistente no existe"})
        }

        res.json(asistente);

    } catch (error) {
        console.log(error);
        res.status(500).send('Hubo un error');
    }
}

exports.borrarAsistente = async (req, res) => {

    try {
        let asistente = await Asistente.findById(req.params.id);

        if(!asistente){
            res.status.json({ msg: "El asistente no existe"})
        }

        await Asistente.findOneAndRemove({_id: req.params.id});
        res.json({ msg: 'El asistente ha sido borrado'});

    } catch (error) {
        console.log(error);
        res.status(500).send('Hubo un error');
    }
}