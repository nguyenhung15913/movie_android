package com.example.movieapi;


import android.app.Application;

import com.example.movieapi.NetworkingService;
import com.example.movieapi.database.DatabaseManager;

public class myApp extends Application {
    private DatabaseManager databaseManager = new DatabaseManager();

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    private NetworkingService networkingService = new NetworkingService();

    public JsonService getJsonService() {
        return jsonService;
    }

    private JsonService jsonService = new JsonService();


    public NetworkingService getNetworkingService() {
        return networkingService;
    }
}
