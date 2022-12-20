/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.MateriasDao;
import modelo.dao.UsuarioDao;
import modelo.dto.Materia;
import modelo.dto.Perfil;
import modelo.dto.Usuario;
import vista.VistaHorario;
import vista.VistaMaterias;
import vista.VistaPrincipal;

/**
 *
 * @author diego
 */
public class ControladorVistaHorario extends MouseAdapter implements ActionListener{
    private VistaPrincipal vistaP;
    private VistaHorario vistaH;
    private Usuario usuario;

    public ControladorVistaHorario(VistaPrincipal vistaP, VistaHorario vistaH, Usuario usuario) throws ClassNotFoundException{
        this.vistaP = vistaP; 
        this.vistaH = vistaH;
        this.usuario = usuario;
        this.vistaH.jBInicio.addActionListener(this);
        this.vistaH.jCalendario.addMouseListener(this);
        MateriasDao ma = new MateriasDao();
        this.vistaH.jCalendario = ma.crearHorario(this.vistaH.jCalendario, this.usuario);
        this.vistaH.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaH.jBInicio)) {
            vistaP.setVisible(true);
            vistaH.setVisible(false);
            vistaH.dispose();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.vistaH.jCalendario.getSelectedColumn() != 0){
            VistaMaterias vistaM = new VistaMaterias();
            int[] posicionCasilla = new int[2];
            posicionCasilla[0] = this.vistaH.jCalendario.getSelectedRow();
            posicionCasilla[1] = this.vistaH.jCalendario.getSelectedColumn();
            if(this.vistaH.jCalendario.getValueAt(posicionCasilla[0], posicionCasilla[1])==null){
                try {
                    ControladorVistaMaterias controllerM = new ControladorVistaMaterias(vistaH, vistaM, posicionCasilla, null, usuario);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                MateriasDao ma = new MateriasDao();
                Materia materia = ma.buscarMateria(posicionCasilla, usuario);
                try {
                    ControladorVistaMaterias controllerM = new ControladorVistaMaterias(vistaH, vistaM, posicionCasilla, materia, usuario);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            vistaM.setVisible(true);
            vistaH.setVisible(false);
        }

    }

}
