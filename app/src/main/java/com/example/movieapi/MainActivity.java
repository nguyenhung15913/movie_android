package com.example.movieapi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.movieapi.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkingService.NetworkingListener  {
    private RecyclerView recyclerView;
    DatabaseManager dbManager;
    NetworkingService networkingManager;
    ArrayList<MovieItem> movieList;
    JsonService jsonService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jsonService = ((myApp)getApplication()).getJsonService();
        networkingManager = ((myApp) getApplication()).getNetworkingService();
        networkingManager.listener = this;

        networkingManager.getMovieList();

         dbManager = ((myApp) getApplication()).getDatabaseManager();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu,menu);
        return  true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.goToFavourite) {
            Intent intent = new Intent(this, FavouriteMovie.class);
            this.startActivity(intent);
        }

        if(item.getItemId() == R.id.clearMovie){
            dbManager.clearAllMovie();
            Intent intent = new Intent(this, FavouriteMovie.class);
            this.startActivity(intent);
        }
        return true;
    }


    @Override
    public void dataListener(String jsonString) {
        movieList = new ArrayList<MovieItem>();
        movieList = jsonService.getMovieFromJson(jsonString);
        MovieAdapter adapter = new MovieAdapter(MainActivity.this , movieList);

        recyclerView.setAdapter(adapter);
    }


}