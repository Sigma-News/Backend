package connection.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * Creation of Flawn
 */
public class VoteHandler extends AbstractHandler {

  public void handle(String s, Request request, HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws IOException, ServletException {
    Object link = (String) httpServletRequest.getParameter("link");
    int vote = Integer.parseInt(httpServletRequest.getParameter("vote"));
    System.out.println(link);
    System.out.println(vote);
  }

}
