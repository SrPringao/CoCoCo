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


public class Client extends JFrame implements ActionListener{

    private JLabel usuarioLabel, contrasenaLabel;
    private JTextField usuarioTextField;
    private JPasswordField contrasenaField;
    private JButton ingresarButton;

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
        
        
        client.Message(idUsr,idChat, mensaje);

        //esto es para el login
        new Client();
        //---------------------
    }
    
    public void Message(int idUsr,int idChat, String mensaje) {

// CREAR HILO PARA ENVIAR



// CREAR HILO PARA RECIBIR
        try {
            clientSocket = new Socket("192.168.1.70", 1234);
            String mensajeCompleto = idChat + "|" + idUsr + "|" + mensaje;
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
            out.write(mensajeCompleto + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarCredenciales (String usuario, String contrasena) {
        try {
            clientSocket = new Socket("192.168.1.70", 1234);
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
            out.write(usuario + "|" + contrasena + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  
}






    
    

    

