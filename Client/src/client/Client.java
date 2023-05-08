package client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.InputStreamReader;

public class Client extends JFrame implements ActionListener {

    private JLabel usuarioLabel, contrasenaLabel;
    private JTextField usuarioTextField;
    private JPasswordField contrasenaField;
    private JButton ingresarButton;
    private static Socket z;

    public Client() {
        // Configuración del frame
        setTitle("Login");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creación de los componentes
        usuarioLabel = new JLabel("Usuario:");
        contrasenaLabel = new JLabel("Contraseña:");
        usuarioTextField = new JTextField(10);
        contrasenaField = new JPasswordField(10);
        ingresarButton = new JButton("Ingresar");

        // Agregando los componentes al panel
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(usuarioLabel);
        panel.add(usuarioTextField);
        panel.add(contrasenaLabel);
        panel.add(contrasenaField);
        panel.add(new JLabel());
        panel.add(ingresarButton);

        // Agregando el panel al frame
        add(panel);

        // Agregando el listener al botón de ingresar
        ingresarButton.addActionListener(this);

        // Mostrando el frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Verificando si el usuario y contraseña son correctos
        String usuario = usuarioTextField.getText();
        String contrasena = new String(contrasenaField.getPassword());

        enviarCredenciales(usuario, contrasena);
    }

    private Socket clientSocket;

    public static void main(String[] args) {
        int idChat = 1;
        int idUsr = 1;

        Client client = new Client();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa tu mensaje: ");
        String mensaje = scanner.nextLine();
        Thread hilo = new Thread(new Runnable(){
            public void run() {
                while (mensaje != null){
                    client.Message(idUsr, idChat, mensaje);
                }
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(z.getInputStream()));
                    String input = in.readLine();
                    System.out.println("Mensaje recibido: " + input);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
       hilo.start();
}  

        // esto es para el login
        //new Client();
        // ---------------------









    public void Message(int idUsr, int idChat, String mensaje) { // ENVÍO DE MENSAJE
        try {
            clientSocket = new Socket("192.168.84.62", 63658);
            String mensajeCompleto = idChat + "|" + idUsr + "|" + mensaje;
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
            out.write(mensajeCompleto + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  public void enviarCredenciales(String usuario, String contrasena) {
    if (usuario.isEmpty() || contrasena.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete ambos campos");
    } else {
        try {
            clientSocket = new Socket("192.168.84.62", 63658);
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
            out.write(usuario + "|" + contrasena + "\n");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String respuesta = in.readLine();

            if (respuesta.equals("OK")) {
                // El usuario se ha autenticado correctamente
                JOptionPane.showMessageDialog(this, "Autenticación exitosa");
            } else {
                // El usuario no se ha autenticado correctamente
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

}
