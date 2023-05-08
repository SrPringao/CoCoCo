package cococo;
import Bd.UsuarioDAO;
import Models.Chat;


import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Server {
    private Socket c;
    private ArrayList<Socket> clients;
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<Socket>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Chat chat;
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        while (true) {
            try {
                System.out.println("Esperando...");
                c = serverSocket.accept();
                clients.add(c);
                System.out.println(c.getInputStream().read());
                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String input = in.readLine();

                String[] parts = input.split("\\|");

                chat = new Chat(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2]);
                usuarioDAO.addChat(chat);

                System.out.println("Mensaje recibido: " + input);
                String mensaje = input;

                Socket s = new Socket("192.168.1.70", 1234); // PONER AQUÍ EL REMITENTE
                byte[] bytes = mensaje.getBytes();
                s.getOutputStream().write(bytes);

            

                for (Socket sa : clients) {
                    System.out.println(s);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}