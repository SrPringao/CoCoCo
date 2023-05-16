package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

public class RegistroUsuario extends JFrame {
    private JTextField nombreTextField;
    private JPasswordField contrasenaField, confirmarContrasenaField;
    private JButton registrarseButton;

    Color verdeclaro = Color.decode("#17E7B7");
    Color verdeoscuro = Color.decode("#0a261f");
    Color gris = Color.decode("#58626C");
    Color verdechillon = Color.decode("#51e5a1");
    private Socket clientSocket;

    public RegistroUsuario() {
        // Configurar la ventana
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Crear los componentes del formulario
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        nombreTextField = new JTextField(20);
        nombreTextField.setBorder(
                BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(15, 10, 15, 10))));

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        contrasenaField = new JPasswordField(20);
        contrasenaField.setBorder(
                BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(15, 10, 15, 10))));
        JLabel confirmarContrasenaLabel = new JLabel("Confirmar contraseña:");
        confirmarContrasenaLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        confirmarContrasenaField = new JPasswordField(20);
        confirmarContrasenaField.setBorder(
                BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(15, 10, 15, 10))));
        registrarseButton = new JButton("Registrarse");

        registrarseButton.setBackground(verdechillon);
        registrarseButton.setFont(new Font("Arial", Font.PLAIN, 20));
        registrarseButton.setForeground(Color.WHITE);
        registrarseButton.setFocusPainted(false);
        registrarseButton.setBorderPainted(false);
        registrarseButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nombreTextField.getText();
                String password = String.valueOf(contrasenaField.getPassword());
                String passwordC = String.valueOf(confirmarContrasenaField.getPassword());

                try {
                    Boolean respuesta = enviarRegistro(username, password, passwordC);
                    if (respuesta) {
                        dispose();
                        new Client();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JPanel registroPanel = new JPanel();
        registroPanel.setLayout(new GridLayout(4, 1, 10, 10));
        // registroPanel.setBackground(verdeoscuro);
        registroPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        nombreLabel.setForeground(Color.WHITE);
        registroPanel.add(nombreLabel);

        constraints.gridx = 1;
        registroPanel.add(nombreTextField);

        constraints.gridx = 0;
        constraints.gridy = 3;
        contrasenaLabel.setForeground(Color.WHITE);
        registroPanel.add(contrasenaLabel);

        constraints.gridx = 1;
        registroPanel.add(contrasenaField);

        constraints.gridx = 0;
        constraints.gridy = 4;
        confirmarContrasenaLabel.setForeground(Color.WHITE);
        registroPanel.add(confirmarContrasenaLabel);

        constraints.gridx = 1;
        registroPanel.add(confirmarContrasenaField);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        registroPanel.add(registrarseButton);

        registroPanel.setBackground(verdeoscuro);

        getContentPane().add(registroPanel, BorderLayout.CENTER);

        setContentPane(registroPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static Socket clienSocket;

    private Boolean enviarRegistro(String usuario, String contraseña, String confirmar)
            throws UnknownHostException, IOException {

        if (contraseña.equals(confirmar)) {
            clientSocket = new Socket("192.168.0.251", 1234);
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
            out.write("register|" + usuario + "|" + contraseña + "\n");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String respuesta = in.readLine();

            System.out.println(respuesta);
            if (respuesta.contains("true")) {
                JOptionPane.showMessageDialog(this, "Usuario generado correctamente");
                return true;
            } else {
                // El usuario no se ha autenticado correctamente
                JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectos");
                return false;
            }
        } else {
            // El usuario no se ha autenticado correctamente
            JOptionPane.showMessageDialog(this, "Contraseñas diferentes");
            return false;
        }
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
}