package com.nikoslardas.movieapp.network.movies;

import java.util.List;

public class JsonResultsResponse {

    private List<JsonMovieResponse> results;

    public List<JsonMovieResponse> getResults() {
        return results;
    }

    public void setResults(List<JsonMovieResponse> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "JsonResultsResponse{" +
                "results=" + results +
                '}';
    }
}