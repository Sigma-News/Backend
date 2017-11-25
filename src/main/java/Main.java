import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * Creation of Flawn
 */
public class Main {
    public static void Main(String[] args){
      System.out.println("Starting Server...");

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
