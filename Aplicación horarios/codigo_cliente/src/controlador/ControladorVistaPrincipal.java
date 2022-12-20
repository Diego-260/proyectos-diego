/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
/**
 *
 * @author diego
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.UsuarioDao;
import modelo.dto.Perfil;
import modelo.dto.Usuario;
import vista.VistaChat;
import vista.VistaEditPerfil;
import vista.VistaHorario;
import vista.VistaIngreso;
import vista.VistaPrincipal;

public class ControladorVistaPrincipal implements ActionListener{
    private VistaPrincipal vistaP;
    private Perfil perfil;

    public ControladorVistaPrincipal(VistaPrincipal vistaP, Perfil perfil) throws IOException, ClassNotFoundException{
        this.vistaP = vistaP;
        this.perfil = perfil;
        this.vistaP.jBChat.addActionListener(this);
        this.vistaP.jBHorario.addActionListener(this);
        this.vistaP.jBPerfil.addActionListener(this);
        this.vistaP.jBExit.addActionListener(this);
        this.vistaP.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaP.jBChat)) {
            VistaChat vistaC = new VistaChat();
            try {
                ControladorVistaChat controllerC = new ControladorVistaChat(vistaP, vistaC, perfil);
            } catch (IOException ex) {
                Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            vistaC.setVisible(true);
            vistaP.setVisible(false);
            vistaP.dispose();    
        }
        
        if (e.getSource().equals(this.vistaP.jBHorario)) {
            VistaHorario vistaH = new VistaHorario();
            UsuarioDao us = new UsuarioDao();
            Usuario usuario = us.buscarDatos(this.perfil);
            try {
                ControladorVistaHorario controllerH = new ControladorVistaHorario(vistaP, vistaH, usuario);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            vistaH.setVisible(true);
            vistaP.setVisible(false);
            vistaP.dispose();    
        }
        
        if (e.getSource().equals(this.vistaP.jBPerfil)) {
            VistaEditPerfil vistaE = new VistaEditPerfil();
            try {
                ControladorVistaEditPerfil controllerE = new ControladorVistaEditPerfil(vistaP, vistaE, perfil);
            } catch (IOException ex) {
                Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } 
            vistaE.setVisible(true);
            vistaP.setVisible(false);
            vistaP.dispose();    
        }
        
        if (e.getSource().equals(this.vistaP.jBExit)){
            VistaIngreso vistaI = new VistaIngreso();
            try {
                ControladorVistaIngreso controllerI = new ControladorVistaIngreso(vistaI);
            } catch (IOException ex) {
                Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorVistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } 
            vistaI.setVisible(true);
            vistaP.setVisible(false);
            vistaP.dispose(); 
        }
        
    }
    
}
    
