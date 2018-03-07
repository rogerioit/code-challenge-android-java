package com.arctouch.codechallenge.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.arctouch.codechallenge.R;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        this.presenter = new HomePresenter(this);

	    this.recyclerView = findViewById(R.id.recyclerView);
	    this.progressBar = findViewById(R.id.progressBar);

        presenter.loadGenresCache();

        presenter.loadUpcomingMovies();
    }
}
