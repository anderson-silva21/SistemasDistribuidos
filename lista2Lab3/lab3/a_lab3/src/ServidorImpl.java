/**
 * Laboratorio 3  
 * Autor: Lucio Agostinho Rocha
 * Ultima atualizacao: 04/04/2023
 */
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.Arrays;

public class ServidorImpl implements IMensagem{
    
    public ServidorImpl() {
                
    }
    
    //Cliente: invoca o metodo remoto 'enviar'
    //Servidor: invoca o metodo local 'enviar'
    @Override
    public Mensagem enviar(Mensagem mensagem) throws RemoteException {
        Mensagem resposta;
        try {
        	System.out.println("Mensagem recebida: " + mensagem.getMensagem());
			resposta = new Mensagem(parserJSON(mensagem.getMensagem()));
		} catch (Exception e) {
			e.printStackTrace();
			resposta = new Mensagem("{\n" + "\"result\": false\n" + "}");
		}
        return resposta;
    }    
    
    public String parserJSON(String json) {
		
        int methodIndex = json.indexOf("method\":\"", 0) + 9;
        int methodEndIndex = json.indexOf("\"", methodIndex);
        String method = json.substring(methodIndex, methodEndIndex);
        System.out.println("ablublublé " + method);

        int argsIndex = json.indexOf("args\":[\"", 0) + 8;
        int argsEndIndex = json.indexOf("\"", argsIndex);
        String args = json.substring(argsIndex, argsEndIndex);
        System.out.println("bleblublu " + args);

        if(method.equals("read")){
            //lê fortuna do servidor
		    return "{\"result\": \"Fortuna Lida com sucesso\"}";
        } else if(method.equals("write")){
            //escreve fortuna do arquivo
		    return "{\"result\": \"Fortuna Escrita com sucesso\"}";
        } else {
		    return "{\"result\": \"Falha ao identificar operação\"}";
        }


	}
    public void iniciar(){

    try {
            Registry servidorRegistro = LocateRegistry.createRegistry(1099);            
            IMensagem skeleton  = (IMensagem) UnicastRemoteObject.exportObject(this, 0); //0: sistema operacional indica a porta (porta anonima)
            servidorRegistro.rebind("servidorFortunes", skeleton);
            System.out.print("Servidor RMI: Aguardando conexoes...");
                        
        } catch(Exception e) {
            e.printStackTrace();
        }        

    }
    
    public static void main(String[] args) {
        ServidorImpl servidor = new ServidorImpl();
        servidor.iniciar();
    }    
}
