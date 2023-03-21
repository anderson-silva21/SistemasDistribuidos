package aula06;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServidor {
    private static final int PORTA=1234;

    public static void main(String [] args) throws IOException {
        String mensagem;

        System.out.println("Servidor");
        System.out.print(">>>");

        try{
            ServerSocket socket = new ServerSocket(PORTA);

            while(true){
                System.out.println("Aguardando...");
                Socket conexao = socket.accept(); //Espera sincrona

                BufferedReader entradaDoCliente = new BufferedReader(conexao.getInputStream());
                    
                DataOutputStream resposta = new DataOutputStream(conexao.getOutputStream());

                resposta.writeBytes(mensagem);
                
                System.out.println(resposta.readLine());

                clienteSocket.close();
                    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
 TCPServidor {
    
}
