/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Servidor {
    public static void main (String[] args){
        try {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("Calculadora", new Rmi());
            JOptionPane.showMessageDialog(null, "Conectado");    
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Desconectado " + e);    
        }
    }
}
