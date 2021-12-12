package com.example.movieapi.database;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import com.example.movieapi.MovieItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {

    static MovieDatabase db;
    ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());

    public interface DatabaseListener {
        void databaseFavouriteMovieListener(List<MovieItem> list);
    }

    public DatabaseListener listener;


    private static void BuildDBInstance (Context context) {
        db = Room.databaseBuilder(context,MovieDatabase.class,"movie_db").build();
    }
    public static MovieDatabase getDBInstance(Context context){
        if (db == null){
            BuildDBInstance(context);
        }
        return db;
    }

    public void insertNewMovie(MovieItem d){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.getMovieDao().insertNewMovie(d);
            }
        });
    }

    public void clearAllMovie(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.getMovieDao().clearMovies();
            }
        });
    }

    public void getAllMovies(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<MovieItem> list =  db.getMovieDao().getAll();
                System.out.println(list);
                db_handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseFavouriteMovieListener(list);
                    }
                });

            }
        });

    }



}