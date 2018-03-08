package com.arctouch.codechallenge.home;

import android.support.annotation.NonNull;

import com.arctouch.codechallenge.model.Movie;

import java.util.List;

/**
 * Created by Rogério Baron Júnior.
 */
interface HomeContract {

	interface View {

		void hideCenteredProgressBar();

		void hideBottomProgressBar();

		void addMoviesToList(@NonNull List<Movie> results);
	}

	interface Presenter {

		void loadUpcomingMovies(long page);
	}
}
