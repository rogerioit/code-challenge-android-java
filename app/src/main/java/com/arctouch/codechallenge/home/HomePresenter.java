package com.arctouch.codechallenge.home;

import android.view.View;

import com.arctouch.codechallenge.api.TmdbApi;
import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.provider.RetrofitFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rogério Baron Júnior.
 */
class HomePresenter implements HomeContract.Presenter {

	private final HomeContract.View mView;

	private final TmdbApi api;

	HomePresenter(HomeContract.View mView) {
		this.mView = mView;

		this.api = RetrofitFactory.provideTmdbApi();
	}

	@Override
	public void loadGenresCache() {

		// if it is loaded, no need to request again
		if( Cache.getGenres().isEmpty() ) {

			api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe( response -> Cache.setGenres(response.genres) );

		}
	}

	@Override
	public void loadUpcomingMovies() {
		api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1L, TmdbApi.DEFAULT_REGION)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(response -> {
					for (Movie movie : response.results) {
						movie.genres = new ArrayList<>();
						for (Genre genre : Cache.getGenres()) {
							if (movie.genreIds.contains(genre.id)) {
								movie.genres.add(genre);
							}
						}
					}

					//todo call mView to update data and hide the progressbar!
					//recyclerView.setAdapter(new HomeAdapter(response.results));
					//progressBar.setVisibility(View.GONE);
				});
	}
}
