package com.arctouch.codechallenge.provider;

import com.arctouch.codechallenge.api.TmdbApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Rogério Baron Júnior.
 */
public class RetrofitFactory {

	private RetrofitFactory() {
		//preventing to create useless instances
	}

	public static TmdbApi provideTmdbApi() {
		return new Retrofit.Builder()
				.baseUrl(TmdbApi.URL)
				.client(new OkHttpClient.Builder().build())
				.addConverterFactory(MoshiConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
				.create(TmdbApi.class);
	}
}
