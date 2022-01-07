package com.nikoslardas.movieapp.castrecyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nikoslardas.movieapp.R;
import com.nikoslardas.movieapp.network.cast.JsonPersonResponse;


public class CastRecyclerViewHolder extends RecyclerView.ViewHolder {

    public CastRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bindData(JsonPersonResponse data) {

        TextView actorName = itemView.findViewById(R.id.cast_item_actor_name_txt);
        if(data.getName().isEmpty()) {
            actorName.setText(R.string.cast_default_actor_name);
        } else {
            actorName.setText(data.getName());
        }

        TextView character = itemView.findViewById(R.id.cast_item_character_txt);
        if(data.getCharacter().isEmpty()) {
            character.setText(R.string.cast_default_character);
        } else {
            character.setText(data.getCharacter());
        }

        ImageView profile = itemView.findViewById(R.id.cast_item_profile_img);
        if(data.getProfile_path() == null) {
            profile.setImageResource(R.drawable.basic_image);
        } else {
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/" + data.getProfile_path()).into(profile);
        }
    }
}