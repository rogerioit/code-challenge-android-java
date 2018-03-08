package com.arctouch.codechallenge.movie.details;

import android.support.annotation.NonNull;

import com.arctouch.codechallenge.api.TmdbApi;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.provider.RetrofitFactory;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rogério Baron Júnior.
 */
class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

	private final MovieDetailsContract.View mView;
	private final TmdbApi api;
	private final MovieImageUrlBuilder movieImageUriBuilder;
	
	MovieDetailsPresenter(MovieDetailsContract.View mView) {
		this.mView = mView;
		this.api = RetrofitFactory.provideTmdbApi();
		this.movieImageUriBuilder = new MovieImageUrlBuilder();
	}
	
	@Override
	public void loadMovieData(long movieId) {
	
		api.movie(movieId, TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(movie -> {
	
					mView.hideProgressBar();
	
					setMovieData(movie);
				});
	}
	
	private void setMovieData(@NonNull Movie movie) {
	
		mView.setPosterImage( movieImageUriBuilder.buildPosterUrl( movie.posterPath ) );
		mView.setMovieTitle( movie.title );
		mView.setMovieReleaseDate( movie.releaseDate );
		mView.setMovieGenres( movie.genres );
		mView.setMovieOverview( movie.overview );
	
		mView.showMovieData();
	}
}