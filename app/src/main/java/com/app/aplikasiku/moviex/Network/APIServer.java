package com.app.aplikasiku.moviex.Network;

import com.app.aplikasiku.moviex.Model.DetailMovieItem;
import com.app.aplikasiku.moviex.Model.DetailTvShowItem;
import com.app.aplikasiku.moviex.Model.MovieItem;
import com.app.aplikasiku.moviex.Model.TvShowItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServer {

    @GET("discover/movie")
    Call<MovieItem> getListMovie(@Query("language") String language);

    @GET("discover/tv")
    Call<TvShowItem> getListTv(@Query("language") String language);

    @GET("movie/{movie_id}")
    Call<DetailMovieItem> getDetailMovie(@Path("movie_id") String movie_id, @Query("language") String language);

    @GET("tv/{tv_id}")
    Call<DetailTvShowItem> getDetailTv(@Path("tv_id") String tv_id, @Query("language") String language);

    @GET("search/movie")
    Call<MovieItem> getSearchMovie(@Query("query") String query, @Query("language") String language);

    @GET("search/tv")
    Call<TvShowItem> getSearchTv(@Query("query") String query, @Query("language") String language);

    @GET("discover/movie")
    Call<MovieItem> getReleasedMovies(@Query("language") String language, @Query("primary_release_date.gte") String date, @Query("primary_release_date.lte") String today);
}
