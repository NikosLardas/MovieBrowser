package com.nikoslardas.movieapp.castrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nikoslardas.movieapp.R;
import com.nikoslardas.movieapp.network.cast.JsonPersonResponse;

import java.util.List;


public class CastRecyclerViewAdapter extends RecyclerView.Adapter<CastRecyclerViewHolder>{

    @NonNull
    private List<JsonPersonResponse> cast;

    public CastRecyclerViewAdapter(List<JsonPersonResponse> listData) {
        this.cast = listData;
    }

    @NonNull
    @Override
    public CastRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new CastRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastRecyclerViewHolder holder, int position) {
        JsonPersonResponse data = cast.get(position);
        holder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.holder_cast_item;
    }
}