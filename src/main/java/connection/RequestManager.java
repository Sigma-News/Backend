package connection;

import connection.handler.StatsHandler;
import connection.handler.VoteHandler;
import core.Config;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

/**
 * Creation of Flawn
 */
public class RequestManager extends Thread {
  public void run()  {
  try {
      System.out.println("Listening on Reqs...");
    Server server = new Server(Config.PORT);

    // Handler for the voting API
    ContextHandler votingContext = new ContextHandler();
    votingContext.setContextPath("/vote");
    votingContext.setHandler(new VoteHandler());
    // Handler for the stats API
    ContextHandler statContext = new ContextHandler();
    statContext.setContextPath("/stats");
    statContext.setHandler(new StatsHandler());

    // summing all the Handlers up to one
    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[] { votingContext, statContext});
    server.setHandler(contexts);
    server.start();
    server.join();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
