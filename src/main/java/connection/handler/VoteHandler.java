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
public class VoteHandler extends AbstractHandler {

  public void handle(String s, Request request, HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws IOException, ServletException {


    System.out.println("New Voterequest!");
    Object link = (String) httpServletRequest.getParameter("link");
    int vote = Integer.parseInt(httpServletRequest.getParameter("vote"));
    System.out.println(link);
    System.out.println(vote);
    httpServletResponse.setContentType("application/json; charset=UTF-8");
    PrintWriter printout = httpServletResponse.getWriter();
    JSONObject JObject = new JSONObject();
    JObject.put("ok", "300");

    printout.print(JObject);
    printout.flush();


  }

}
