/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class Cliente {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{
            Registry meuRegistro = LocateRegistry.getRegistry("localhost", 1099);
            Calculadora c = (Calculadora)Naming.lookup("//localhost/Calculadora");
            
            while(true){
                String menu = JOptionPane.showInputDialog(" Calculadora\n\n"
                        + "Digite (1)..Somar\n"
                        + "Digite (2)..Subtrair\n"
                        + "Digite (3)..Multiplicar\n"
                        + "Digite (4)..Dividir\n"
                        + "Digite (5)..Potencia\n"
                        + "Digite (6)..Raiz\n"
                        + "Digite (7)..Raiz quadrada\n");
                switch(menu) {
                    case "1":{
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro numero que quer somar"));
                        int y = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo numero que quer somar"));
                        
                        JOptionPane.showMessageDialog(null, "A soma é: " + c.add(x, y));
                        break;
                    }
                    case "2":{
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro numero que quer subtrair"));
                        int y = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo numero que quer subtrair"));
                        
                        JOptionPane.showMessageDialog(null, "A subtração é: " + c.sub(x, y));
                        break;
                    }
                    
                    case "3":{
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro numero que quer multiplicar"));
                        int y = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo numero que quer multiplicar"));
                        
                        JOptionPane.showMessageDialog(null, "A Multiplicação é: " + c.mul(x, y));
                        break;
                    }
                    
                    case "4":{
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro numero que quer dividir"));
                        int y = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo numero que quer dividir"));
                        
                        JOptionPane.showMessageDialog(null, "A Divisão é: " + c.div(x, y));
                        break;
                    }
                    case "5":{
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro numero que quer Potencia"));
                        int y = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo numero que quer Potencia"));
                        
                        JOptionPane.showMessageDialog(null, "A Potencia é: " + c.pot(x, y));
                        break;
                    }
                    case "6":{
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro numero que quer raiz"));
                        double y = Integer.parseInt(JOptionPane.showInputDialog("Digite o grau da raiz"));
                        
                        JOptionPane.showMessageDialog(null, "A raiz é: " + c.root(x, y));
                        break;
                    }
                    case "7":{
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero que quer a Raiz quadrada"));
                        
                        JOptionPane.showMessageDialog(null, "A raiz é: " + c.rootsq(x));
                        break;
                    }                         
                }
            }
        }catch(Exception e){
            System.out.println("Servidor desconectado " + e);
        }
    }
}
