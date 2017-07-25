/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.vistas;


import SoftRest.controladores.cClientes;
import SoftRest.modelos.Clientes;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author henvisi
 */
public class frmClientes extends javax.swing.JFrame {

    /**
     * Creates new form frmClientes
     */
   /* ConectorBD con = new ConectorBD();
    Connection cn = con.conexion();
    */
    String atributo = "cli_ced";
    DefaultTableModel modelo;
    
    cClientes lis;
    //Registro indica la posicion en el conjunto de datos
    int Registro = 0;
    //op = 0 Si se guarda un nuevo registro; op=1 Si se actualiza un registro
    int op = 0;
    
    //Se crea la lista enlazada ListaCli
   // public ListaEnlazadaClientes ListaCli = new ListaEnlazadaClientes();
    public Clientes cli;
    
    public frmClientes() {
        this.setTitle("INSERTAR CLIENTE");
        initComponents();
        setLocationRelativeTo(null);
        setResizable(true);
       /* ListaCli.Cargar();
        ListaCli.Visualizar();*/
        validarNumeros(txtcedula);
        validarLetras(txtNombre);
        validarLetras(txtApellido);
        validarLetras(txtDireccion);
        validarNumeros(txtTelefono);
        cerrar();
    }
     private Clientes leer() {
            Clientes ob=null;
            if(form_validado()){
            ob=new Clientes();
            ob.setCedula(txtcedula.getText());
            ob.setNombre(txtNombre.getText());
            ob.setApellido(txtApellido.getText());	  
            ob.setTelefono(txtTelefono.getText());	  
            ob.setEmail(txtEmail.getText());	  
            ob.setDireccion(txtDireccion.getText());	
           
               
            //obtiene el codigo de la categoria y fabricante seleccionados
                               
            System.out.print(ob.toString());            
        }
        return ob;
    }
     public boolean form_validado() {
        boolean ok = true;
        //validar requerido
        if (!Validaciones.esRequerido(txtcedula)) {
            ok = false;
            lbNum.setText("Nombre categoría es requerido");
        }
        //aquí colocar invocación a otros métodos de validación

        //validar más controles
        return ok;
    }

    //ver registro
    public void ver_registro(int pos) {

    }

