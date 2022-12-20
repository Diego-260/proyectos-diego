// Rutas para asistente
const express = require('express');
const router = express.Router();
const asistenteController = require('../controllers/asistenteController');

//api/asistentes
router.post('/', asistenteController.crearAsistente);
router.get('/', asistenteController.obtenerAsistentes);
router.put('/:id', asistenteController.actualizarAsistente);
router.get('/:id', asistenteController.obtenerAsistente);
router.delete('/:id', asistenteController.borrarAsistente);

module.exports = router;