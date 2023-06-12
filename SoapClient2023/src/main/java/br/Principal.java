/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br;

/**
 *
 * @author a2150875
 */
public class Principal {
    public static void main(String[] args) {
        System.out.println("Principal: envia mensagem para o servidor");
        //String soapEndpointUrl = "http://wscorreio.multilaser.com.br/soap/AtendeCliente.wsdl";
        String soapEndpointUrl = "https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl";
        String soapAction = "http://cliente.bean.master.sigep.bsb.correios.com.br";
        SoapClient sc = new SoapClient();
        //Requisicao1
        System.out.println("\n---Requisicao1---");
        sc.setCEP("86812507");
        sc.callSoapWebService(soapEndpointUrl, soapAction);

        System.out.println("Fim!"); 
    }
}
