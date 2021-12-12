package com.example.movieapi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {

    public ArrayList<MovieItem> getMovieFromJson(String json)  {

        ArrayList<MovieItem> arrayList = new ArrayList<>(0);
        try {
            JSONObject json_movies = new JSONObject(json);
            String path = "https://image.tmdb.org/t/p/w500/";
            for(int i = 0; i <= json_movies.length(); i++) {
                String title = json_movies.getJSONArray("results").getJSONObject(i).getString("original_title");
                String poster = path + json_movies.getJSONArray("results").getJSONObject(i).getString("poster_path");
                String overview = json_movies.getJSONArray("results").getJSONObject(i).getString("overview");
                double rating = Double.parseDouble(json_movies.getJSONArray("results").getJSONObject(i).getString("vote_average"));
                MovieItem item = new MovieItem(title , poster ,overview , rating);
                arrayList.add(item);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

}
