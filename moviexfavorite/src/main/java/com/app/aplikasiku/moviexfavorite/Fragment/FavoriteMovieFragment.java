package com.app.aplikasiku.moviexfavorite.Fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.aplikasiku.moviexfavorite.Adapter.rcFavoriteMovie;
import com.app.aplikasiku.moviexfavorite.R;
import com.app.aplikasiku.moviexfavorite.FavoriteContract;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteMovieFragment extends Fragment {

    private rcFavoriteMovie rcFavoriteMovie;
    private Cursor favorite;
    ProgressBar progressBar;
    RecyclerView listmovie;
    public final String kat = "Movie";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);

        listmovie = view.findViewById(R.id.rc_film);
        progressBar = view.findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);

        //tampil data list favorite film
        loadView();
        new loadFavoriteAsync().execute();
        return view;
    }

    private void loadView() {
        rcFavoriteMovie = new rcFavoriteMovie(favorite, getContext());
        listmovie.setLayoutManager(new LinearLayoutManager(getContext()));
        listmovie.setAdapter(rcFavoriteMovie);
    }

    private class loadFavoriteAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getActivity().getContentResolver().query(FavoriteContract.CONTENT_URI,
                    null,
                    kat,
                    null,
                    null);
        }

        @Override
        protected void onPostExecute(Cursor items) {
            super.onPostExecute(items);
            progressBar.setVisibility(View.GONE);

            favorite = items;
            rcFavoriteMovie.setData(favorite);

            if (favorite.getCount() == 0) {
                showSnackbarMessage(getString(R.string.no_data));
            }

        }
    }
    private void showSnackbarMessage(String message) {
        Snackbar.make(listmovie, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadFavoriteAsync().execute();
    }
}
