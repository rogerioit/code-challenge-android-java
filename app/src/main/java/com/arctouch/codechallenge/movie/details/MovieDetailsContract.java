package com.arctouch.codechallenge.movie.details;

import com.arctouch.codechallenge.model.Genre;

import java.util.List;

/**
 * Created by Rogério Baron Júnior.
 */
interface MovieDetailsContract {

	interface View {
		void hideProgressBar();

		void setPosterImage(String uri);

		void setMovieTitle(String title);

		void setMovieReleaseDate(String releaseDate);

		void setMovieGenres(List<Genre> genres);

		void setMovieOverview(String movieOverview);

		void showMovieData();
	}

	interface Presenter {
		void loadMovieData(long movieId);
	}
}
