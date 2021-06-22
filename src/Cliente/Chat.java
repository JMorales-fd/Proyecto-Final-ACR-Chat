/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Servidor.InterfazServidor;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author fran_
 */
public class Chat extends javax.swing.JFrame implements Runnable {

    private ChatCliente cliente;
    private InterfazServidor server;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private Vector<String> listClientes;
    private String nombre;
    private GroupLayout grupoLayaut;

    /**
     * Creates new form Chat
     */
    public Chat(String nombre, String autoriazcion, InterfazServidor server) {
        initComponents();
        this.server = server;
        this.nombre = nombre;

        if (autoriazcion.equals("Administrador")) {
            System.out.println(autoriazcion);
            jList1.setComponentPopupMenu(jPopupMenu1);
        }
        this.setLocationRelativeTo(null);
        this.setTitle("Chat (" + nombre + ")");
        jPanel2.setLayout(new GridLayout(100, 1));
        jPanel2.setBorder(new EmptyBorder(5, 10, 10, 10));
        
        //pregunta al cliente si quiere cerrar sesion si es asi se eliminara de la lista
        this.addWindowListener(new java.awt.event.WindowAdapter() {    
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(new JFrame(), 
                    "estas seguro que quieres sali del chat?", "Cerrar chat?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    try {
                        server.eliminarCliente(nombre);
                    } catch (RemoteException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    System.exit(0);
                }else{
                   
                }
            }
        });

        EntradaMsg.setForeground(Color.GRAY);
        EntradaMsg.setText("Ingresa el mensaje ...");
        EntradaMsg.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (EntradaMsg.getText().equals("Ingresa el mensaje ...")) {
                    EntradaMsg.setText("");
                    EntradaMsg.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                 if (EntradaMsg.getText().isEmpty()) {
                EntradaMsg.setForeground(Color.GRAY);
                EntradaMsg.setText("Ingresa el mensaje ...");
            }
            }

        });
        listClientes = new Vector<>();
        jList1.setListData(listClientes);
        
        try {
            cliente = new ChatCliente(nombre, server, EntradaMsg, AreaMsg, jPanel2);
            
        } catch (RemoteException e) {
            System.out.println("Error: " + e.getMessage());
        }  
        Timer minuteur = new Timer();
        TimerTask tache = new TimerTask(){
            @Override
            public void run() {
                try{
                int[] indice = jList1.getSelectedIndices();
                model.clear();
                listClientes = server.getNombreListaCliente(nombre);
                int i = 0;
                while(i<listClientes.size()){
                    model.addElement(listClientes.get(i));
                    i++;
                }
                }catch(RemoteException e){
                    System.out.println("Error: "+ e.getMessage());
                
                }
            }
            
        };
        minuteur.schedule(tache, 0, 20000);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemRemov = new javax.swing.JMenuItem();
        jMenuItemBloq = new javax.swing.JMenuItem();
        jMenuItemDesbloq = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButtonRefreshList = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreaMsg = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        EntradaMsg = new javax.swing.JTextArea();
        jButtonEnviarArcchivo = new javax.swing.JButton();
        jButtonEnviarMsg = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();

        jMenuItemRemov.setText("Remover Usuario");
        jMenuItemRemov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRemovActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemRemov);

        jMenuItemBloq.setText("Bloquear Usuario");
        jMenuItemBloq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBloqActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemBloq);

        jMenuItemDesbloq.setText("Desbloquear Usuario");
        jMenuItemDesbloq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDesbloqActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemDesbloq);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setMaximumSize(new java.awt.Dimension(15, 40));
        jList1.setMinimumSize(new java.awt.Dimension(15, 40));
        jList1.setPreferredSize(new java.awt.Dimension(15, 40));
        jScrollPane1.setViewportView(jList1);

        jButtonRefreshList.setText("Refrescar");
        jButtonRefreshList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshListActionPerformed(evt);
            }
        });

        AreaMsg.setEditable(false);
        AreaMsg.setColumns(20);
        AreaMsg.setRows(5);
        jScrollPane2.setViewportView(AreaMsg);

        EntradaMsg.setColumns(20);
        EntradaMsg.setRows(5);
        jScrollPane3.setViewportView(EntradaMsg);

        jButtonEnviarArcchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarArcchivoActionPerformed(evt);
            }
        });

        jButtonEnviarMsg.setText(">");
        jButtonEnviarMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarMsgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRefreshList, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEnviarArcchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEnviarMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonEnviarMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonEnviarArcchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRefreshList)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnviarMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarMsgActionPerformed
        // TODO add your handling code here:
        if(!EntradaMsg.getText().equals("")){
            if(!EntradaMsg.getText().equals("Ingresa tu mensaje ...")){
                try {
                    cliente.enviarMensaje(jList1.getSelectedValuesList());
                } catch (RemoteException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
                EntradaMsg.setText("");
            }else{
                JOptionPane.showMessageDialog(this,"Porfavor ingrreasa tu mensaje ","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,"Porfavor ingrreasa tu mensaje ","Alert",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEnviarMsgActionPerformed

    private void jButtonRefreshListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshListActionPerformed
        // TODO add your handling code here:
        Thread thread = new Thread(this);
        thread.start();
        
    }//GEN-LAST:event_jButtonRefreshListActionPerformed

    private void jMenuItemRemovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRemovActionPerformed
        // TODO add your handling code here:
        try {
            server.eliminarTodosCliente(jList1.getSelectedValuesList());
        } catch (RemoteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemRemovActionPerformed

    private void jMenuItemBloqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBloqActionPerformed
        
        try {
            server.bloquearCliente(jList1.getSelectedValuesList());
        } catch (RemoteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemBloqActionPerformed

    private void jMenuItemDesbloqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDesbloqActionPerformed
        try {
            server.activarCliente(jList1.getSelectedValuesList());
            EntradaMsg.setEnabled(true);
            EntradaMsg.setEditable(true);
        } catch (RemoteException e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemDesbloqActionPerformed

    private void jButtonEnviarArcchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarArcchivoActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            String[] extension = file.getName().split("\\.");
            System.out.println(extension.length);

                try {
                    ArrayList<Integer> inc;
                    try (FileInputStream in = new FileInputStream(file)) {
                        inc = new ArrayList<>();
                        int c=0;
                        while((c=in.read()) != -1) {
                            inc.add(c);
                        }
                        in.close();
                    }
                    server.DifundirArchivo(inc, listClientes,file.getName());
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (RemoteException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }

                JLabel jfile = new JLabel(file.getName() + " subiendo archivo ...");
                jPanel2.add(jfile);
                jPanel2.repaint();
                jPanel2.revalidate();

        }
        
        
    }//GEN-LAST:event_jButtonEnviarArcchivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaMsg;
    private javax.swing.JTextArea EntradaMsg;
    private javax.swing.JButton jButtonEnviarArcchivo;
    private javax.swing.JButton jButtonEnviarMsg;
    private javax.swing.JButton jButtonRefreshList;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenuItem jMenuItemBloq;
    private javax.swing.JMenuItem jMenuItemDesbloq;
    private javax.swing.JMenuItem jMenuItemRemov;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            model.clear();
            listClientes = server.getNombreListaCliente(nombre);
            int  i = 0;
            while(i<listClientes.size()){
                model.addElement(listClientes.get(i));
                i++;
            }
            jList1.setModel(model);
        } catch (RemoteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
