package com.example.movieapi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movieapi.database.DatabaseManager;
import com.example.movieapi.database.MovieDatabase;

import java.time.Instant;
import java.util.List;

public class MovieDetail extends AppCompatActivity implements DatabaseManager.DatabaseListener {
    DatabaseManager dbManager;
    MovieDatabase db;

    String mOverView;
    String mTitle;
    String mPoster;

    double mRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        ImageView imageView = findViewById(R.id.poster_image);
        TextView rating_tv = findViewById(R.id.mRating);
        TextView title_tv = findViewById(R.id.mTitle);
        TextView overview_tv = findViewById(R.id.movervie_tv);

        Bundle bundle = getIntent().getExtras();

        mPoster = bundle.getString("poster");

        mTitle = bundle.getString("title");

        mOverView = bundle.getString("overview");
        mRating = bundle.getDouble("rating");

        Glide.with(this).load(mPoster).into(imageView);
        rating_tv.setText(Double.toString(mRating));

        overview_tv.setText(mOverView);

        title_tv.setText(mTitle);


     dbManager = ((myApp) getApplication()).getDatabaseManager();
     dbManager.listener = this;
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


        return true;
    }


    public  void buttonAdd(View view) {
        MovieItem movie = new MovieItem(mTitle,mPoster,mOverView,mRating);
        dbManager.insertNewMovie(movie);
        Intent intent = new Intent(this, FavouriteMovie.class);
        this.startActivity(intent);
    }

    public void backToList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void databaseFavouriteMovieListener(List<MovieItem> list) {

    }
}
