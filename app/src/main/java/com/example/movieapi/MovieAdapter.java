package com.example.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{

    private Context context;
    private ArrayList<MovieItem> movieList;

    public MovieAdapter(Context context , ArrayList<MovieItem> movies){
        this.context = context;
        movieList = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_movie_item , parent , false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieHolder holder, int position) {
        MovieItem movie = movieList.get(position);

        holder.title.setText(movie.getTitle());

        Glide.with(context).load(movie.getPoster()).into(holder.imageView);

        holder.rating.setText(movie.getRating().toString());

        holder.overview.setText(movie.getOverview());


        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , MovieDetail.class);

                Bundle bundle = new Bundle();

                bundle.putDouble("rating" , movie.getRating());

                bundle.putString("title" , movie.getTitle());

                bundle.putString("poster" , movie.getPoster());


                bundle.putString("overview" , movie.getOverview());

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title ;
        TextView overview;
        TextView rating;

        ConstraintLayout constraintLayout;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            rating = itemView.findViewById(R.id.rating);


            title = itemView.findViewById(R.id.title_tv);
            overview = itemView.findViewById(R.id.overview_tv);

            imageView = itemView.findViewById(R.id.imageview);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
