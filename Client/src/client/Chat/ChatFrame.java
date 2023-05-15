package client.Chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatFrame extends JFrame {

    private Usuario usuario;

    private JTextPane chatTextPane;
    private JTextField messageTextField;
    private JButton sendButton;
    public ArrayList<Mensaje> mensajes;
     private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    public ChatFrame(Usuario usuario, int id) {
        this.usuario = usuario;
        
  try {
            String hostName = "192.168.1.207";
            socket = new Socket(hostName, 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // query DAO de mensajes
        // MensajeDAO mensajeDAO = new MensajeDAO();
        // ArrayList<Mensaje> mensajes = mensajeDAO.getMensajes(usuario.getId());
        mensajes = new ArrayList<Mensaje>();
        /*
         * mensajes.add(new Mensaje(1, 1, 2, "Hola"));
         * mensajes.add(new Mensaje(2, 2, 1, "Hola, ¿cómo estás?"));
         * mensajes.add(new Mensaje(3, 1, 2, "Bien, ¿y tú?"));
         * mensajes.add(new Mensaje(4, 2, 1, "Bien, ¿qué haces?"));
         * mensajes.add(new Mensaje(5, 1, 2, "Nada, ¿y tú?"));
         * mensajes.add(new Mensaje(6, 2, 1, "Nada, ¿qué haces?"));
         * mensajes.add(new Mensaje(7, 1, 2, "Nada, ¿y tú?"));
         * mensajes.add(new Mensaje(8, 2, 1, "Nada, ¿qué haces?"));
         * mensajes.add(new Mensaje(9, 1, 2, "Nada, ¿y tú?"));
         */
        // Configura la ventana de chat
        setTitle("Chat con " + usuario.getNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Agrega elementos a la ventana de chat
        JPanel contentPane = new JPanel(new BorderLayout());

        // Agrega el área de texto para mostrar el chat
        chatTextPane = new JTextPane();
        chatTextPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatTextPane);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // mostrar mensajes en el arraylist
        for (Mensaje mensaje : mensajes) {
            StyledDocument doc = chatTextPane.getStyledDocument();
            SimpleAttributeSet leftAlign = new SimpleAttributeSet();
            StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
            StyleConstants.setForeground(leftAlign, Color.WHITE);
            StyleConstants.setBackground(leftAlign, Color.BLUE);
            StyleConstants.setFontFamily(leftAlign, "Arial");
            StyleConstants.setFontSize(leftAlign, 14); // Reducir el tamaño de fuente a 14

            try {

                // Agregar el mensaje
                doc.insertString(doc.getLength(), mensaje.getIdUser() + ": " + mensaje.getMessage() + "\n\n",
                        leftAlign);
                mensajes.add(new Mensaje(1, 1, 2, "socket.getInputStream()"));

            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }

        // Agrega el panel para escribir y enviar mensajes
        JPanel inputPanel = new JPanel(new BorderLayout());

        messageTextField = new JTextField();
        inputPanel.add(messageTextField, BorderLayout.CENTER);

        sendButton = new JButton("Enviar");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageTextField.getText().trim();
                if (!message.isEmpty()) {
                    try {
                        sendMessage(message, id, usuario);
                    } catch (Exception ee) {
                        // TODO: handle exception
                    }
                    messageTextField.setText("");
                }
            }
        });
        inputPanel.add(sendButton, BorderLayout.EAST);

        contentPane.add(inputPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);

        messageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String message = messageTextField.getText().trim();
                    if (!message.isEmpty()) {
                        try {
                            sendMessage(message, id, usuario);
                        } catch (Exception ee) {
                            // TODO: handle exception
                        }
                        messageTextField.setText("");
                    }
                }
            }
        });

        setVisible(true);
        Thread t = new Thread(this::receiveMessage);
        t.start();
    }

    private void sendMessage(String message, int id, Usuario usuario) throws IOException {

            StyledDocument doc = chatTextPane.getStyledDocument();
            SimpleAttributeSet rightAlign = new SimpleAttributeSet();
            StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);
            StyleConstants.setForeground(rightAlign, Color.WHITE);
            StyleConstants.setBackground(rightAlign, Color.BLUE);
            StyleConstants.setFontFamily(rightAlign, "Arial");
            StyleConstants.setFontSize(rightAlign, 14); // Reducir el tamaño de fuente a 14

            //String hostName = "192.168.1.207";
            System.out.println(usuario.getIp());

            try {
                //socket = new Socket(hostName, 1234);
                // Agregar el mensaje
                doc.insertString(doc.getLength(), "Tú: " + message + "\n\n", rightAlign);
                String mensajeParticionado = "idchat" + "|" + id + "|" + message;
                // Enviar el mensaje al servidor
                // Socket socket = new Socket("localhost", 5000);
                // DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                // dos.writeUTF(mensajeParticionado);
                // dos.close();
                // socket.close();
                System.out.println("send|" + usuario.getIp() + "|" + message);
                out.println("send|" + usuario.getIp() + "|" + message + "\n");
                System.out.println("Mensaje enviado: " + mensajeParticionado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {

            try {
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    String message = in.readLine();
                    System.out.println("Llego:" + message);
                    if (message != null) {
                        // Mostrar el mensaje recibido en el JTextPane
                        StyledDocument doc = chatTextPane.getStyledDocument();
                        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
                        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
                        StyleConstants.setForeground(leftAlign, Color.WHITE);
                        StyleConstants.setBackground(leftAlign, Color.BLUE);
                        StyleConstants.setFontFamily(leftAlign, "Arial");
                        StyleConstants.setFontSize(leftAlign, 14);
                        doc.insertString(doc.getLength(), message + "\n\n", leftAlign);

                    }
                }
                
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
