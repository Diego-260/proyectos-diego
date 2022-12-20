/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlador.ControladorVistaIngreso;
import java.io.IOException;
import vista.VistaIngreso;

/**
 *
 * @author diego
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ControladorVistaIngreso controladorVI = new ControladorVistaIngreso(new VistaIngreso());
    }
}
