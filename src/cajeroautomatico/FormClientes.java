/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico;

import cajeroautomatico.adapter.ClienteAdapter;
import cajeroautomatico.constantes.Constantes;
import cajeroautomatico.entities.Cliente;
import cajeroautomatico.http.EdicionDatosClienteTask;
import cajeroautomatico.http.RegistroClienteTask;
import cajeroautomatico.http.ReporteClienteTask;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sun.net.www.http.HttpClient;

import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author Kenny
 */
public class FormClientes extends javax.swing.JFrame {
    
    private boolean ISEDITMODE = false;
    private Cliente clienteSeleccionado = new Cliente();
    
    /**
     * Creates new form FormClientes
     */
    public FormClientes() {
        initComponents();
         
        recargarLista();
        lvCLientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                try{
                    if (!arg0.getValueIsAdjusting()) { 
                        ClienteAdapter clienteAdapter = ((ClienteAdapter)lvCLientes.getModel());
                        Cliente cliente = clienteAdapter.get(lvCLientes.getSelectedIndex());
                        etNombreUsuario.setText(cliente.getNombre());
                        etNumeroTarjeta.setText(cliente.getNumeroTarjeta());
                        etDireccion.setText(cliente.getDireccion());
                        etTelefono.setText(cliente.getTelefono());
                        etClaveTarjeta.setText(cliente.getClaveTarjeta());
                        etSaldoActual.setText(String.valueOf(cliente.getSaldo()));
                        System.out.println("cliente ; " + cliente.getNombre());
                        enableViews(false);
                        ISEDITMODE = false;
                        tvInfoAccion.setText("*Info : Usuario Seleccionado..");
                        clienteSeleccionado = cliente;
                    }
                }
                catch(Exception e){
                    System.err.println(e.getMessage());
                } 
            }
        });
         
        
    }
      
     
    private void recargarLista(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                new ReporteClienteTask(new ReporteClienteTask.OnResult() {
                    @Override
                    public void onResult(int codResultado, String message, ArrayList<Cliente> alCliente) {
                        if(codResultado == Constantes.RESULT_ERROR)
                        {
                            JOptionPane.showMessageDialog(null, message);
                            return;
                        }

                        ClienteAdapter clienteAdapter = new ClienteAdapter();
                        clienteAdapter.addList(alCliente); 
                        lvCLientes.setModel(clienteAdapter);
                        System.out.println("lista cargada = " + alCliente.size() + " elementos");
                    }
                }).execute(); 
            }
        }).start();
         
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lvCLientes = new javax.swing.JList<>();
        etNombreUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        etNumeroTarjeta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        etDireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        etTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnAgregarUsuario = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        etClaveTarjeta = new javax.swing.JPasswordField();
        etSaldoActual = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        tvInfoAccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Clientes Registrados");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 10, 140, 16);

        lvCLientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lvCLientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lvCLientes);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 40, 380, 280);

        etNombreUsuario.setEditable(false);
        etNombreUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(etNombreUsuario);
        etNombreUsuario.setBounds(410, 60, 260, 40);

        jLabel2.setText("Nombre Cliente");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(410, 20, 110, 40);

        etNumeroTarjeta.setEditable(false);
        etNumeroTarjeta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etNumeroTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etNumeroTarjetaKeyTyped(evt);
            }
        });
        jPanel1.add(etNumeroTarjeta);
        etNumeroTarjeta.setBounds(410, 130, 260, 40);

        jLabel3.setText("Número Tarjeta (16 dígitos máx)");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(410, 90, 200, 40);

        etDireccion.setEditable(false);
        etDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etDireccionActionPerformed(evt);
            }
        });
        jPanel1.add(etDireccion);
        etDireccion.setBounds(410, 200, 260, 40);

        jLabel4.setText("Dirección");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(410, 160, 110, 40);

        etTelefono.setEditable(false);
        etTelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etTelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(etTelefono);
        etTelefono.setBounds(410, 280, 170, 40);

        jLabel5.setText("Teléfono ( 9 dígitos)");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(410, 240, 160, 40);

        btnEditar.setText("Editar");
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(190, 330, 150, 50);

        btnAgregarUsuario.setText("Agregar Cliente");
        btnAgregarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarUsuarioMouseClicked(evt);
            }
        });
        jPanel1.add(btnAgregarUsuario);
        btnAgregarUsuario.setBounds(20, 330, 150, 50);

        btnSeleccionar.setText("Seleccionar Usuario");
        btnSeleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseClicked(evt);
            }
        });
        jPanel1.add(btnSeleccionar);
        btnSeleccionar.setBounds(20, 475, 160, 50);

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(410, 435, 130, 40);

        jLabel6.setText("Saldo Actual");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(570, 320, 130, 40);

        etClaveTarjeta.setEditable(false);
        etClaveTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etClaveTarjetaKeyTyped(evt);
            }
        });
        jPanel1.add(etClaveTarjeta);
        etClaveTarjeta.setBounds(410, 360, 120, 40);

        etSaldoActual.setEditable(false);
        etSaldoActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etSaldoActualKeyTyped(evt);
            }
        });
        jPanel1.add(etSaldoActual);
        etSaldoActual.setBounds(570, 360, 110, 40);

        jLabel7.setText("Clave (4 dígitos)");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(410, 320, 130, 40);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Guía"));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Agregar Cliente\nPuede registrar un nuevo usuario.\n\nEditar \nLa edición se puede realizar a cualquier\ncliente que se muestre en la lista.\n\nSeleccionar Usuario\nIniciará el programa con el número \nde tarjeta del usuario seleccionado.");
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(710, 40, 320, 280);

        tvInfoAccion.setText("*info");
        jPanel1.add(tvInfoAccion);
        tvInfoAccion.setBounds(710, 330, 242, 52);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void etDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etDireccionActionPerformed

    private void btnAgregarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioMouseClicked
        // TODO add your handling code here:
        enableViews(true);    // habilitar views..
        clearViews();
        tvInfoAccion.setText("*info : Ingresar datos de nuevo cliente y guardar");
        btnGuardar.setEnabled(true);
        ISEDITMODE = false;
    }//GEN-LAST:event_btnAgregarUsuarioMouseClicked

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        // TODO add your handling code here:
        
        Cliente cliente = new Cliente();
        cliente.setClaveTarjeta(etClaveTarjeta.getText());
        cliente.setDireccion(etDireccion.getText());
        cliente.setNombre(etNombreUsuario.getText());
        cliente.setNumeroTarjeta(etNumeroTarjeta.getText());
        cliente.setTelefono(etTelefono.getText());
        cliente.setSaldo(((etSaldoActual.getText()).length() == 0)? 0 : Double.parseDouble(etSaldoActual.getText().toString()));
        if(cliente.getClaveTarjeta().length() < 4 || cliente.getDireccion().length() == 0 ||
            cliente.getNombre().length() == 0 || cliente.getNumeroTarjeta().length() == 0 ||  cliente.getTelefono().length() < 9)
        {
            JOptionPane.showMessageDialog(null, "Completar todos los campos, porfavor"); 
        }else{ 
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(!ISEDITMODE)
                        new RegistroClienteTask(cliente, new RegistroClienteTask.OnResult() {
                            @Override
                            public void onResult(int codResultado, String message, Cliente cliente) {
                                if(codResultado == Constantes.RESULT_ERROR)
                                {
                                    JOptionPane.showMessageDialog(null, message);
                                    return;
                                }
                                // podría agregarse la entidad cliente directamente al adaptador sin consultar el servicio http..
                                recargarLista();
                                clearViews();
                                enableViews(false);
                                btnGuardar.setEnabled(false);
                                tvInfoAccion.setText("");
                            }
                        }).execute(); 
                    else
                        new EdicionDatosClienteTask(cliente, new EdicionDatosClienteTask.OnResult() {
                            @Override
                            public void onResult(int codResultado, String message, Cliente cliente) {
                                if(codResultado == Constantes.RESULT_ERROR)
                                {
                                    JOptionPane.showMessageDialog(null, message);
                                    return;
                                }
                                // podría agregarse la entidad cliente directamente al adaptador sin consultar el servicio http..
                                recargarLista();
                                clearViews();
                                enableViews(false);
                                btnGuardar.setEnabled(false);
                                tvInfoAccion.setText("");
                            }
                        }).execute(); 
                }
            }).start();
        } 
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void etClaveTarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etClaveTarjetaKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
            return;
        }
         if(etClaveTarjeta.getText().length() > 3) {  
            evt.consume(); 
         }
    }//GEN-LAST:event_etClaveTarjetaKeyTyped

    private void etTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etTelefonoKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
            return;
        }
         if(etTelefono.getText().length() > 8) {  
            evt.consume(); 
         }
    }//GEN-LAST:event_etTelefonoKeyTyped

    private void btnSeleccionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseClicked
        if(clienteSeleccionado.getNumeroTarjeta().length() != 0){
            new SplashScreen(clienteSeleccionado).setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar algún cliente de la lista.");
        }
    }//GEN-LAST:event_btnSeleccionarMouseClicked

    private void etSaldoActualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etSaldoActualKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
            return;
        }        
        if(etSaldoActual.getText().length() > 5) {  
           evt.consume(); 
        }
    }//GEN-LAST:event_etSaldoActualKeyTyped

    private void etNumeroTarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etNumeroTarjetaKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
            return;
        }        
        if(etNumeroTarjeta.getText().length() > 16) {  
           evt.consume(); 
        }
        
    }//GEN-LAST:event_etNumeroTarjetaKeyTyped

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        // TODO add your handling code here:
        ISEDITMODE = true;
        enableViews(true);
        // bloqueamos algunas vistas no editables
        etNumeroTarjeta.setEditable(false); 
        btnGuardar.setEnabled(true);
        tvInfoAccion.setText("*Info : Módo de edición habilitado.");
    }//GEN-LAST:event_btnEditarMouseClicked

    private void lvCLientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lvCLientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lvCLientesMouseClicked

    public void enableViews(boolean isEnable){
//        etDireccion.setEnabled(isEnable);
//        etNombreUsuario.setEnabled(isEnable);
//        etNumeroTarjeta.setEnabled(isEnable);
//        etTelefono.setEnabled(isEnable);
//        etClaveTarjeta.setEnabled(isEnable);
        
        etDireccion.setEditable(isEnable);
        etNombreUsuario.setEditable(isEnable);
        etNumeroTarjeta.setEditable(isEnable);
        etTelefono.setEditable(isEnable);
        etClaveTarjeta.setEditable(isEnable);
        etSaldoActual.setEditable(isEnable);
        
//        btnGuardar.setEnabled(isDisable); 
    }
    
    public void clearViews(){
        etDireccion.setText("");
        etNombreUsuario.setText("");
        etNumeroTarjeta.setText("");
        etTelefono.setText(""); 
        etClaveTarjeta.setText("");
        etSaldoActual.setText("");
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
            java.util.logging.Logger.getLogger(FormClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPasswordField etClaveTarjeta;
    private javax.swing.JTextField etDireccion;
    private javax.swing.JTextField etNombreUsuario;
    private javax.swing.JTextField etNumeroTarjeta;
    private javax.swing.JTextField etSaldoActual;
    private javax.swing.JTextField etTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList<String> lvCLientes;
    private javax.swing.JLabel tvInfoAccion;
    // End of variables declaration//GEN-END:variables
}
