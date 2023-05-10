package clientFront;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainWindow {
    public static void startInterface()
    {
        JFrame mainFrame=new JFrame("CoCoCo");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800,500);
        mainFrame.setVisible(true);
        BoxLayout mainLayout=new BoxLayout(mainFrame, 0);
        JButton button=new JButton("Dede");
        button.setLayout(mainLayout);
        mainFrame.add(button,mainLayout);
    }
    public static void main(String[] args) {
        startInterface();
    }
}
