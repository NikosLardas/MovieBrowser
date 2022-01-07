package com.nikoslardas.movieapp.moviesrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nikoslardas.movieapp.R;
import com.nikoslardas.movieapp.network.movies.JsonMovieResponse;

import java.util.List;


public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewHolder>{

    public interface Listener {
        void onItemClick(View view, JsonMovieResponse data);
    }

    @NonNull
    private List<JsonMovieResponse> movies;
    private Listener callback;

    public MoviesRecyclerViewAdapter(List<JsonMovieResponse> listData, Listener callback) {
        this.movies = listData;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MoviesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new MoviesRecyclerViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesRecyclerViewHolder holder, int position) {
        JsonMovieResponse data = movies.get(position);
        holder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.holder_movie_item;
    }
}