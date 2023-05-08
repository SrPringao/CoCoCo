package cococo;

import Bd.UsuarioDAO;
import Models.*;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Server {
    private Socket c;
    private ArrayList<Socket> clients = new ArrayList<>();
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Chat chat;
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usr = new Usuario();

        while (true) {
            try {
                // aquí se espera a que un cliente se conecte
                System.out.println("Esperando...");
                // aquí se acepta la conexión
                c = serverSocket.accept();
                // aqui se agrega el socket a la lista de sockets
                clients.add(c);

                boolean loggedIn = false;
                while (!loggedIn) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    String input = in.readLine();

                    String[] parts = input.split("\\|");

                    // Aquí se llama al método login()
                    loggedIn = login(parts[0], parts[1]);

                    if (!loggedIn) {
                        // Si el usuario no está autenticado, cierra la conexión del cliente
                        c.close();
                        clients.remove(c);
                    }
                }

                System.out.println(c.getInputStream().read());
                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String input = in.readLine();

                String[] parts = input.split("\\|");

                chat = new Chat(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2]);
                usuarioDAO.addChat(chat);

                System.out.println("Mensaje recibido: " + input);
                String mensaje = input;


                // Socket s = new Socket("192.168.1.70", 1234); // PONER AQUÍ EL REMITENTE
                // byte[] bytes = mensaje.getBytes();
                // s.getOutputStream().write(bytes);

                for (Socket s : clients) {
                    if (s != c) { // Evitar reenviar el mensaje al cliente que lo envió
                        OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
                        out.write(mensaje + "\n");
                        out.flush();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean login(String username, String password) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (!usuarioDAO.verificarUsuario(username, password)) {
            return false;
        } else {
            return true;
        }
    }
}