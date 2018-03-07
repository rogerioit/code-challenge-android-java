package com.arctouch.codechallenge.home;

/**
 * Created by Rogério Baron Júnior.
 */
interface HomeContract {

	interface View {

	}

	interface Presenter {

		void loadGenresCache();

		void loadUpcomingMovies();
	}
}
