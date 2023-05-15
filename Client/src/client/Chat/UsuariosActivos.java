
package client.Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class UsuariosActivos extends JFrame {

    public UsuariosActivos(ArrayList<Usuario> usuarios, int id) {
        // Configura la ventana principal
        setTitle("Usuarios activos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // set padding to the frame
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Crea un panel para mostrar los usuarios
        JPanel userPanel = new JPanel(new BorderLayout());

        // Agrega un título al panel en la región norte
        JLabel userLabel = new JLabel("Usuarios conectados", SwingConstants.CENTER);
        userLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        userLabel.setFont(new Font("Arial", Font.BOLD, 24));
        userPanel.add(userLabel, BorderLayout.NORTH);

        // Crea un panel para mostrar los usuarios en la región central
        JPanel userButtonsPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        for (Usuario usuario : usuarios) {
            JButton userButton = new JButton(usuario.getNombre());
            userButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Clickeaste en " + usuario.getNombre());

                    ChatFrame chatFrame = new ChatFrame(usuario, id);
                }
            });
            userButtonsPanel.add(userButton);
        }
        userPanel.add(userButtonsPanel, BorderLayout.CENTER);

        // Agrega el panel a la ventana principal
        getContentPane().add(userPanel);

        // Muestra la ventana principal
        setVisible(true);
    }
}