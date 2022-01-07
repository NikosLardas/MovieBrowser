package com.nikoslardas.movieapp.network.cast;

public class JsonPersonResponse {

    private String name;
    private String character;
    private String profile_path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    @Override
    public String toString() {
        return "JsonPersonResponse{" +
                "name='" + name + '\'' +
                ", character='" + character + '\'' +
                ", profile_path='" + profile_path + '\'' +
                '}';
    }
}