package cococo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Bd.UsuarioDAO;
import Models.Chat;
import Models.Usuario;

public class ManejadorCliente implements Runnable {
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;

  public ManejadorCliente(Socket socket) {
    this.clientSocket = socket;
  }

  public void run() {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    try {
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        String[] datos=inputLine.split("\\|");
        
        switch(datos[0])
        {
          case "login":
          {
            System.out.println("Usuario:"+datos[1] + "Contraseña: " + datos[2]);
            
            break;
          }
          case "register":
          {
            break;
          }
          case "sendmessage":
          {
            Server.sendMessage(datos[1]+datos[2], clientSocket);
            break;
          }
          case "sendgroup":
          {
            break;
          }  
        }




        Server.sendMessage(datos[1],Server.usuariosConectados.get(datos[0]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}