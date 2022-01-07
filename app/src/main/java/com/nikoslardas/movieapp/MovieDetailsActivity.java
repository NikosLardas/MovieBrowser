package com.nikoslardas.movieapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nikoslardas.movieapp.castrecyclerview.CastRecyclerViewAdapter;
import com.nikoslardas.movieapp.network.cast.JsonCastResponse;


public class MovieDetailsActivity extends AbstractActivity {

    @Override
    int getLayout() {
        return R.layout.activity_movie_details;
    }

    @Override
    void uiSetup() {

        long id = getIntent().getLongExtra("id",0);
        String title = getResources().getString(R.string.movie_default_title);
        Drawable poster = AppCompatResources.getDrawable(this, R.drawable.basic_image);
        Drawable cover = AppCompatResources.getDrawable(this, R.drawable.basic_image);
        String description = getResources().getString(R.string.movie_default_description);
        String rating = getResources().getString(R.string.movie_default_rating);
        String release_date = getResources().getString(R.string.movie_default_release_date);

        // Setup Movie Details

        Intent intent = getIntent();

        TextView movieTitle = findViewById(R.id.movie_details_title_txt);
        if(intent.getStringExtra("title").isEmpty()) {
            movieTitle.setText(title);
        } else {
            movieTitle.setText(intent.getStringExtra("title"));
        }

        ImageView moviePoster = findViewById(R.id.movie_details_poster_img);
        if(intent.getStringExtra("poster") == null) {
            moviePoster.setImageDrawable(poster);
        } else {
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + intent.getStringExtra("poster")).into(moviePoster);
        }

        ImageView movieCover = findViewById(R.id.movie_details_cover_img);
        if(intent.getStringExtra("cover") == null) {
            movieCover.setImageDrawable(cover);
        } else {
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + intent.getStringExtra("cover")).into(movieCover);
        }

        TextView movieDescription = findViewById(R.id.movie_details_description_txt);
        if(intent.getStringExtra("description").isEmpty()) {
            movieDescription.setText(description);
        } else {
            movieDescription.setText(intent.getStringExtra("description"));
        }

        movieDescription.setMovementMethod(new ScrollingMovementMethod());

        TextView movieRating = findViewById(R.id.movie_details_rating_txt);
        if(intent.getDoubleExtra("rating",1) == 0) {
            movieRating.setText(rating);
        } else {
            movieRating.setText(Double.toString(intent.getDoubleExtra("rating",1)));
        }

        TextView movieReleaseDate = findViewById(R.id.movie_details_release_date_txt);
        if(intent.getStringExtra("release_date").isEmpty()) {
            movieReleaseDate.setText(release_date);
        } else {
            movieReleaseDate.setText(intent.getStringExtra("release_date"));
        }

        // Handle Cast Request

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/movie/" + id + "/credits?api_key=dc313b5a8e7f05ef725a92427cf6a570&language=en-US";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonCastResponse castResponse= new Gson().fromJson(response, JsonCastResponse.class);
                        if(castResponse == null){
                            Toast.makeText(MovieDetailsActivity.this,
                                    "There was an error while retrieving the cast of the Movie. Please try again later!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            RecyclerView recyclerView = findViewById(R.id.movie_cast_recycler_view_list);

                            CastRecyclerViewAdapter castAdapter = new CastRecyclerViewAdapter(castResponse.getCast());

                            recyclerView.setAdapter(castAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof NoConnectionError) {
                            Toast.makeText(MovieDetailsActivity.this,
                                    "Internet connection could no be established... Please activate your WiFi or Mobile Data",
                                    Toast.LENGTH_SHORT).show();
                        }  else {
                            Toast.makeText(MovieDetailsActivity.this,
                                    "Sorry, something went wrong. Please try again later!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
        });

        queue.add(stringRequest);
    }

    @Override
    void startOperations() {

    }

    @Override
    void stopOperations() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}