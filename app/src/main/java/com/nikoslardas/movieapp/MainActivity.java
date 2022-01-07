package com.nikoslardas.movieapp;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nikoslardas.movieapp.moviesrecyclerview.MoviesRecyclerViewAdapter;
import com.nikoslardas.movieapp.network.movies.JsonMovieResponse;
import com.nikoslardas.movieapp.network.movies.JsonResultsResponse;


public class MainActivity extends AbstractActivity {

    @Override
    int getLayout() {
        return R.layout.activity_main_movies;
    }

    @Override
    void uiSetup() {

        RecyclerView recyclerView = findViewById(R.id.movies_recycler_view_list);

        // Handle Popular Movies Request

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=dc313b5a8e7f05ef725a92427cf6a570&language=en-US&page=1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonResultsResponse moviesResponse= new Gson().fromJson(response, JsonResultsResponse.class);
                        if(moviesResponse == null){
                            Toast.makeText(MainActivity.this,
                                    "There was an error while retrieving the list of Movies. Please try again later!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            MoviesRecyclerViewAdapter moviesAdapter = new MoviesRecyclerViewAdapter(moviesResponse.getResults(), new MoviesRecyclerViewAdapter.Listener() {
                                @Override
                                public void onItemClick(View view, JsonMovieResponse data) {
                                    goToDetails(data);
                                }
                            });

                            recyclerView.setAdapter(moviesAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof NoConnectionError) {
                            Toast.makeText(MainActivity.this,
                                    "Internet connection could no be established... Please activate your WiFi or Mobile Data",
                                    Toast.LENGTH_SHORT).show();
                        }  else {
                            Toast.makeText(MainActivity.this,
                                    "Sorry, something went wrong. Please try again later!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
        });

        queue.add(stringRequest);

        // Handle Specific Search Request

        SearchView movieSearch = findViewById(R.id.movies_searchview);
        movieSearch.setOnQueryTextListener (new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.isEmpty()) {
                    queue.add(stringRequest);
                }
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                    String url = "https://api.themoviedb.org/3/search/movie?api_key=dc313b5a8e7f05ef725a92427cf6a570&language=en-US&query=" + query;

                    StringRequest searchStringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String searchResponse) {
                                    JsonResultsResponse searchMoviesResponse = new Gson().fromJson(searchResponse, JsonResultsResponse.class);
                                    if (searchMoviesResponse == null) {
                                        Toast.makeText(MainActivity.this,
                                                "There was an error while retrieving the list of Movies. Please try again later!",
                                                Toast.LENGTH_SHORT).show();
                                    } else if (searchMoviesResponse.getResults().isEmpty()) {
                                       Toast.makeText(MainActivity.this,
                                               "Sorry, we found no movies with the selected title!",
                                               Toast.LENGTH_SHORT).show();
                                    } else {
                                        MoviesRecyclerViewAdapter searchMoviesAdapter = new MoviesRecyclerViewAdapter(searchMoviesResponse.getResults(), new MoviesRecyclerViewAdapter.Listener() {
                                            @Override
                                            public void onItemClick(View view, JsonMovieResponse data) {
                                                goToDetails(data);
                                            }
                                        });

                                        recyclerView.setAdapter(searchMoviesAdapter);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error instanceof NoConnectionError) {
                                Toast.makeText(MainActivity.this,
                                        "Internet connection could no be established... Please activate your WiFi or Mobile Data",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this,
                                        "Sorry, something went wrong. Please try again later!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    queue.add(searchStringRequest);

                    return true;
            }
        });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void goToDetails(JsonMovieResponse currentMovie) {

        Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);

        intent.putExtra("id",currentMovie.getId());
        intent.putExtra("title",currentMovie.getTitle());
        intent.putExtra("poster",currentMovie.getPoster_path());
        intent.putExtra("cover", currentMovie.getBackdrop_path());
        intent.putExtra("description",currentMovie.getOverview());
        intent.putExtra("rating",currentMovie.getVote_average());
        intent.putExtra("release_date",currentMovie.getRelease_date());

        startActivity(intent);
    }
}