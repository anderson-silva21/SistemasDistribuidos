package aula06;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class TCPCliente {
    private static final int PORTA=1234;

    public static void main(String [] args) throws IOException {
        String mensagem;

        System.out.println("Cliente");
        System.out.print(">>>");
        Scanner in = new Scanner(System.in);
        mensagem = in.nextLine();

        Socket clienteSocket = new Socket("localhost", PORTA);

        DataOutputStream saida = new DataOutputStream(
                        clienteSocket.getOutputStream()   
                );

        BufferedReader resposta = new BufferedReader(
            new InputStreamReader(clienteSocket.getInputStream())
        );

        saida.writeBytes(mensagem);

        System.out.println(resposta.readLine());
    }
}
