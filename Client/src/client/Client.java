/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 1234;

        Socket socket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);
        String userInput;
        while ((userInput = scanner.nextLine()) != null) {
            out.println(userInput);
            System.out.println("Mensaje enviado: " + userInput);
            String response = in.readLine();
            System.out.println("Respuesta recibida: " + response);
        }

        out.close();
        in.close();
        socket.close();
    }
}
