package client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.BorderFactory;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.io.PrintWriter;
import client.Chat.*;
import java.util.ArrayList;
import java.io.ObjectInputStream;

public class Client extends JFrame {
    private static Socket clientSocket;

    private static final Color BACKGROUND_COLOR = new Color(81, 229, 161);
    Color verdeclaro = Color.decode("#17E7B7");
    Color verdeoscuro = Color.decode("#0a261f");
    Color gris = Color.decode("#58626C");
    Color verdechillon = Color.decode("#51e5a1");
    ManejadorServidor chat;

    public Client() throws IOException {

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(verdeoscuro);
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        JTextField usuarioTextField = new JTextField();
        usuarioTextField.setColumns(1);
        usuarioTextField.setBorder(
                BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(15, 10, 15, 10))));

        Box usuariofield = Box.createVerticalBox();
        usuariofield.add(usuarioTextField);
        usuariofield.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        formPanel.add(usernameLabel);
        formPanel.add(usuariofield);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        JPasswordField contrasenaField = new JPasswordField();
        contrasenaField.setPreferredSize(new Dimension(200, 30));
        JButton registerButton = new JButton("Registrarse");
        registerButton.setOpaque(false);
        registerButton.setBackground(null);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear la nueva ventana
                RegistroUsuario registerFrame = new RegistroUsuario();

                registerFrame.setVisible(true);

                // Cerrar la ventana actual (si es necesario)
                dispose(); // constructor login ArrayList idUsuarios e idUsuario
            }
        });
        Box boxPass = Box.createVerticalBox();
        boxPass.add(passwordLabel);
        boxPass.add(registerButton);
        formPanel.add(boxPass);

        Box passField = Box.createVerticalBox();
        passField.add(contrasenaField);
        passField.setBorder(BorderFactory.createEmptyBorder(20, 0, 80, 0));
        contrasenaField.setBorder(
                BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(15, 10, 15, 10))));

        formPanel.add(passField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(verdeoscuro);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JButton loginButton = new JButton("Entrar");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioTextField.getText();
                String password = String.valueOf(contrasenaField.getPassword());

                enviarCredenciales(username, password);
            }
        });

        loginButton.setBackground(verdechillon);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        // loginButton.setBorder(
        // BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new
        // Insets(1, 1, 1, 1))));
        buttonPanel.add(loginButton);

        JPanel mainPanel = new BackgroundPanel("C:/Users/Diego/Cococo/CoCoCo/Client/src/client/liquid-cheese.png");

        mainPanel.setLayout(new BorderLayout());

        // Agrega la etiqueta al marco en el panel de fondo
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 80));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        Box boxForm = Box.createVerticalBox();
        boxForm.add(formPanel, BorderLayout.CENTER);
        boxForm.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(boxForm, BorderLayout.SOUTH);

        JPanel box = new JPanel();
        box.setLayout(new BorderLayout());
        box.add(mainPanel);
        setContentPane(box);// mainPanel2

        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);// Abrir en pantalla completa
    }

    class CustomeBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(verdeoscuro);
            g2d.drawRoundRect(x, y, width, height, 25, 25);
        }
    }

    public static Socket socket;
    private static Client client;

    public static void main(String[] args) throws IOException {
        client = new Client();
    }

    public void enviarCredenciales(String usuario, String contrasena) {
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete ambos campos");
        } else {
            try {
                clientSocket = new Socket("192.168.0.251", 1234);
                OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
                out.write("login|" + usuario + "|" + contrasena + "\n");
                out.flush();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String respuesta = in.readLine();

                System.out.println(respuesta);
                if (respuesta.contains("login|true")) {
                    // El usuario se ha autenticado correctamente
                    JOptionPane.showMessageDialog(this, "Autenticación exitosa");
                    String[] partsResponse = respuesta.split("\\|");

                    ArrayList<Usuario> usuarios = new ArrayList<>();
                    String[] usersWithoutDiv = partsResponse[3].split("\\-");
                    int i = 1;
                    for (String item : usersWithoutDiv) {
                        String[] partsUser = item.split("\\,");
                        usuarios.add(new Usuario(i, partsUser[1], partsUser[0]));
                        i++;
                    }
                    int id = Integer.parseInt(partsResponse[2]);
                    client.dispose();
                    new UsuariosActivos(usuarios, id);
                } else {
                    // El usuario no se ha autenticado correctamente
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
                }
                chat = new ManejadorServidor(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
                while (clientSocket.isConnected()) {

                    while (clientSocket.getInputStream() != null) {
                        chat.run();
                    }

                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}