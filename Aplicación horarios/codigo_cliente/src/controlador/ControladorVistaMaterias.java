/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.dao.MateriasDao;
import modelo.dto.Materia;
import modelo.dto.Usuario;
import vista.VistaHorario;
import vista.VistaMaterias;

/**
 *
 * @author diego
 */
public class ControladorVistaMaterias implements ActionListener {

    private VistaMaterias vistaM;
    private VistaHorario vistaH;
    private Materia materia;
    private int[] posicionCasilla;
    private Usuario usuario;

    public ControladorVistaMaterias(VistaHorario vistaH, VistaMaterias vistaM, int[] posicionCasilla, Materia materia, Usuario usuario) throws IOException, ClassNotFoundException {
        this.vistaH = vistaH;
        this.vistaM = vistaM;
        this.materia = materia;
        this.usuario = usuario;
        this.posicionCasilla = posicionCasilla;
        this.vistaM.jBBorrar.setVisible(false);
        this.vistaM.jBGuardarMateria.addActionListener(this);
        this.vistaM.jBInicio.addActionListener(this);
        this.vistaM.jBBorrar.addActionListener(this);
        if (materia != null) {
            this.vistaM.jBBorrar.setVisible(true);
            this.vistaM.jTMateria.setText(materia.getNombre_materia());
            this.vistaM.jCSemestre.setSelectedItem(materia.getSemestre());
            this.vistaM.jTNumGrupo.setText(String.valueOf(materia.getNumero_grupo()));
            this.vistaM.jCBDuracionClase.setSelectedItem(String.valueOf(materia.getDuracion_clase()));
            this.vistaM.jTNomProfesor.setText(materia.getNombre_profesor());
            this.vistaM.jTLink.setText(materia.getLink());
            this.vistaM.jTSalon.setText(materia.getSalon());
            if (materia.getDias().contains("Lu")) {
                this.vistaM.jRBLunes.setSelected(true);
            }
            if (materia.getDias().contains("Ma")) {
                this.vistaM.jRBMartes.setSelected(true);
            }
            if (materia.getDias().contains("Mi")) {
                this.vistaM.jRBMiercoles.setSelected(true);
            }
            if (materia.getDias().contains("Ju")) {
                this.vistaM.jRBJueves.setSelected(true);
            }
            if (materia.getDias().contains("Vi")) {
                this.vistaM.jRBViernes.setSelected(true);
            }
            if (materia.getDias().contains("Sa")) {
                this.vistaM.jRBSabado.setSelected(true);
            }
        } else {
            if (posicionCasilla[1] == 1) {
                this.vistaM.jRBLunes.setSelected(true);
                this.vistaM.jRBLunes.setEnabled(false);
            }
            if (posicionCasilla[1] == 2) {
                this.vistaM.jRBMartes.setSelected(true);
                this.vistaM.jRBMartes.setEnabled(false);
            }
            if (posicionCasilla[1] == 3) {
                this.vistaM.jRBMiercoles.setSelected(true);
                this.vistaM.jRBMiercoles.setEnabled(false);
            }
            if (posicionCasilla[1] == 4) {
                this.vistaM.jRBJueves.setSelected(true);
                this.vistaM.jRBJueves.setEnabled(false);
            }
            if (posicionCasilla[1] == 5) {
                this.vistaM.jRBViernes.setSelected(true);
                this.vistaM.jRBViernes.setEnabled(false);
            }
            if (posicionCasilla[1] == 6) {
                this.vistaM.jRBSabado.setSelected(true);
                this.vistaM.jRBSabado.setEnabled(false);
            }
        }
        this.vistaM.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaM.jBGuardarMateria)) {
            try{
                ArrayList<String> dias = new ArrayList();
                    if (this.vistaM.jRBLunes.isSelected()) {
                        dias.add("Lu");
                    }
                    if (this.vistaM.jRBMartes.isSelected()) {
                        dias.add("Ma");
                    }
                    if (this.vistaM.jRBMiercoles.isSelected()) {
                        dias.add("Mi");
                    }
                    if (this.vistaM.jRBJueves.isSelected()) {
                        dias.add("Ju");
                    }
                    if (this.vistaM.jRBViernes.isSelected()) {
                        dias.add("Vi");
                    }
                    if (this.vistaM.jRBSabado.isSelected()) {
                        dias.add("Sa");
                    }
                if(this.vistaM.jTMateria.getText().trim().isEmpty()||this.vistaM.jTNumGrupo.getText().trim().isEmpty()||
                dias.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
                }else{
                    Materia materia = new Materia();
                    materia.setNombre_materia(this.vistaM.jTMateria.getText());
                    materia.setSemestre(this.vistaM.jCSemestre.getSelectedItem().toString());
                    materia.setNumero_grupo(Integer.valueOf(this.vistaM.jTNumGrupo.getText()));
                    materia.setDuracion_clase(Integer.valueOf(this.vistaM.jCBDuracionClase.getSelectedItem().toString()));
                    materia.setNombre_profesor(this.vistaM.jTNomProfesor.getText());
                    materia.setLink(this.vistaM.jTLink.getText());
                    materia.setSalon(this.vistaM.jTSalon.getText());
                    materia.setDias(dias);
                    if (this.materia != null) {
                        materia.setID_materia(this.materia.getID_materia());
                    }
                    MateriasDao ma = new MateriasDao();
                    if (ma.verificarMateriaCruzada(materia, posicionCasilla, usuario) == false) {
                        if (this.materia == null) {
                            ma.crearMateria(materia, posicionCasilla, usuario);
                            JOptionPane.showMessageDialog(null, "La materia se ha creado");
                        } else {    
                            this.vistaH.jCalendario = ma.quitarMateriaHorario(materia, this.vistaH.jCalendario);
                            ma.editarMateria(materia, posicionCasilla, usuario);
                            JOptionPane.showMessageDialog(null, "La materia se ha editado");
                        }
                        ma.crearHorario(this.vistaH.jCalendario, usuario);
                        vistaH.setVisible(true);
                        vistaM.setVisible(false);
                        vistaM.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "La materia se cruza con otra, verifique los dias y la duración de la clase");
                    }
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Se ingreso un caracter en un campo numérico");
            }
        }

        if (e.getSource().equals(this.vistaM.jBBorrar)) {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere eliminar la materia?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                MateriasDao ma = new MateriasDao();
                this.vistaH.jCalendario = ma.quitarMateriaHorario(this.materia, this.vistaH.jCalendario);
                ma.eliminarMateria(this.materia);
                JOptionPane.showMessageDialog(null, "La materia se ha borrado");
                ma.crearHorario(this.vistaH.jCalendario, usuario);
                vistaH.setVisible(true);
                vistaM.setVisible(false);
                vistaM.dispose();
            }
        }
        
        if (e.getSource().equals(this.vistaM.jBInicio)) {
            vistaH.setVisible(true);
            vistaM.setVisible(false);
            vistaM.dispose();
        }

    }

}
