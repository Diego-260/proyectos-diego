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
import javax.swing.JOptionPane;
import modelo.dao.UsuarioDao;
import modelo.dto.Perfil;
import modelo.dto.Usuario;
import vista.VistaEditPerfil;
import vista.VistaIngreso;
import vista.VistaPrincipal;

public class ControladorVistaEditPerfil implements ActionListener {

    private VistaEditPerfil vistaE;
    private VistaPrincipal vistaP;
    private Perfil perfil;
    private Usuario usuario;

    public ControladorVistaEditPerfil(VistaPrincipal vistaP, VistaEditPerfil vistaE, Perfil perfil) throws IOException, ClassNotFoundException {
        this.vistaP = vistaP;
        this.vistaE = vistaE;
        this.perfil = perfil;
        this.vistaE.jBEditarPerfil.addActionListener(this);
        this.vistaE.jBPrincipal.addActionListener(this);
        this.vistaE.jBBorrar.addActionListener(this);
        UsuarioDao us = new UsuarioDao();
        this.usuario = us.buscarDatos(this.perfil);
        this.vistaE.jTNombre.setText(usuario.getNombre());
        this.vistaE.jTCorreo.setText(usuario.getCorreo());
        this.vistaE.jTInstitucion.setText(usuario.getInstitucion());
        this.vistaE.jTUsuario.setText(perfil.getNombre_usuario());
        this.vistaE.jConstrasena1.setText(perfil.getContrasena());
        this.vistaE.jConstrasena2.setText(perfil.getContrasena());
        this.vistaE.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaE.jBEditarPerfil)) {
            if(this.vistaE.jTNombre.getText().trim().isEmpty()||this.vistaE.jTCorreo.getText().trim().isEmpty()||
            this.vistaE.jTUsuario.getText().trim().isEmpty()||this.vistaE.jConstrasena1.getPassword().length == 0){
                JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
            }else{
                if (String.valueOf(this.vistaE.jConstrasena1.getPassword()).equals(String.valueOf(this.vistaE.jConstrasena2.getPassword()))) {
                    this.usuario.setNombre(this.vistaE.jTNombre.getText());
                    this.usuario.setCorreo(this.vistaE.jTCorreo.getText());
                    this.usuario.setInstitucion(this.vistaE.jTInstitucion.getText());
                    this.perfil.setNombre_usuario(this.vistaE.jTUsuario.getText());
                    this.perfil.setContrasena(String.valueOf(this.vistaE.jConstrasena1.getPassword()));
                    UsuarioDao us = new UsuarioDao();
                    us.actualizarDatos(this.usuario, this.perfil);
                    JOptionPane.showMessageDialog(null, "Los datos se han actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                }
            }
        }

        if (e.getSource().equals(this.vistaE.jBPrincipal)) {
            VistaPrincipal vistaP = new VistaPrincipal();
            try {
                ControladorVistaPrincipal controllerP = new ControladorVistaPrincipal(vistaP, this.perfil);
            } catch (IOException ex) {
                Logger.getLogger(ControladorVistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorVistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
            }
            vistaP.setVisible(true);
            vistaE.setVisible(false);
            vistaE.dispose();
        }

        if (e.getSource().equals(this.vistaE.jBBorrar)) {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere eliminar el perfil?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                UsuarioDao us = new UsuarioDao();
                us.eliminarUsuario(this.usuario);
                JOptionPane.showMessageDialog(null, "Se ha eliminido el perfil");
                VistaIngreso vistaI = new VistaIngreso();
                try {
                    ControladorVistaIngreso controllerI = new ControladorVistaIngreso(vistaI);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorVistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorVistaIngreso.class.getName()).log(Level.SEVERE, null, ex);
                }
                vistaI.setVisible(true);
                vistaE.setVisible(false);
                vistaE.dispose();
            }
        }
    }

}
