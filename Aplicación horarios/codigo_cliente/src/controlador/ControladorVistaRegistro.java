/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import modelo.dao.UsuarioDao;
import modelo.dto.Perfil;
import vista.VistaRegistro;
import vista.VistaIngreso;
import modelo.dto.Usuario;

/**
 *
 * @author diego
 */
public class ControladorVistaRegistro implements ActionListener{
   private VistaRegistro vistaR;
   private VistaIngreso vistaI;

    public ControladorVistaRegistro(VistaIngreso vistaI ,VistaRegistro vistaR) throws IOException, ClassNotFoundException{
        this.vistaR = vistaR;
        this.vistaI = vistaI;
        this.vistaR.jBCrearPerfil.addActionListener(this);
        this.vistaR.jBIngreso.addActionListener(this);
        this.vistaR.jBSalir.addActionListener(this);
        this.vistaR.setVisible(true);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaR.jBCrearPerfil)) {
            if(this.vistaR.jTNombre.getText().trim().isEmpty()||this.vistaR.jTCorreo.getText().trim().isEmpty()||
            this.vistaR.jTUsuario.getText().trim().isEmpty()||this.vistaR.jTContrasena1.getPassword().length == 0){
                JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
            }else{
                Usuario usuario = new Usuario();
                Perfil perfil = new Perfil();
                usuario.setNombre(this.vistaR.jTNombre.getText());
                usuario.setCorreo(this.vistaR.jTCorreo.getText());
                usuario.setInstitucion(this.vistaR.jTInstitucion.getText());
                perfil.setNombre_usuario(this.vistaR.jTUsuario.getText());
                perfil.setContrasena(String.valueOf(this.vistaR.jTContrasena1.getPassword()));
                if (perfil.getContrasena().equals(String.valueOf(this.vistaR.jTContrasena2.getPassword()))){
                    UsuarioDao us = new UsuarioDao();
                    us.crearUsuario(usuario, perfil);
                    JOptionPane.showMessageDialog(null, "Perfil creado");
                }else{
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                }
            }
        }
        
        if (e.getSource().equals(this.vistaR.jBIngreso)) {
            vistaI.setVisible(true);
            vistaR.setVisible(false);
            vistaR.dispose();
        }
        
        if (e.getSource().equals(this.vistaR.jBSalir)) {
            System.exit(0);
        }
        
       
    }
}
