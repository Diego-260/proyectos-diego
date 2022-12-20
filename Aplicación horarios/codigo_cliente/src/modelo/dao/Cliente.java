/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dto.Paquete;
import modelo.dto.Perfil;
import vista.VistaChat;

/**
 *
 * @author diego
 */
public class Cliente implements Runnable{
    private VistaChat vistaC;
    private Perfil perfil;
    private Boolean continuarHilo;
    private HashMap <String,String> nombresMenu;

    public Cliente(VistaChat vistaC, Perfil perfil) {
        this.vistaC = vistaC;
        this.perfil = perfil;
        this.continuarHilo = true;
        enviarOnline();
        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    public void detenerHilo() {
        continuarHilo = false;
    }
    
    public void enviarOnline(){
        try {
            Socket miSocket = new Socket("192.168.0.6", 9999);
            Paquete datos = new Paquete();
            datos.setNombre_usuario(perfil.getNombre_usuario());
            datos.setMensaje(" online");
            datos.setIp("Online");
            ObjectOutputStream flujo_salida = new ObjectOutputStream(miSocket.getOutputStream());
            flujo_salida.writeObject(datos);
            flujo_salida.close();
            miSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconectarse(){
        try{
            Socket miSocket = new Socket("192.168.0.6", 9999);
            Paquete datos = new Paquete();
            datos.setMensaje("Desconectarse");
            datos.setIp("Desconectarse");
            ObjectOutputStream flujo_salida = new ObjectOutputStream(miSocket.getOutputStream());
            flujo_salida.writeObject(datos);
            flujo_salida.close();
            miSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void iniciarCliente() {
            try {
                Socket miSocket = new Socket("192.168.0.6", 9999);
                Paquete paquete = new Paquete();
                paquete.setNombre_usuario(perfil.getNombre_usuario());
                String nombreUsuarioEnvio = (String) this.vistaC.jCBContacto.getSelectedItem();
                paquete.setIp(getSingleKeyFromValue(nombresMenu, nombreUsuarioEnvio));
                paquete.setMensaje(this.vistaC.jTMensaje.getText());
                ObjectOutputStream flujo_salida = new ObjectOutputStream(miSocket.getOutputStream());
                flujo_salida.writeObject(paquete);
                flujo_salida.close();
                miSocket.close();
            } catch (UnknownHostException e1) {              
                e1.printStackTrace();
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }    
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor_cliente = new ServerSocket(9090);
            Socket cliente;
            Paquete paquete_recibido;
            while (continuarHilo) {
                cliente = servidor_cliente.accept();
                ObjectInputStream flujo_entrada = new ObjectInputStream(cliente.getInputStream());
                paquete_recibido = (Paquete) flujo_entrada.readObject();
                if(paquete_recibido.getConectados()== null){
                    this.vistaC.jTAChat.append("\n"+ paquete_recibido.getNombre_usuario() + ": " + paquete_recibido.getMensaje());
                }else{
                    nombresMenu = new HashMap<String,String>();
                    nombresMenu = paquete_recibido.getConectados();
                    this.vistaC.jCBContacto.removeAllItems();
                    for(Map.Entry<String, String> entry : nombresMenu.entrySet()) {
                        String nombre_usuario = entry.getValue();
                        this.vistaC.jCBContacto.addItem(nombre_usuario);
                    }     
                }
            }
            System.out.println("Hilo terminado");
            servidor_cliente.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static String getSingleKeyFromValue(HashMap<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    
}
