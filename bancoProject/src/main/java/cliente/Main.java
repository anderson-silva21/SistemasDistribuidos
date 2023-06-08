/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import interfaces.MensageiroInterface;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


/**
 *
 * @author User
 */
public class Main {
    /**
     * @param args
     * @throws NotBoundException
     * @throws RemoteException
     * @throws MalformedURLException
     */
    
    private static void exibirMenu() {
        System.out.println("1 - Criar conta");
        System.out.println("2 - Remover conta");
        System.out.println("3 - Depositar dinheiro na conta");
        System.out.println("4 - Sacar dinheiro da conta");
        System.out.println("5 - Transferir de uma conta para outra");
        System.out.println("0 - Sair");
        System.out.println("Opção: ");
    }
    
    public static void main(String[] args) throws MalformedURLException,
            RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(123);
        MensageiroInterface gerente = (MensageiroInterface) registry.lookup("gestor");
        Scanner sc = new Scanner(System.in);
        String nome;
        int conta, conta1, conta2;
        double valor;
        String op = "1";
        while(!op.equals("0")){
            exibirMenu();
            op = sc.next();
            switch (op) {
                case "1":
                    System.out.println("Criar conta");
                    System.out.println("Informe seu nome: ");
                    nome = sc.next();
                    System.out.println("Informe o número da conta: ");
                    conta1 = sc.nextInt();
                    if(gerente.criarConta(nome, conta1)) {
                        System.out.println("Conta criada com sucesso.");
                    } else {
                        System.out.println("Não existe conta com esse número");
                    }
                    break;
                
                case "2":
                    System.out.println("Remover conta");
                    System.out.println("Informe a conta");
                    conta = sc.nextInt();
                    if(gerente.removerConta(conta)){
                        System.out.println("Conta removida com sucesso.");
                    } else {
                        System.out.println("Não existe conta com esse número");
                    }
                    break;
                case "3":
                    System.out.println("Depositar dinheiro na conta");
                    System.out.println("Informe a conta");
                    conta = sc.nextInt();
                    System.out.println("Qual valor a ser depositado?");
                    valor = sc.nextInt();
                    if (gerente.depositar(conta, valor)) {
                        System.out.println("Deposito com sucesso.");
                    } else {
                        System.out.println("Não existe conta para deposito. " +
                                "Tente outra conta.");
                    }
                    break;
                case "4":
                    System.out.println("Sacar dinheiro da conta");
                    System.out.println("Informe a conta");
                    conta = sc.nextInt();
                    System.out.println("Qual o valor a ser sacado?");
                    valor = sc.nextInt();
                    if(gerente.saque(conta, valor)) {
                        System.out.println("Saque com sucesso");
                    } else {
                        System.out.println("Não existe saldo para saque na conta. " +
                                "Tente outro valor.");
                    }
                    break;
                case "5":
                    System.out.println("Transferir dinheiro de uma conta para outra");
                    System.out.println("Informe a conta que será retirado o dinheiro: ");
                    conta1 = sc.nextInt();
                    System.out.println("Informe a conta para transferência: ");
                    conta2 = sc.nextInt();
                    System.out.println("Qual o valor a ser transferido?");
                    valor = sc.nextInt();
                    if (gerente.transferir(conta1, conta2, valor)){
                        System.out.println("Transferência com sucesso.");
                    } else {
                        System.out.println("Não existe saldo para saque na conta. " +
                                "Tente outro valor.");
                    }
                    break;
            }
        }
        System.out.println("Sistema encerrado");
    }
}
