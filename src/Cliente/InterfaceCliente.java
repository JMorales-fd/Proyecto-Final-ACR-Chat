/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.util.ArrayList;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 *
 * @author fran_
 */
public interface InterfaceCliente extends Remote{
    
    public void  RecuperaMensaje(String mensage) throws RemoteException;
    
    public void RecuperarArchivo(String NombeArchivo, ArrayList<Integer> inc)throws RemoteException;
    
    public void enviarMensaje(List<String> list)throws RemoteException;
    
    public String getName()throws RemoteException;
    
    public void cerrarChat(String mensaje) throws RemoteException;
    
    public void abrirChat() throws RemoteException;
}
