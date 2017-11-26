package core;

import DB.MongoDBManager;

import connection.RequestManager;

/**
 * Creation of Flawn
 */
public class Main {
    public static MongoDBManager mdb;
    public static RequestManager rm;

    public static void main(String[] args){
        RequestManager rm = new RequestManager();
        MongoDBManager mdb = new MongoDBManager("10.23.41.191:27017", "ratings", "admin", "hugo1234");
        rm.start();
        mdb.start();

    }
}

