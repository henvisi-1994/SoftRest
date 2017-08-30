/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftRest.vistas;

import SoftRest.controladores.cTipoPlato;
import SoftRest.modelos.TipoPlato;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JViewport;


/**
 *
 * @author Berty
 */
public class dlgTipoPlato extends javax.swing.JDialog {

    //datos
    cTipoPlato lis;
    //Registro indica la posicion en el conjunto de datos
    int Registro=0;
    //op = 0 Si se guarda un nuevo registro; op=1 Si se actualiza un registro
    int op=0;
    
    /**
     * Creates new form dlgTipoPlato
     */
    public dlgTipoPlato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //inicia en el primer registro
        Registro=0;
        int op=0;
        lis=new cTipoPlato();
	//op= 0 Si se guarda un nuevo registro; op=1 Si se actualiza un registro
	try{           
            lis.consultaAll();                    
        }catch(Exception ex){lbMensaje.setText(ex.getMessage());}
        //000000000000tabla.setModel(lis.getTablaDatos());   
        habilitar(true);        
        this.setLocationRelativeTo(null); 
        //invocar al método de formato de tabla
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
        btCancelar = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btInicio = new javax.swing.JButton();
        btAtras = new javax.swing.JButton();
        lbRegistro = new javax.swing.JLabel();
        btSiguiente = new javax.swing.JButton();
        btUltimo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbMensaje = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtdato = new javax.swing.JTextField();
        btbuscar_varios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Tipos de Platos");

        jToolBar1.setRollover(true);

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

        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/buscar.png"))); // NOI18N
        btBuscar.setToolTipText("Buscar");
        btBuscar.setFocusable(false);
        btBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(btBuscar);

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
        jToolBar1.add(jSeparator1);

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

        btAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SoftRest/Imagenes2/anterior.png"))); // NOI18N
        btAtras.setToolTipText("Atrás");
        btAtras.setFocusable(false);
        btAtras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAtras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtrasActionPerformed(evt);
            }
        });
        jToolBar1.add(btAtras);
        jToolBar1.add(lbRegistro);

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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Código:");

        jLabel2.setText("Nombre Categoría:");

        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Datos de Tipos de Platos");
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        lbMensaje.setText("  ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMensaje)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Dato a buscar:");

        txtdato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdatoKeyReleased(evt);
            }
        });

        btbuscar_varios.setText("Buscar");
        btbuscar_varios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbuscar_variosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btbuscar_varios)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbuscar_varios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        habilitar(true);
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        String msg="";
        int pos=0;
        //lee datos del formulario y valida
        TipoPlato cat=leer();
        if(cat==null) return; //Si no se ha validado,finaliza el método
         
        try{            
            //verifica si ya se ha ingresado categoría
            pos=lis.buscar_nombre(txtNombre.getText());
            if(pos>=0)
            {
                if(op==0)lbMensaje.setText("Nombre categoría ya ingresada");
                else if(pos>0 && op!=0) lbMensaje.setText("Nombre categoría ya ingresada");
                else lbMensaje.setText("No hay cambios que guardar");
                return; //finaliza método
            }
            if(op==0){  //guardar un nuevo objeto - insert en base de datos              
                lis.insertar(cat);            
                msg="Registro guardado exitosamente";  
            }
            else{ //guarda un objeto modificado; update en base de datos                
                lis.actualizar(cat);                
                msg="Registro actualizado exitosamente";                
            }    
            lis.consultaAll();
            Registro=op==0?lis.Count()-1:Registro;                           
        }catch(Exception ex){lbMensaje.setText(ex.getMessage());}
        habilitar(true);
        lbMensaje.setText(msg);        
    }//GEN-LAST:event_btGuardarActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        //limpiar textos
        limpiar_textos();
        //habilitar textos
        habilitar_textos(true);
        long pos=1;
        if(lis.Count()>=0)
            pos=Integer.parseInt(lis.get_Codigo(lis.Count()-1))+1;
        txtCodigo.setText(pos+"");
        txtNombre.requestFocus();
        //desabilitar botones
        habilitar_botones(false);
        op=0;

    }//GEN-LAST:event_btNuevoActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        //habilitar textos
        habilitar_textos(true);
        //enviar curso al campo nombre
        txtNombre.requestFocus();
        //desabilitar botones
        habilitar_botones(false);
        op=1;        
    }//GEN-LAST:event_btEditarActionPerformed

    public void mover_tabla(int pos)
    {
        tabla.setRowSelectionInterval(pos, pos);
        JViewport viewport = (JViewport)tabla.getParent();
        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = tabla.getCellRect(pos, 0, true);
        // The location of the viewport relative to the table
        Point pt = viewport.getViewPosition();
        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0)
        rect.setLocation(rect.x-pt.x, rect.y-pt.y);
        tabla.scrollRectToVisible(rect);
    }
    
    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        try{
            String cod=JOptionPane.showInputDialog("Ingrese el código:");
            int pos=lis.buscar_codigo(cod);
            if (pos>-1)
            {
                Registro=pos>=0?pos:Registro;
                ver_registro(Registro); 
            }
            else lbMensaje.setText("Registro no encontrado");
        }catch(Exception ex){lbMensaje.setText(ex.getMessage());}
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btbuscar_variosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbuscar_variosActionPerformed
        // TODO add your handling code here:
        try{
            cTipoPlato cat=lis.buscar_varios(txtdato.getText());
            tabla.setModel(cat.getTablaDatos());
        }catch(Exception ex){lbMensaje.setText(ex.getMessage());}
    }//GEN-LAST:event_btbuscar_variosActionPerformed

    private void btInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInicioActionPerformed
        Registro=0;
        ver_registro(Registro);
    }//GEN-LAST:event_btInicioActionPerformed

    private void btAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtrasActionPerformed
        Registro--;
        Registro=Registro>=0 && Registro <lis.Count()?Registro:0;
        ver_registro(Registro);
    }//GEN-LAST:event_btAtrasActionPerformed

    private void btSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSiguienteActionPerformed
        Registro++;
        Registro=Registro>=0 && Registro <lis.Count()?Registro:lis.Count()-1;
        ver_registro(Registro);
    }//GEN-LAST:event_btSiguienteActionPerformed

    private void btUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUltimoActionPerformed
        Registro=lis.Count()-1;
        ver_registro(Registro);
    }//GEN-LAST:event_btUltimoActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
       int row= tabla.getSelectedRow();
       Registro=row>=0 && row <lis.Count()?row:Registro;
       ver_registro(Registro);
    }//GEN-LAST:event_tablaMouseClicked

    private void txtdatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdatoKeyReleased
        cTipoPlato cat=lis.buscar_varios(txtdato.getText());
        tabla.setModel(cat.getTablaDatos());
    }//GEN-LAST:event_txtdatoKeyReleased

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
       // if evt.getKeyChar()
       int row= tabla.getSelectedRow();
       Registro=row>=0 && row <lis.Count()?row:Registro;
       ver_registro(Registro);
    }//GEN-LAST:event_tablaKeyReleased

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        try{           
            if(JOptionPane.showConfirmDialog(null, 
            "Esta seguro de eliminar el registro con ID="+txtCodigo.getText()+
            " ?")==JOptionPane.YES_OPTION)
            {
               lis.eliminar(Integer.parseInt(txtCodigo.getText()));
               System.out.println("Aqui  1");
               lis.consultaAll();
               //tabla.setModel(lis.getTablaDatos());
               Registro=Registro>=lis.Count()?lis.Count()-1:Registro;
               System.out.println("Aqui  2");
               ver_registro(Registro);
               System.out.println("Aqui  3");
               lbMensaje.setText("Registro eliminado exitosamente");
            }            
        }catch(Exception ex){lbMensaje.setText(ex.getMessage());}
    }//GEN-LAST:event_btEliminarActionPerformed

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        Validaciones.esRequerido(txtNombre);
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if(Validaciones.esRequerido(txtNombre)) Validaciones.pinta_text(txtNombre);
    }//GEN-LAST:event_txtNombreKeyReleased
    
    
    

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
            java.util.logging.Logger.getLogger(dlgTipoPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgTipoPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgTipoPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgTipoPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgTipoPlato dialog = new dlgTipoPlato(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    //metodos personalizados
    //desahabilita textos, habilita botones y muestra datos en el formulario
    public void habilitar(Boolean ok)
    {
        //deshabilitar textos
        habilitar_textos(!ok); 
        //habilitar botones
        habilitar_botones(ok);
        //visualizar el registro actual
        ver_registro(Registro);
    }
    
    
    //metodo para limpiar cajas de texto
    public void limpiar_textos()
    {
        txtCodigo.setText("");
        txtNombre.setText("");		
    }

    //metodo para habilitar o desabilitar cajas de texto
    public void habilitar_textos(Boolean ok)
    {
        txtCodigo.setEditable(false);
        txtNombre.setEditable(ok);	
        //txtNombre.setEnabled(ok);
    }

    //metodo para habilitar o desabilitar botones
    public void habilitar_botones(Boolean ok)
    {
        btNuevo.setEnabled(ok);
        btEditar.setEnabled(ok);
        btBuscar.setEnabled(ok);
        btEliminar.setEnabled(ok);
        btInicio.setEnabled(ok);
        btAtras.setEnabled(ok);
        btSiguiente.setEnabled(ok);
        btUltimo.setEnabled(ok);    
        //hacen lo contrario de los otros botones
        btGuardar.setEnabled(!ok);
        btCancelar.setEnabled(!ok);
    }

    //metodo para crear un objeto
    public TipoPlato leer()
    {
        TipoPlato ob=null;
        if(form_validado()){
            ob=new TipoPlato();
            ob.setTipoplato_id(Integer.parseInt(txtCodigo.getText()));
            ob.setTipoplato_nombre(txtNombre.getText().trim());	
            System.out.print(ob.toString());            
        }
        return ob;
    }

    //validación de formulario
    
    public boolean form_validado(){
        boolean ok=true;
        //validar requerido
        if(!Validaciones.esRequerido(txtNombre)){
            ok=false;
            lbMensaje.setText("Nombre categoría es requerido");
        }
        //aquí colocar invocación a otros métodos de validación
        
        
        //validar más controles
        return ok;
    }
    //ver registro
    public void ver_registro(int pos)
    {
        if(pos>=0 && pos<lis.Count())
        {            
            tabla.setModel(lis.getTablaDatos());
            mover_tabla(pos); 
            txtCodigo.setText(lis.get_Codigo(pos));
            txtNombre.setText(lis.get_Nombre(pos));
            int reg=Registro+1;
            lbRegistro.setText(" Reg. Nº: "+reg+" de "+lis.Count()+ " ");
            lbMensaje.setText("");  
            Validaciones.pinta_text(txtNombre);
        }
        txtdato.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtras;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btInicio;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btSiguiente;
    private javax.swing.JButton btUltimo;
    private javax.swing.JButton btbuscar_varios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbMensaje;
    private javax.swing.JLabel lbRegistro;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtdato;
    // End of variables declaration//GEN-END:variables
}
