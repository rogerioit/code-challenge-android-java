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

	private static TmdbApi tmdbApi;
	
	private RetrofitFactory() {
		//preventing the creation of useless instances
	}
	
	public static TmdbApi provideTmdbApi() {
	
		if( tmdbApi == null ) {
			tmdbApi = new Retrofit.Builder()
					.baseUrl(TmdbApi.URL)
					.client(new OkHttpClient.Builder().build())
					.addConverterFactory(MoshiConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build()
					.create(TmdbApi.class);
		}
	
		return tmdbApi;
	}
}
