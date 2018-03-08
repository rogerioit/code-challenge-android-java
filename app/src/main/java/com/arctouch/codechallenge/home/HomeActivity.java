package com.arctouch.codechallenge.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.view.EndlessRecyclerViewScrollListener;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

	private ProgressBar mCenteredProgressBar;
	private ProgressBar mBottomProgressBar;

    private HomeContract.Presenter presenter;
	private HomeAdapter mAdapter;

	@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        this.presenter = new HomePresenter(this);

		RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
	    this.mCenteredProgressBar = findViewById(R.id.centeredProgressBar);
	    this.mBottomProgressBar = findViewById(R.id.bottomProgressBar);

	    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
	    mRecyclerView.setLayoutManager(linearLayoutManager);

	    mAdapter = new HomeAdapter();

	    mRecyclerView.setAdapter(mAdapter);

	    mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
		    @Override
		    public void onLoadMore(long page, long totalItemsCount, RecyclerView view) {
			    showBottomProgressBar();
			    presenter.loadUpcomingMovies(page);
		    }
	    });

        presenter.loadUpcomingMovies(1L);
    }

	private void showBottomProgressBar() {
		if(mBottomProgressBar.getVisibility() == View.GONE) {
			mBottomProgressBar.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void hideCenteredProgressBar() {
		if(mCenteredProgressBar.getVisibility() == View.VISIBLE) {
			mCenteredProgressBar.setVisibility(View.GONE);
		}
	}

	@Override
	public void hideBottomProgressBar() {
		if(mBottomProgressBar.getVisibility() == View.VISIBLE) {
			mBottomProgressBar.setVisibility(View.GONE);
		}
	}

	@Override
	public void addMoviesToList(@NonNull List<Movie> results) {
		mAdapter.addItems(results);
	}
}
