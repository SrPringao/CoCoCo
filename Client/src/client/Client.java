package client;

import java.net.Socket;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Client {
    private static Socket clientSocket;
    
    
    
    public static void main(String[] args) {
        try {
            clientSocket = new Socket("192.168.84.62", 1458);
        } catch (Exception e) {
            System.out.println("Error de conexión");
        }
        
        Client correr = new Client();
        
            Scanner scanner = new Scanner(System.in);
    
            System.out.println("Ingresa tu mensaje: ");
            correr.run("Héctor se ha unido a la conversación");
            boolean session = true;
        while (session == true) // cambiar por (session == true) y que cierre la conexión en cuanto se cierre sesión
        {
            String mensaje = scanner.nextLine();
            mensaje = "Hector" + ": " + mensaje; // CAMBIAR "Hector" POR SELECT GLOBAL DEL USUARIO
            
            correr.run(mensaje);
        }
        scanner.close();
    }
    
    public void run(String mensaje) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
            out.write(mensaje + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

