
package client.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Socket socket;

    public static void main(String[] args) throws IOException {
        String hostName = "192.168.85.6";
        int portNumber = 1234;

        socket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);
        String userInput;
        while ((userInput = scanner.nextLine()) != null) {

            out.println("192.168.85.7" + "|" + userInput);
            String response = in.readLine();
            System.out.println(response);
        }

        out.close();
        in.close();
        socket.close();
    }
}