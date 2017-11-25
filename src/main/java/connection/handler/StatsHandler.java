package connection.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.JSONObject;

/**
 * Creation of Flawn
 */
public class StatsHandler extends AbstractHandler {


  public void handle(String s, Request request, HttpServletRequest httpServletRequest,
      HttpServletResponse response) throws IOException, ServletException {
    /*
    * Writing the response, for the stats request over netty
    */
    System.out.println("New Statrequest!");
    response.setContentType("application/json; charset=UTF-8");
    PrintWriter printout = response.getWriter();
    JSONObject JObject = new JSONObject();
    JObject.put("Response", "1");
    JObject.put("Message", "Client unauthorized");

    printout.print(JObject);
    printout.flush();

  }

}
