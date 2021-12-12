package com.example.movieapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Bundle;


@Entity
public class MovieItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title ;

    public String poster;

    public String overview;

    private Double rating;

    public MovieItem(String title , String poster , String overview , Double rating){
        this.title = title;

        this.poster = poster;

        this.overview = overview;

        this.rating = rating;
    }



    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public Double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }
}