    private void disenio_ventana(String nombreImagen) {
        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon uno = new ImageIcon(this.getClass().getResource("/Imagenes/" + nombreImagen));
        JLabel fondo = new JLabel();
        fondo.setIcon(uno);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, uno.getIconWidth(), uno.getIconHeight());
    }
    public void Limpiar(){
        txtcedula.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
    }
    public void Habilitar_texto(boolean b ){
       txtcedula.setEditable(b);
       txtNombre.setEditable(b);
       txtApellido.setEditable(b);
       txtDireccion.setEditable(b);
       txtEmail.setEditable(b);
       txtTelefono.setEditable(b);
    }
    public void Inhabilitar() {
        
        btNuevo.setEnabled(true);
        btGuardar.setEnabled(false);
        btCancelar.setEnabled(false);
        btCerrar.setEnabled(true);
        
        txtcedula.setEditable(false);
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtDireccion.setEditable(false);
        txtEmail.setEditable(false);
        txtTelefono.setEditable(false);
        Limpiar();
    }
    public void Habilitar() {
        
        btNuevo.setEnabled(true);
        btEditar.setEnabled(true);
        btGuardar.setEnabled(true);
        btCancelar.setEnabled(true);
        btBuscar.setEnabled(true);
        btListar.setEnabled(true);
        btEliminar.setEnabled(true);
        btPrimero.setEnabled(true);
        btPrimero.setEnabled(true);
        btSiguiente.setEnabled(true);
        btUltimo.setEnabled(true);
        txtcedula.setEditable(true);
        txtNombre.setEditable(true);
        txtApellido.setEditable(true);
        txtDireccion.setEditable(true);
        txtEmail.setEditable(true);
        txtTelefono.setEditable(true);
        
    }//habilitar/deshabilitar cuadros de texto
    public void cerrar(){
        try{
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    confirmaSalida();
                }
            }); 
        }catch(Exception e){
            
        }
    }
    public void confirmaSalida(){
        int valor= JOptionPane.showConfirmDialog(this, "Esta Seguro De Salir","Advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(valor==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, "Gracias por su visita,Hasta Pronto","Gracias",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    public void validarLetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }
    public void validarNumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
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

        jToolBar1 = new javax.swing.JToolBar();
        btNuevo = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        btListar = new javax.swing.JButton();
        btInicio = new javax.swing.JButton();
        btPrimero = new javax.swing.JButton();
        lbNum = new javax.swing.JLabel();
        btSiguiente = new javax.swing.JButton();
        btUltimo = new javax.swing.JButton();
        btCerrar = new javax.swing.JButton();
        panelCentral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcedula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lbMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(102, 102, 102));
        jToolBar1.setForeground(new java.awt.Color(153, 153, 153));
        jToolBar1.setRollover(true);

        btNuevo.setBackground(new java.awt.Color(153, 153, 153));
        btNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/nuevo.png"))); // NOI18N
        btNuevo.setToolTipText("Nuevo");
        btNuevo.setFocusable(false);
        btNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(btNuevo);

        btEditar.setBackground(new java.awt.Color(153, 153, 153));
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/editar.png"))); // NOI18N
        btEditar.setToolTipText("Editar");
        btEditar.setFocusable(false);
        btEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btEditar);

        btGuardar.setBackground(new java.awt.Color(153, 153, 153));
        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/guardar.png"))); // NOI18N
        btGuardar.setToolTipText("Guardar");
        btGuardar.setFocusable(false);
        btGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btGuardar);

        btBuscar.setBackground(new java.awt.Color(153, 153, 153));
        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/buscar.png"))); // NOI18N
        btBuscar.setToolTipText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(btBuscar);

        btCancelar.setBackground(new java.awt.Color(153, 153, 153));
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/cancelar.png"))); // NOI18N
        btCancelar.setToolTipText("Cancelar");
        btCancelar.setFocusable(false);
        btCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(btCancelar);

        btEliminar.setBackground(new java.awt.Color(153, 153, 153));
        btEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/eliminar.png"))); // NOI18N
        btEliminar.setToolTipText("Eliminar");
        btEliminar.setFocusable(false);
        btEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btEliminar);

        btListar.setBackground(new java.awt.Color(153, 153, 153));
        btListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/listar.png"))); // NOI18N
        btListar.setToolTipText("Listar");
        btListar.setFocusable(false);
        btListar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btListar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListarActionPerformed(evt);
            }
        });
        jToolBar1.add(btListar);

        btInicio.setBackground(new java.awt.Color(153, 153, 153));
        btInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/inicio.png"))); // NOI18N
        btInicio.setToolTipText("Inicio");
        btInicio.setFocusable(false);
        btInicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btInicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInicioActionPerformed(evt);
            }
        });
        jToolBar1.add(btInicio);

        btPrimero.setBackground(new java.awt.Color(153, 153, 153));
        btPrimero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/anterior.png"))); // NOI18N
        btPrimero.setToolTipText("Anterior");
        btPrimero.setFocusable(false);
        btPrimero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPrimero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrimeroActionPerformed(evt);
            }
        });
        jToolBar1.add(btPrimero);

        lbNum.setText("Mensaje");
        jToolBar1.add(lbNum);

        btSiguiente.setBackground(new java.awt.Color(153, 153, 153));
        btSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/siguiente.png"))); // NOI18N
        btSiguiente.setToolTipText("Siguiente");
        btSiguiente.setFocusable(false);
        btSiguiente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSiguiente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSiguienteActionPerformed(evt);
            }
        });
        jToolBar1.add(btSiguiente);

        btUltimo.setBackground(new java.awt.Color(153, 153, 153));
        btUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/fin.png"))); // NOI18N
        btUltimo.setToolTipText("Último");
        btUltimo.setFocusable(false);
        btUltimo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btUltimo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUltimoActionPerformed(evt);
            }
        });
        jToolBar1.add(btUltimo);

        btCerrar.setBackground(new java.awt.Color(153, 153, 153));
        btCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/cerrar.png"))); // NOI18N
        btCerrar.setFocusable(false);
        btCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCerrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btCerrar);

        panelCentral.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1.setText("C.i.");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setText("Apellido");

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel7.setText("Direccion");

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel6.setText("email");

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel8.setText("telefono");

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(panelCentralLayout.createSequentialGroup()
                        .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelCentralLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(50, 50, 50))
                                .addGroup(panelCentralLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDireccion)
                            .addComponent(txtApellido)
                            .addGroup(panelCentralLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                    .addComponent(txtcedula))))))
                .addGap(591, 591, 591))
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentralLayout.createSequentialGroup()
                .addComponent(lbMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        String msg="";
        int pos=0;
        //lee datos del formulario y valida
        Clientes ob=leer();
        if(ob==null) return; //Si no se ha validado,finaliza el método         
        try{            
            //verifica si ya se ha ingresado un producto
            cClientes l=lis.buscar_ruc_bd(txtcedula.getText());
            int n=l.Count();
            if(n>=1 && op==0){
                lbNum.setText("Ruc de Empleado ya ingresado");                
                return; //finaliza método
            }           
            if(op==0){  //guardar un nuevo objeto - insert en base de datos              
                txtcedula.setText(""+lis.insertar(ob));            
                msg="Registro guardado exitosamente";  
            }
            else{ //guarda un objeto modificado; update en base dedatos                
                lis.actualizar(ob);                
                msg="Registro actualizado exitosamente";                
            }           
        }catch(Exception ex){lbNum.setText(ex.getMessage());}
        //mover_tabla(pos);
        lbNum.setText(msg);
        //Limpiar();
        Habilitar();
    }//GEN-LAST:event_btNuevoActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
       //habilitar textos
        Habilitar();
        //enviar curso al campo nombre
        txtcedula.requestFocus();
        //desabilitar botones
        op = 1;
    }//GEN-LAST:event_btEditarActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        String msg = "";
        int pos = 0;
        //lee datos del formulario y valida
