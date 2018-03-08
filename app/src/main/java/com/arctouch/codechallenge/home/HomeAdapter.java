package com.arctouch.codechallenge.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private @NonNull final List<Movie> movies = new ArrayList<>();
    private final OnItemClickListener mItemClickListener;
    
    HomeAdapter(@NonNull OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
    
    interface OnItemClickListener {
        void onItemClick(Movie item);
    }
    
    static class ViewHolder extends RecyclerView.ViewHolder {
    
        private final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();
        
        private final TextView titleTextView;
        private final TextView genresTextView;
        private final TextView releaseDateTextView;
        private final ImageView posterImageView;
        
        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            genresTextView = itemView.findViewById(R.id.genresTextView);
            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
        }
        
        void bind(Movie movie, OnItemClickListener listener) {
            titleTextView.setText(movie.title);
            genresTextView.setText(TextUtils.join(", ", movie.genres));
            releaseDateTextView.setText(movie.releaseDate);
            
            String posterPath = movie.posterPath;
            if ( ! TextUtils.isEmpty(posterPath) ) {
                Glide.with(itemView)
                        .load(movieImageUrlBuilder.buildPosterUrl(posterPath))
                        .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                        .into(posterImageView);
            }
        
            itemView.setOnClickListener(v -> listener.onItemClick(movie));
        }
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public int getItemCount() {
        return movies.size();
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movies.get(position), mItemClickListener);
    }
    
    void addItems(@NonNull List<Movie> results) {
    
        int previousSize = this.movies.size();
        int amountOfItemsToAdd = results.size();
    
        this.movies.addAll(results);
        notifyItemRangeInserted( previousSize, amountOfItemsToAdd );
    }
}
