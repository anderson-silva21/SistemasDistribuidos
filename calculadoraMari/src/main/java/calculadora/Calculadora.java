/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package calculadora;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author User
 */
public interface Calculadora extends Remote {
    public double div(int a, int b) throws RemoteException;
    public int mul(int a, int b) throws RemoteException;
    public int sub(int a, int b) throws RemoteException;
    public int add(int a, int b) throws RemoteException;
    public double pot(int a, int b) throws RemoteException;
    public double root(int a, double b) throws RemoteException;
    public double rootsq(int a) throws RemoteException;
    
}
