/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br;

/**
 *
 * @author a2150875
 */
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

public class SoapClient {

    private static String CEP;

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public void callSoapWebService(String soapEndpointUrl,
            String soapAction) {
        try {
            // Criar conexao SOAP
            SOAPConnectionFactory soapConnectionFactory
                    = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection
                    = soapConnectionFactory.createConnection();
            // Enviar SOAP Message para o server
            SOAPMessage soapResponse
                    = soapConnection.call(createSOAPRequest(soapAction),
                            soapEndpointUrl);
            // Imprimir resposta
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();
            soapConnection.close();
        } catch (Exception e) {
            System.out.println("ERRO:");
            System.out.println(e.getMessage());
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        //criar mensagem SOAP
        MessageFactory messageFactory
                = MessageFactory.newInstance();
        SOAPMessage soapMessage
                = messageFactory.createMessage();
        //criar envelope SOAP
        createSoapEnvelope(soapMessage);

        //MimeHeaders headers = soapMessage.getMimeHeaders();
        //headers.addHeader("SOAPAction", soapAction);
        soapMessage.saveChanges();
//Exibir mensagem
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
//System.out.println("\nPassei por aqui\n");
        return soapMessage;
    }

    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
//@Lucio: esse pode mudar
        String myNamespace = "ns";
//@Lucio: (esse tem que manter) dica do plugin Wizdler para Firefox
        String myNamespaceURI
                = "http://cliente.bean.master.sigep.bsb.correios.com.br/";
// SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace,
                myNamespaceURI);
// SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem
                = soapBody.addChildElement("consultaCEP", myNamespace);
        SOAPElement soapBodyElem1
                = soapBodyElem.addChildElement("cep");
        soapBodyElem1.addTextNode(CEP);
    }
}
