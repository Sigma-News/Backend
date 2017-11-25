package server;

import core.Config;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * Creation of Flawn
 */
public class ConnectionManager {
  public ConnectionManager(){
    System.out.println("Starting server...");

    try {
      InetAddress inetAddress = InetAddress.getByName("192.168.1.1");
      ServerSocket ss = new ServerSocket(Config.PORT);

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
