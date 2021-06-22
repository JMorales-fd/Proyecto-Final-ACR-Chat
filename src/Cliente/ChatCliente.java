/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Servidor.InterfazServidor;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author fran_
 */
public class ChatCliente extends UnicastRemoteObject implements InterfaceCliente {

    private final InterfazServidor servidor;
    private final String nombre;
    private final JTextArea input;
    private final JTextArea output;
    private final JPanel jpanel;

    public ChatCliente(String name, InterfazServidor server, JTextArea jtext1, JTextArea jtext2, JPanel jpanel) throws RemoteException {
        this.nombre = name;
        this.servidor = server;
        this.input = jtext1;
        this.output = jtext2;
        this.jpanel = jpanel;
        server.addCliente(this);
    }

    @Override
    public void RecuperaMensaje(String mensage) throws RemoteException {
        output.setText(output.getText() + "\n" + mensage);
    }

    @Override
    public void RecuperarArchivo(String NombeArchivo, ArrayList<Integer> inc) throws RemoteException {
        JLabel label = new JLabel("<HTML><U><font size=\"4\" color=\"#365899\">" + NombeArchivo + "</font></U></HTML>");
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    FileOutputStream out;
                    String separator;
                    separator = "\\";
                    out = new FileOutputStream(System.getProperty("user.home") + separator + NombeArchivo);
                    String[] extension = NombeArchivo.split("\\.");
                    for (int i = 0; i<inc.size(); i++) {
                        int cc = inc.get(i);
                            out.write((byte)cc);
                    }
                    out.flush();
                    out.close();
                    JOptionPane.showMessageDialog(new JFrame(),"el archivo se guardo en " + System.getProperty("user.home") + separator+ NombeArchivo,"Archivo guardado",JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }             
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        jpanel.add(label);
        jpanel.repaint();
        jpanel.revalidate();
    }

    @Override
    public void enviarMensaje(List<String> list) throws RemoteException {
        try {
            servidor.DifundirMensaje(nombre + " : " + input.getText(), list);
        } catch (RemoteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getName() throws RemoteException {
        return nombre;
    }

    @Override
    public void cerrarChat(String mensaje) throws RemoteException {
        input.setEditable(false);
        input.setEnabled(false);
        JOptionPane.showMessageDialog(new JFrame(), mensaje, " Alerta ", JOptionPane.WARNING_MESSAGE);

    }

    @Override
    public void abrirChat() throws RemoteException {
        input.setEditable(true);
        input.setEnabled(true);
    }

}
