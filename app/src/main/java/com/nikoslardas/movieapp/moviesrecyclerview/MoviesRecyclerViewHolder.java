package com.nikoslardas.movieapp.moviesrecyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nikoslardas.movieapp.R;
import com.nikoslardas.movieapp.network.movies.JsonMovieResponse;

public class MoviesRecyclerViewHolder extends RecyclerView.ViewHolder {

    private MoviesRecyclerViewAdapter.Listener callback;

    public MoviesRecyclerViewHolder(@NonNull View itemView, MoviesRecyclerViewAdapter.Listener callback) {
        super(itemView);
        this.callback = callback;
    }

    public void bindData(JsonMovieResponse data) {

        TextView movieTitle = itemView.findViewById(R.id.movie_item_title_txt);
        if(data.getTitle().isEmpty()) {
            movieTitle.setText(R.string.movie_default_title);
        } else {
            movieTitle.setText(data.getTitle());
        }

        TextView movieReleaseDate = itemView.findViewById(R.id.movie_item_release_date_txt);
        if(data.getRelease_date().isEmpty()) {
            movieReleaseDate.setText(R.string.movie_default_release_date);
        } else {
            movieReleaseDate.setText(data.getRelease_date());
        }

        TextView movieRating = itemView.findViewById(R.id.movie_item_rating_txt);
        if(data.getVote_average() == 0) {
            movieRating.setText(R.string.movie_default_rating);
        } else {
            movieRating.setText(Double.toString(data.getVote_average()));
        }

        TextView movieDescription = itemView.findViewById(R.id.movie_item_description_txt);
        if(data.getOverview().isEmpty()) {
            movieDescription.setText(R.string.movie_default_description);
        } else {
            movieDescription.setText(data.getOverview());
        }

        ImageView moviePoster = itemView.findViewById(R.id.movie_item_poster_img);
        if(data.getPoster_path() == null) {
            moviePoster.setImageResource(R.drawable.basic_image);
        } else {
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/" + data.getPoster_path()).into(moviePoster);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callback.onItemClick(v, data);
            }
        });
    }
}