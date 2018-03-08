package com.arctouch.codechallenge.movie.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    private static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";

	private ViewGroup mParentLayout;
	private ProgressBar mProgressBar;
	private ImageView mImageViewPoster;
	private TextView mTextViewMovieTitle;
	private TextView mTextViewReleaseDate;
	private TextView mTextViewGenres;
	private TextView mTextViewOverview;

    private MovieDetailsContract.Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_movie_details);
	
	    mParentLayout = findViewById(R.id.dataContainerScrollView);
	    mProgressBar = findViewById(R.id.centeredProgressBar);
		mImageViewPoster = findViewById(R.id.posterImageView);
		mTextViewMovieTitle = findViewById(R.id.textViewMovieTitle);
		mTextViewReleaseDate = findViewById(R.id.releaseDateTextView);
		mTextViewGenres = findViewById(R.id.genresTextView);
		mTextViewOverview = findViewById(R.id.textViewOverview);
	
	    presenter = new MovieDetailsPresenter(this);
	
	    Bundle extras = getIntent().getExtras();
	    if(extras == null) {
	        throw new IllegalArgumentException("Missing extras with the movie data to be loaded");
	    }
	
	    long movieId = extras.getLong(EXTRA_MOVIE_ID);
	
	    presenter.loadMovieData( movieId );
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return super.onRetainCustomNonConfigurationInstance();
	}
	
	public static Bundle generateExtras(Movie movie) {
	
	    Bundle extras = new Bundle();
	    extras.putLong(EXTRA_MOVIE_ID, movie.id);
	
	    return extras;
	}
	
	@Override
	public void hideProgressBar() {
		if(mProgressBar.getVisibility() == View.VISIBLE) {
			mProgressBar.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void setPosterImage(String uri) {
		Glide.with(this)
				.load(uri)
				.apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
				.into(mImageViewPoster);
	}
	
	@Override
	public void setMovieTitle(String title) {
		mTextViewMovieTitle.setText(title);
	}
	
	@Override
	public void setMovieReleaseDate(String releaseDate) {
		mTextViewReleaseDate.setText( String.format(getString(R.string.release_date), releaseDate ) );
	}
	
	@Override
	public void setMovieGenres(List<Genre> genres) {
		int sizeListGenres = genres.size();
	
		if(sizeListGenres > 0) {
	
			StringBuilder stringBuilder = new StringBuilder();
	
			for (int index = 0; index < sizeListGenres; index++) {
				stringBuilder.append("\t- ").append(genres.get(index).name);
	
				if( (index < sizeListGenres - 1) ) {
					stringBuilder.append("\n");
				}
			}
	
			mTextViewGenres.setText( String.format(getString(R.string.genres), stringBuilder.toString() ) );
		}
	}
	
	@Override
	public void setMovieOverview(String movieOverview) {
		mTextViewOverview.setText(String.format(getString(R.string.overview), movieOverview ) );
	}
	
	@Override
	public void showMovieData() {
		if(mParentLayout.getVisibility() == View.GONE) {
			mParentLayout.setVisibility(View.VISIBLE);
		}
	}
}
