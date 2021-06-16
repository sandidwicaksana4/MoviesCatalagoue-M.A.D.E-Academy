package com.app.aplikasiku.moviex;

import android.util.Log;

import com.app.aplikasiku.moviex.Activity.DetailMovieActivity;
import com.app.aplikasiku.moviex.Activity.DetailTvShowActivity;
import com.app.aplikasiku.moviex.Activity.MainActivity;
import com.app.aplikasiku.moviex.Adapter.Language;
import com.app.aplikasiku.moviex.Model.DetailMovieItem;
import com.app.aplikasiku.moviex.Model.DetailTvShowItem;
import com.app.aplikasiku.moviex.Model.MovieItem;
import com.app.aplikasiku.moviex.Model.TvShowItem;
import com.app.aplikasiku.moviex.Network.APIItem;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MovieItem>> listMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShowItem>> listTvShow = new MutableLiveData<>();
    private MutableLiveData<DetailMovieItem> detailMovies = new MutableLiveData<>();
    private MutableLiveData<DetailTvShowItem> detailTvShows = new MutableLiveData<>();
    private APIItem apiItem = new APIItem();

    public void setListMovies (){
        Call<MovieItem> apiCall = apiItem.getService().getListMovie(Language.getCountry());
        apiCall.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
               try{
                   ArrayList<MovieItem> movies = response.body().getResults();
                   listMovies.postValue(movies);
               } catch (Exception e) {
                   Log.d(MainActivity.class.getSimpleName(),e.getLocalizedMessage());
               }
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {
                Log.d(MainActivity.class.getSimpleName(),t.getLocalizedMessage());
          }
        });
    }

    public void searchMovies(String query){
        Call<MovieItem> apiCall = apiItem.getService().getSearchMovie(query,Language.getCountry());
        apiCall.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                try {
                    ArrayList<MovieItem> movies = response.body().getResults();
                    listMovies.postValue(movies);
                } catch (Exception e) {
                    Log.d(MainActivity.class.getSimpleName(),e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {
                Log.d(MainActivity.class.getSimpleName(),t.getLocalizedMessage());
            }
        });
    }

    public void setDetailMovies(String movie_id){
        Call<DetailMovieItem> apiCall = apiItem.getService().getDetailMovie(movie_id,Language.getCountry());
        apiCall.enqueue(new Callback<DetailMovieItem>() {
            @Override
            public void onResponse(Call<DetailMovieItem> call, Response<DetailMovieItem> response) {
                try {
                    DetailMovieItem item = response.body();
                    detailMovies.postValue(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DetailMovieItem> call, Throwable t) {
                Log.d(DetailMovieActivity.class.getSimpleName(),t.getLocalizedMessage());
            }
        });
    }

    public LiveData<ArrayList<MovieItem>> getMovies(){ return listMovies;}
    public LiveData<DetailMovieItem> getDetailMovies(){ return detailMovies;}



    public void setListTvShow (){
        Call<TvShowItem> apiCall = apiItem.getService().getListTv(Language.getCountry());
        apiCall.enqueue(new Callback<TvShowItem>() {
            @Override
            public void onResponse(Call<TvShowItem> call, Response<TvShowItem> response) {
                try {
                    ArrayList<TvShowItem> tvshow = response.body().getResults();
                    listTvShow.postValue(tvshow);
                } catch (Exception e){
                    Log.d(MainActivity.class.getSimpleName(),e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<TvShowItem> call, Throwable t) {
                Log.d(MainActivity.class.getSimpleName(),t.getLocalizedMessage());
            }
        });
    }

    public void searchTvShow (String query){
        Call<TvShowItem> apiCall = apiItem.getService().getSearchTv(query,Language.getCountry());
        apiCall.enqueue(new Callback<TvShowItem>() {
            @Override
            public void onResponse(Call<TvShowItem> call, Response<TvShowItem> response) {
                try {
                    ArrayList<TvShowItem> tvshow = response.body().getResults();
                    listTvShow.postValue(tvshow);
                } catch (Exception e){
                    Log.d(MainActivity.class.getSimpleName(),e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<TvShowItem> call, Throwable t) {
                Log.d(MainActivity.class.getSimpleName(),t.getLocalizedMessage());
            }
        });
    }

    public void setDetailTvShow(String tv_id){
        Call<DetailTvShowItem> apiCall = apiItem.getService().getDetailTv(tv_id,Language.getCountry());
        apiCall.enqueue(new Callback<DetailTvShowItem>() {
            @Override
            public void onResponse(Call<DetailTvShowItem> call, Response<DetailTvShowItem> response) {
                try {
                    DetailTvShowItem item = response.body();
                    detailTvShows.postValue(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DetailTvShowItem> call, Throwable t) {
                Log.d(DetailTvShowActivity.class.getSimpleName(),t.getLocalizedMessage());
            }
        });
    }

    public LiveData<ArrayList<TvShowItem>> getTvShow(){ return listTvShow;}
    public LiveData<DetailTvShowItem> getDetailTvshow(){ return detailTvShows;}
}
