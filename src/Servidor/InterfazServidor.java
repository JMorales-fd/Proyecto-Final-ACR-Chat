/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import Cliente.InterfaceCliente;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
/**
 *
 * @author fran_
 */
public interface InterfazServidor extends Remote{
    //
    public void DifundirMensaje(String msg ,List <String> list) throws RemoteException;
    public void DifundirArchivo(ArrayList <Integer> inc, List<String> list,String nombreArchivo) throws RemoteException;
    public Vector<String> getNombreListaCliente(String name) throws RemoteException;
    public void addCliente(InterfaceCliente cliente) throws RemoteException;
    public void bloquearCliente(List<String> cliente) throws RemoteException;
    public void eliminarTodosCliente(List<String> clinete) throws RemoteException;
    public void eliminarCliente(String cliente) throws RemoteException;
    public void activarCliente(List<String> cliente) throws RemoteException;
    public boolean existeNombreClinete(String nombreUser) throws RemoteException;
    
}
