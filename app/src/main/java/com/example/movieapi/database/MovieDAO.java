package com.example.movieapi.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.movieapi.MovieItem;

import java.util.List;

@Dao
public interface MovieDAO {
    @Insert
    void insertNewMovie(MovieItem item);

    @Query("DELETE FROM MovieItem")
    void clearMovies();

    @Query("SELECT * FROM MovieItem")
    List<MovieItem> getAll();

}
