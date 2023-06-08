/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

/**
 *
 * @author User
 */
public class Conta {
    
    private int conta;
    private double saldo, saque;
    private double limite;
    private String nome;
    
    public Conta(String nome, int conta) {
        this.nome = nome;
        this.conta = conta;
        saldo = 0;
    }
    
    public void extrato(){
        System.out.println("EXTRATO");
        System.out.println("Nome: " + this.nome);
        System.out.println("Número da conta: " + this.conta);
        System.out.println("Saldo atual: %.2f\n" + this.saldo);
        System.out.println("Saques realizado hoje: " + this.saque + "\n");
    }
    
    public boolean sacar(double valor) {
        if(saldo >= valor) {
            saldo = saldo - valor;
            saque++;
            return true;
        }
        return false;
    }
    
    public void depositar(double valor) {
        saldo = saldo + valor;
    }
    
    public boolean transferir(double valor, Conta c2) {
        if(valor > 0) {
            this.sacar(valor);
            c2.depositar(valor);
            return true;
        }
        return false;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaque() {
        return saque;
    }

    public void setSaque(double saque) {
        this.saque = saque;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
