const express = require('express');
const conectarDB = require('./config/db')
const cors = require('cors');

// Se crea el servidor
const app = express();

//Conectar a la base de datos
conectarDB();

app.use(cors());

app.use(express.json());

app.use('/api/asistentes', require('./routes/asistente'));

app.listen(4000, () => {
    console.log('El servidor esta escuchando')
})