//        Categoria cat=leer();
        //if(cat==null) return; //Si no se ha validado,finaliza el método

        try {
            //verifica si ya se ha ingresado categoría
            //   pos=lis.buscar_nombre(txtNombre.getText());
            if (pos >= 0) {
                if (op == 0) {
                    lbMensaje.setText("Nombre de Cliente ya ingresado");
                } else if (pos > 0 && op != 0) {
                    lbMensaje.setText("Nombre categoría ya ingresada");
                } else {
                    lbMensaje.setText("No hay cambios que guardar");
                }
                return; //finaliza método
            }
            if (op == 0) {  //guardar un nuevo objeto - insert en base de datos
                //       lis.insertar(cat);
                msg = "Registro guardado exitosamente";
            } else { //guarda un objeto modificado; update en base de datos
                //      lis.actualizar(cat);
                msg = "Registro actualizado exitosamente";
            }
            //    lis.consultaAll();
            Registro = op == 0 ? lis.Count() - 1 : Registro;
        } catch (Exception ex) {
            lbMensaje.setText(ex.getMessage());
        }
        Habilitar();
        lbMensaje.setText(msg);
    }//GEN-LAST:event_btGuardarActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
       
        // TODO add your handling code here:
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        Inhabilitar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btEliminarActionPerformed

    private void btListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListarActionPerformed
        frmClientesLista clilis=new frmClientesLista();
        clilis.setVisible(true);       
    }//GEN-LAST:event_btListarActionPerformed

    private void btInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInicioActionPerformed
        Registro = 0;
        ver_registro(Registro);
    }//GEN-LAST:event_btInicioActionPerformed

    private void btPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrimeroActionPerformed
        Registro--;
        Registro = Registro >= 0 && Registro < lis.Count() ? Registro : 0;
        ver_registro(Registro);
    }//GEN-LAST:event_btPrimeroActionPerformed

    private void btSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSiguienteActionPerformed
       Registro++;
        Registro = Registro >= 0 && Registro < lis.Count() ? Registro : lis.Count() - 1;
        ver_registro(Registro);
    }//GEN-LAST:event_btSiguienteActionPerformed

    private void btUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUltimoActionPerformed
       Registro = lis.Count() - 1;
        ver_registro(Registro);
    }//GEN-LAST:event_btUltimoActionPerformed

    private void btCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCerrarActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btCerrarActionPerformed

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
            java.util.logging.Logger.getLogger(frmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btCerrar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btInicio;
    private javax.swing.JButton btListar;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btPrimero;
    private javax.swing.JButton btSiguiente;
    private javax.swing.JButton btUltimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbMensaje;
    private javax.swing.JLabel lbNum;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtcedula;
    // End of variables declaration//GEN-END:variables
}
