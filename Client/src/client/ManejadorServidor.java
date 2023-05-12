package client;

import java.io.BufferedReader;
import java.io.IOException;

import client.Chat.Client;

public class ManejadorServidor implements Runnable {
  private BufferedReader in;

  public ManejadorServidor(BufferedReader in) {
    this.in = in;
  }

  @Override
  public void run() {
    while (Client.socket.isConnected()) {
      try {
        String response = in.readLine();
        System.out.println(response);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}