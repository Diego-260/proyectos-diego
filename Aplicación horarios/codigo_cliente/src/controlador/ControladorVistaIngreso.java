/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.dao.UsuarioDao;
import modelo.dto.Perfil;
import vista.VistaRegistro;
import vista.VistaIngreso;
import vista.VistaPrincipal;
/**
 *
 * @author diego
 */
public class ControladorVistaIngreso implements ActionListener{
    
    private VistaIngreso vistaI;

    public ControladorVistaIngreso(VistaIngreso vistaI) throws IOException, ClassNotFoundException{
        this.vistaI = vistaI;
        this.vistaI.jBIngresar.addActionListener(this);
        this.vistaI.jBRegistrar.addActionListener(this);
        this.vistaI.jBSalir.addActionListener(this);
        this.vistaI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaI.jBIngresar)) {
            Perfil perfil = new Perfil();
            perfil.setNombre_usuario(this.vistaI.jTUsuario.getText());
            perfil.setContrasena(String.valueOf(this.vistaI.jTContrasena.getPassword()));
            UsuarioDao us = new UsuarioDao();
            if (us.verificarPerfil(perfil)){
                VistaPrincipal vistaP = new VistaPrincipal();
                try {
                ControladorVistaPrincipal controllerP = new ControladorVistaPrincipal(vistaP, perfil);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorVistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorVistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
                }
                vistaP.setVisible(true);
                vistaI.setVisible(false);
                vistaI.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "La contraseña o el nombre de usuario son incorrectos");
            }      
        }
        
        if (e.getSource().equals(this.vistaI.jBRegistrar)) {
            VistaRegistro vistaR = new VistaRegistro();
            try {
                ControladorVistaRegistro controllerR = new ControladorVistaRegistro(vistaI, vistaR);
            } catch (IOException ex) {
                Logger.getLogger(ControladorVistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorVistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
            }
            vistaR.setVisible(true);
            vistaI.setVisible(false);
            vistaI.dispose();
        }
        
        if (e.getSource().equals(this.vistaI.jBSalir)) {
            System.exit(0);
        }
        
    }
    
}
