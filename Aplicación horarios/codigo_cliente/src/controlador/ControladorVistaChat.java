/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import modelo.dao.Cliente;
import modelo.dto.Perfil;
import vista.VistaChat;
import vista.VistaPrincipal;

/**
 *
 * @author diego
 */
public class ControladorVistaChat implements ActionListener{
    private VistaPrincipal vistaP;
    private VistaChat vistaC;
    private Perfil perfil;
    private Cliente cliente;

    public ControladorVistaChat(VistaPrincipal vistaP, VistaChat vistaC, Perfil perfil) throws IOException, ClassNotFoundException{
        this.vistaP = vistaP;
        this.vistaC = vistaC;
        this.perfil = perfil;
        cliente = new Cliente(this.vistaC, this.perfil);
        this.vistaC.jBEnviar.addActionListener(this);
        this.vistaC.jBPrincipal.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){ 
        if (e.getSource().equals(this.vistaC.jBEnviar)) {
            this.vistaC.jTAChat.append("\n"+ this.vistaC.jTMensaje.getText());
            cliente.iniciarCliente();
            this.vistaC.jTMensaje.setText(null);
        }
    
        if (e.getSource().equals(this.vistaC.jBPrincipal)) {
            cliente.detenerHilo();
            cliente.iniciarCliente();
            cliente.desconectarse();
            vistaP.setVisible(true);
            vistaC.setVisible(false);
            vistaC.dispose();  
        }
        
    }
    
}
