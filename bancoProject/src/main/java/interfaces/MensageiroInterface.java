/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author User
 */
public interface MensageiroInterface extends Remote{
    boolean criarConta(String nome, int conta) throws RemoteException;
    
    boolean removerConta(int conta) throws RemoteException;
    
    boolean saque(int coanta, double valor) throws RemoteException;
    
    boolean transferir(int conta1, int conta2, double valor) throws RemoteException;
    
    boolean depositar(int conta, double valor) throws RemoteException;
}
