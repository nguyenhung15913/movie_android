package com.example.movieapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavouriteMovieAdapter extends RecyclerView.Adapter<FavouriteMovieAdapter.MovieHolder> {
    private Context context;
    private ArrayList<MovieItem> favMovieList;

    public FavouriteMovieAdapter(Context context , ArrayList<MovieItem> movies){
        this.context = context;
        favMovieList = movies;
    }

    @NonNull
    @Override
    public FavouriteMovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.activity_movie_item , parent , false);
        return new FavouriteMovieAdapter.MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteMovieAdapter.MovieHolder holder, int position) {
        MovieItem movie = favMovieList.get(position);
        holder.rating.setText(String.valueOf(movie.getRating()));
        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());
        Glide.with(context).load(movie.getPoster()).into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetail.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", movie.getTitle());
                bundle.putString("overview", movie.getOverview());
                bundle.putString("poster", movie.getPoster());
                bundle.putDouble("rating", movie.getRating());

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
            });
    }

    @Override
    public int getItemCount() { return favMovieList.size(); }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title , overview , rating;
        ConstraintLayout constraintLayout;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title_tv);
            overview = itemView.findViewById(R.id.overview_tv);
            rating = itemView.findViewById(R.id.rating);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
