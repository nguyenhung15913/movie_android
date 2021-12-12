package com.example.movieapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.movieapi.database.DatabaseManager;
import com.example.movieapi.database.MovieDatabase;

import java.util.ArrayList;
import java.util.List;

public class FavouriteMovie extends AppCompatActivity implements DatabaseManager.DatabaseListener {
    private RecyclerView recyclerViewFav;
    ArrayList<MovieItem> listFromDB;
    DatabaseManager dbManager;

    MovieDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movie);

        recyclerViewFav= findViewById(R.id.recyclerviewfav);
        recyclerViewFav.setHasFixedSize(true);
        recyclerViewFav.setLayoutManager(new LinearLayoutManager(this));

        db = DatabaseManager.getDBInstance(this);
        dbManager = ((myApp)getApplication()).getDatabaseManager();
        dbManager.listener = this;
        dbManager.getAllMovies();
    }


    @Override
    public void databaseFavouriteMovieListener(List<MovieItem> list) {
        listFromDB = new ArrayList<>(list);
        FavouriteMovieAdapter adapter = new FavouriteMovieAdapter(this,listFromDB );
        recyclerViewFav.setAdapter(adapter);
    }



}