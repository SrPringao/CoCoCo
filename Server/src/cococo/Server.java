package cococo;

import Bd.UsuarioDAO;
import Models.*;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Server {
    public static Map<String, Socket> usuariosConectados = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Servidor iniciado, esperando conexiones...");

        while (!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexión entrante desde " + clientSocket.getInetAddress().getHostAddress());
            Thread t = new Thread(new ManejadorCliente(clientSocket));
            t.start();
            System.out.println("Id del Hilo: " + t.getId());
            usuariosConectados.put(clientSocket.getInetAddress().getHostAddress(), clientSocket);


            System.out.println(usuariosConectados.get(clientSocket.getInetAddress().getHostAddress()));
        }
    }

    public static void sendMessage(String mensaje,Socket sender) throws IOException
    {
        System.out.println(mensaje);
        PrintWriter out=new PrintWriter(sender.getOutputStream(),false);
        out.write(mensaje+"\n");
        out.flush();
    }
}