package com.nikoslardas.movieapp.network.cast;

import java.util.List;

public class JsonCastResponse {

    private List<JsonPersonResponse> cast;

    public List<JsonPersonResponse> getCast() {
        return cast;
    }

    public void setCast(List<JsonPersonResponse> cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "JsonCastResponse{" +
                "cast=" + cast +
                '}';
    }
}