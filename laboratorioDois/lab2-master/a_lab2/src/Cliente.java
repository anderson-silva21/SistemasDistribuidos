
/**
 * Laboratorio 1 de Sistemas Distribuidos
 * 
 * Autor: Lucio A. Rocha
 * Ultima atualizacao: 17/12/2022
 */

 import java.io.BufferedReader;
 import java.io.DataInputStream;
 import java.io.DataOutputStream;
 import java.io.InputStreamReader;
 import java.net.Socket;
import java.util.Scanner;
 
 public class Cliente {
     
     private static Socket socket;
     private static DataInputStream entrada;
     private static DataOutputStream saida;
     
     private int porta=1025;
     
     
     public void iniciar(){
         System.out.println("Cliente iniciado na porta: "+porta);
         try {
             socket = new Socket("127.0.0.1", porta); 
 
             entrada = new DataInputStream(socket.getInputStream());
             saida = new DataOutputStream(socket.getOutputStream());
 
             //Recebe do usuario algum valor
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             String opcao = new String();
             do {
                 System.out.println("\nMENU\n1 - Ler Fortuna\n2 - Escrever Fortuna\n3 - Sair");
                 System.out.print("Opção: ");
                 opcao = br.readLine();
                 
                 switch(opcao) {
                     case "1":
                         read();
                         break;
                     case "2":
                         write();
                         break;
                     case "3":
                         System.out.println("Encerrando conexão com o servidor...");
                         break;
                     default:
                         System.out.println("Opção inválida!");
                 }
             } while(!opcao.equals("3"));
             socket.close();           
         } catch(Exception e) {
            System.out.println("cai no problema");
             e.printStackTrace();
         }
     }
 
     private static void read() throws Exception {
         String mensagem = "{\"method\": \"read\", \"args\": [\"\"]}\n";
         saida.writeBytes(mensagem);
         String resultado = new String(entrada.readLine());
         //System.out.println("{\"result\": \"" + resultado + "\"}");
         System.out.println(resultado + "\"}");
     }
 
     private static void write() throws Exception {
         Scanner fortuna = new Scanner(System.in);
        System.out.println("entrei no write do cliente");       
         String mensagem = "{\"method\": \"write\", \"args\": [\"" + fortuna.nextLine() + "\"]}\n";
         saida.writeBytes(mensagem+"\n");
         String resultado = new String(entrada.readLine());
         if(resultado.equals("false")) {
             System.out.println("Erro ao escrever fortuna!");
         } else {
             System.out.println(resultado + "\"}");
             //System.out.println(resultado);
         }
         fortuna.close();
     }
     
     public static void main(String[] args) {
         new Cliente().iniciar();
     }
 }
 