/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Client {
    private Socket clientSocket;
  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client correr = new Client();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa tu nombre: ");
        String mensaje = scanner.nextLine();
        
        correr.run(mensaje);


    }
    
    public void run(String mensaje) {
        try {
            clientSocket = new Socket("192.168.1.70", 1234);
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
            out.write(mensaje + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

