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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexp
 */
public class Server {
    int port=2000;
DatagramSocket dSocket;
DatagramPacket inPacket;
DatagramPacket outPacket;
byte[] buffer;
String messageIn, messageOut;
Date d;
InetAddress clientAddress;
int clientPort;
   
    
    public Server(int port){
        try {
           
            dSocket = new DatagramSocket(port);
            System.out.println("Apertura porta in corso!");
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scrivi(){
        try {
            d = new Date();
            
            messageOut = d.toString();
            outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
            dSocket.send(outPacket);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leggi(){
        try {
            buffer = new byte[256];
            inPacket = new DatagramPacket(buffer, buffer.length);
            dSocket.receive(inPacket);
            clientAddress = inPacket.getAddress();
            clientPort = inPacket.getPort();
            messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("SONO IL CLIENT " + clientAddress +
                    ":" + clientPort + "> " + messageIn);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
				
    }
    
    public void chiudi(){
        dSocket.close();
        System.out.println("Connessione chiusa!");
    }
    
}
