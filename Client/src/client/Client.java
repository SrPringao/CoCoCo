package client;

import java.net.Socket;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Client {
    private static Socket serverSocket;
    private static Socket mySocket;
    private static Socket z;
    
    
    public static void main(String[] args) {
        try {
            serverSocket = new Socket("192.168.84.62", 1458);

            mySocket.getLocalAddress();
            mySocket.getLocalPort();
            
        } catch (Exception e) {
            System.out.println("Error de conexión");
        }
        
        Client correr = new Client();
        // correr.run(myip); // Para enviar la ip actual del cliente al servidor y que allá el serv la actualice con un update a mi id, quizá necesite separar myip de mi id para ponerlo donde va
            correr.run("Héctor se ha unido a la conversación");
            boolean session = true; // cambiar por (session == true) y que cierre la conexión en cuanto se cierre sesión
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingresa tu mensaje: ");
            

        while (session == true) 
        {
            String user = "Hector"; // CAMBIAR "Hector" POR SELECT GLOBAL DEL USUARIO
            // Envío
            String mensaje = scanner.nextLine();
            mensaje = user + ": " + mensaje; 
            correr.run(mensaje);
            
            // Recepción
            /*
            BufferedReader in = new BufferedReader(new InputStreamReader(z.getInputStream()));
            String input = in.readLine();
            System.out.println("Mensaje recibido: " + input);
            */
        }
        scanner.close();
    }
    
    public void run(String mensaje) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(serverSocket.getOutputStream());
            out.write(mensaje + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

