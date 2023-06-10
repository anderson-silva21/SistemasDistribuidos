/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;


import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.lang.Math;
/**
 *
 * @author User
 */
public class Rmi extends UnicastRemoteObject implements Calculadora {
    public Rmi() throws RemoteException {
        int a, b;
    }
    public int add (int a, int b)throws RemoteException{
        return a + b;
    }
    public int sub (int a, int b)throws RemoteException{
        return a - b;
    }
    public int mul (int a, int b)throws RemoteException{
        return a * b;
    }
    public double div (int a, int b)throws RemoteException{
        return a / b;
    }
    public double pot (int a, int b)throws RemoteException{
        return Math.pow(a,b);
    }
    public double root (int a, double b)throws RemoteException{
        return Math.pow(a, (1/b));
    }
    public double rootsq (int a)throws RemoteException{
        return Math.sqrt(a);
    }
}
