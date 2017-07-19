package SoftRest.vistas;

import SoftRest.controladores.ConectorBD;
import SoftRest.controladores.ListaEnlazadaClientes;
import SoftRest.controladores.Cliente;
import SoftRest.controladores.ListaEnlazadaProveedores;
import SoftRest.controladores.Proveedores;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Paul Torres
 */
public class frmProveedoresLista extends javax.swing.JFrame {

    ConectorBD con = new ConectorBD();
    Connection cn = con.conexion();

    String atributo = "prov_ruc";
    DefaultTableModel modelo;

    //Se crea la lista enlazada ListaCli
    public ListaEnlazadaProveedores ListaPro = new ListaEnlazadaProveedores();
    public Proveedores prov;

    public frmProveedoresLista() {
        this.setTitle("LISTA DE PROVEEDORES");
        initComponents();
        setLocationRelativeTo(null);
        setResizable(true);

        MostrarTabla("");
        buttonGroup1.add(buscarCedula);
        buttonGroup1.add(buscarNombre);
        buscarCedula.doClick();
        ListaPro.Cargar();
        ListaPro.Visualizar();
    }

    public void MostrarTabla(String valor) {

        DefaultTableModel modelo = new DefaultTableModel();

        //nombre de los parametros del la tabla modelo
        modelo.addColumn("RUC");
        modelo.addColumn("Nombre");
        modelo.addColumn("direccion");
        modelo.addColumn("Email");
        modelo.addColumn("Telefono");
        tabla.setModel(modelo);

        String sql;
        if (valor.equals("")) {
            sql = "SELECT * FROM proveedores";
        } else {
            sql = "SELECT * FROM proveedores WHERE " + atributo + "='" + valor + "'";
        }

        String datos[] = new String[5];
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //Se asigna los datos de la base de datos en la tabla modelo 
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            tabla.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Inhabilitar() {
        btnEditar.setEnabled(true);
        btnEliminarUno.setEnabled(false);
        btnCancelarEliminar.setEnabled(false);
    }

    public void Habilitar() {
        btnEditar.setEnabled(true);
        btnEliminarUno.setEnabled(true);
        btnCancelarEliminar.setEnabled(true);
        btnRegresar.setEnabled(true);
    }

    public boolean ValidarCedula(String cedula) {
        boolean valido;
        int[] digito = new int[10];
        int sumaDeDigitos = 0;
        int decimoDigito = 0;
        int a = 1000000000;
        int b = 2;
        for (int i = 0; i <= digito.length - 1; i++) {
            if (i == 0) {
                digito[i] = (Integer.parseInt(cedula) / a) * b;
            } else {
                digito[i] = ((Integer.parseInt(cedula) / a) % 10) * b;
            }

            if (digito[i] > 9) {
                digito[i] = digito[i] - 9;
            }
            a = a / 10;
            if (i == 9) {
                decimoDigito = digito[i];
            } else {
                sumaDeDigitos = digito[i] + sumaDeDigitos;
            }
            if (b == 2)//Posicion Impar
            {
                b = b - 1;
            } else//posicion Par
            {
                b = b + 1;
            }

        }
        if (sumaDeDigitos <= 10) {
            sumaDeDigitos = 10 - sumaDeDigitos;
        }
        if (sumaDeDigitos > 10 && sumaDeDigitos <= 20) {
            sumaDeDigitos = 20 - sumaDeDigitos;
        }
        if (sumaDeDigitos > 20 && sumaDeDigitos <= 30) {
            sumaDeDigitos = 30 - sumaDeDigitos;
        }
        if (sumaDeDigitos > 30 && sumaDeDigitos <= 40) {
            sumaDeDigitos = 40 - sumaDeDigitos;
        }
        if (sumaDeDigitos > 40 && sumaDeDigitos <= 50) {
            sumaDeDigitos = 50 - sumaDeDigitos;
        }
        if (sumaDeDigitos > 50 && sumaDeDigitos <= 60) {
            sumaDeDigitos = 60 - sumaDeDigitos;
        }
        if (sumaDeDigitos > 60 && sumaDeDigitos <= 70) {
            sumaDeDigitos = 70 - sumaDeDigitos;
        }
        if (decimoDigito == sumaDeDigitos) {
            valido = true;
        } else {
            valido = false;
        }
        return valido;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VentanaActualizar = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        txtNombre1 = new javax.swing.JTextField();
        txtApellido1 = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDireccion1 = new javax.swing.JTextField();
        txtCedula1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnActualizar1 = new javax.swing.JButton();
        btnCancelarEditar1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        buscarApellido = new javax.swing.JRadioButton();
        buscarNombre = new javax.swing.JRadioButton();
        buscarCedula = new javax.swing.JRadioButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnEliminarUno = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelarEliminar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnMostrarTabla = new javax.swing.JButton();

        VentanaActualizar.setSize(new java.awt.Dimension(684, 330));
        VentanaActualizar.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "ACTUALIZAR CLIENTE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Candara", 1, 24))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre1.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });
        txtNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre1KeyTyped(evt);
            }
        });
        jPanel9.add(txtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 61, 196, -1));

        txtApellido1.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        txtApellido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellido1ActionPerformed(evt);
            }
        });
        txtApellido1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellido1KeyTyped(evt);
            }
        });
        jPanel9.add(txtApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 96, 196, -1));

        txtEmail1.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        txtEmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmail1ActionPerformed(evt);
            }
        });
        txtEmail1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmail1KeyTyped(evt);
            }
        });
        jPanel9.add(txtEmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 173, 195, -1));

        txtTelefono1.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        txtTelefono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefono1ActionPerformed(evt);
            }
        });
        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyTyped(evt);
            }
        });
        jPanel9.add(txtTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 213, 110, -1));

        jLabel10.setFont(new java.awt.Font("Candara", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Nombres:");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 64, 90, -1));

        jLabel11.setFont(new java.awt.Font("Candara", 1, 13)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Apellidos:");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 99, 90, -1));

        jLabel12.setFont(new java.awt.Font("Candara", 1, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Cedula:");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 35, 57, -1));

        jLabel13.setFont(new java.awt.Font("Candara", 1, 13)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Email");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 176, 43, -1));

        jLabel14.setFont(new java.awt.Font("Candara", 1, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Telefono:");
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 216, 90, -1));

        txtDireccion1.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        txtDireccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccion1ActionPerformed(evt);
            }
        });
        txtDireccion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccion1KeyTyped(evt);
            }
        });
        jPanel9.add(txtDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 136, 291, -1));

        txtCedula1.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        txtCedula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedula1ActionPerformed(evt);
            }
        });
        txtCedula1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedula1KeyTyped(evt);
            }
        });
        jPanel9.add(txtCedula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 32, 140, -1));

        jLabel7.setFont(new java.awt.Font("Candara", 1, 13)); // NOI18N
        jLabel7.setText("Dirección:");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 139, -1, -1));

        btnActualizar1.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        btnActualizar1.setText("Actualizar");
        btnActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar1ActionPerformed(evt);
            }
        });
        jPanel9.add(btnActualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 144, -1));

        btnCancelarEditar1.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        btnCancelarEditar1.setText("Cancelar");
        btnCancelarEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEditar1ActionPerformed(evt);
            }
        });
        jPanel9.add(btnCancelarEditar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 122, -1));

        VentanaActualizar.getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 640, 252));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(438, 45));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtBuscarAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtBuscar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txtBuscarMouseMoved(evt);
            }
        });
        txtBuscar.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                txtBuscarAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        txtBuscar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtBuscarInputMethodTextChanged(evt);
            }
        });
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel5.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 15, 283, -1));

        buscarApellido.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        buscarApellido.setText("Apellido");
        buscarApellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarApellidoMouseClicked(evt);
            }
        });
        jPanel5.add(buscarApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 16, -1, -1));

        buscarNombre.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        buscarNombre.setText("Nombre");
        buscarNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarNombreMouseClicked(evt);
            }
        });
        jPanel5.add(buscarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 16, -1, -1));

        buscarCedula.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        buscarCedula.setText("Cedula");
        buscarCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarCedulaMouseClicked(evt);
            }
        });
        buscarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarCedulaActionPerformed(evt);
            }
        });
        jPanel5.add(buscarCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 16, -1, -1));

        btnBuscar.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 136, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 666, 60));

        tabla.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 670, 170));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setMinimumSize(new java.awt.Dimension(438, 45));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminarUno.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        btnEliminarUno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/eliminar.png"))); // NOI18N
        btnEliminarUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUnoActionPerformed(evt);
            }
        });
        jPanel7.add(btnEliminarUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        btnEditar.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/editar.png"))); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel7.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 110, -1));

        btnCancelarEliminar.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        btnCancelarEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/cancelar.png"))); // NOI18N
        btnCancelarEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEliminarActionPerformed(evt);
            }
        });
        jPanel7.add(btnCancelarEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 100, -1));

        btnRegresar.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/cerrar.png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel7.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 100, -1));

        btnMostrarTabla.setFont(new java.awt.Font("Candara", 0, 13)); // NOI18N
        btnMostrarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/listar.png"))); // NOI18N
        btnMostrarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTablaActionPerformed(evt);
            }
        });
        jPanel7.add(btnMostrarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 110, -1));

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 670, 60));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyTyped
        char c = evt.getKeyChar();

        if (txtNombre1.getText().length() >= 30) {
            evt.consume();
        }
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtNombre1KeyTyped

    private void txtApellido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellido1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellido1ActionPerformed

    private void txtApellido1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellido1KeyTyped
        char c = evt.getKeyChar();
        if (txtApellido1.getText().length() >= 30) {
            evt.consume();
        }
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtApellido1KeyTyped

    private void txtEmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmail1ActionPerformed

    }//GEN-LAST:event_txtEmail1ActionPerformed

    private void txtEmail1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmail1KeyTyped
        char c = evt.getKeyChar();
        if (txtEmail1.getText().length() >= 35) {
            evt.consume();
        }

    }//GEN-LAST:event_txtEmail1KeyTyped

    private void txtTelefono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefono1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono1ActionPerformed

    private void txtTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyTyped
        char c = evt.getKeyChar();
        if (txtTelefono1.getText().length() >= 9) {
            evt.consume();
        }
        if (!Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefono1KeyTyped

    private void txtDireccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccion1ActionPerformed

    private void txtDireccion1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccion1KeyTyped
        char c = evt.getKeyChar();
        if (txtDireccion1.getText().length() >= 40) {
            evt.consume();
        }
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

    }//GEN-LAST:event_txtDireccion1KeyTyped

    private void txtCedula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedula1ActionPerformed

    private void txtCedula1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedula1KeyTyped
        char car = evt.getKeyChar();

        if (txtCedula1.getText().length() == 10) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCedula1KeyTyped

    private void btnActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
        String nombre, ruc, direccion, email, telefono, comparar;
        nombre = txtNombre1.getText();
        ruc = txtCedula1.getText();
        direccion = txtDireccion1.getText();
        email = txtEmail1.getText();
        telefono = txtTelefono1.getText();

        nombre = nombre.replaceAll(" ", "");
        ruc = ruc.replaceAll(" ", "");
        direccion = direccion.replaceAll(" ", "");
        email = email.replaceAll(" ", "");
        telefono = telefono.replaceAll(" ", "");

        //Verifica el los campos estan vacios
        if (nombre.length() == 0 || ruc.length() == 0 || email.length() == 0 || telefono.length() == 0 || direccion.length() == 0) {
            JOptionPane.showMessageDialog(null, "POR FAVOR NO DEJE CAMPOS VACIOS");
        } else {
            if (ValidarCedula(ruc)) {
                //Compara si la cedula del CLIENTE ingresado se repite
                try {
                    PreparedStatement pps = cn.prepareStatement("UPDATE proveedores SET prov_ruc='" + txtCedula1.getText()
                            + "',prov_nom='" + txtNombre1.getText() + "',prov_dir='" + txtDireccion1.getText() + "',prov_ema='" + txtEmail1.getText()
                            + "',prov_tel='" + txtTelefono1.getText() + "' WHERE prov_ruc='" + txtCedula1.getText() + "'");
                    pps.executeUpdate();
                    MostrarTabla("");
                    VentanaActualizar.setVisible(false);
                    ListaPro.VaciarLista();
                    ListaPro.Cargar();
                    ListaPro.Visualizar();
                    JOptionPane.showMessageDialog(null, "PROVEEDOR ACTUALIZADO");

                } catch (SQLException ex) {
                    Logger.getLogger(frmProveedores.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "NUMERO DE RUC INVALIDO");
            }
        }
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void btnCancelarEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEditar1ActionPerformed
        VentanaActualizar.hide();
    }//GEN-LAST:event_btnCancelarEditar1ActionPerformed

    private void txtBuscarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtBuscarAncestorAdded

    }//GEN-LAST:event_txtBuscarAncestorAdded

    private void txtBuscarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMouseMoved

    private void txtBuscarAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txtBuscarAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarAncestorMoved

    private void txtBuscarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtBuscarInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarInputMethodTextChanged

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased

    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void buscarApellidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarApellidoMouseClicked
        txtBuscar.requestFocus();
    }//GEN-LAST:event_buscarApellidoMouseClicked

    private void buscarNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarNombreMouseClicked
        txtBuscar.requestFocus();
    }//GEN-LAST:event_buscarNombreMouseClicked

    private void buscarCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarCedulaMouseClicked
        txtBuscar.requestFocus();
    }//GEN-LAST:event_buscarCedulaMouseClicked

    private void buscarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarCedulaActionPerformed

    }//GEN-LAST:event_buscarCedulaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (buscarCedula.isSelected() || buscarNombre.isSelected() || buscarApellido.isSelected()) {
            if (buscarCedula.isSelected()) {
                atributo = "prov_ced";
                MostrarTabla(txtBuscar.getText());
            } else if (buscarNombre.isSelected()) {
                atributo = "prov_nom";
                MostrarTabla(txtBuscar.getText());
            }
            btnRegresar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe primero escoger como desea buscarlo");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

    }//GEN-LAST:event_tablaMouseClicked

    private void btnEliminarUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUnoActionPerformed
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            int resp = JOptionPane.showConfirmDialog(null, "¿ESTAS SEGURO ELIMINAR ESTE PROVEEDOR?", "ELIMINAR", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                try {
                    String valor = tabla.getValueAt(fila, 0).toString();
                    PreparedStatement pps = cn.prepareStatement("DELETE FROM clientes WHERE prov_cruc = '" + valor + "'");
                    pps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "PROVEEDOR ELIMINADO");
                    MostrarTabla("");
                    ListaPro.VaciarLista();
                    ListaPro.Cargar();
                    ListaPro.Visualizar();
                } catch (SQLException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                tabla.clearSelection();
                Inhabilitar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "FILA NO SELECCIONADA");
        }
    }//GEN-LAST:event_btnEliminarUnoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            VentanaActualizar.setVisible(true);
            VentanaActualizar.setLocationRelativeTo(null);
            VentanaActualizar.setResizable(false);

            txtCedula1.setText(tabla.getValueAt(fila, 0).toString());
            txtNombre1.setText(tabla.getValueAt(fila, 1).toString());
            txtDireccion1.setText(tabla.getValueAt(fila, 3).toString());
            txtEmail1.setText(tabla.getValueAt(fila, 4).toString());
            txtTelefono1.setText(tabla.getValueAt(fila, 5).toString());
        } else {
            JOptionPane.showMessageDialog(null, "FILA NO SELECCIONADA");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEliminarActionPerformed
        Inhabilitar();
    }//GEN-LAST:event_btnCancelarEliminarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();

    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnMostrarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTablaActionPerformed
        MostrarTabla("");
        txtBuscar.setText("");
        btnMostrarTabla.setEnabled(false);
    }//GEN-LAST:event_btnMostrarTablaActionPerformed

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
            java.util.logging.Logger.getLogger(frmProveedoresLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProveedoresLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProveedoresLista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame VentanaActualizar;
    private javax.swing.JButton btnActualizar1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelarEditar1;
    private javax.swing.JButton btnCancelarEliminar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminarUno;
    private javax.swing.JButton btnMostrarTabla;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JRadioButton buscarApellido;
    private javax.swing.JRadioButton buscarCedula;
    private javax.swing.JRadioButton buscarNombre;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula1;
    private javax.swing.JTextField txtDireccion1;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtTelefono1;
    // End of variables declaration//GEN-END:variables
}
