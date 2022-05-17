/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udptime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexp
 */
public class Client {
    int port = 2000;
    InetAddress serverAddress;
    DatagramSocket dSocket;
    DatagramPacket outPacket;
    DatagramPacket inPacket;
    byte[] buffer;
    String message="RICHIESTA DATA E ORA";
    String response;
    
    public Client(){
        try {
            serverAddress = InetAddress.getLocalHost();
            System.out.println("Indirizzo del server trovato!");
            try {
                dSocket = new DatagramSocket();
            } catch (SocketException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
     public void scrivi(){
        try {
            outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
            dSocket.send(outPacket);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
			
        }
     
     public void leggi(){
        try {
            buffer = new byte[256];
            inPacket = new DatagramPacket(buffer, buffer.length);
            dSocket.receive(inPacket);
            response = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("Data e ora del server: " + response);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
			
     }
       public void chiudi(){
        dSocket.close();
        System.out.println("Connessione chiusa!");
    }
    
}
