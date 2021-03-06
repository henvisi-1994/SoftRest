/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.vistas;

import SoftRest.controladores.cUsuario;
import SoftRest.vistas.FrmMenuPrincipal;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author lab06-maq16
 */
public class frmInicioSesion extends javax.swing.JFrame {

    private cUsuario lis;
    /**
     * Creates new form InicioSesión
     */
    public frmInicioSesion() {
        initComponents();
        lis = new cUsuario();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jpassClave = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 80, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Login");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

        txtUsuario.setBackground(new java.awt.Color(153, 51, 0));
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 80, -1));

        jpassClave.setBackground(new java.awt.Color(153, 51, 0));
        jpassClave.setForeground(new java.awt.Color(255, 255, 255));
        jpassClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpassClaveKeyPressed(evt);
            }
        });
        getContentPane().add(jpassClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 80, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Restaurante.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 230));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jpassClaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpassClaveKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnentrarMouseClicked(null);
        }
    }//GEN-LAST:event_jpassClaveKeyPressed
 private void btnentrarKeyTyped(java.awt.event.KeyEvent evt) {                                   
         //se asegura de presionar la tecla enter y descartar a las demas
        char enter=evt.getKeyChar();
        //da clic en el boton aceptar y luego lo ejecuta
        if(enter==KeyEvent.VK_ENTER)
        {
             Logeo();
        }
    }      
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
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jpassClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    private void btnentrarMouseClicked(Object object) {
         Logeo();
    }

    private void Logeo() {
         char clave[]=jpassClave.getPassword();
        String clavedef=DigestUtils.md5Hex(new String(clave));
        System.out.print("Paso1 Usuario:"+txtUsuario.getText()+"Contrasenia: "+ clavedef);
        if (lis.Login(txtUsuario.getText(),clavedef.trim())== true)
        {
            this.dispose();
                    FrmMenuPrincipal menu = new FrmMenuPrincipal();
                    menu.setVisible(true);
            }else {
                    JOptionPane.showMessageDialog(null, "Acceso denegado:\n"
                    + "Por favor ingrese un usuario y/o contraseña correctos", "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE);
            }
    }
}
