/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cococo;


import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Usuario
 */
public class ServerTemp {

        private static ArrayList<ClientThread> usuariosConectados=new ArrayList<ClientThread>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Stablish Console
        JFrame console = new JFrame("Server Console");
        console.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        console.setSize(400,400);
        console.getContentPane().setBackground(Color.black);
        JTextArea consoleText=new JTextArea();
        consoleText.setBackground(Color.black);
        consoleText.setForeground(Color.white);
        consoleText.setEditable(false);
        consoleText.setFont(new Font("Monospaced",Font.PLAIN,11));
        
        //ScrollBar
        JScrollPane scrollBar=new JScrollPane
        (
                consoleText,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        
        //Add Components
        console.add(scrollBar);
        console.setVisible(true);
        
        //Establish Server Connection
        ServerSocket server=new ServerSocket(0); // 0 selecciona un puerto disponible al azar
        printConsole("Listening to Port: "+server.getLocalPort(),consoleText); //Imprimir el puerto seleccionado
        
        Boolean conConfirm=true;
        while(!server.isClosed())
        {
            printConsole("Awaiting for connection...",consoleText);
            
            Socket client=null;
            
            try
            {
                client=server.accept();
                printConsole("Connection Stablished with " + client,consoleText);
                
                //In % Out Streams
                InputStreamReader inData=new InputStreamReader(client.getInputStream());
                OutputStreamWriter outData=new OutputStreamWriter(client.getOutputStream());
                
                printConsole("Assigning new thread",consoleText);
                
                ClientThread cT=new ClientThread(client,inData,outData);
                usuariosConectados.add(cT);
                cT.run();
            }
            catch(Exception e)
            {
                printConsole("Something went wrong",consoleText);
            }
        }
    }
    
    private static void printConsole(String s,JTextArea place)
    {
        place.append("\n["+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+"] ");
        place.append(s);
    }
    
}

class ClientThread implements Runnable
{
    public final Socket s;
    public final userName;
    private final InputStreamReader inData;
    private final OutputStreamWriter outData;

    public ClientThread(Socket s,InputStreamReader in,OutputStreamWriter out)
    {
        this.s=s;
        this.inData=in;
        this.outData=out;
    }

    @Override
    public void run()
    {
        while(s.isConnected())
        {
            try
            {
                outData.write("Hola Mundos");
                outData.flush();
            }
            catch(IOException e)
            {
                System.out.println("Could Not Send Message");
            }
        }
    }
}
