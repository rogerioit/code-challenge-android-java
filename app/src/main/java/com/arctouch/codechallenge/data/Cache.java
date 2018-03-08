package com.arctouch.codechallenge.data;

import com.arctouch.codechallenge.model.Genre;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class Cache {

    private static @NonNull List<Genre> genres = new ArrayList<>();
    
    public static @NonNull List<Genre> getGenres() {
        return genres;
    }
    
    public static void setGenres(@NonNull List<Genre> genres) {
        Cache.genres.clear();
        Cache.genres.addAll(genres);
    }
}
