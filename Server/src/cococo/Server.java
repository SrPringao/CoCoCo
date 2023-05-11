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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Servidor iniciado, esperando conexiones...");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexión entrante desde " + clientSocket.getInetAddress().getHostAddress());
            Thread t = new Thread(new ManejadorCliente(clientSocket));
            t.start();
        }
    }
}
