/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_proyectoarquitectura;

import modelo.dto.Paquete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author diego
 */
public class Servidor implements Runnable{
    
    private VistaServidor vistaS;
    
    public Servidor(VistaServidor vistaS) {
        this.vistaS = vistaS;
        Thread miHilo = new Thread(this);
        miHilo.start();
        this.vistaS.setVisible(true);
    }
    
    @Override
    public void run(){
        try {
            ServerSocket servidor = new ServerSocket(9999);
            Paquete paquete_recibido;
            HashMap <String,String> listaConectados = new HashMap<String,String>();
            while (true) {
                Socket miSocket = servidor.accept();
                ObjectInputStream flujo_entrada = new ObjectInputStream(miSocket.getInputStream());
                paquete_recibido = (Paquete) flujo_entrada.readObject();
                if (paquete_recibido.getMensaje().equals(" online") && paquete_recibido.getIp().equals("Online")){
                    InetAddress localizacion = miSocket.getInetAddress();
                    String ip_remota = localizacion.getHostAddress();
                    System.out.println("Online" + ip_remota);
                    listaConectados.put(ip_remota, paquete_recibido.getNombre_usuario());
                    paquete_recibido.setConectados(listaConectados);
                    for(Map.Entry<String, String> entry : listaConectados.entrySet()) {
                        String ip = entry.getKey();
                        String nombre_usuario = entry.getValue();
                        Socket enviaPaquete = new Socket(ip, 9090);
                        ObjectOutputStream flujo_salida = new ObjectOutputStream(enviaPaquete.getOutputStream());
                        flujo_salida.writeObject(paquete_recibido);
                        flujo_salida.close();
                        enviaPaquete.close();
                        miSocket.close();
                    }
                }else if (paquete_recibido.getMensaje().equals("Desconectarse") && paquete_recibido.getIp().equals("Desconectarse")){
                    InetAddress localizacion = miSocket.getInetAddress();
                    String ip_remota = localizacion.getHostAddress();
                    listaConectados.remove(ip_remota);
                    System.out.println(ip_remota + "Desconectado");
                }else{
                    this.vistaS.jTAMensajes.append("\n" + paquete_recibido.getNombre_usuario() + ": "+ paquete_recibido.getMensaje() + " para " + paquete_recibido.getIp());
                    Socket enviaPaquete = new Socket(paquete_recibido.getIp(), 9090);
                    ObjectOutputStream flujo_salida = new ObjectOutputStream(enviaPaquete.getOutputStream());
                    flujo_salida.writeObject(paquete_recibido);
                    flujo_salida.close();
                    enviaPaquete.close();
                    miSocket.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
