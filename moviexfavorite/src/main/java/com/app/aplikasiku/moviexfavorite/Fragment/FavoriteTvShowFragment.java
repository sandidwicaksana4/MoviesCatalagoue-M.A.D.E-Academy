package com.app.aplikasiku.moviexfavorite.Fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.aplikasiku.moviexfavorite.Adapter.rcFavoriteTvShow;
import com.app.aplikasiku.moviexfavorite.R;
import com.app.aplikasiku.moviexfavorite.FavoriteContract;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteTvShowFragment extends Fragment {

    private rcFavoriteTvShow rcFavoriteTvShow;
    private Cursor favorite;
    ProgressBar progressBar;
    RecyclerView listtv;
    public final String kat = "Tv Show";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);

        listtv = view.findViewById(R.id.rc_tv);
        progressBar = view.findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);

        //tampil data list favorite film
        loadView();
        new loadFavoriteAsync().execute();
        return view;
    }

    private void loadView() {
        rcFavoriteTvShow = new rcFavoriteTvShow(favorite, getContext());
        rcFavoriteTvShow.setData(favorite);
        listtv.setLayoutManager(new LinearLayoutManager(getContext()));
        listtv.setAdapter(rcFavoriteTvShow);
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
            rcFavoriteTvShow.setData(favorite);

            if (favorite.getCount() == 0) {
                showSnackbarMessage(getString(R.string.no_data));
            }

        }
    }
    private void showSnackbarMessage(String message) {
        Snackbar.make(listtv, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadFavoriteAsync().execute();
    }
}
