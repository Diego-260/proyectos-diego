const mongoose = require('mongoose');

const AsistenteSchema = mongoose.Schema({
    nombre: {
        type: String,
        require: true,
    },
    tipoDoc: {
        type: String,
        require: true,
    },
    numDoc: {
        type: String,
        require: true,
    },
    telefono: {
        type: Number,
        require: true,
    },
    email: {
        type: String,
        require: true,
    }
});

module.exports = mongoose.model('Asistente', AsistenteSchema);