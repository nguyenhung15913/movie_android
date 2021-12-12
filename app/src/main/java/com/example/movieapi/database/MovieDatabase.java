package com.example.movieapi.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.movieapi.MovieItem;

@Database(version = 1,entities = {MovieItem.class})
public abstract class MovieDatabase extends RoomDatabase {

    abstract public MovieDAO getMovieDao();

}
