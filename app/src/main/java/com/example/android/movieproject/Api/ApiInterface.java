package com.example.android.movieproject.Api;

import com.example.android.movieproject.Others.Movie;
import com.example.android.movieproject.Others.TrailerResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by owner on 8/5/2017.
 */

public interface ApiInterface {

    public static final String BASE_IMG = "http://image.tmdb.org/t/p/w185";
    public static final String BACKDROP_IMG = "http://image.tmdb.org/t/p/w185";

    final String DB_KEY_API = "316a57e9c368de897a88e006c074706e";

    @GET("popular?api_key=" + DB_KEY_API)
    Call<Movie> getPopular();

    @GET("top_rated?api_key=" + DB_KEY_API)
    Call<Movie> getTopRated();

    @GET("upcoming?api_key=" + DB_KEY_API)
    Call<Movie> getUpcoming();

    @GET("now_playing?api_key=" + DB_KEY_API)
    Call<Movie> getNowPlaying();

    @GET("{id_movie}/videos?api_key=" + DB_KEY_API)
    Call<TrailerResult> getTrailerMovie(@Path("id_movie")int id);
}
