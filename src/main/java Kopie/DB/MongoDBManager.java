package DB;

/*
        TODO:  - Indexes müssen noch eingefügt werden.
         */

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Date;

public class MongoDBManager extends Thread{

    private MongoClient client;
    private MongoDatabase db;
    private String host;
    private String database;
    private String user;
    private String password;

    public MongoDBManager(String host, String database, String user, String password){
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = user;
    }

    /**
     * Konstruktor stellt Verbindung zu MongoDB-Server her
     * @param host Die Adresse des Servers.
     * @param database Die zu benutzende Datenbank.
     * @param user Der zu benutzende Benutzer.
     * @param password Das zu benutzende Passwort des Benutzers.
     */
    public void run()
    {
        System.out.println("Starting MongoDB Manager...");
        // Es wird ein neuer MongoClient mit den Parametern host, database, user und password erstellt
        MongoClientURI uri = new MongoClientURI("mongodb://" + user + ":" + password + "@" + host + "/" + database); //
        client = new MongoClient(uri);

        // Es wird die Datenbank mit dem Wert der Variable
        db = client.getDatabase(database);
        if(db.getCollection("ratings") == null) db.createCollection("ratings");
    }

    /*public static void main(String[] args)
    {
        MongoDBManager man = new MongoDBManager(args[0], args[1], args[2], args[3]);
        for(String item : args) System.out.println(item);
    }*/

    /**
     * Die Methode storeURL speichert eine URL mit der gegebenen Bewertung und der aktuellen Zeit.
     * @param URL Die URL wird übergeben.
     * @param trusted Vertrauenswürdige Einschätzung, wenn trusted = true ist.
     */
    private void storeURL(String URL, boolean trusted)
    {
        int value;
        if(trusted) value = 1;
        else value = -1;
        Document doc = new Document("URL", URL).append("rating", value).append("time", new Date());
        db.getCollection("ratings").insertOne(doc);
    }

    /**
     * Liefert true zurück, wenn die gesuchte URL vorhanden ist.
     * @param URL URL die verglichen werden soll.
     * @return true, wenn URL vorhanden ist.
     */
    private boolean isURLStored(String URL)
    {
        Document doc = new Document("URL", URL);
        return db.getCollection("ratings").count(doc) > 0;
    }

    //TODO: Elemente gruppieren und average Value berechnen
    public int returnValueToURL(String URL)
    {
        int rating = 0;
        return rating;
    }

    /**
     * Die Verbindung zum MongoDB-Server wird beendet.
     */
    public void closeConnection()
    {
        client.close();
    }
}
