/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cococo;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author PC
 */
public class Server {
    private Socket c;
  
  
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start() {
        while (true) {
            try {
                
                System.out.println("Esperando...");
                c = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String input = in.readLine();
                System.out.println("Mensaje recibido: " + input);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}