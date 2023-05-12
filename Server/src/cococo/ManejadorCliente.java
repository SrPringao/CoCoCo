package cococo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;

  public ManejadorCliente(Socket socket) {
    this.clientSocket = socket;
  }

  public void run() {
    try {
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        Server.sendMessage(inputLine,Server.usuariosConectados.get(clientSocket.hashCode()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}