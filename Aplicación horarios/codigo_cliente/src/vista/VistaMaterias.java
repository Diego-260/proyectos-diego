/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author USUARIO
 */
public class VistaMaterias extends javax.swing.JFrame {

    /**
     * Creates new form Materias
     */
    public VistaMaterias() {
        initComponents();
        this.setLocationRelativeTo(null);
        ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/logo1.png"));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(logoApp_lbl.getWidth(), logoApp_lbl.getHeight(), Image.SCALE_DEFAULT));
        logoApp_lbl.setIcon(icono);
        ImageIcon imagen2 = new ImageIcon(getClass().getResource("/Imagenes/home.png"));
        Icon icono2 = new ImageIcon(imagen2.getImage().getScaledInstance(jBInicio.getWidth(), jBInicio.getHeight(), Image.SCALE_DEFAULT));
        jBInicio.setIcon(icono2);
        ImageIcon imagen3 = new ImageIcon(getClass().getResource("/Imagenes/borrar.png"));
        Icon icono3 = new ImageIcon(imagen3.getImage().getScaledInstance(jBBorrar.getWidth(), jBBorrar.getHeight(), Image.SCALE_DEFAULT));
        jBBorrar.setIcon(icono3);
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBBorrar = new javax.swing.JButton();
        logoApp_lbl = new javax.swing.JLabel();
        jBInicio = new javax.swing.JButton();
        fondoPerfil_lbl = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jBGuardarMateria = new javax.swing.JButton();
        jTNumGrupo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTMateria = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTNomProfesor = new javax.swing.JTextField();
        jRBMiercoles = new javax.swing.JRadioButton();
        jRBJueves = new javax.swing.JRadioButton();
        jRBViernes = new javax.swing.JRadioButton();
        jRBSabado = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTSalon = new javax.swing.JTextField();
        jTLink = new javax.swing.JTextField();
        jRBLunes = new javax.swing.JRadioButton();
        jRBMartes = new javax.swing.JRadioButton();
        jCSemestre = new javax.swing.JComboBox<>();
        jCBDuracionClase = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        jBBorrar.setContentAreaFilled(false);
        jBBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBorrarActionPerformed(evt);
            }
        });
        getContentPane().add(jBBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 60, 60));

        logoApp_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo1.png"))); // NOI18N
        getContentPane().add(logoApp_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 60));

        jBInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/home.png"))); // NOI18N
        jBInicio.setContentAreaFilled(false);
        jBInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInicioActionPerformed(evt);
            }
        });
        getContentPane().add(jBInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 50, 50));

        fondoPerfil_lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/wp4009285.jpg"))); // NOI18N
        getContentPane().add(fondoPerfil_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 552, 80));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 8));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBGuardarMateria.setText("GUARDAR MATERIA");
        jBGuardarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarMateriaActionPerformed(evt);
            }
        });
        jPanel1.add(jBGuardarMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 150, 40));

        jTNumGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTNumGrupoKeyTyped(evt);
            }
        });
        jPanel1.add(jTNumGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 190, 30));

        jLabel9.setText("Num. de Grupo:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, 30));

        jLabel8.setText("Semestre:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 70, 30));
        jPanel1.add(jTMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 190, 30));

        jLabel7.setText("Materia:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 70, 30));

        jLabel10.setText("Duración clase:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 90, 30));

        jLabel11.setText("Dias:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 50, 30));

        jLabel12.setText("Nom. Profesor:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 90, 30));

        jTNomProfesor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTNomProfesorKeyTyped(evt);
            }
        });
        jPanel1.add(jTNomProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 190, 30));

        jRBMiercoles.setText("Miercoles");
        jPanel1.add(jRBMiercoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, -1));

        jRBJueves.setText("Jueves");
        jPanel1.add(jRBJueves, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, -1, -1));

        jRBViernes.setText("Viernes");
        jPanel1.add(jRBViernes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        jRBSabado.setText("Sabado");
        jPanel1.add(jRBSabado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        jLabel13.setText("Link:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 70, 30));

        jLabel14.setText("Salon:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 70, 30));
        jPanel1.add(jTSalon, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 190, 30));
        jPanel1.add(jTLink, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 190, 30));

        jRBLunes.setText("Lunes");
        jPanel1.add(jRBLunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

        jRBMartes.setText("Martes");
        jPanel1.add(jRBMartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jCSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jCSemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSemestreActionPerformed(evt);
            }
        });
        jPanel1.add(jCSemestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 190, 30));

        jCBDuracionClase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        jCBDuracionClase.setToolTipText("");
        jPanel1.add(jCBDuracionClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 190, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 340, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInicioActionPerformed
  
    }//GEN-LAST:event_jBInicioActionPerformed

    private void jBGuardarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarMateriaActionPerformed
       
    }//GEN-LAST:event_jBGuardarMateriaActionPerformed

    private void jTNumGrupoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTNumGrupoKeyTyped

    }//GEN-LAST:event_jTNumGrupoKeyTyped

    private void jTNomProfesorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTNomProfesorKeyTyped

    }//GEN-LAST:event_jTNomProfesorKeyTyped

    private void jCSemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSemestreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCSemestreActionPerformed

    private void jBBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBBorrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                new VistaMaterias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondoPerfil_lbl;
    public javax.swing.JButton jBBorrar;
    public javax.swing.JButton jBGuardarMateria;
    public javax.swing.JButton jBInicio;
    public javax.swing.JComboBox jCBDuracionClase;
    public javax.swing.JComboBox<String> jCSemestre;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JRadioButton jRBJueves;
    public javax.swing.JRadioButton jRBLunes;
    public javax.swing.JRadioButton jRBMartes;
    public javax.swing.JRadioButton jRBMiercoles;
    public javax.swing.JRadioButton jRBSabado;
    public javax.swing.JRadioButton jRBViernes;
    public javax.swing.JTextField jTLink;
    public javax.swing.JTextField jTMateria;
    public javax.swing.JTextField jTNomProfesor;
    public javax.swing.JTextField jTNumGrupo;
    public javax.swing.JTextField jTSalon;
    private javax.swing.JLabel logoApp_lbl;
    // End of variables declaration//GEN-END:variables
}
