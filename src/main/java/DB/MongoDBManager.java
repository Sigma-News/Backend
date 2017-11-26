package DB;

/*
        TODO:  - Indexes müssen noch eingefügt werden.
         */

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;

public class MongoDBManager extends Thread{

    private MongoClient client;
    private MongoDatabase db;
    //MongoCollection collection;
    private String host;
    private String database;
    private String user;
    private String password;

    /**
     * Konstruktor stellt Verbindung zu MongoDB-Server her
     * @param host Die Adresse des Servers.
     * @param database Die zu benutzende Datenbank.
     * @param user Der zu benutzende Benutzer.
     * @param password Das zu benutzende Passwort des Benutzers.
     */
    public MongoDBManager(String host, String database, String user, String password){
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = user;
    }

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

    /**
     * Es wird ein neues Objekt der Klasse MongoDBHandler
     * @param args Initialisionswerte
     */
    public static void main(String[] args)
    {
        MongoDBManager man = new MongoDBManager(args[0], args[1], args[2], args[3]);
        for(String item : args) System.out.println(item);
    }

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
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        MongoCollection<Document> collection = db.getCollection("ratings");

        collection.aggregate(
                Arrays.asList(
                        Aggregates.group("URL", Accumulators.avg("rating", 1))))
                .forEach(printBlock);
        System.out.println(printBlock.toString());
        return 0;
    }

    /**
     * Die Verbindung zum MongoDB-Server wird beendet.
     */
    public void closeConnection()
    {
        client.close();
    }
}
