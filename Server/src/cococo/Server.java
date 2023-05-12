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
import java.util.ArrayList;
import cococo.Bd.UsuarioDAO;
import cococo.Models.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Servidor iniciado, esperando conexiones...");
        //Usuario usuario = new Usuario("Frank","1234","192.168.1.99",1);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        //usuarioDAO.addUser(usuario);
        //boolean respuesta = usuarioDAO.Login("PEP", "123", "192.168.1.200");
        //System.out.println(respuesta);
        ArrayList<Usuario> resultados = new ArrayList();
        //resultados = usuarioDAO.getOnlineUsr("192.168.1.200");
        //for(Usuario a:resultados){
        //    System.out.println(a.Nombre);
        //}
        Chat chat = new Chat(1,1,"Callate");
        //usuarioDAO.addMensaje(chat);
        ArrayList<Chat> chatActual = usuarioDAO.MensajesChat(1);
        for(Chat a: chatActual){
            System.out.println(a.Message);
        }
        
                while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexión entrante desde " + clientSocket.getInetAddress().getHostAddress());
            Thread t = new Thread(new ManejadorCliente(clientSocket));
            t.start();
        }
    }
}
